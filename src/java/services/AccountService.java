package services;

import dataaccess.DBException;
import dataaccess.UserBroker;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;



/**
 *
 * @author Mason
 */
public class AccountService {

    private final UserBroker ab;

    public AccountService() {
        ab = new UserBroker();
    }

    
    /**
     * Validation method to check if the user has the proper credentials to enter the site. 
     * Uses SHA-256.
     * 
     * Process: The username and password are entered in Login.JSP and submitted. 
     * The users existing hash is retrieved from the Database using their email. Then a new
     * SHA-256 Hash is generated using the email and password. The two hashes are compared. 
     * 
     * @param email The email entered by the user.
     * @param password The password entered by the user.
     * @return Returns True if the two hashes match. Else returns false.
     */
    public boolean validate(String email, String password){
        HashingService hs = new HashingService();
        
        //Retrieve users existing hash
        String oldHash;
        
        try {
            oldHash = getUserHash(email);
            
        } catch (DBException ex) {
            
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        //Create new hash based on email, password
        String newHash = hs.generateHash(password);
        
        //Compare hashes. I dont understand why the IDE is making a big deal about this.
        if(oldHash.equals(newHash))
            return true;
        return false;
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
}