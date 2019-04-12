/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Genre.java - Class describing all attributes and operations for a Genre object.
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
@Entity
@Table(name = "language")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l")
    , @NamedQuery(name = "Language.findByLanguageId", query = "SELECT l FROM Language l WHERE l.languageId = :languageId")
    , @NamedQuery(name = "Language.findByLanguageName", query = "SELECT l FROM Language l WHERE l.languageName = :languageName")
    , @NamedQuery(name = "Language.findByLanguageCode", query = "SELECT l FROM Language l WHERE l.languageCode = :languageCode")})
public class Language implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "language_id")
    private Integer languageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "language_name")
    private String languageName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "language_code")
    private String languageCode;
    @ManyToMany(mappedBy = "languageList")
    private List<Account> accountList;

    public Language() {
    }

    /**
     * Constructor which takes the below arguments
     * @param languageId  the language id
     */
    public Language(Integer languageId) {
        this.languageId = languageId;
    }

    /**
     * Constructor which takes the below arguments
     * @param languageId the language id
     * @param languageName the language name
     * @param languageCode  the language code
     */
    public Language(Integer languageId, String languageName, String languageCode) {
        this.languageId = languageId;
        this.languageName = languageName;
        this.languageCode = languageCode;
    }

    /**
     * Method to get Language id
     * @return Language id
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * Method to set Language id
     * @param languageId the Language id
     */
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    /**
     * Method to get Language name
     * @return language name
     */
    public String getLanguageName() {
        return languageName;
    }

    /**
     * Method to set language name
     * @param languageName  the language name
     */
    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    /**
     * Method to get language code
     * @return  language code
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * Method to set language code
     * @param languageCode  the language code
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * XML transient method to get list of accounts
     * @return List of Account
     */
    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    /**
     * Method to set list of accounts
     * @param accountList List of Account
     */
    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (languageId != null ? languageId.hashCode() : 0);
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
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        if ((this.languageId == null && other.languageId != null) || (this.languageId != null && !this.languageId.equals(other.languageId))) {
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
        return "models.Language[ languageId=" + languageId + " ]";
    }
    
}
