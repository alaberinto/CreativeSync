package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Location;

/**
 * LocationBroker is a data-access class to retrieve Location information from the database.
 * 
 * @author Mason
 */
public class LocationBroker {
    
    /**
     * Access method to retrieve a single Location for the database with the matching ID.
     * @param locationId The id of the Location to find.
     * @return null if the Position is not found, Otherwise the Position with the matching ID.
     * @throws DBException When there is a database error.
     */
    public Location getLocation(int locationId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Location loc = em.find(Location.class, locationId);
            return loc;

        } finally {
            em.close();
        }
    }
    
    /**
     * Mutator method to remove a specific Location from the database.
     * @param loc The Location to remove.
     * @throws DBException When a database error occurs.
     */
    public void deleteLocation(Location loc) throws DBException{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.remove(em.merge(loc));
            trans.commit();
        }catch(Exception ex){
            if (trans.isActive()) {
                trans.rollback();
            }
            Logger.getLogger(LocationBroker.class.getName()).log(Level.SEVERE, "Cannot delete location", ex);
        }finally{
            em.close();
        }
    }
    
    /**
     * Mutator method to insert a specific Location into the database.
     * @param loc The Location to insert.
     * @throws DBException  When a database error occurs.
     */
    public void insertLocation(Location loc) throws DBException{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
           trans.begin();
           em.persist(loc);
           trans.commit();
        }catch(Exception ex){
            Logger.getLogger(LocationBroker.class.getName()).log(Level.SEVERE, "Cannot read location", ex);
        }finally{
            em.close();
        }
    }
    
    /**
     * Access method to retrieve an ArrayList of all Location objects in the database. 
     * @return An ArrayList of Locations.
     * @throws DBException When a database error occurs.
     */
    public ArrayList<Location> getAllLocations() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Location> loc = em.createNamedQuery("Location.findAll", Location.class).getResultList();
            return new ArrayList(loc);
        } catch (Exception ex) {
            Logger.getLogger(LocationBroker.class.getName()).log(Level.SEVERE, "Cannot read locations", ex);
        }
        
        return null;
    }
}
