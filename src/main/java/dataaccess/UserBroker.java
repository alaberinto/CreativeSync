package dataaccess;

//Remove this import after DB is setup
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Account;


/**
 *
 * @author Mason
 */
public class UserBroker {

    /**
     * Gets the user based on their registered email.
     *
     * @param email the email associated with the user.
     * @return a User object containing information about the User.
     */
    public Account getUserByEmail(String email) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("Account.findByEmail", Account.class);
            query.setParameter("email", email);
            
            List<Account> users = query.getResultList();
            return users.get(0);
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read user", ex);
            throw new DBException("Error getting user.");
        } finally {
            em.close();
        }
    }
    public Account getUserById(int userId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("Account.findByUserId", Account.class);
            query.setParameter("userId", userId);
            
            List<Account> users = query.getResultList();
            return users.get(0);
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read user", ex);
            throw new DBException("Error getting user.");
        } finally {
            em.close();
        }
    }
     public int update(Account ac) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(ac);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
        return 1;
    }

    /**
     * Gets all the Users from the database.
     *
     * @return a Collection of all Users in the table.
     * @throws dataaccess.DBException
     */
    public Collection<Account> getAllUsers() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Collection<Account> users = em.createNamedQuery("Account.findAll", Account.class).getResultList();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new DBException("Error getting users.");
        }
    }
     public int delete(Account account) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(em.merge(account));
            trans.commit();
            
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
        return 1;
    }
public int insertUser(Account ac) throws DBException {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(ac);
            trans.commit();
        } catch (Exception e) {
             if (trans.isActive()) {
                trans.rollback();
             }
        } finally {
            em.close();
        }
        return 1;
    }
    /**
     * Gets all the Users associated with a Title
     *
     * @return a Collection of Users working on a specific Title.
     */
    public Collection<Account> getUsersByTitle() {
        return null;
    }
}
