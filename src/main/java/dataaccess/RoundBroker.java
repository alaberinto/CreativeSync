/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
/**
 *
 * @author 731866
 */
import javax.persistence.EntityManager;
import models.RoundArtwork;
public class RoundBroker {
    
    public RoundBroker(){
        
    }
    public RoundArtwork getRound() throws DBException{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        try{
//            Query query = em.createNamedQuery("RoundArtwork.findByRoundNumber", resultClass)
//        }
        return null;
    }
    
    
    
}
