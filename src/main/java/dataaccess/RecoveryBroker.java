package dataaccess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.RecoveryUser;

/**
 *
 * @author Mason
 */
public class RecoveryBroker {

    public RecoveryUser getRecoveryByEmail(String email) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("RecoveryUser.findByEmail", RecoveryUser.class);
            query.setParameter("email", email);

            List<RecoveryUser> users = query.getResultList();
            if (users.size() > 0) {
                return users.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(RecoveryBroker.class.getName()).log(Level.SEVERE, "Cannot read user", ex);
            throw new DBException("Error getting user.");
        } finally {
            em.close();
        }
    }

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
            throw new DBException("Error adding user");
        } finally {
            em.close();
        }
    }

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
