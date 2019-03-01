package services;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.NetworkIOException;
import com.dropbox.core.RetryException;
import com.dropbox.core.util.IOUtil;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CommitInfo;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadSessionCursor;
import com.dropbox.core.v2.files.UploadSessionFinishErrorException;
import com.dropbox.core.v2.files.UploadSessionLookupErrorException;
import com.dropbox.core.v2.files.WriteMode;
import dataaccess.AssetBroker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author Mason
 */
public class FileService {

    private static final String ACCESS_TOKEN = "tNpg99t4i3AAAAAAAAAAHS6CG-fDCK_LMhZac-hPwdpYJvpL3ljHUypZUX_7PGKu";
    private final DbxRequestConfig config;
    private final DbxClientV2 client;
    private final long CHUNKED_UPLOAD_CHUNK_SIZE = 8L << 20; // 8 MiB
    private final int CHUNKED_UPLOAD_MAX_ATTEMPTS = 5;

    /**
     * Fix this to be dynamic with title name.
     */
    private final AssetBroker ab;

    public FileService() {
        ab = new AssetBroker();
        config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    public void handleUpload(List<FileItem> multiparts, String titleName) {
        try {
            for (FileItem item : multiparts) {
                if (!item.isFormField()) {
                    String uploadName = item.getName();
                    long size = item.getSize();
                    InputStream in = item.getInputStream();

                    if (size < CHUNKED_UPLOAD_CHUNK_SIZE) {
                        uploadSmall(uploadName, titleName, in);
                    } else {
                        uploadLarge(uploadName, titleName, in, size);
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean uploadSmall(String uploadName, String titleName, InputStream in) throws DbxException, FileNotFoundException, IOException {
        FileMetadata metadata = client.files().uploadBuilder("/Title/" + titleName + "asset/" + uploadName)
                .uploadAndFinish(in);
        return metadata.getSize() != 0;
    }

    public boolean uploadLarge(String uploadName, String titleName, InputStream in, final long size) throws DbxException, FileNotFoundException, IOException {
        long uploaded = 0L;
        DbxException thrown = null;

        IOUtil.ProgressListener progressListener = new IOUtil.ProgressListener() {
            long uploadedBytes = 0;

            @Override
            public void onProgress(long l) {
                printProgress(l + uploadedBytes, size);
                if (l == CHUNKED_UPLOAD_CHUNK_SIZE) {
                    uploadedBytes += CHUNKED_UPLOAD_CHUNK_SIZE;
                }
            }
        };

        // Chunked uploads have 3 phases, each of which can accept uploaded bytes:
        //
        //    (1)  Start: initiate the upload and get an upload session ID
        //    (2) Append: upload chunks of the file to append to our session
        //    (3) Finish: commit the upload and close the session
        //
        // We track how many bytes we uploaded to determine which phase we should be in.
        String sessionId = null;
        for (int i = 0; i < CHUNKED_UPLOAD_MAX_ATTEMPTS; ++i) {
            if (i > 0) {
                System.out.printf("Retrying chunked upload (%d / %d attempts)\n", i + 1, CHUNKED_UPLOAD_MAX_ATTEMPTS);
            }
            try {
                in.skip(uploaded);

                // (1) Start
                if (sessionId == null) {
                    sessionId = client.files().uploadSessionStart()
                            .uploadAndFinish(in, CHUNKED_UPLOAD_CHUNK_SIZE, progressListener)
                            .getSessionId();
                    uploaded += CHUNKED_UPLOAD_CHUNK_SIZE;
                    printProgress(uploaded, size);
                }

                UploadSessionCursor cursor = new UploadSessionCursor(sessionId, uploaded);

                // (2) Append
                while ((size - uploaded) > CHUNKED_UPLOAD_CHUNK_SIZE) {
                    client.files().uploadSessionAppendV2(cursor)
                            .uploadAndFinish(in, CHUNKED_UPLOAD_CHUNK_SIZE, progressListener);
                    uploaded += CHUNKED_UPLOAD_CHUNK_SIZE;
                    printProgress(uploaded, size);
                    cursor = new UploadSessionCursor(sessionId, uploaded);
                }

                // (3) Finish
                long remaining = size - uploaded;
                CommitInfo commitInfo = CommitInfo.newBuilder("/EP01-CAP.54.tif")
                        .withMode(WriteMode.ADD)
                        .withClientModified(new Date())
                        .build();
                FileMetadata metadata = client.files().uploadSessionFinish(cursor, commitInfo)
                        .uploadAndFinish(in, remaining, progressListener);

                System.out.println(metadata.toStringMultiline());
                return true;
            } catch (RetryException ex) {
                thrown = ex;
                // RetryExceptions are never automatically retried by the client for uploads. Must
                // catch this exception even if DbxRequestConfig.getMaxRetries() > 0.
                sleepQuietly(ex.getBackoffMillis());
                continue;

            } catch (NetworkIOException ex) {
                thrown = ex;
                // network issue with Dropbox (maybe a timeout?) try again
                continue;
            } catch (UploadSessionLookupErrorException ex) {
                if (ex.errorValue.isIncorrectOffset()) {
                    thrown = ex;
                    // server offset into the stream doesn't match our offset (uploaded). Seek to
                    // the expected offset according to the server and try again.
                    uploaded = ex.errorValue
                            .getIncorrectOffsetValue()
                            .getCorrectOffset();
                    continue;
                } else {
                    // Some other error occurred, give up.
                    System.err.println("Error uploading to Dropbox: " + ex.getMessage());
                    System.exit(1);
                    return false;
                }
            } catch (UploadSessionFinishErrorException ex) {
                if (ex.errorValue.isLookupFailed() && ex.errorValue.getLookupFailedValue().isIncorrectOffset()) {
                    thrown = ex;
                    // server offset into the stream doesn't match our offset (uploaded). Seek to
                    // the expected offset according to the server and try again.
                    uploaded = ex.errorValue
                            .getLookupFailedValue()
                            .getIncorrectOffsetValue()
                            .getCorrectOffset();
                    continue;
                } else {
                    // some other error occurred, give up.
                    System.err.println("Error uploading to Dropbox: " + ex.getMessage());
                    System.exit(1);
                    return false;
                }
            } catch (DbxException ex) {
                System.err.println("Error uploading to Dropbox: " + ex.getMessage());
                System.exit(1);
                return false;
            } catch (IOException ex) {
                System.err.println("Error reading from file \"" + uploadName + "\": " + ex.getMessage());
                System.exit(1);
                return false;
            }
        }

        // if we made it here, then we must have run out of attempts
        System.err.println("Maxed out upload attempts to Dropbox. Most recent error: " + thrown.getMessage());
        System.exit(1);
        return false;
    }

    private static void printProgress(long uploaded, long size) {
        System.out.printf("Uploaded %12d / %12d bytes (%5.2f%%)\n", uploaded, size, 100 * (uploaded / (double) size));
    }

    private static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            // just exit
            System.err.println("Error uploading to Dropbox: interrupted during backoff.");
            System.exit(1);
        }
    }

    public ArrayList<String> getAssets(String titleName) throws IOException {
        ArrayList<String> assetsClean = new ArrayList<String>();
        ArrayList<String> assetsUnclean = ab.getAssetByTitle(titleName);

        for (int i = 0; i < assetsUnclean.size(); i++) {
            String asset = assetsUnclean.get(i);
            String fileName = asset.substring(asset.lastIndexOf("\\") + 1);
            assetsClean.add(fileName);
        }

        return assetsClean;
    }

    public void deleteAsset(String titleName, String name) {
        File file = new File("C:\\Users\\697467\\Desktop\\Netbeans\\sync0210DEMO\\web\\css\\images\\Title\\" + titleName + "\\asset" + File.separator + name);
        file.delete();
    }
}
