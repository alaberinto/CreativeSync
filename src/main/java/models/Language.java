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
 *
 * @author 731866
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

    public Language(Integer languageId) {
        this.languageId = languageId;
    }

    public Language(Integer languageId, String languageName, String languageCode) {
        this.languageId = languageId;
        this.languageName = languageName;
        this.languageCode = languageCode;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (languageId != null ? languageId.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "models.Language[ languageId=" + languageId + " ]";
    }
    
}
