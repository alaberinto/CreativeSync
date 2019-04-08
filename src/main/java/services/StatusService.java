package services;

import dataaccess.DBException;
import dataaccess.StatusBroker;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Status;

/**
 *
 * @author Mason
 */
public class StatusService {
    
    private final StatusBroker sb;
    
    public StatusService() {
        this.sb = new StatusBroker();
    }
    
    public Status getStatusById(int id) {
        try {
            return sb.getStatus(id);
        } catch (DBException ex) {
            return null;
        }
    }
}
