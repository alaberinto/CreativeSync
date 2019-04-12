package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Title;

/**
 * TitleBroker is a data-access class to manage Title information in database.
 * 
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 * @version 1.0
 */
public class TitleBroker {

    /**
     * Access method to retrieve an ArrayList of all Title objects in the database.
     * 
     * @return A ArrayList of all Titles.
     * @throws DBException When there is a database error.
     */
    public ArrayList<Title> getAllTitles() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Title> titles = em.createNamedQuery("Title.findAll", Title.class).getResultList();
            return new ArrayList(titles);
        } catch (Exception ex) {
            Logger.getLogger(Title.class.getName()).log(Level.SEVERE, "Cannot read title", ex);
        } finally {
            em.close();
        }
        return null;
    }

    /**
     * Mutator method to persist any changes to a Title into the database.
     * 
     * @param title The updated Title to persist.
     * @throws DBException When a database error occurs.
     */
    public void updateTitle(Title title) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(title);
            trans.commit();
        } catch (Exception ex) {
            if (trans.isActive()) {
                trans.rollback();
            }
        } finally {
            em.close();
        }
    }

    /**
     * Mutator method to remove a specified Title from the database.
     * 
     * @param title The Title to remove.
     * @throws DBException When a database error occurs.
     */
    public void deleteTitle(Title title) throws DBException{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(title));
            trans.commit();
        } catch (Exception ex) {
            if (trans.isActive()) {
                trans.rollback();
            }
            Logger.getLogger(TitleBroker.class.getName()).log(Level.SEVERE, "Cannot delete title", ex);
        } finally {
            em.close();
        }
    }

    /**
     * Access method to retrieve a single Title from the database with a matching ID.
     * 
     * @param id The id of the Title to find.
     * @return null if the Title is not found, Otherwise the Title with the matching ID.
     * @throws DBException When there is a database error.
     */
    public Title getTitleById(Integer id) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Title title = em.find(Title.class, id);
            return title;

        } finally {
            em.close();
        }
    }
    
    /**
     * Access method to retrieve a Title object by its Title name.
     * 
     * @param name the name of the title to be retrieved.
     * @return a Title object holding information on a Title.
     * @throws DBException if there was a problem retrieving a Title from the database.
     */
    public Title getTitleByName(String name) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("Title.findByName", Title.class);
            query.setParameter("name", name);

            List<Title> users = query.getResultList();
            return users.get(0);
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read title", ex);
            return null;
        } finally {
            em.close();
        }
    }
    

    /**
     * Access method to retrieve an ArrayList of all active Titles in the database.
     * @return An ArrayList of active Titles.
     * @throws DBException When there is a database error.
     */
    public ArrayList<Title> getActiveTitles() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("Title.findByIsActive", Title.class);
            query.setParameter("isActive", 1);

            List<Title> titles = query.getResultList();
            return new ArrayList(titles);
        } catch (Exception ex) {
            Logger.getLogger(TitleBroker.class.getName()).log(Level.SEVERE, "Cannot read titles", ex);
        } finally {
            em.close();
        }
        return null;
    }
    
    /**
     * Access method to retrieve a list of titles that have been completed.
     * 
     * @return an ArrayList object of Title objects that have been completed.
     * @throws DBException if there was a problem retrieving completed Titles from the database.
     */
    public ArrayList<Title> getCompleteTitles() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("Title.findByCompleted", Title.class);
            query.setParameter("completed", 1);

            List<Title> titles = query.getResultList();
            return new ArrayList(titles);
        } catch (Exception ex) {
            Logger.getLogger(TitleBroker.class.getName()).log(Level.SEVERE, "Cannot read titles", ex);
        } finally {
            em.close();
        }
        return null;
    }
    
    /**
     * Mutator method to persist a Title into the database.
     * 
     * @param title The title to insert.
     * @throws DBException When a database error occurs.
     */
    public void insertTitle(Title title) throws DBException {
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
    }
    
    /**
     * Updates a list of titles in the database.
     * 
     * @param titles an ArrayList object of Title objects to be updated.
     */
    public void updateTitles(ArrayList<Title> titles) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            for(int i = 0; i < titles.size(); i++) {
                em.merge(titles.get(i));
            }
            trans.commit();
        } catch (Exception ex) {
            if (trans.isActive()) {
                trans.rollback();
            }
        } finally {
            em.close();
        }
    }
}
