package services;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.NetworkIOException;
import com.dropbox.core.RetryException;
import com.dropbox.core.util.IOUtil;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CommitInfo;
import com.dropbox.core.v2.files.CreateFolderBatchLaunch;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.DownloadZipResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadSessionCursor;
import com.dropbox.core.v2.files.UploadSessionFinishErrorException;
import com.dropbox.core.v2.files.UploadSessionLookupErrorException;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.ListSharedLinksResult;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import dataaccess.ArtworkBroker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Artwork;
import org.apache.commons.fileupload.FileItem;

/**
 * FileService is a class to connect to Dropbox and download, upload, retrieve images from the
 * Dropbox.
 * @author Alvin Labarinto
 */
public class FileService {

    private static final String ACCESS_TOKEN = "tNpg99t4i3AAAAAAAAAAOqycycl78X9LN64VtVGM_7JywiZNaBCIUL0KXpgGr83U";
    private DbxRequestConfig config;
    private DbxClientV2 client;
    private final long CHUNKED_UPLOAD_CHUNK_SIZE = 8L << 20; // 8 MiB
    private final int CHUNKED_UPLOAD_MAX_ATTEMPTS = 5;
    private ArtworkBroker ab;

    public FileService() {
        ab = new ArtworkBroker();
        config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    /**
     * Handles the upload of assets and artworks.
     * 
     * @param multiparts list of FileItems submitted from the from.
     * @param titleName the name of the accessed title.
     * @param inputType whether an asset or an artwork.
     * @return true if uploaded successful. otherwise, return false.
     */
    public String handleUpload(List<FileItem> multiparts, String titleName, String inputType) {
        try {
            for (FileItem item : multiparts) {
                if (!item.isFormField()) {
                    String uploadName = item.getName();
                    long size = item.getSize();
                    InputStream in = item.getInputStream();

                    boolean check;
                    if (size < CHUNKED_UPLOAD_CHUNK_SIZE) {
                        check = uploadSmall(uploadName, titleName, in, inputType);
                    } else {
                        check = uploadLarge(uploadName, titleName, in, size, inputType);
                    }
                    
                    if(check) {
                        String refName = "/Title/" + titleName + "/" + inputType + "/" + uploadName;
                        Artwork artwork = new Artwork(null, refName, refName, 75, (short) 1, 1);
                        int rows = ab.insertArtwork(artwork);
                        
                        if(rows != 0)
                            return "File uploaded successfully!";
                        else
                            return null;
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Uploads files smaller than 8MB.
     * 
     * @param uploadName name of the uploaded file.
     * @param titleName the name of the accessed title.
     * @param in the File to be input.
     * @param inputType whether an asset or an artwork.
     * @return true if uploaded successful. otherwise, return false.
     * @throws DbxException if Dropbox connection does not allow for the upload.
     * @throws FileNotFoundException if the File being sent to this method is not found.
     * @throws IOException if the File being sent to this method is not Found.
     */
    public boolean uploadSmall(String uploadName, String titleName, InputStream in, String inputType) throws DbxException, FileNotFoundException, IOException {
        FileMetadata metadata = client.files().uploadBuilder("/Title/" + titleName + "/" + inputType + "/" + uploadName)
                .uploadAndFinish(in);
        
        //creates the shared link for access to image retrieval.
        if (metadata.getSize() != 0) {
            createSharedLink(uploadName, titleName, inputType);
        }
        
        return metadata.getSize() != 0;
    }

    /**
     * Uploads files larger than 8MB.
     * 
     * @param uploadName name of the uploaded file.
     * @param titleName the name of the accessed title.
     * @param in the File to be input.
     * @param size the size of the file.
     * @param inputType whether an asset or an artwork.
     * @return true if uploaded successful. otherwise, return false.
     * @throws DbxException if Dropbox connection does not allow for the upload.
     * @throws FileNotFoundException if the File being sent to this method is not found.
     * @throws IOException  if the File being sent to this method is not Found.
     */
    public boolean uploadLarge(String uploadName, String titleName, InputStream in, final long size, String inputType) throws DbxException, FileNotFoundException, IOException {
        long uploaded = 0L;
        DbxException thrown = null;

        /* 
        *   This progress listener can be fully implemented to show an actual progress bar.
        *   Check the printProgress method to see the functionality. 
        */
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
                CommitInfo commitInfo = CommitInfo.newBuilder("/Title/" + titleName + "/asset/" + uploadName)
                        .withMode(WriteMode.ADD)
                        .withClientModified(new Date())
                        .build();
                FileMetadata metadata = client.files().uploadSessionFinish(cursor, commitInfo)
                        .uploadAndFinish(in, remaining, progressListener);

                if (metadata.getSize() != 0) {
                    createSharedLink(uploadName, titleName, inputType);
                }
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

    /**
     * Gets all assets associated with the title being accessed.
     * 
     * @param titleName the name of the accessed title.
     * @return a list of shared link URLs of all assets associated with the title.
     * @throws DbxException if there are troubles accessing Dropbox.
     */
    public ArrayList<String> getAssets(String titleName) throws DbxException {
        ArrayList<String> urls = new ArrayList<>();
        ListSharedLinksResult result = client.sharing().listSharedLinks();
        List<SharedLinkMetadata> links = result.getLinks();

        for (SharedLinkMetadata slm : links) {
            if (!slm.getPathLower().contains(".jpg") && !slm.getPathLower().contains(".png")) {
                continue;
            }
            if (slm.getPathLower().contains(titleName.toLowerCase()) && slm.getPathLower().contains("asset")) {
                String url = slm.getUrl();
                url = url.substring(0, url.lastIndexOf("?") + 1) + "raw=1";
                urls.add(url);
            }
        }

        return urls;
    }

    /**
     * Gets all artworks associated with the title being accessed.
     * 
     * @param titleName the name of the accessed title.
     * @return a list of shared link URLs of all artworks associated with the title.
     * @throws DbxException if there are troubles accessing Dropbox.
     */
    public ArrayList<String> getArtworks(String titleName) throws DbxException {
        ArrayList<String> urls = new ArrayList<>();
        ListSharedLinksResult result = client.sharing().listSharedLinks();
        List<SharedLinkMetadata> links = result.getLinks();

        for (SharedLinkMetadata slm : links) {
            if (!slm.getPathLower().contains(".jpg")  && !slm.getPathLower().contains(".png"))  {
                continue;
            }
            if (slm.getPathLower().contains(titleName.toLowerCase()) && slm.getPathLower().contains("artwork")) {
                String url = slm.getUrl();
                url = url.substring(0, url.lastIndexOf("?") + 1) + "raw=1";
                urls.add(url);
            }
        }

        return urls;
    }

    /**
     * Deletes an asset associated with a title.
     * 
     * @param titleName the name of the title in which the asset will be deleted.
     * @param url the shared link of the asset to be deleted.
     * @return true if the deletion was successful. otherwise, return false.
     * @throws DbxException if there are troubles accessing Dropbox.
     */
    public boolean deleteAsset(String titleName, String url) throws DbxException {
        url = url.substring(url.lastIndexOf("/") + 1, url.length());
        url = url.substring(0, url.lastIndexOf("?"));

        DeleteResult deleteV2 = client.files().deleteV2("/Title/" + titleName + "/asset/" + url);
        Metadata metadata = deleteV2.getMetadata();
        return metadata.getPathLower() != null;
    }

    /**
     * Deletes an artwork associated with a title.
     * 
     * @param titleName the name of the title in which the artwork will be deleted.
     * @param url the shared link of the artwork to be deleted.
     * @return true if the deletion was successful. otherwise, return false.
     * @throws DbxException  if there are troubles accessing Dropbox.
     */
    public boolean deleteArtwork(String titleName, String url) throws DbxException {
        url = url.substring(url.lastIndexOf("/") + 1, url.length());
        url = url.substring(0, url.lastIndexOf("?"));

        DeleteResult deleteV2 = client.files().deleteV2("/Title/" + titleName + "/artwork/" + url);
        Metadata metadata = deleteV2.getMetadata();
        return metadata.getPathLower() != null;
    }

    private void createSharedLink(String uploadName, String titleName, String inputType) throws DbxException {
        SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings("/Title/" + titleName + "/" + inputType + "/" + uploadName);
        sharedLinkMetadata.getUrl();
    }
    
    /**
     * Upon creation of a title, creates folders for the title's artworks and assets.
     * 
     * @param titleName the name of the title that has been created.
     * @return true if the title's folders have been created successfully. otherwise, return false.
     * @throws DbxException if there are troubles accessing Dropbox.
     */
    public boolean createTitleFolders(String titleName) throws DbxException {
        List<String> paths = new ArrayList<>();
        paths.add("/Title/" + titleName + "/asset");
        paths.add("/Title/" + titleName + "/artwork");
        CreateFolderBatchLaunch folders = client.files().createFolderBatchBuilder(paths).start();
        return folders.isComplete();
    }
    
    /**
     * Uploads the SQL backup to Dropbox.
     * 
     * @param uploadName the name of the file to be uploaded.
     * @param in FileInputStream of the file to be sent to Dropbox.
     * @return true if successful. otherwise, return false.
     * @throws DbxException if Dropbox connection does not allow for the upload.
     * @throws FileNotFoundException if the File being sent to this method is not found.
     * @throws IOException if the File being sent to this method is not Found.
     */
    public boolean uploadBackup(String uploadName, InputStream in) throws DbxException, FileNotFoundException, IOException {
        FileMetadata metadata = client.files().uploadBuilder("/Backups/" + uploadName)
                .uploadAndFinish(in);
        
        //creates the shared link for access to image retrieval.
        if (metadata.getSize() != 0) {
            createShareableBackup(uploadName);
        }
        
        return metadata.getSize() != 0;
    }
    
    private void createShareableBackup(String uploadName) throws DbxException {
        SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings("/Backups/" + uploadName);
        sharedLinkMetadata.getUrl();
    }
    
    
    public String downloadAllAssets(String titleName) throws FileNotFoundException, DbxException, IOException {
        DbxDownloader<DownloadZipResult> downloader = client.files().downloadZip("/Title/" + titleName + "/asset");
         try {
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + titleName + "-assets.zip");
            FileOutputStream out = new FileOutputStream(file);
            downloader.download(out);
            out.close();
        } catch (DbxException ex) {
            System.out.println(ex.getMessage());
        }
         
         return "Downloaded file. Please check " + System.getProperty("user.home") + "/Downloads/" + titleName + "-assets.zip";
    }
}
