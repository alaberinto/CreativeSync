/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Account;
import models.Position;


/**
 *
 * @author 731866
 */
public class PositionBroker {

    public PositionBroker() {

    }

    public Position getPosition(int position) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("Position.findByPositionId", Position.class);
            query.setParameter("positionId", position);

            List<Position> pos = query.getResultList();
            return pos.get(0);
        } catch (Exception ex) {
            Logger.getLogger(PositionBroker.class.getName()).log(Level.SEVERE, "Cannot read position", ex);
            throw new DBException("Error getting position.");
        } finally {
            em.close();
        }
    }
    
    
    public int deletePosition(Position pos) throws DBException{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.remove(em.merge(pos));
            trans.commit();
        }catch(Exception ex){
             Logger.getLogger(PositionBroker.class.getName()).log(Level.SEVERE, "Cannot read position", ex);
            throw new DBException("Error getting position.");
        }finally{
            em.close();
        }
        return 1;
    }
    
    public int insertPosition(Position pos) throws DBException{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
           trans.begin();
           em.persist(pos);
           trans.commit();
        }catch(Exception ex){
             Logger.getLogger(PositionBroker.class.getName()).log(Level.SEVERE, "Cannot read position", ex);
            throw new DBException("Error getting position.");
        }finally{
            em.close();
        }
        return 1;
    }
    
    public ArrayList<Position> getAllPositions() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Position> pos = em.createNamedQuery("Position.findAll", Position.class).getResultList();
            return new ArrayList(pos);
        } catch (Exception ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, "Cannot read positions", ex);
            throw new DBException("Error getting positions.");
        }
    }
}
