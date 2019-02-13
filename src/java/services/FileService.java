/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AssetBroker;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author Mason
 */
public class FileService {

    /**
     * Fix this to be dynamic with title name.
     */
    private final AssetBroker ab;

    public FileService() {
        ab = new AssetBroker();
    }

    public void handleUpload(List<FileItem> multiparts, String titleName) {
        try {
            for (FileItem item : multiparts) {
                if (!item.isFormField()) {
                    String name = new File(item.getName()).getName();
                    item.write(new File("C:\\Users\\Mason\\Documents\\NetBeansProjects\\sync0210\\web\\css\\images\\Title\\" + titleName + "\\asset" + File.separator + name));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getAssets(String titleName) {
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
        File file = new File("C:\\Users\\Mason\\Documents\\NetBeansProjects\\sync0210\\web\\css\\images\\Title\\" + titleName + "\\asset" + File.separator + name);
        file.delete();
    }
}
