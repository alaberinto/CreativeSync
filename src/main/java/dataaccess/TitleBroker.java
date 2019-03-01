package dataaccess;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Account;
import models.Artwork;
import models.Title;

/**
 *
 * @author Alvin
 */
public class TitleBroker {
    
    public TitleBroker(){
        
    }

    public Collection <Title> getAllTitles() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            Collection<Title>titles = em.createNamedQuery("Title.findAll", Title.class).getResultList();
            return titles;
        }catch(Exception ex){
            Logger.getLogger(Title.class.getName()).log(Level.SEVERE, "Cannot read title", ex);
            throw new DBException("Error getting title.");
        }finally{
            em.close();
        }
        
    }

    public int updateTitle(Title title) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.persist(title);
            trans.commit();
        }catch(Exception ex){
            if (trans.isActive()) {
                trans.rollback();
            }
        }finally{
            em.close();
        }
        return 1;
    }

    public int deleteTitle(Title title) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.remove(em.merge(title));
            trans.commit();
        }catch(Exception ex){
            if (trans.isActive()) {
                trans.rollback();
            }
        }finally{
            em.close();
        }
        return 1;
    }

    public Title insertTitle(Title title) {
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