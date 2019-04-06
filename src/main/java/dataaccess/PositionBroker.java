package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Position;

/**
 * PositionBroker is a data-access class to retrieve Position information from the database.
 * 
 * @author Cooper Vasiliou & Mason Hill
 * @version 1.0
 */
public class PositionBroker {

    /**
     * Access method to retrieve a single Position for the database with the matching ID.
     * @param positionId The if of the Position to find.
     * @return null if the Position is not found, Otherwise the Position with the matching ID.
     * @throws DBException When there is a database error.
     */
    public Position getPosition(int positionId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Position pos = em.find(Position.class, positionId);
            return pos;

        } finally {
            em.close();
        }
    }
    
    /**
     * Mutator method to remove a specific Position from the database.
     * @param pos The Position to remove.
     * @throws DBException When a database error occurs.
     */
    public void deletePosition(Position pos) throws DBException{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.remove(em.merge(pos));
            trans.commit();
        }catch(Exception ex){
            if (trans.isActive()) {
                trans.rollback();
            }
            Logger.getLogger(PositionBroker.class.getName()).log(Level.SEVERE, "Cannot delete position", ex);
        }finally{
            em.close();
        }
    }
    
    /**
     * Mutator method to insert a specific Position into the database.
     * @param pos The Position to insert.
     * @throws DBException  When a database error occurs.
     */
    public void insertPosition(Position pos) throws DBException{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
           trans.begin();
           em.persist(pos);
           trans.commit();
        }catch(Exception ex){
            Logger.getLogger(PositionBroker.class.getName()).log(Level.SEVERE, "Cannot read position", ex);
        }finally{
            em.close();
        }
    }
    
    /**
     * Access method to retrieve an ArrayList of all Position objects in the database. 
     * @return An ArrayList of Positions.
     * @throws DBException When a database error occurs.
     */
    public ArrayList<Position> getAllPositions() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Position> pos = em.createNamedQuery("Position.findAll", Position.class).getResultList();
            return new ArrayList(pos);
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read positions", ex);
        }
        return null;
    }
}
