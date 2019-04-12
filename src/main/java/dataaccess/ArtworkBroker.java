/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Artwork;

/**

 *
 * ArtworkBroke is a data-access class to retrieve Artwork information from the
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
public class ArtworkBroker {

    /**
     * Default constructor.
     */
    public ArtworkBroker() {

    }

    /**
     * Access method to retrieve an Artwork by an ID.
     * 
     * @param artworkId the id of the artwork to get.
     * @return the Artwork information.
     * @throws DBException if there was an error getting an artwork.
     */
    public Artwork getArtworkById(int artworkId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("Artwork.findByArtworkId", Artwork.class);
            query.setParameter("artworkId", artworkId);

            List<Artwork> art = query.getResultList();
            return art.get(0);

        } catch (Exception ex) {
            Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, "Cannot read artwork", ex);
            throw new DBException("Error getting artwork.");
        } finally {
            em.close();
        }
    }

    /**
     * Access method to retrieve all Artworks.
     * 
     * @return a List object of all Artwork objects.
     * @throws DBException if there was an error getting artworks.
     */
    public List<Artwork> getAllArtwork() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Artwork> art = em.createNamedQuery("Artwork.findAll", Artwork.class).getResultList();
            return art;
        } catch (Exception ex) {
            Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, "Cannot read artwork", ex);
            throw new DBException("Error getting artowork.");
        } finally {
            em.close();
        }
    }

    /**
     * Inserts artwork information into the database.
     * 
     * @param artwork the artwork to be inserted into the database.
     * @return an int for how many rows were inserted.
     * @throws DBException if there was an error inserting into the database.
     */
    public int insertArtwork(Artwork artwork) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(artwork);
            trans.commit();
        } catch (Exception ex) {
            Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, "Cannot read artwork", ex);
            throw new DBException("Error inserting artwork.");
        } finally {
            em.close();
        }
        return 1;
    }

    /**
     * Updates the artwork information in the database.
     * 
     * @param artwork artwork to be updated.
     * @return an int for how many rows were updated.
     * @throws DBException if there was an error updating into the database.
     */
    public int updateArtwork(Artwork artwork) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(artwork);
            trans.commit();
        } catch (Exception ex) {
            Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, "Cannot update artwork", ex);
            throw new DBException("Error updating artwork.");
        } finally {
            em.close();
        }
        return 1;
    }

    /**
     * Deletes the artwork information from the database.
     * 
     * @param artwork artwork to be deleted.
     * @return an int for how many rows were deleted.
     * @throws DBException if there was an error deleting from the database.
     */
    public int deleteArtwork(Artwork artwork) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(artwork));
            trans.commit();
        } catch (Exception ex) {
            Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, "Cannot deleting artwork", ex);
            throw new DBException("Error deleting artwork.");
        } finally {
            em.close();
        }
        return 1;
    }

}
