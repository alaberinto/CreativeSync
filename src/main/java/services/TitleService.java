package services;

import dataaccess.DBException;
import dataaccess.TitleBroker;
import dataaccess.TitleHasAccountBroker;
import dataaccess.UserBroker;
import exceptions.InvalidTitlesViewException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;
import models.Genre;
import models.Status;
import models.Title;
import models.TitleHasAccount;
import viewModels.TitlesView;

/**
 *
 * @author 697467
 */
public class TitleService {

    private final TitleBroker tb;
    private final UserBroker ab;
    private final TitleHasAccountBroker tha;

    public TitleService() {
        tb = new TitleBroker();
        ab = new UserBroker();
        tha = new TitleHasAccountBroker();
    }

    public Title getTitleById(Integer id) {
        try {
            return tb.getTitleById(id);
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Method to get the titles that will be displayed in titles JSP.
     *
     *
     * @param user
     * @return If the user is an admin, coordinator, or design lead a list of
     * all active titles will be returned. If the user is a freelancer then
     * return the titles that the user is currently assigned too. If they are
     * not currently assigned to anything or they are waiting then a list of
     * titles with matching genres to the user is returned.
     */
    public ArrayList<TitlesView> getTitlesByUserForTitlesJSP(Account user) {
        ArrayList<TitlesView> tv = new ArrayList<>();

        //If User is a freelancer
        if (user.getPosition().getPositionDesc().equals("Freelancer")) {
            tv = getFreelancersTitles(user);
        } else {
            tv = getPrivilagedTitles(user);
        }

        return tv;
    }

    private ArrayList<TitlesView> getFreelancersTitles(Account user) {
        ArrayList<TitlesView> tv = new ArrayList<>();

        //Get titles currently assigned to this user
        ArrayList<TitleHasAccount> titles = new ArrayList(user.getTitleHasAccountList());

        //Add assigned titles too view model
        for (int i = 0; i < titles.size(); i++) {
            try {
                tv.add(new TitlesView(titles.get(i).getTitle(), true, titles.get(i).getStatus()));

            } catch (InvalidTitlesViewException ex) {
                Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Check if any of the assigned titles requires work.
        Boolean hasWork = false;
        for (int i = 0; i < titles.size(); i++) {

            //If title is still active and its status is ongoing the user has work.
            if (titles.get(i).getTitle().getIsActive() == 1 && titles.get(i).getStatus().getStatusDesc().equals("Ongoing")) {
                hasWork = true;
            }
        }

        //If has work then return
        if (hasWork) {
            return tv;

            //If has no work then Get all active titles
        } else {
            try {
                ArrayList<Title> activeTitles = new ArrayList(tb.getActiveTitles());

                for (int i = 0; i < tv.size(); i++) {
                    if (activeTitles.contains(tv.get(i).getTitle())) {
                        activeTitles.remove(tv.get(i).getTitle());
                    }
                }

                //Get users genre list
                ArrayList<Genre> userGenres = new ArrayList(user.getGenreList());

                //Loop through active titles and compare the two lists
                for (int i = 0; i < activeTitles.size(); i++) {
                    Title title = activeTitles.get(i);

                    List<Genre> activeTitleGenres = activeTitles.get(i).getGenreList();
                    activeTitleGenres.retainAll(userGenres);

                    //If any of them have at least one match then add them too tv
                    if (!activeTitleGenres.isEmpty()) {
                        if (title.getMaxNumberOfFreelancers() >= title.getNumberOfFreelancers()) {
                            try {
                                tv.add(new TitlesView(title, false, new Status(4, "Unassigned")));
                            } catch (InvalidTitlesViewException ex) {
                                Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                }

                return tv;
            } catch (DBException ex) {
                Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private ArrayList<TitlesView> getPrivilagedTitles(Account user) {
        ArrayList<TitleHasAccount> titles = new ArrayList(user.getTitleHasAccountList());
        ArrayList<TitlesView> tv = new ArrayList<>();

        //Add already assigned titles to list
        for (int i = 0; i < titles.size(); i++) {
            try {
                tv.add(new TitlesView(titles.get(i).getTitle(), true, titles.get(i).getStatus()));
            } catch (InvalidTitlesViewException ex) {
                Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            ArrayList<Title> activeTitles = new ArrayList(tb.getActiveTitles());

            for (int i = 0; i < tv.size(); i++) {
                if (activeTitles.contains(tv.get(i).getTitle())) {
                    activeTitles.remove(tv.get(i).getTitle());
                }
            }

            for (int i = 0; i < activeTitles.size(); i++) {
                try {
                    tv.add(new TitlesView(activeTitles.get(i), false, new Status(4, "Unassigned")));
                } catch (InvalidTitlesViewException ex) {
                    Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tv;
    }
    
    public String insert(String name, String startDate, String endDate, String priority, String designInfo, int designLeadId, int coordinatorId, int maxNumberOfFreelancers) {

        try {
            if (coordinatorId == -1) {
                coordinatorId = ab.getUserById(300).getUserId();
            }
            if(designLeadId == -1){
                designLeadId = ab.getUserById(200).getUserId();
            }
            Date newStartDate = new SimpleDateFormat("YYYY-MM-DD").parse(startDate);
            Date newEndDate = new SimpleDateFormat("YYY-MM-DD").parse(endDate);
            short newPriority = 1;
            
            if (priority == null)
                newPriority = 0;
            
            //if(curdate < startdate) then isActive 0; Then if startdate = curdate in db then set to active
            Short isActive = new Short("1");
            
            Title title = new Title(null, name, newStartDate, newEndDate, isActive, newPriority, designInfo, 1, designLeadId, coordinatorId, maxNumberOfFreelancers);
            tb.insertTitle(title);
            
            TitleHasAccount lead = new TitleHasAccount(title.getTitleId(), designLeadId, 1);
            TitleHasAccount coor = new TitleHasAccount(title.getTitleId(), coordinatorId, 1);
            
            tha.insertTitleHasAccount(lead);
            tha.insertTitleHasAccount(coor);
            
            return null;
            
        } catch (ParseException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error inserting into table";
    }
}
