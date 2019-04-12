package dataaccess;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import models.RecoveryUser;

/**
 * RecoveryBroker is a data-access class to retrieve AccountRecovery information from the database.
 * 
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 * @version 1.0
 */
public class RecoveryBroker {

    /**
     * Access method to retrieve a single RecoverUser from the database with the matching email.
     * 
     * @param email The email to find.
     * @return The RecoveryAccount from with matching email, Otherwise null.
     * @throws DBException  When a database error occurs.
     */
    public RecoveryUser getRecoveryByEmail(String email) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            RecoveryUser rec = em.find(RecoveryUser.class, email);
            return rec;

        } finally {
            em.close();
        }
    }

    /**
     * Mutator method to insert an RecoveryUser into the database.
     * 
     * @param user The RecoveryUser to insert.
     * @throws DBException When a database error occurs.
     */
    public void addRecovery(RecoveryUser user) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(user);
            trans.commit();

        } catch (Exception ex) {
            if (trans.isActive()) {
                trans.rollback();
            }
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot adding " + user.toString(), ex);
        
        } finally {
            em.close();
        }
    }

    /**
     * Mutator method to remove a RecoveryUser from the database.
     * 
     * @param user The User to remove.
     * @throws DBException When a database error occurs.
     */
    public void deleteRecovery(RecoveryUser user) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(RecoveryBroker.class.getName()).log(Level.SEVERE, "Cannot delete " + user.toString(), ex);
            throw new DBException("Error deleting user");

        } finally {
            em.close();
        }
    }
}