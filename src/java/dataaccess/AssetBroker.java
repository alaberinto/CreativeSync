package dataaccess;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import models.Asset;

/**
 *
 * @author Alvin
 */
public class AssetBroker {

    public Asset getAsset(int assetId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return null;
        } catch (Exception ex) {
            Logger.getLogger(AssetBroker.class.getName()).log(Level.SEVERE, "Cannot read asset", ex);
            throw new DBException("Error getting asset.");
        }
    }

    public ArrayList<String> getAssetByTitle(String titleName) {

        File folder = new File("C:\\Users\\Mason\\Documents\\NetBeansProjects\\sync0210\\web\\css\\images\\Title\\" + titleName + "\\asset");
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> fileNames = new ArrayList<String>();
        String fileName = null;

        
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                fileName = listOfFile.toString();
                fileNames.add(fileName);
            }
        }
        return fileNames;
    }

    public Asset updateAsset(int assetId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return null;
        } catch (Exception ex) {
            Logger.getLogger(AssetBroker.class.getName()).log(Level.SEVERE, "Cannot update user", ex);
            throw new DBException("Error updating asset.");
        }
    }

    public Asset addAsset(int assetId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return null;
        } catch (Exception ex) {
            Logger.getLogger(AssetBroker.class.getName()).log(Level.SEVERE, "Cannot add asset", ex);
            throw new DBException("Error adding asset.");
        }
    }

    public Asset deleteAsset(int assetId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return null;
        } catch (Exception ex) {
            Logger.getLogger(AssetBroker.class.getName()).log(Level.SEVERE, "Cannot delete asset", ex);
            throw new DBException("Error deleting asset.");
        }
    }
}
