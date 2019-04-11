package services;

import dataaccess.DBException;
import dataaccess.TitleBroker;
import dataaccess.UserBroker;
import exceptions.InvalidTitlesViewException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;
import models.Title;
import viewModels.TitlesView;
import viewModels.UsersView;

/**
 * ReportService is a service class to process requests to access or mutate
 * Report information.
 * @author Cooper Vasiliou
 */
public class ReportService {

    private UserBroker ab;
    private TitleBroker tb;

    /**
     * Non-default constructor that instantiates UserBroker and TitleBroker.
     */
    public ReportService() {
        ab = new UserBroker();
        tb = new TitleBroker();
    }

    /**
     * Access method to retrieve a list of all users.
     * 
     * @return an ArrayList object of all Account objects.
     */
    public ArrayList<Account> getAllUsers() {
        return new ArrayList(ab.getAllUsers());
    }

    /**
     * Method that allows us to view users by position.
     * 
     * @param positionId a list of ids of all positions to view.
     * @return an ArrayList object of UsersView objects.
     */
    public ArrayList<UsersView> viewUserByPosition(String[] positionId) {
        ArrayList<Integer> toParse = new ArrayList<>();
        for (int j = 0; j < positionId.length; j++) {
            toParse.add(Integer.parseInt(positionId[j]));
        }
        ArrayList<Account> allAccounts = getAllUsers();
        ArrayList<Account> cleanArray = new ArrayList<>();

        for (int i = 0; i < allAccounts.size(); i++) {
            for (int k = 0; k < toParse.size(); k++) {
                if (allAccounts.get(i).getPosition().getPositionId() == toParse.get(k)) {
                    cleanArray.add(allAccounts.get(i));
                    break;
                }
            }
        }
        ArrayList<UsersView> usersView = new ArrayList<UsersView>();

        for (int i = 0; i < cleanArray.size(); i++) {
            usersView.add(new UsersView(cleanArray.get(i)));
        }
        return usersView;

    }

    /**
     * Method that allows us to view user information.
     * 
     * @param userIds a String array of all userIds whose information we want to view.
     * @return an ArrayList object of UsersView objects.
     */
    public ArrayList<UsersView> viewUserInfo(String[] userIds) {
        try {
            ArrayList<Integer> acc = new ArrayList();
            for (int i = 0; i < userIds.length; i++) {
                acc.add(Integer.parseInt(userIds[i]));
                
            }
            ArrayList<Account> cleanArray = new ArrayList<>();
            for (int j = 0; j < acc.size(); j++) {
                cleanArray.add(ab.getUserById(acc.get(j)));
            }
            ArrayList<UsersView> usersView = new ArrayList<UsersView>();

            for (int i = 0; i < cleanArray.size(); i++) {
                usersView.add(new UsersView(cleanArray.get(i)));
            }
              return usersView;
        } catch (DBException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /**
     * Access method to retrieve a list of all active titles.
     * 
     * @return an ArrayList object of TitlesView objects that are defined as active.
     */
    public ArrayList<TitlesView> getAllActiveTitles() {
        try {
            ArrayList<Title> isActive = tb.getActiveTitles();

            ArrayList<TitlesView> titlesView = new ArrayList<TitlesView>();
            for (int j = 0; j < isActive.size(); j++) {
                titlesView.add(new TitlesView(isActive.get(j), false, null));
            }
            return titlesView;

        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidTitlesViewException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Access method to retrieve a list of all completed titles.
     * 
     * @return an ArrayList object of TitlesView objects that are defined as completed.
     */
    public ArrayList<TitlesView> getAllCompletedTitles() {
        ArrayList<Title> allTitles;
        ArrayList<Title> cleanArray = new ArrayList();
        try {
            allTitles = new ArrayList(tb.getAllTitles());

            for (int i = 0; i < allTitles.size(); i++) {
                if (allTitles.get(i).getCompleted() == 1) {
                    cleanArray.add(allTitles.get(i));
                }
            }
            ArrayList<TitlesView> titlesView = new ArrayList<TitlesView>();
            for (int j = 0; j < cleanArray.size(); j++) {
                titlesView.add(new TitlesView(cleanArray.get(j), false, null));
            }
            return titlesView;

        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidTitlesViewException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Method that allows us to view the title information.
     * 
     * @param tId a String array of title IDs whose title information we want to view.
     * @return an ArrayList object of TitlesView objects to view their information.
     */
    public ArrayList<TitlesView> viewTitleInformation(String[] tId) {
        try {
            ArrayList<Integer> titleIds = new ArrayList();
            for (int i = 0; i < tId.length; i++) {
                titleIds.add(Integer.parseInt(tId[i]));
            }
            ArrayList<Title> title = new ArrayList();
            for (int j = 0; j < titleIds.size(); j++) {
                title.add(tb.getTitleById(titleIds.get(j)));
            }
            ArrayList<TitlesView> titlesView = new ArrayList<TitlesView>();
            for (int k = 0; k < title.size(); k++) {
                titlesView.add(new TitlesView(title.get(k), false, null));
            }
            if(titlesView == null){
                return null;
            }
            return titlesView;
        } catch (DBException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidTitlesViewException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
