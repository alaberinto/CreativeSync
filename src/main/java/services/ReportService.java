/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author 731866
 */
public class ReportService {

    private UserBroker ab;
    private TitleBroker tb;

    public ReportService() {
        ab = new UserBroker();
        tb = new TitleBroker();
    }

    public ArrayList<Account> getAllUsers() {
        return new ArrayList(ab.getAllUsers());
    }

    public ArrayList<Account> getAllActiveUsers() {
        ArrayList<Account> allUsers = getAllUsers();
        ArrayList<Account> cleanArray = new ArrayList<>();
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getIsactive() == 1) {
                cleanArray.add(allUsers.get(i));
            }
        }
        return cleanArray;

    }

    public ArrayList<Account> viewUserByPosition(String[] positionId) {
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
        return cleanArray;

    }

    public ArrayList<Account> viewUserInfo(String firstname, String lastname, String email) {
        ArrayList<Account> acc = getAllUsers();
        ArrayList<Account> cleanArray = new ArrayList<>();
        for (int i = 0; i < acc.size(); i++) {
            if (acc.get(i).getFirstname().equals(firstname) && acc.get(i).getLastname().equals(lastname) && acc.get(i).getEmail().equals(email)) {
                cleanArray.add(acc.get(i));
            }

        }
        return cleanArray;

    }

    public ArrayList<Title> getAllActiveTitles() {
        try {
            ArrayList<Title> isActive = tb.getActiveTitles();
            return isActive;
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<Title> getAllCompletedTitles() {
        ArrayList<Title> allTitles;
        ArrayList<Title> cleanArray = new ArrayList();
        try {
            allTitles = new ArrayList(tb.getAllTitles());
            for (int i = 0; i < allTitles.size(); i++) {
                if (allTitles.get(i).getCompleted() == 0) {
                    cleanArray.add(allTitles.get(i));
                }
            }
            return cleanArray;
        } catch (DBException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public TitlesView viewTitleInformation(String tId) {

        try {

            Integer titleId = Integer.parseInt(tId);
            Title title = tb.getTitleById(titleId);
            TitlesView tv = new TitlesView(title, true, null);
            return tv;

        } catch (DBException | InvalidTitlesViewException ex) {
            Logger.getLogger(TitleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
