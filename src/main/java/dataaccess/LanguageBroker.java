package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import models.Language;

/**
 * LanguageBroker is a data-access class to retrieve Language information from the
 * database.
 * @author Mason
 */
public class LanguageBroker {
    
    /**
     * Gets all language information.
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
     * Gets a language by its associated ID.
     * 
     * @param id the id of the language.
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
