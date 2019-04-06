package dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.TitleHasAccount;

/**
 * TitleHasAccountBroker is a data-access class to manage TitleHasAccount information.
 * 
 * @author Mason
 * @version 1.0
 */
public class TitleHasAccountBroker {
    
    /**
     * Mutator method to insert a TitleHasAccount into the database.
     * 
     * @param tha The TitleHasAccount to insert.
     * @return null if the insert was successful, "Error inserting into table!" if the insert failed.
     */
     public String insertTitleHasAccount(TitleHasAccount tha) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(tha);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            return "Error inserting into table";
        } finally {
            em.close();
        }
        return null;
    }
}
