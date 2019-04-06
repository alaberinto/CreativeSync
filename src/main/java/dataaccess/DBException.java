package dataaccess;

/**
 * Exception class defining an exception thrown when a error occurs performing JPA calls to the database.
 * 
 * @author 731866 & Mason Hill
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
