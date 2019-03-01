package services;

import dataaccess.DBException;
import dataaccess.TitleBroker;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Title;

/**
 *
 * @author 697467
 */
public class TitleService {

    private final TitleBroker tb;

    public TitleService() {
        tb = new TitleBroker();
    }

    public Title insert(Integer titleId, String name, Date startDate, Date endDate, short isActive, short priority, String designInfo) {
        Title title = new Title(titleId, name, startDate, endDate, isActive, priority, designInfo);
        return tb.insertTitle(title);
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
