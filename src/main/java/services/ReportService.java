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
import viewModels.UsersView;

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

    public ArrayList<UsersView> viewUserInfo(String[] userIds) {
        ArrayList<Integer> acc = new ArrayList();
        for (int i = 0; i < userIds.length; i++) {
            Integer.parseInt(userIds[i]);
        }
        ArrayList<Account> cleanArray = new ArrayList<>();
        for (int j = 0; j < acc.size(); j++) {
            try {
                cleanArray.add(ab.getUserById(acc.get(j)));
            } catch (DBException ex) {
                Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ArrayList<UsersView> usersView = new ArrayList<UsersView>();

        for (int i = 0; i < cleanArray.size(); i++) {
            usersView.add(new UsersView(cleanArray.get(i)));
        }
        return usersView;

    }

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

    public ArrayList<TitlesView> viewTitleInformation(String[] tId) {
        try {
            ArrayList<Integer> titleIds = new ArrayList();
            for (int i = 0; i < tId.length; i++) {
                Integer.parseInt(tId[i]);
            }
            ArrayList<Title> title = new ArrayList();
            for (int j = 0; j < titleIds.size(); j++) {
                title.add(tb.getTitleById(j));
            }
             ArrayList<TitlesView> titlesView = new ArrayList<TitlesView>();
            for (int k = 0; k < title.size(); k++) {
                titlesView.add(new TitlesView(title.get(k), false, null));
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
