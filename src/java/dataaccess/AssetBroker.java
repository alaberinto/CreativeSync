//package dataaccess;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.persistence.EntityManager;
//import models.Asset;
//
///**
// *
// * @author Alvin
// */
//public class AssetBroker {
//    public Asset getAsset(int assetId) throws DBException {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        try {
//            return null;
//        } catch (Exception ex) {
//            Logger.getLogger(AssetBroker.class.getName()).log(Level.SEVERE, "Cannot read asset", ex);
//            throw new DBException("Error getting asset.");
//        }
//    }
//    
//    public Asset updateAsset(int assetId) throws DBException {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        try {
//            return null;
//        } catch (Exception ex) {
//            Logger.getLogger(AssetBroker.class.getName()).log(Level.SEVERE, "Cannot update user", ex);
//            throw new DBException("Error updating asset.");
//        }
//    }
//    
//    public Asset addAsset(int assetId) throws DBException {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        try {
//            return null;
//        } catch (Exception ex) {
//            Logger.getLogger(AssetBroker.class.getName()).log(Level.SEVERE, "Cannot add asset", ex);
//            throw new DBException("Error adding asset.");
//        }
//    }
//    
//    public Asset deleteAsset(int assetId) throws DBException {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        try {
//            return null;
//        } catch (Exception ex) {
//            Logger.getLogger(AssetBroker.class.getName()).log(Level.SEVERE, "Cannot delete asset", ex);
//            throw new DBException("Error deleting asset.");
//        }
//    }
//}
