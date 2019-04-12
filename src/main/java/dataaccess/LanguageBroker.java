package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import models.Language;

/**
 * LanguageBroker is a data-access class to retrieve Artwork Language from the
 * database.
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 */
public class LanguageBroker {
    
    /**
     * Access method to retrieve all language information.
     * 
     * @return an ArrayList object holding Language objects.
     */
    public ArrayList<Language> getAllLanguages() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Language> lan = em.createNamedQuery("Language.findAll", Language.class).getResultList();
            return new ArrayList(lan);
        } catch (Exception ex) {
            Logger.getLogger(LocationBroker.class.getName()).log(Level.SEVERE, "Cannot read locations", ex);
        }
        
        return null;
    }
    
    /**
     * Access method to retrieve a language by its matching ID.
     * 
     * @param id the id of the language to be retrieved.
     * @return a Language object found by its ID.
     * @throws DBException if the language could not be retrieved from the database.
     */
    public Language getLanguageById(Integer id) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Language lang = em.find(Language.class, id);
            return lang;

        } finally {
            em.close();
        }
    }
}
