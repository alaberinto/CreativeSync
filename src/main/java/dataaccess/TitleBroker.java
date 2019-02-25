package dataaccess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Account;
import models.Title;

/**
 *
 * @author Alvin
 */
public class TitleBroker {
    public Title getTitle(int titleId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        return null;
    }
    
    public Title getAllTitles() {
        return null;
    }
    
    public Title updateTitle(int titleId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        return null;
    }
    
    public Title deleteTitle(int titleId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        return null;
    }
    public Title insertTitle(Title title){
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(title);
            trans.commit();
        } catch (Exception e) {
             if (trans.isActive()) {
                trans.rollback();
             }
        } finally {
            em.close();
        }
        return title;
    }

    public Title getTitleById(Integer id) throws DBException {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
         
         try {
            Title title = em.find(Title.class, id);
            return title;
            
         } finally {
             em.close();
         }
    }
}
