/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModels;

import java.util.ArrayList;
import models.Account;
import models.TitleHasAccount;

/**
 * UsersView is a view model class to contain the presentation data for display
 * on the Users.JSP. For each user, the UsersView contains the user's First and Last name,
 * their position, the titles they are working on, and the status of each title.
 * 
 * @author Mason
 */
public class UsersView {
    
    /**
     * The titles that the user is working on.
     */
    private ArrayList<TitleHasAccount> titles;
    
    /**
     * The account of the user.
     */
    private Account user;
    
    /**
     * Non-Default constructor to create a new UsersView object for display on
     * Users.JSP.
     * 
     * @param user The account of the user to be displayed.
     * @param titles The titles associated with the user to be displayed as ongoing or completed.
     */
    public UsersView(Account user, ArrayList<TitleHasAccount> titles) {
        this.titles= titles;
        this.user = user;
    }
    
    /**
     * Non-Default constructor to create a new UsersView object for display on
     * Users.JSP. If the user is not associated with any title, create a new list.
     * 
     * @param user The account of the user to be displayed.
     */
    public UsersView(Account user) {
        this.user = user;
        this.titles = new ArrayList(user.getTitleHasAccountList());
    }

    /**
     * Access method to retrieve the titles associated with the user.
     * 
     * @return an ArrayList object of TitleHasAccount objects to display the titles
     * assigned to the user.
     */
    public ArrayList<TitleHasAccount> getTitles() {
        return titles;
    }

    /**
     * Mutator method that sets the titles associated with the user.
     * 
     * @param titles the titles to be associated with the user.
     */
    public void setTitles(ArrayList<TitleHasAccount> titles) {
        this.titles = titles;
    }

    /**
     * Access method to retrieve the user information.
     * 
     * @return an Account object associated with the user.
     */
    public Account getUser() {
        return user;
    }

    /**
     * Mutator method that sets the user information.
     * 
     * @param user the Account object to be set.
     */
    public void setUser(Account user) {
        this.user = user;
    }   
}
