package services;

import dataaccess.DBException;
import dataaccess.LocationBroker;
import dataaccess.PositionBroker;
import dataaccess.RecoveryBroker;
import dataaccess.UserBroker;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;
import models.Genre;
import models.Language;
import models.RecoveryUser;
import models.Title;
import models.TitleHasAccount;
import viewModels.UsersView;

/**
 * AccountService is a service class to process requests to access or mutate
 * Account information.
 *
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 * 
 * @version 1.0
 */
public class AccountService {

    /**
     * Final PositionBroker to handle all Position information in the database.
     */
    private final PositionBroker pb;

    /**
     * Final UserBroker to handle all Account information in the database.
     */
    private final UserBroker ab;

    /**
     * Final HashingBroker to handle all Hashing information in the database.
     */
    private final HashingService hs;

    /**
     * Final LocationBroker to handle all Hashing information in the database.
     */
    private final LocationBroker lb;

    /**
     * Default constructor to create a new instance of AccountService and
     * construct brokers.
     */
    public AccountService() {
        ab = new UserBroker();
        pb = new PositionBroker();
        hs = new HashingService();
        lb = new LocationBroker();
    }

    /**
     * Access method to retrieve an Account from the database by its ID.
     *
     * @param userId The id of the Account to find.
     * @return The Account with the matching ID. OTherwise null.
     * @throws DBException When a database error occurs.
     */
    public Account getUserById(int userId) {
        try {
            return ab.getUserById(userId);
        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Access method to retrieve user Account by their first name.
     * 
     * @param name name of User
     * @return User with matching name
     * @throws DBException When a database error occurs
     */
    public Account getUserByFirstname(String name) throws DBException {
        return ab.getUserByFirstname(name);

    }

    /**
     * Access method to retrieve an ArrayList of all Accounts in the database.
     *
     * @return An ArrayList of users.
     */
    public ArrayList<Account> getAllUsers() {
        return new ArrayList(ab.getAllUsers());
    }

    /**
     * Access method to construct an ArrayList of UsersView objects to be
     * displayed in Users.JSP.
     *
     * This list will contain all the titles and relevant information for each
     * user that this user has accessibility too in the system. See
     * viewModels.UsersView for more information.
     *
     * @param user The Account information for the user currently logged in.
     * @return ArrayList of UsersView
     */
    public ArrayList<UsersView> getUsersView(Account user, String searchFilter) {
        ArrayList<UsersView> views = new ArrayList<>();
        ArrayList<Account> users = null;

        if (!user.getPosition().getPositionDesc().equals("Freelancer")) {
            users = getAllUsers();

            if (users != null) {
                for (int i = users.size() - 1; i >= 0; i--) {
                    if (users.get(i).getPosition().getPositionId() == 1) {
                        users.remove(i);
                    } else if (searchFilter != null) {
                        if (!(users.get(i).getFirstname() + " " + users.get(i).getLastname()).toLowerCase().contains(searchFilter.toLowerCase())
                                && !users.get(i).getPosition().getPositionDesc().toLowerCase().contains(searchFilter.toLowerCase())) {
                            users.remove(i);
                        }
                    }
                }
            }

        } else {
            users = getAllDesignleadsCoordinators();
        }

        for (int i = 0; i < users.size(); i++) {
            views.add(new UsersView(users.get(i), new ArrayList(users.get(i).getTitleHasAccountList())));
        }

        return views;
    }

    /**
     * Access method to retrieve a UsersView object for the specified username.
     * 
     * @param username the username of the Account object to be found.
     * @return null if there is no acc. otherwise, return a UsersView object.
     */
    public UsersView getUsersViewMyAccount(String username) {
        Account acc = getUserByName(username);

        if (acc == null) {
            return null;
        }

        return new UsersView(acc);
    }

    /**
     * Access method to retrieve a list of all active users on the system.
     * 
     * @return an ArrayList object of UsersView objects which are defined as active.
     */
    public ArrayList<UsersView> getAllActiveUsers() {
        ArrayList<Account> allUsers = getAllUsers();
        ArrayList<Account> cleanArray = new ArrayList<>();
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getIsactive() == 1) {
                cleanArray.add(allUsers.get(i));
            }
        }
        ArrayList<UsersView> usersView = new ArrayList<UsersView>();

        for (int i = 0; i < cleanArray.size(); i++) {
            usersView.add(new UsersView(cleanArray.get(i)));
        }
        return usersView;
    }

    /**
     * Access method to get a list of all Accounts that are a Design Lead or
     * Coordinator.
     *
     * @return ArrayList of Accounts that are Design Leads or Coordinators.
     */
    public ArrayList<Account> getAllDesignleadsCoordinators() {

        ArrayList<Account> acc = new ArrayList(ab.getAllUsers());
        ArrayList<Account> cleanArray = new ArrayList<>();

        for (int i = 0; i < acc.size(); i++) {
            if (acc.get(i).getPosition().getPositionId() == 3 || acc.get(i).getPosition().getPositionId() == 2) {
                cleanArray.add(acc.get(i));
            }
        }
        return cleanArray;
    }

    /**
     * Mutator method to validate and process the deleting of an Account.
     *
     * @param userId The userID of the account to delete.
     * @param accountDeleting The Account of the user deleting.
     * @return A feedback message verifying if the delete was successful or not.
     */
    public String delete(Account loggedInUser, Account accountToDelete) {
        TitleService ts = new TitleService();

        //If not allowed
        if (loggedInUser.getPosition().getPositionId() != 1) {
            return "You Do Not Have Privilages To Delete Users!";
        } else {
            try {
                if (accountToDelete == null) {
                    return "User Not Found";
                }

                ArrayList<Title> titlesToUpdate = ts.getTitlesByUser(accountToDelete);

                for (int i = 0; i < titlesToUpdate.size(); i++) {
                    ArrayList<TitleHasAccount> tha = new ArrayList(titlesToUpdate.get(i).getTitleHasAccountList());

                    for (int j = tha.size() - 1; j >= 0; j--) {
                        if (tha.get(j).getAccount().getUserId() == accountToDelete.getUserId()) {
                            titlesToUpdate.get(i).getTitleHasAccountList().remove(j);
                            break;
                        }
                    }
                }

                ab.deleteUser(accountToDelete);
                ts.updateTitles(titlesToUpdate);
            } catch (DBException ex) {
                System.out.println(ex.toString());
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
                return "An error occured deleting this user";
            }
        }

        return null;
    }

    /**
     * Mutator method to validate and process the creation of a new Account.
     *
     * @param positionId The positionId corresponding to the users job Position.
     * @param password The users password.
     * @param firstname The users first name.
     * @param lastname The users last name.
     * @param email The users email. This must be unique.
     * @param locationId The locationId corresponding to the users current
     * country.
     * @param rate The rate that the user is paid in USD. ()
     * @return Null if the insert was sucessfull. Otherwise an error String is
     * returned.
     */
    public String insert(String firstname, String lastname, String email, String rate, String password, String locationId, String[] languageIds, String positionId, String[] genreIds) {
        try {

            if (positionId == null || password == null || firstname == null || lastname == null || email == null || locationId == null || rate == null || genreIds == null) {
                return "Missing information!";
            }
            email = email.toLowerCase();
            if (ab.getUserByEmail(email) != null) {
                return "Email In Use!";
            }

            Integer position = Integer.parseInt(positionId);
            Double userRate = Double.parseDouble(rate);
            Integer location = Integer.parseInt(locationId);
            Short isActive = 1;

            Account ac = new Account(null, hs.generateHash(password), firstname, lastname, email, userRate, isActive);
            ac.setPosition(pb.getPositionById(position));
            ac.setLocation(lb.getLocation(location));

            //Set Genres
            GenreService gs = new GenreService();
            ArrayList<Genre> genres = new ArrayList<>();

            for (int i = 0; i < genreIds.length; i++) {
                genres.add(gs.getGenreById(genreIds[i]));
            }

            //Set languages
            LanguageService ls = new LanguageService();
            ArrayList<Language> languages = new ArrayList<>();

            for (int i = 0; i < languageIds.length; i++) {
                languages.add(ls.getLanguageById(languageIds[i]));
            }

            ac.setGenreList(genres);
            ac.setLanguageList(languages);
            ab.insertUser(ac);

        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return "An error occured!";
        }

        return null;
    }

    /**
     * Mutator method to update the logged in users account.
     *
     * @param ac The Account of the user logged in.
     * @param firstname The new first name to set.
     * @param lastname The new last name to set.
     * @param password The new password to set.
     * @param locationId The new locationId to set.
     * @return null if the update was successful. Otherwise an error String.
     */
    public String updateThisAccount(Account ac, String firstname, String lastname, String password, String locationId) {

        if (password != null) {
            ac.setPassword(hs.generateHash(password));
        }

        ac.setFirstname(firstname);
        ac.setLastname(lastname);

        try {
            ac.setLocation(lb.getLocation(Integer.parseInt(locationId)));
            ab.update(ac);
        } catch (DBException | NumberFormatException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return "Error updating user";
        }

        return null;
    }

    /**
     * Validation method to check if the user has the proper credentials to
     * enter the site. Uses SHA-256.
     *
     * Process: The username and password are entered in Login.JSP and
     * submitted. The users existing hash is retrieved from the Database using
     * their email. Then a new SHA-256 Hash is generated using the email and
     * password. The two hashes are compared.
     *
     * @param email The email entered by the user.
     * @param password The password entered by the user.
     * @return Returns the account if one is found with matching credentials.
     * Otherwise returns null.
     */
    public Account validate(String email, String password) {
        String lowerEmail = email.toLowerCase();
        //Retrieve users existing hash
        String oldHash = getUserHash(lowerEmail);

        if (oldHash == null) {
            return null;
        }

        //Create new hash based on email, password
        String newHash = hs.generateHash(password);

        //Compare hashes. I dont understand why the IDE is making a big deal about this.
        if (oldHash.equals(newHash)) {
            try {
                return ab.getUserByEmail(email);
            } catch (DBException ex) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Access method to take in the entered email and retrieves that users hash
     * from the database.
     *
     * @param email The email of the account to find.
     * @return a String value containing the hashed password input.
     */
    public String getUserHash(String email) {
        try {
            Account user = ab.getUserByEmail(email);

            if (user != null) {
                return user.getPassword();
            }

        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Access method to retrieve a user by there email.
     *
     * @param email The email of the Account to find.
     * @return The account with a matching email. Otherwise null.
     */
    public Account getUserByEmail(String email) {
        try {
            return ab.getUserByEmail(email);
        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Method to create and send an account recovery email to users unable to
     * access there accounts. If the email entered is found. A recovery code is
     * sent to that email address.
     *
     *
     * @param email The email of the account to recover.
     * @param path The location of the email template.
     */
    public void recover(String email, String path) {

        RecoveryBroker rb = new RecoveryBroker();
        EmailService es = new EmailService();
        Account user = getUserByEmail(email);

        if (user != null) {
            try {
                String subject = "Netflix Creative Sync Account Recovery";
                String template = path + "/emailTemplates/recovery.html";
                String code = es.generateCode();

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstname());
                tags.put("lastname", user.getLastname());
                tags.put("code", code);

                RecoveryUser ru = rb.getRecoveryByEmail(email);
                if (ru != null) {
                    rb.deleteRecovery(ru);
                }

                rb.addRecovery(new RecoveryUser(email, new Date(System.currentTimeMillis()), code));
                es.sendMail(email, subject, template, tags);

            } catch (DBException ex) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Method to check if the recovery code entered matches an existing code in
     * the database.
     *
     * @param rid The recovery code entered by the user.
     * @param email The email of the account recovering.
     * @return True if the code is valid. Otherwise false.
     */
    public boolean checkRecovery(String rid, String email) {
        RecoveryBroker rb = new RecoveryBroker();
        try {
            RecoveryUser user = rb.getRecoveryByEmail(email);

            if (user != null) {
                if (user.getRecoveryId().equals(rid)) {
                    return true;
                }
            }
        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Mutator method to update a users password.
     *
     * @param password The new password to set.
     * @param email The email of the account logged in.
     * @return null if the update was successful. Otherwise an error String.
     */
    public String updatePassword(String password, String email) {
        try {
            Account user = ab.getUserByEmail(email);

            HashingService hs = new HashingService();
            password = hs.generateHash(password);
            user.setPassword(password);

            ab.update(user);

            return null;

        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return "Update failed!";
        }
    }

    /**
     * Method to get all active design leads in the database.
     *
     * @return A list of all design leads that are active.
     */
    public ArrayList<Account> getActiveLeads() {
        ArrayList<Account> acc = new ArrayList<>();
        ArrayList<Account> cleanArray = new ArrayList<>();

        acc = getAllUsers();
        for (int i = 0; i < acc.size(); i++) {
            if (acc.get(i).getPosition().getPositionDesc().equals("Design Lead")) {
                if (acc.get(i).getIsactive() == 1) {
                    cleanArray.add(acc.get(i));
                }
            }
        }
        return cleanArray;
    }

    /**
     * Method to get all active coordinators in the database.
     *
     * @return An list of all active coordinators.
     */
    public ArrayList<Account> getActiveCoordinators() {

        ArrayList<Account> acc = new ArrayList<>();
        ArrayList<Account> cleanArray = new ArrayList<>();
        acc = getAllUsers();

        for (int i = 0; i < acc.size(); i++) {
            if (acc.get(i).getPosition().getPositionDesc().equals("Coordinator")) {
                if (acc.get(i).getIsactive() == 1) {
                    cleanArray.add(acc.get(i));
                }
            }
        }
        return cleanArray;

    }

    /**
     * Access method to retrieve a list of active freelancers.
     * 
     * @return an ArrayList object of Account objects of freelancers that are defined as active.
     */
    public ArrayList<Account> getActiveFreelancers() {
        ArrayList<Account> acc = new ArrayList<>();
        ArrayList<Account> cleanArray = new ArrayList<>();
        acc = getAllUsers();

        for (int i = 0; i < acc.size(); i++) {
            if (acc.get(i).getPosition().getPositionDesc().equals("Freelancer")) {
                if (acc.get(i).getIsactive() == 1) {
                    cleanArray.add(acc.get(i));
                }
            }
        }
        return cleanArray;
    }

    /**
     * Access method to retrieve a user by their name.
     * 
     * @param name the name of the user whose account is to be found.
     * @return an Account object that is associated with the name.
     */
    public Account getUserByName(String name) {
        if (name == null) {
            return null;
        }

        String[] names = name.split(" ");

        ArrayList<Account> users = ab.getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getFirstname().toLowerCase().equals(names[0].toLowerCase())
                    && users.get(i).getLastname().toLowerCase().equals(names[1].toLowerCase())) {
                return users.get(i);

            }
        }
        return null;
    }

    /**
     * Access method to retrieve the wanted freelancers by the title specified.
     * 
     * @param title the title that is being worked on by the wanted freelancers.
     * @return an ArrayList object of TitleHasAccount objects that get the Freelancers working on a title.
     */
    public ArrayList<TitleHasAccount> getFreelancersByTitle(Title title) {
        ArrayList<TitleHasAccount> acc = new ArrayList(title.getTitleHasAccountList());

        if (acc != null) {
            for (int i = acc.size() - 1; i >= 0; i--) {
                if (acc.get(i).getAccount().getPosition().getPositionId() != 4) {
                    acc.remove(i);
                }
            }
        }

        return acc;
    }

    /**
     * Access method to retrieve an account and all of its associated Titles.
     * 
     * @param name the name of the user whose account is to be found or located.
     * @return a UsersView object with the Titles associated with the Account object.
     */
    public UsersView getTitlesViewByName(String name) {
        Account acc = this.getUserByName(name);
        return new UsersView(acc, new ArrayList(acc.getTitleHasAccountList()));
    }

    /**
     * Edits a user based on their account information.
     * 
     * @param ac the account object of the user.
     * @param firstname the first name of the user.
     * @param lastname the last name of the user.
     * @param email the email of the user.
     * @param rate the hourly rate paid to the user.
     * @param active denotes if the user is active on the system or not.
     * @param genreIds the IDs of the genres that this user has indicated they work on.
     * @param location the location where the user is currently at.
     * @param languageIds the IDs of the languages that this user has indicated they are proficient in.
     * @param position the position of the user.
     * @return a String object indicating that the user was updated or not.
     */
    public String editUser(Account ac, String firstname, String lastname, String email, String rate, String[] genreIds, String location, String[] languageIds, String position,String password) {

        try {

            //Set Genres
            GenreService gs = new GenreService();
            ArrayList<Genre> genres = new ArrayList<>();

            for (int i = 0; i < genreIds.length; i++) {
                genres.add(gs.getGenreById(genreIds[i]));
            }

            //Set languages
            LanguageService ls = new LanguageService();
            ArrayList<Language> languages = new ArrayList<>();

            for (int i = 0; i < languageIds.length; i++) {
                languages.add(ls.getLanguageById(languageIds[i]));
            }
            if (rate == null) {
                ac.setRate(ac.getRate());
            } else {
                ac.setRate(Double.parseDouble(rate));
            }
            if (position == null) {
                ac.setPosition(ac.getPosition());
            } else {
                Integer posId = Integer.parseInt(position);
                ac.setPosition(pb.getPositionById(posId));
            }

            if (email == null) {
                ac.setEmail(ac.getEmail());
            } else {
                ac.setEmail(email);
            }
            if (position == null) {
                ac.setPosition(ac.getPosition());
            } else {
                ac.setPosition(pb.getPositionById(Integer.parseInt(position)));
            }
            ac.setGenreList(genres);
            ac.setLanguageList(languages);
            ac.setFirstname(firstname);
            ac.setLastname(lastname);
            ac.setLocation(lb.getLocation(Integer.parseInt(location)));
            try {

                ab.update(ac);
            } catch (DBException | NumberFormatException ex) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
                return "Error updating user";
            }

            return "Success";
        } catch (DBException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Updated success";
    }
}
