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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 587568
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
    , @NamedQuery(name = "Account.findByLocation", query = "SELECT a FROM Account a WHERE a.location = :location")
    , @NamedQuery(name = "Account.findByRate", query = "SELECT a FROM Account a WHERE a.rate = :rate")
    , @NamedQuery(name = "Account.findByPortfolio", query = "SELECT a FROM Account a WHERE a.portfolio = :portfolio")
    , @NamedQuery(name = "Account.findByIsactive", query = "SELECT a FROM Account a WHERE a.isactive = :isactive")
    , @NamedQuery(name = "Account.findByImagePath", query = "SELECT a FROM Account a WHERE a.imagePath = :imagePath")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
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
    @Size(min = 1, max = 100)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rate")
    private int rate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "portfolio")
    private String portfolio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive")
    private short isactive;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "image_path")
    private String imagePath;
    @JoinTable(name = "account_has_message_group", joinColumns = {
        @JoinColumn(name = "ACCOUNT_user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "MESSAGE_GROUP_message_group_id", referencedColumnName = "message_group_id")})
    @ManyToMany
    private Collection<MessageGroup> messageGroupCollection;
    @JoinTable(name = "title_has_account", joinColumns = {
        @JoinColumn(name = "ACCOUNT_user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "TITLE_title_id", referencedColumnName = "title_id")})
    @ManyToMany
    private Collection<Title> titleCollection;
    @JoinTable(name = "account_has_skillset", joinColumns = {
        @JoinColumn(name = "ACCOUNT_user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "SKILLSET_skillset_id", referencedColumnName = "skillset_id")})
    @ManyToMany
    private Collection<Skillset> skillsetCollection;
    @JoinTable(name = "account_has_language", joinColumns = {
        @JoinColumn(name = "ACCOUNT_user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "LANGUAGE_language_id", referencedColumnName = "language_id")})
    @ManyToMany
    private Collection<Language> languageCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private Report report;
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    @ManyToOne(optional = false)
    private Position positionId;

    public Account() {
    }

    public Account(Integer userId) {
        this.userId = userId;
    }

    public Account(Integer userId, String password, String firstname, String lastname, String email, String location, int rate, String portfolio, short isactive, String imagePath) {
        this.userId = userId;
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
    public Collection<Title> getTitleCollection() {
        return titleCollection;
    }

    public void setTitleCollection(Collection<Title> titleCollection) {
        this.titleCollection = titleCollection;
    }

    @XmlTransient
    public Collection<Skillset> getSkillsetCollection() {
        return skillsetCollection;
    }

    public void setSkillsetCollection(Collection<Skillset> skillsetCollection) {
        this.skillsetCollection = skillsetCollection;
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

    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
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
