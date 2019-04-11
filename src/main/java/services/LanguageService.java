package services;

import dataaccess.DBException;
import dataaccess.LanguageBroker;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Language;

/**
 *
 * @author Mason
 */
public class LanguageService {
    
    private LanguageBroker lb;
    
    /**
     * Constructor that instantiates the LanguageBroker.
     */
    public LanguageService() {
        this.lb = new LanguageBroker();
    }
    
    /**
     * Gets all languages.
     * 
     * @return an ArrayList object of all Language objects.
     */
    public ArrayList<Language> getAllLanguages() {
        return lb.getAllLanguages();
    }
    
    /**
     * Gets language by its associated ID.
     * 
     * @param id the associated ID of the language to be retrieved.
     * @return a Language object containing Language information.
     */
    public Language getLanguageById(String id) {
        try {
            return lb.getLanguageById(Integer.parseInt(id));
        } catch (DBException ex) {
            Logger.getLogger(GenreService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
