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
import models.Feedback;

/**
 *
 * @author Matthew
 */
public class FeedbackBroker {

    public FeedbackBroker() {

    }

    public Feedback getFeedbackById(int feedbackId) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Query query = em.createNamedQuery("Feedback.findByFeedbackId", Feedback.class);
            query.setParameter("feedbackId", feedbackId);

            List<Feedback> feedback = query.getResultList();
            return feedback.get(0);

        } catch (Exception ex) {
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, "Cannot read feedback", ex);
            throw new DBException("Error getting feedback.");
        } finally {
            em.close();
        }
    }

    public List<Feedback> getAllFeedback() throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Feedback> feedback = em.createNamedQuery("Feedback.findAll", Feedback.class).getResultList();
            return feedback;
        } catch (Exception ex) {
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, "Cannot read feedback", ex);
            throw new DBException("Error getting feedback.");
        } finally {
            em.close();
        }
    }

    public int insertFeedback(Feedback feedback) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(feedback);
            trans.commit();
        } catch (Exception ex) {
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, "Cannot read feedback", ex);
            throw new DBException("Error inserting feedback.");
        } finally {
            em.close();
        }
        return 1;
    }

    public int updateFeedback(Feedback feedback) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(feedback);
            trans.commit();
        } catch (Exception ex) {
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, "Cannot update feedback", ex);
            throw new DBException("Error updating feedback.");
        } finally {
            em.close();
        }
        return 1;
    }

    public int deleteFeedback(Feedback feedback) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(feedback));
            trans.commit();
        } catch (Exception ex) {
            Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, "Cannot deleting feedback", ex);
            throw new DBException("Error deleting feedback.");
        } finally {
            em.close();
        }
        return 1;
    }

}
