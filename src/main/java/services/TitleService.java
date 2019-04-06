package services;

import dataaccess.DBException;
import dataaccess.TitleBroker;
import dataaccess.TitleHasAccountBroker;
import dataaccess.UserBroker;
import exceptions.InvalidTitlesViewException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * TitleService is a service class to precess requests and access to Title
 * information.
 *
 * @author Mason Hill & Cooper Vasiliou
 */
public class TitleService {

    /**
     * Final TitleBroker to handle all Title information in the database.
     */
    private final TitleBroker tb;

    /**
     * Final UserBroker to handle all Account Position information in the
     * database.
     */
    private final UserBroker ab;

    /**
     * Final TitleHasBroker to handle all TitleHasAccount information in the
     * database.
     */
    private final TitleHasAccountBroker tha;

    /**
     * Default constructor to create a new instance of TitleService and
     * construct brokers.
     */
    public TitleService() {
        tb = new TitleBroker();
        ab = new UserBroker();
        tha = new TitleHasAccountBroker();
    }

    /**
     * Access method to retrieve a Title by its ID.
     *
     * @param id The ID of the title to find.
     * @return The Title if found. Otherwise null.
     */
    public Title getTitleById(Integer id) {
        try {
            return tb.getTitleById(id);
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   

    /**
     * Access method to get an ArrayList of TitlesViews that this user can see
     * on Titles.JSP.
     *
     *
     * @param user The Account to filter.
     * @return If the user is an admin, coordinator, or design lead a list of
     * all active titles will be returned. If the user is a freelancer then
     * return the titles that the user is currently assigned to. If they are not
     * currently assigned to anything or they have no ongoing work then a list
     * of titles with matching genres to the user is returned.
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
    
    public TitlesView getTitlesViewByName(String name) {
        Title title = this.getTitleByName(name);
        
        if(title != null) {
            try {
                return new TitlesView(title, false, null);
            } catch (InvalidTitlesViewException ex) {
                Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<TitlesView> getAssignedTitles(Account user, String searchFilter) {
        ArrayList<TitleHasAccount> tha = new ArrayList(user.getTitleHasAccountList());
        ArrayList<TitlesView> tv = new ArrayList<>();
        
        if(searchFilter != null) {
            searchFilter = searchFilter.toLowerCase();
        }

        for (int i = 0; i < tha.size(); i++) {
            try {
                if (searchFilter == null || tha.get(i).getTitle().getName().toLowerCase().contains(searchFilter)) {
                    tv.add(new TitlesView(tha.get(i).getTitle(), true, tha.get(i).getStatus()));
                }
            } catch (InvalidTitlesViewException ex) {
                Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (tv.isEmpty()) {
            return null;
        }

        return tv;
    }

    public ArrayList<TitlesView> getUnassignedTitles(Account user, ArrayList<TitlesView> assigned, String searchFilter) {
        ArrayList<TitlesView> tv = getTitlesByUserForTitlesJSP(user);
        ArrayList<TitlesView> fin = new ArrayList<>();

        if (tv == null) {
            return tv;
        }

        //Clear TV of titles the user is already assigned to.
        if (assigned != null) {
            for (int i = tv.size() - 1; i > 0; i--) {
                for (int j = 0; j < assigned.size(); j++) {
                    if (tv.get(i).getTitle().getTitleId() == assigned.get(j).getTitle().getTitleId()) {
                        tv.remove(i);
                        break;
                    }
                }
            }
        }

        //Search through list and get all matching search filter.
        if (searchFilter != null) {
            searchFilter = searchFilter.toLowerCase();

            for (int i = 0; i < tv.size(); i++) {
                if (tv.get(i).getTitle().getName().toLowerCase().contains(searchFilter)) {
                    fin.add(tv.get(i));
                }
            }
        }
        else {
            if(tv.isEmpty())
                return null;
            else
                return tv;
        }

        if(fin.isEmpty())
            return null;
        
        return fin;
    }

    /**
     * Helper method to get all the TitleView models that this freelance user
     * can view.
     *
     * @param user The Account to filter.
     * @return An ArrayList of TitleViews.
     */
    private ArrayList<TitlesView> getFreelancersTitles(Account user) {
        ArrayList<TitlesView> tv = new ArrayList<>();

        try {
            ArrayList<Title> activeTitles = new ArrayList(tb.getActiveTitles());

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
            if (tv.isEmpty()) {
                return null;
            }
            return tv;
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Helper method to get all the TitleView models that any non-freelance user
     * can view.
     *
     * @param user The Account to filter.
     * @return An ArrayList of TitleViews.
     */
    private ArrayList<TitlesView> getPrivilagedTitles(Account user) {
        ArrayList<TitleHasAccount> titles = new ArrayList(user.getTitleHasAccountList());
        ArrayList<TitlesView> tv = new ArrayList<>();

        try {
            ArrayList<Title> activeTitles = new ArrayList(tb.getActiveTitles());

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

    /**
     * Mutator method to create a new Title.
     *
     * @param name The name of the Title.
     * @param startDate The Titles start date.
     * @param endDate The Titles end date.
     * @param priority If the title is a priority.
     * @param designInfo Any design information possible entered on creation.
     * @param designLeadId The id of the assigned design lead.
     * @param coordinatorId The id of the assigned coordinator.
     * @param maxNumberOfFreelancers The max number of freelancers assignable.
     * @return null if the creation was successful. Otherwise an error String.
     */
    public String insert(String name, String startDate, String endDate, String priority, String designInfo, int designLeadId, int coordinatorId, int maxNumberOfFreelancers, String[] freelancers) {
        Title existing;
        existing = getTitleByName(name);
        
        if(existing != null)
            return "This Title Already Exists!";
        
        try {
            if (coordinatorId == -1) {
                try {
                    coordinatorId = ab.getUserById(300).getUserId();
                } catch (DBException ex) {
                    Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (designLeadId == -1) {
                try {
                    designLeadId = ab.getUserById(200).getUserId();
                } catch (DBException ex) {
                    Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Date newStartDate = new SimpleDateFormat("YYYY-MM-DD").parse(startDate);
            Date newEndDate = new SimpleDateFormat("YYY-MM-DD").parse(endDate);
            short newPriority = 1;

            if (priority == null) {
                newPriority = 0;
            }

            //if(curdate < startdate) then isActive 0; Then if startdate = curdate in db then set to active
            Short isActive = new Short("1");

            Title title = new Title(null, name, newStartDate, newEndDate, isActive, newPriority, designInfo, 1, designLeadId, coordinatorId, maxNumberOfFreelancers);
            try {
                tb.insertTitle(title);
            } catch (DBException ex) {
                Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            }

            TitleHasAccount lead = new TitleHasAccount(title.getTitleId(), designLeadId, 1);
            TitleHasAccount coor = new TitleHasAccount(title.getTitleId(), coordinatorId, 1);
            
            for(int i = 0; i < freelancers.length;i++) {
                tha.insertTitleHasAccount(new TitleHasAccount(title.getTitleId(), Integer.parseInt(freelancers[i]), 1));
            }

            tha.insertTitleHasAccount(lead);
            tha.insertTitleHasAccount(coor);

            return null;
        } catch (ParseException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error adding user";
    }
    
    public Title getTitleByName(String name){
        try {
            return tb.getTitleByName(name);
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
