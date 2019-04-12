package viewModels;

import exceptions.InvalidTitlesViewException;
import java.util.ArrayList;
import java.util.List;
import models.Title;
import models.Account;
import models.Status;
import models.TitleHasAccount;

/**
 * TitlesView is a view model class to contain the presentation data for display
 * on the Titles.JSP. For each title, the TitlesView contains the Title, the
 * lead and coordinator, the status, and if the a boolean tracking if the user
 * logged in is assigned to this title.
 *
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 * @version 1.0
 */
public class TitlesView {

    /**
     * The Title to be displayed.
     */
    private Title title;

    /**
     * The Account of the Design Lead assigned to this title.
     */
    private Account lead;

    /**
     * The Account of the Coordinator assigned to this title.
     */
    private Account coor;

    /**
     * The state tracking if the user logged in is assigned to this title. True
     * if the user is assigned. Otherwise, false.
     */
    private boolean isAssigned;

    /**
     * The Status of this title for the user logged in.
     *
     * If the user is a freelancer, The status is directly associated with that
     * users status on the Title. If the user is a Design Lead, The status
     * reflects the state of the Title across all Freelancers assigned to this
     * title. If any freelancer status is not ongoing, Then the status
     * description should be "Requires Attention".
     *
     * If the user is a Coordinator, The status is ongoing until the title has
     * been posted to management.
     */
    private Status status;

    private ArrayList<Account> freelancers;

    /**
     * Non-Default constructor to create a new TitleView object for display on
     * Titles.JSP.
     *
     * @param title The Title being listed.
     * @param isAssigned Boolean defining if the user viewing the page is
     * currently assigned to the Title.
     * @param status The Status for the user viewing the page for this specific
     * Title.
     * @throws InvalidTitlesViewException Thrown when the title does not have
     * all the required information to be presented.
     */
    public TitlesView(Title title, boolean isAssigned, Status status) throws InvalidTitlesViewException {
        this.title = title;
        this.isAssigned = isAssigned;
        this.status = status;

        int coorId = title.getCoordinatorId();
        int leadId = title.getDesignLeadId();

        this.freelancers = new ArrayList<Account>();

        List<TitleHasAccount> accounts = title.getTitleHasAccountList();

        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i).getAccount();

            if (coorId == accounts.get(i).getAccount().getUserId()) {
                coor = account;
            } else if (leadId == accounts.get(i).getAccount().getUserId()) {
                lead = account;
            }

            if (accounts.get(i).getAccount().getPosition().getPositionId() == 4) {
                freelancers.add(accounts.get(i).getAccount());
            }
        }

        if (lead == null || coor == null) {
            throw new InvalidTitlesViewException();
        }
    }

    /**
     * Access method to get the Status of this TitleView.
     *
     * @return The Status of this TitleView.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Mutator method to set the Status of this TitleView.
     *
     * @param status The Status to set.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Access method to get the Title of this TitleView.
     *
     * @return The Title of this TitleView.
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Mutator method to set the Title of this TitleView.
     *
     * @param title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * Access method to get the Design Lead for this TitleView.
     *
     * @return The Design Lead of this TitleView.
     */
    public Account getLead() {
        return lead;
    }

    /**
     * Mutator method to set the Lead of this TitleView.
     *
     * @param lead The Account to set.
     */
    public void setLead(Account lead) {
        this.lead = lead;
    }

    /**
     * Access method to get the Coordinator for this TitleView.
     *
     * @return The Account of the TitleViews Coordinator.
     */
    public Account getCoor() {
        return coor;
    }

    /**
     * Mutator method to set the Coordinator of this TitleView.
     *
     * @param coor The Account to set.
     */
    public void setCoor(Account coor) {
        this.coor = coor;
    }

    /**
     * Access method to get check if the viewing user is assigned to this
     * TItleView.
     *
     * @return True if the user is assigned.
     */
    public boolean isIsAssigned() {
        return isAssigned;
    }

    /**
     * Mutator method to set the isAssigned status for this TitleView for this
     * user.
     *
     * @param isAssigned The value to set.
     */
    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    /**
     * Accessor method to get Freelancers for this TitleView.
     * @return an ArrayList object of all Account objects for freelancers.
     */
    public ArrayList<Account> getFreelancers() {
        return freelancers;
    }

    /**
     * Mutator method to set Freelancers for this TitleView.
     * @param freelancers an ArrayList object of all Account objects of freelancers to be set.
     */
    public void setFreelancers(ArrayList<Account> freelancers) {
        this.freelancers = freelancers;
    }

}
