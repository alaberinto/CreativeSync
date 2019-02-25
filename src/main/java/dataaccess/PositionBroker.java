/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Account;
import models.Position;


/**
 *
 * @author 731866
 */
public class PositionBroker {
    
    
    public PositionBroker()
    {
        
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


}
