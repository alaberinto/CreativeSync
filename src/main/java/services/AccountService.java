package services;

import dataaccess.DBException;
import dataaccess.PositionBroker;
import dataaccess.RecoveryBroker;
import dataaccess.UserBroker;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;
import models.RecoveryUser;

/**
 *
 * @author Mason
 */
public class AccountService {

    private final PositionBroker pb;
    private final UserBroker ab;
    private final HashingService hs;

    public AccountService() {
        ab = new UserBroker();
        pb = new PositionBroker();
        hs = new HashingService();
    }

    public Account getUserById(int userId) throws DBException {
        return ab.getUserById(userId);

    }
    public int delete(Integer userId) throws DBException{
        Account ac =ab.getUserById(userId);
        return ab.deleteUser(ac);
    }
    public int insert(Integer userId, Integer positionId, String password, String firstname, String lastname, String email, String location, double rate, String portfolio, short isactive, String imagePath) throws DBException {

        Account ac = new Account(userId, hs.generateHash(password), firstname, lastname, email, location, rate, isactive);
        ac.setPosition(pb.getPosition(positionId));
        return ab.insertUser(ac);
        //public Account(Integer userId, String password, String firstname, String lastname, String email, String location, int rate, short isactive) {
    }

    public int update(Account ac, String firstname, String lastname, String email, String password, String location) throws DBException {
        ac.setPassword(hs.generateHash(password));
        ac.setFirstname(firstname);
        ac.setLastname(lastname);
        ac.setEmail(email);
        return ab.update(ac);
    }

    /**
     * Validation method to check if the user has the proper credentials to
     * enter the site. Uses SHA-256.
     *
     * Process: The username and password are entered in Login.JSP and
     * submitted. The users existing hash is retrieved from the Database using
     * their email. Then a new SHA-256 Hash is generated using the email and
     * password. The two hashes are compared.
     *
     * @param email The email entered by the user.
     * @param password The password entered by the user.
     * @return Returns True if the two hashes match. Else returns false.
     */
    public Account validate(String email, String password) {

        //Retrieve users existing hash
        String oldHash;

        try {
            oldHash = getUserHash(email);

        } catch (DBException ex) {

            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        //Create new hash based on email, password
        String newHash = hs.generateHash(password);

        //Compare hashes. I dont understand why the IDE is making a big deal about this.
        if (oldHash.equals(newHash)) {
            try {
                return ab.getUserByEmail(email);
            } catch (DBException ex) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Takes in the entered email and retrieves that emails hash from the DB.
     *
     * @param email
     * @return a String value containing the hashed password input.
     */
    public String getUserHash(String email) throws DBException {
        Account user = ab.getUserByEmail(email);
        return user.getPassword();
    }

    public Account getUserByEmail(String email) {
        try {
            return ab.getUserByEmail(email);
        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void recover(String email, String path) {

        RecoveryBroker rb = new RecoveryBroker();
        EmailService es = new EmailService();
        Account user = getUserByEmail(email);

        if (user != null) {
            try {
                String subject = "Netflix Creative Sync Account Recovery";
                String template = path + "/emailTemplates/recovery.html";
                String code = es.generateCode();

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstname());
                tags.put("lastname", user.getLastname());
                tags.put("code", code);

                RecoveryUser ru = rb.getRecoveryByEmail(email);
                if (ru != null) {
                    rb.deleteRecovery(ru);
                }

                rb.addRecovery(new RecoveryUser(email, new Date(System.currentTimeMillis()), code));
                es.sendMail(email, subject, template, tags);
                
            } catch (DBException ex) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean checkRecovery(String rid, String email) {
        RecoveryBroker rb = new RecoveryBroker();
        try {
            RecoveryUser user = rb.getRecoveryByEmail(email);
            
            if(user != null) {
                if(user.getRecoveryId().equals(rid)) {
                    return true;
                }
            }
        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return false;
    }

    public void updatePassword(String password, String email) {
        try {
            Account user = ab.getUserByEmail(email);
            
            HashingService hs = new HashingService();
            password = hs.generateHash(password);
            user.setPassword(password);
            
            ab.update(user);
            
        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
