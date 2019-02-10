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
import javax.persistence.Lob;
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
 * @author 731866
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByLocation", query = "SELECT u FROM User u WHERE u.location = :location")
    , @NamedQuery(name = "User.findByRate", query = "SELECT u FROM User u WHERE u.rate = :rate")
    , @NamedQuery(name = "User.findByPortfolio", query = "SELECT u FROM User u WHERE u.portfolio = :portfolio")
    , @NamedQuery(name = "User.findByIsactive", query = "SELECT u FROM User u WHERE u.isactive = :isactive")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
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
    @Column(name = "rate")
    private Integer rate;
    @Column(name = "portfolio")
    private String portfolio;
    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;
    @Basic(optional = false)
    @Column(name = "isactive")
    private short isactive;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<MessageGroup> messageGroupCollection;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<Skillset> skillsetCollection;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<Title> titleCollection;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<Language> languageCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Report report;
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    @ManyToOne(optional = false)
    private Position positionId;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String password, String firstname, String lastname, String email, String location, short isactive) {
        this.userId = userId;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public short getIsactive() {
        return isactive;
    }

    public void setIsactive(short isactive) {
        this.isactive = isactive;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.User[ userId=" + userId + " ]";
    }
    
}
