/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import models.Genre;
/**
 *
 * @author Mason
 */
public class GenreBroker {
    
    public GenreBroker() {
        
    }
    
    public Collection<Genre> getAllGenres() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Collection<Genre> genres = em.createNamedQuery("Genre.findAll", Genre.class).getResultList();
            return genres;
        } catch (Exception ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, "Cannot read genres", ex);
            throw new DBException("Error getting genres.");
        } finally {
            em.close();
        }
    }
    
}
