package services;

import dataaccess.DBException;
import dataaccess.StatusBroker;
import models.Status;

/**
 * StatusService is a service class to process requests to access or mutate
 * Status information.
 * @author Mason
 */
public class StatusService {
    
    private final StatusBroker sb;
    
    /**
     * Constructor that instantiates the StatusBroker.
     */
    public StatusService() {
        this.sb = new StatusBroker();
    }
    
    /**
     * Gets a status by its ID.
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
