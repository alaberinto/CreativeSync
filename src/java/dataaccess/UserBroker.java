package dataaccess;

//Remove this import after DB is setup
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import models.User;
import services.HashingService;

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
    public User getUserByEmail(String email) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user = em.find(User.class, email);
            return user;
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read user", ex);
            throw new DBException("Error getting user.");
        }
    }

    /**
     * Gets all the Users from the database.
     *
     * @return a Collection of all Users in the table.
     */
    public Collection<User> getAllUsers() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Collection<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
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
    public Collection<User> getUsersByTitle() {
        return null;
    }

    /**
     * Takes in the entered email and retrieves that emails hash from the DB.
     *
     * @param email
     * @return a String value containing the hashed password input.
     */
    public String getUserHash(String email) throws DBException {
        User user = getUserByEmail(email);
        return user.getPassword();
    }
}
