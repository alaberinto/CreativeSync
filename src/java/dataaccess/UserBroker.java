package dataaccess;

//Remove this import after DB is setup
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
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
            Account user = (Account) em.createNamedQuery("Account.findByEmail",Account.class);
            return user;
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

    /**
     * Gets all the Users associated with a Title
     *
     * @return a Collection of Users working on a specific Title.
     */
    public Collection<Account> getUsersByTitle() {
        return null;
    }
}
