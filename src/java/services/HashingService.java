package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * See https://crackstation.net/hashing-security.htm for learning hashing.
 * See https://www.baeldung.com/sha-256-hashing-java for where this code came from.
 * 
 * Not using salted hashes currently. Can make them salted later if we want after we get Db functional.
 * @author Mason
 */
public class HashingService {
    
    public HashingService() {
        
    }
    
    public String generateHash(String password) { 
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            byte[] hash = digest.digest(password.getBytes());
            return bytesToHex(hash);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
