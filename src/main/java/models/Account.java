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

/**
 *
 * @author Mason
 */
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
    , @NamedQuery(name = "Account.findByPortfolio", query = "SELECT a FROM Account a WHERE a.portfolio = :portfolio")
    , @NamedQuery(name = "Account.findByIsactive", query = "SELECT a FROM Account a WHERE a.isactive = :isactive")
    , @NamedQuery(name = "Account.findByImagePath", query = "SELECT a FROM Account a WHERE a.imagePath = :imagePath")})
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
    @Size(max = 150)
    @Column(name = "portfolio")
    private String portfolio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive")
    private short isactive;
    @Size(max = 200)
    @Column(name = "image_path")
    private String imagePath;
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
    @JoinTable(name = "artwork_has_account", joinColumns = {
        @JoinColumn(name = "ACCOUNT_user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "ARTWORK_artwork_id", referencedColumnName = "artwork_id")})
    @ManyToMany
    private List<Artwork> artworkList;
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

    public Account(Integer userId) {
        this.userId = userId;
    }

    public Account(Integer userId, String password, String firstname, String lastname, String email, double rate, short isactive) {
        this.userId = userId;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.rate = rate;
        this.isactive = isactive;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public short getIsactive() {
        return isactive;
    }

    public void setIsactive(short isactive) {
        this.isactive = isactive;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @XmlTransient
    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    @XmlTransient
    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    @XmlTransient
    public List<Artwork> getArtworkList() {
        return artworkList;
    }

    public void setArtworkList(List<Artwork> artworkList) {
        this.artworkList = artworkList;
    }

    @XmlTransient
    public List<AccountHasMessageGroup> getAccountHasMessageGroupList() {
        return accountHasMessageGroupList;
    }

    public void setAccountHasMessageGroupList(List<AccountHasMessageGroup> accountHasMessageGroupList) {
        this.accountHasMessageGroupList = accountHasMessageGroupList;
    }

    @XmlTransient
    public List<TitleHasAccount> getTitleHasAccountList() {
        return titleHasAccountList;
    }

    public void setTitleHasAccountList(List<TitleHasAccount> titleHasAccountList) {
        this.titleHasAccountList = titleHasAccountList;
    }

    @XmlTransient
    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "models.Account[ userId=" + userId + " ]";
    }
    
}
