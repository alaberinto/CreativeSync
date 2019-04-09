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
import models.Backup;

/**
 *
 * @author 731866
 */
public class BackupBroker {
    public BackupBroker(){
        
    }
    
    
    public Backup insertBackup(Backup ba) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(ba);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
        } finally {
            em.close();
        }
        return ba;
    }
    
    public ArrayList<Backup> getAllBackups() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Backup> backups = em.createNamedQuery("Backup.findAll", Backup.class).getResultList();
            return new ArrayList(backups);
        } catch (Exception ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, "Cannot read backups", ex);
        } finally {
            em.close();
        }
        
        return null;
    }
    public Backup getBackupById(Integer id){
         EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Backup backup = em.find(Backup.class, id);
            return backup;

        } finally {
            em.close();
        }
    }
    
}
