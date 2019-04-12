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
 * BackupBroker is a data-access class to retrieve Backup information from the
 * database.

 * 
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 */
public class BackupBroker {
    
    /**
     * Default constructor.
     */
    public BackupBroker(){
        
    }
    
    /**
     * Inserts backup information into the database.
     * 
     * @param ba backup information to be inserted to the database.
     * @return a Backup object holding backup information
     * @throws DBException if there was a problem inserting into the database.
     */
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
    
    /**
     * Access method to retrieve all backup information.
     * 
     * @return an ArrayList object of all backup information.
     */
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
    
    /**
     * Access method to retrieve backup by its matching ID.
     * 
     * @param id the id of the backup.
     * @return the backup information found by its ID.
     */
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
