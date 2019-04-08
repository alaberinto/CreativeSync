package dataaccess;

import javax.persistence.EntityManager;
import models.Status;

/**
 *
 * @author Mason
 */
public class StatusBroker {
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
