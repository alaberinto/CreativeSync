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
 *
 * @author Mason
 */
public class UsersView {
    
    private ArrayList<TitleHasAccount> titles;
    private Account user;
    
    public UsersView(Account user, ArrayList<TitleHasAccount> titles) {
        this.titles= titles;
        this.user = user;
    }
    
    public UsersView(Account user) {
        this.user = user;
        this.titles = new ArrayList(user.getTitleHasAccountList());
    }

    public ArrayList<TitleHasAccount> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<TitleHasAccount> titles) {
        this.titles = titles;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }   
}
