/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByUserId", query = "SELECT a FROM Account a WHERE a.userId = :userId")
    , @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password")
    , @NamedQuery(name = "Account.findByFirstname", query = "SELECT a FROM Account a WHERE a.firstname = :firstname")
    , @NamedQuery(name = "Account.findByLastname", query = "SELECT a FROM Account a WHERE a.lastname = :lastname")
    , @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email")
    , @NamedQuery(name = "Account.findByRate", query = "SELECT a FROM Account a WHERE a.rate = :rate")
    , @NamedQuery(name = "Account.findByIsactive", query = "SELECT a FROM Account a WHERE a.isactive = :isactive")})




/**
 * Account.java - Class describing all attributes and operations for a Account object.
 * 
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
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lastname")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rate")
    private double rate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive")
    private short isactive;
    @JoinTable(name = "account_has_language", joinColumns = {
        @JoinColumn(name = "ACCOUNT_user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "LANGUAGE_language_id", referencedColumnName = "language_id")})
    @ManyToMany
    private List<Language> languageList;
    @JoinTable(name = "genre_has_account", joinColumns = {
        @JoinColumn(name = "ACCOUNT_user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "GENRE_genre_id", referencedColumnName = "genre_id")})
    @ManyToMany
    private List<Genre> genreList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<AccountHasMessageGroup> accountHasMessageGroupList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<TitleHasAccount> titleHasAccountList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uId")
    private List<Report> reportList;
    @JoinColumn(name = "location", referencedColumnName = "location_id")
    @ManyToOne(optional = false)
    private Location location;
    @JoinColumn(name = "position", referencedColumnName = "position_id")
    @ManyToOne(optional = false)
    private Position position;

    public Account() {
    }

    /**
     * Constructor which takes the userId as argument
     * @param userId the userId of the user Account
     */
    public Account(Integer userId) {
        this.userId = userId;
    }

    /**
     * Constructor which takes the below arguments
     * @param userId the userId of the Account
     * @param password the password of the Account
     * @param firstname the firstName of the Account
     * @param lastname the lastName of the Account
     * @param email the email of the Account
     * @param rate the rate of the Account
     * @param isactive  the status of the Account
     */
    public Account(Integer userId, String password, String firstname, String lastname, String email, double rate, short isactive) {
        this.userId = userId;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.rate = rate;
        this.isactive = isactive;
    }

    /**
     * Access method to get UserId
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Mutator method to set userId
     * @param userId the userId of user account
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Access method to get the password
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Mutator method to get the password
     * @param password  the password of user account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Access method to get the firstName of the user Account
     * @return firstName of UserAccount
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Mutator method to set the firstName of user account
     * @param firstname the firstName of user account
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Access method to get the lastName of the user Account
     * @return lastName of UserAccount
     */
    public String getLastname() {
        return lastname;
    }

     /**
     * Mutator method to set the lastName of user account
     * @param lastname the lastName of user account
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Access method to get the email of user account
     * @return 
     */
    public String getEmail() {
        return email;
    }

    /**
     * Mutator method to set the email
     * @param email  the email of user account
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * access method to get the rate 
     * @return rate of the user account
     */
    public double getRate() {
        return rate;
    }

    /**
     * Mutator method to set the rate of user account
     * @param rate the rate of user account
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * Access method to get the status of user account
     * @return status of the user account
     */
    public short getIsactive() {
        return isactive;
    }

    /**
     * Mutator method to set the status of the user account
     * @param isactive the status of user account
     */
    public void setIsactive(short isactive) {
        this.isactive = isactive;
    }

    /**
     * XMLTransient method to get the language list
     * @return List of Language
     */
    @XmlTransient
    public List<Language> getLanguageList() {
        return languageList;
    }

    /**
     * Mutator method to set the list of languages
     * @param languageList the list of Languages
     */
    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    /**
     * XMLTransient method to get the Genre List
     * @return List of Genre
     */
    @XmlTransient
    public List<Genre> getGenreList() {
        return genreList;
    }

    /**
     * Method to set the List of Genre
     * @param genreList the list of Genre
     */
    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    /**
     * XMLTransient method to get the list of AccountHasMessageGroup
     * @return List of AccountHasMessageGroup
     */
    @XmlTransient
    public List<AccountHasMessageGroup> getAccountHasMessageGroupList() {
        return accountHasMessageGroupList;
    }

    /**
     * Method to set the List of AccountHasMessageGroup
     * @param accountHasMessageGroupList List of AccountHasMessageGroup
     */
    public void setAccountHasMessageGroupList(List<AccountHasMessageGroup> accountHasMessageGroupList) {
        this.accountHasMessageGroupList = accountHasMessageGroupList;
    }

    /**
     * XMLTransient method to get the TitleHasAccount List
     * @return List of TitleHAsAccount
     */
    @XmlTransient
    public List<TitleHasAccount> getTitleHasAccountList() {
        return titleHasAccountList;
    }

    /**
     * Method to set list of TitleHasAccount
     * @param titleHasAccountList  List of titleHasAccount
     */
    public void setTitleHasAccountList(List<TitleHasAccount> titleHasAccountList) {
        this.titleHasAccountList = titleHasAccountList;
    }

    /**
     * XMLTransient method to get List of Reports
     * @return List of Report
     */
    @XmlTransient
    public List<Report> getReportList() {
        return reportList;
    }

    /**
     * Method to set List of Reports
     * @param reportList  List of Reports
     */
    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    /**
     * Method to get the Location
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Method to get location
     * @param location the location of user account
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * method to get position of user account
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Method to set position of user account
     * @param position  Position of user account
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    /**
     * Overridden method of equals
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    /**
     * Overridden method of toString
     * @return 
     */
    @Override
    public String toString() {
        return "models.Account[ userId=" + userId + " ]";
    }
    
}
