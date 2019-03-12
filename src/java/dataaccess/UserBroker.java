package dataaccess;

//Remove this import after DB is setup
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
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

    /**
     * Gets all the Users from the database.
     *
     * @return a Collection of all Users in the table.
     * @throws dataaccess.DBException
     */
    public List<Account> getAllUsers() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Account> users = em.createNamedQuery("Account.findAll", Account.class).getResultList();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new DBException("Error getting users.");
        }
    }

    /**
     * Gets all the Users associated with a Title
     *
     * @return a Collection of Users working on a specific Title.
     */
    public List<Account> getUsersByTitle() {
        return null;
    }
}
