package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Data-access class creating a static instance of the EntityManagerFactory used as the connection to the database.
 * 
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
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
