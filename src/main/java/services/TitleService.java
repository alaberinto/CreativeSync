package services;

import dataaccess.DBException;
import dataaccess.GenreBroker;
import dataaccess.TitleBroker;
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
import org.apache.commons.lang3.ArrayUtils;
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
    //private final TitleHasAccountBroker tha;
    /**
     * Default constructor to create a new instance of TitleService and
     * construct brokers.
     */
    public TitleService() {
        tb = new TitleBroker();
        ab = new UserBroker();
        //tha = new TitleHasAccountBroker();
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
     * Access method to retrieve a list of all titles.
     * 
     * @return an ArrayList object of all Title objects.
     */
    public ArrayList<Title> getAllTitles(){
        try {
            return tb.getAllTitles();
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    /**
     * Access method to retrieve a TitlesView object by the name of the title.
     * 
     * @param name the name of the title.
     * @return a TitlesView object with the title information to be displayed.
     */
    public TitlesView getTitlesViewByName(String name) {
        Title title = this.getTitleByName(name);

        if (title != null) {
            try {
                return new TitlesView(title, false, null);
            } catch (InvalidTitlesViewException ex) {
                Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Access method to retrieve titles that are assigned to a user's account.
     * 
     * @param user the account of the user whose titles are to be found.
     * @param searchFilter search filter used for the title name.
     * @return an ArrayList object of TitlesView objects that will display assigned titles for a user.
     */
    public ArrayList<TitlesView> getAssignedTitles(Account user, String searchFilter) {
        ArrayList<TitleHasAccount> tha = new ArrayList(user.getTitleHasAccountList());
        ArrayList<TitlesView> tv = new ArrayList<>();

        if (searchFilter != null) {
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

    /**
     * Access method to retrieve titles that are not assigned to a user's account.
     * 
     * @param user the account of the user whose titles are to be found.
     * @param assigned list of titles assigned to the user's account.
     * @param searchFilter search filter used for the title name.
     * @return an ArrayList object of TitlesView objects that will display unassigned titles for a user.
     */
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
        } else {
            if (tv.isEmpty()) {
                return null;
            } else {
                return tv;
            }
        }

        if (fin.isEmpty()) {
            return null;
        }

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
    public String insert(String name, String startDate, String endDate, String priority, String designInfo, String designId, String coorId, String maxNumberOfFreelancers, String[] freelancers, String[] genres) {
        Title existing = getTitleByName(name);

        Integer designLeadId = Integer.parseInt(designId);
        Integer coordinatorId = Integer.parseInt(coorId);
        Integer maxFrees = Integer.parseInt(maxNumberOfFreelancers);
        Date newStartDate;
        Date newEndDate;
        short newPriority = 1;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            newStartDate = sdf.parse(startDate);
            newEndDate = sdf.parse(endDate);

            if (newStartDate.after(newEndDate)) {
                return "End date is before start date.";
            }

            if (newStartDate.before(new Date())) {
                return "Start date is before today!";
            }
        } catch (ParseException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
            return "Could not parse dates";
        }

        if (existing != null) {
            return "This Title Already Exists!";
        }

        if ((!ArrayUtils.isEmpty(freelancers)) && freelancers.length > maxFrees) {
            return "Cannot Assign This Many Freelancers!";
        }

        try {
            if (coordinatorId == -1) {
                coordinatorId = 300;
            }

            if (designLeadId == -1) {
                designLeadId = 200;
            }

            //priority button is OFF
            if (priority == null) {
                newPriority = 0;
            }

            Short isActive;

            if (newStartDate.after(new Date())) {
                isActive = new Short("1");
            } else {
                isActive = new Short("0");
            }

            //Integer titleId, String name, Date startDate, Date endDate, short isActive, short priority, String designInfo, int numberOfFreelancers, int designLeadId, int coordinatorId, int maxNumberOfFreelancers, short completed
            Title title = new Title(null, name, newStartDate, newEndDate, isActive, newPriority, designInfo, maxFrees, designLeadId, coordinatorId, maxFrees, new Short("0"));
            tb.insertTitle(title);

                
            AccountService as = new AccountService();
            StatusService gs = new StatusService();
            
            title.getTitleHasAccountList().add(new TitleHasAccount(title.getTitleId(), designLeadId, 1));
            Account dAcc = as.getUserById(designLeadId);
            title.getTitleHasAccountList().get(0).setAccount(dAcc);
            title.getTitleHasAccountList().get(0).setTitle(title);
            title.getTitleHasAccountList().get(0).setStatus(new Status(1));

            title.getTitleHasAccountList().add(new TitleHasAccount(title.getTitleId(), coordinatorId, 1));
            title.getTitleHasAccountList().get(1).setAccount(as.getUserById(coordinatorId));
            title.getTitleHasAccountList().get(1).setTitle(title);
            title.getTitleHasAccountList().get(1).setStatus(new Status(1));
            
            
            if (freelancers != null) {
                for (int i = 0; i < freelancers.length; i++) {
                    title.getTitleHasAccountList().add(new TitleHasAccount(title.getTitleId(), Integer.parseInt(freelancers[i]), 1));
                    title.getTitleHasAccountList().get(i + 2).setAccount(as.getUserById(Integer.parseInt(freelancers[i])));
                    title.getTitleHasAccountList().get(i + 2).setTitle(title);
                    title.getTitleHasAccountList().get(i + 2).setStatus(new Status(1));
                }
            }
            
            //Set Genres
            GenreBroker gb = new GenreBroker();
            
            if(genres != null) {
                for(int i = 0; i < genres.length; i++) {
                    title.getGenreList().add(gb.getGenreById(Integer.parseInt(genres[i])));
                }
            }
            
            tb.updateTitle(title);

            return null;
        } catch (Exception ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error adding title";
    }

    /**
     * Access method to retrieve a title by its name.
     * 
     * @param name the name of the title.
     * @return a Title object retrieved by its name.
     */
    public Title getTitleByName(String name) {
        try {
            return tb.getTitleByName(name);
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Access method to retrieve titles associated with a user.
     * 
     * @param user the user whose titles to be retrieved.
     * @return an ArrayList object of Title objects associated with a user.
     */
    public ArrayList<Title> getTitlesByUser(Account user) {
        ArrayList<TitleHasAccount> titleHasAccountList = new ArrayList(user.getTitleHasAccountList());
        ArrayList<Title> titles = new ArrayList();

        for (int i = 0; i < titleHasAccountList.size(); i++) {
            titles.add(titleHasAccountList.get(i).getTitle());
        }

        return titles;
    }

    /**
     * Method that updates a list of titles.
     * 
     * @param titles an ArrayList object of Title objects to be updated.
     */
    public void updateTitles(ArrayList<Title> titles) {
        tb.updateTitles(titles);
    }
    
    /**
     * Access method to retrieve a list of completed titles.
     * 
     * @return an ArrayList object of TitlesView objects that are defined as completed. 
     */
    public ArrayList<TitlesView> getCompleteTitles() {
        ArrayList<TitlesView> completed = new ArrayList<>();
        try {
            ArrayList<Title> titles = tb.getCompleteTitles();
            
            if(titles != null) {
                for(int i = 0; i < titles.size(); i++) {
                    try {
                        completed.add(new TitlesView(titles.get(i), false, new Status(4, "Unassigned")));
                    } catch (InvalidTitlesViewException ex) {
                        Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return completed;
    }
}
