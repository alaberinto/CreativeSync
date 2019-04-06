package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Data-access class creating a static instance of the EntityManagerFactory used as the connection to the database.
 * 
 * @author Cooper Vasiliou
 */
public class DBUtil {
    
    /**
     * Static EntityManagerFactory containing a single instance. 
     */
     private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("NetflixPU");

     /**
      * Access method to retrieve the EntityManagerFactory.
      * @return The EntityManagerFactory.
      */
    public static EntityManagerFactory getEmFactory() {
        return emf;
    } 
}
