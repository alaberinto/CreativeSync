package dataaccess;

import javax.persistence.EntityManager;
import models.Status;

/**
 * StatusBroker is a data-access class to retrieve Status information from the
 * database.
 * @author Mason
 */
public class StatusBroker {
    
    /**
     * Access method to retrieve a Status by its matching ID.
     * 
     * @param statId the id of the status.
     * @return a Status object found by its ID.
     * @throws DBException if the status could not be retrieved from the database.
     */
    public Status getStatus(int statId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Status stat = em.find(Status.class, statId);
            return stat;

        } finally {
            em.close();
        }
    }
}
