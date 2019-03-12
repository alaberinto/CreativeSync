package viewModels;

import exceptions.InvalidTitlesViewException;
import java.util.List;
import models.Title;
import models.Account;
import models.Status;
import models.TitleHasAccount;

/**
 *
 * @author Mason
 */
public class TitlesView {

    private Title title;
    private Account lead;
    private Account coor;
    private boolean isAssigned;
    private Status status;
    
    
    
    public TitlesView(Title title, boolean isAssigned, Status status) throws InvalidTitlesViewException {
        this.title = title;
        this.isAssigned = isAssigned;
        this.status = status;
        
        int coorId = title.getCoordinatorId();
        int leadId = title.getDesignLeadId();
        
        List<TitleHasAccount> accounts = title.getTitleHasAccountList();
        
        for(int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i).getAccount();
            
            if(coorId == accounts.get(i).getAccount().getUserId()) {
                coor = account;
            }
            else if(leadId == accounts.get(i).getAccount().getUserId()) {
                lead = account;
            }
        }
        
        if(lead == null || coor == null) {
            throw new InvalidTitlesViewException();
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Account getLead() {
        return lead;
    }

    public void setLead(Account lead) {
        this.lead = lead;
    }

    public Account getCoor() {
        return coor;
    }

    public void setCoor(Account coor) {
        this.coor = coor;
    }

    public boolean isIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }
}
