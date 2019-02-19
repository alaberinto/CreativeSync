package com.company.CreativeSync.services;

import com.company.CreativeSync.dataaccess.DBException;
import com.company.CreativeSync.dataaccess.TitleBroker;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.company.CreativeSync.models.Title;

/**
 *
 * @author 697467
 */
public class TitleService {
    
    private final TitleBroker tb;
    
    public TitleService() {
        tb = new TitleBroker();
    }
    
    public Title getTitleById(Integer id) {  
        try {
            return tb.getTitleById(id);
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
