package dataaccess;

/**
 * Exception class defining an exception thrown when a error occurs performing JPA calls to the database.
 * 
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 */
public class DBException extends Exception{
    
    /**
     * Default constructor calling the Exception super class.
     * @param message 
     */
    public DBException(String message)
    {
        super(message);
    }
}
