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
    
    public LanguageService() {
        this.lb = new LanguageBroker();
    }
    
    public ArrayList<Language> getAllLanguages() {
        return lb.getAllLanguages();
    }
    
    public Language getLanguageById(String id) {
        try {
            return lb.getLanguageById(Integer.parseInt(id));
        } catch (DBException ex) {
            Logger.getLogger(GenreService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
