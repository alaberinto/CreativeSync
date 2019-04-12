package services;

import dataaccess.DBException;
import dataaccess.StatusBroker;
import models.Status;

/**
 * StatusService is a service class that status of titles or users
 * 
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 *
 * 
 */
public class StatusService {
    
    private final StatusBroker sb;
    
    /**
     * Non-default constructor that instantiates the StatusBroker.
     */
    public StatusService() {
        this.sb = new StatusBroker();
    }
    
    /**
     * Access method to retrieve a status by its matching ID.
     * 
     * @param id the id of the status to be retrieved.
     * @return a Status object containing Status information.
     */
    public Status getStatusById(int id) {
        try {
            return sb.getStatus(id);
        } catch (DBException ex) {
            return null;
        }
    }
}
