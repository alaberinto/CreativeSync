/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 697467
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByUserId", query = "SELECT a FROM Account a WHERE a.accountPK.userId = :userId")
    , @NamedQuery(name = "Account.findByPositionId", query = "SELECT a FROM Account a WHERE a.accountPK.positionId = :positionId")
    , @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password")
    , @NamedQuery(name = "Account.findByFirstname", query = "SELECT a FROM Account a WHERE a.firstname = :firstname")
    , @NamedQuery(name = "Account.findByLastname", query = "SELECT a FROM Account a WHERE a.lastname = :lastname")
    , @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email")
    , @NamedQuery(name = "Account.findByLocation", query = "SELECT a FROM Account a WHERE a.location = :location")
    , @NamedQuery(name = "Account.findByRate", query = "SELECT a FROM Account a WHERE a.rate = :rate")
    , @NamedQuery(name = "Account.findByPortfolio", query = "SELECT a FROM Account a WHERE a.portfolio = :portfolio")
    , @NamedQuery(name = "Account.findByIsactive", query = "SELECT a FROM Account a WHERE a.isactive = :isactive")
    , @NamedQuery(name = "Account.findByImagePath", query = "SELECT a FROM Account a WHERE a.imagePath = :imagePath")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccountPK accountPK;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @Column(name = "rate")
    private int rate;
    @Basic(optional = false)
    @Column(name = "portfolio")
    private String portfolio;
    @Basic(optional = false)
    @Column(name = "isactive")
    private short isactive;
    @Basic(optional = false)
    @Column(name = "image_path")
    private String imagePath;
    @ManyToMany(mappedBy = "accountCollection")
    private Collection<MessageGroup> messageGroupCollection;
    @ManyToMany(mappedBy = "accountCollection")
    private Collection<Skillset> skillsetCollection;
    @ManyToMany(mappedBy = "accountCollection")
    private Collection<Title> titleCollection;
    @ManyToMany(mappedBy = "accountCollection")
    private Collection<Language> languageCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private Report report;
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Position position;

    public Account() {
    }

    public Account(AccountPK accountPK) {
        this.accountPK = accountPK;
    }

    public Account(AccountPK accountPK, String password, String firstname, String lastname, String email, String location, int rate, String portfolio, short isactive, String imagePath) {
        this.accountPK = accountPK;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.location = location;
        this.rate = rate;
        this.portfolio = portfolio;
        this.isactive = isactive;
        this.imagePath = imagePath;
    }

    public Account(int userId, int positionId) {
        this.accountPK = new AccountPK(userId, positionId);
    }

    public AccountPK getAccountPK() {
        return accountPK;
    }

    public void setAccountPK(AccountPK accountPK) {
        this.accountPK = accountPK;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
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
    public Collection<MessageGroup> getMessageGroupCollection() {
        return messageGroupCollection;
    }

    public void setMessageGroupCollection(Collection<MessageGroup> messageGroupCollection) {
        this.messageGroupCollection = messageGroupCollection;
    }

    @XmlTransient
    public Collection<Skillset> getSkillsetCollection() {
        return skillsetCollection;
    }

    public void setSkillsetCollection(Collection<Skillset> skillsetCollection) {
        this.skillsetCollection = skillsetCollection;
    }

    @XmlTransient
    public Collection<Title> getTitleCollection() {
        return titleCollection;
    }

    public void setTitleCollection(Collection<Title> titleCollection) {
        this.titleCollection = titleCollection;
    }

    @XmlTransient
    public Collection<Language> getLanguageCollection() {
        return languageCollection;
    }

    public void setLanguageCollection(Collection<Language> languageCollection) {
        this.languageCollection = languageCollection;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
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
        hash += (accountPK != null ? accountPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountPK == null && other.accountPK != null) || (this.accountPK != null && !this.accountPK.equals(other.accountPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Account[ accountPK=" + accountPK + " ]";
    }
    
}
