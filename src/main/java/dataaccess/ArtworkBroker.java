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
 * @author Matthew
 */
public class ArtworkBroker {

    public ArtworkBroker() {

    }

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
