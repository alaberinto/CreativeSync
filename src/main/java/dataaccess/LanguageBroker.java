package dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import models.Language;

/**
 *
 * @author Mason
 */
public class LanguageBroker {
    
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
