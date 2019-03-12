/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.TitleHasAccount;

/**
 *
 * @author Mason
 */
public class TitleHasAccountBroker {
    
    public TitleHasAccountBroker() {
        
    }
    
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
