/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 731866
 */
@Entity
@Table(name = "skillset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skillset.findAll", query = "SELECT s FROM Skillset s")
    , @NamedQuery(name = "Skillset.findBySkillsetId", query = "SELECT s FROM Skillset s WHERE s.skillsetId = :skillsetId")
    , @NamedQuery(name = "Skillset.findBySkillsetDesc", query = "SELECT s FROM Skillset s WHERE s.skillsetDesc = :skillsetDesc")})
public class Skillset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "skillset_id")
    private Integer skillsetId;
    @Basic(optional = false)
    @Column(name = "skillset_desc")
    private String skillsetDesc;
    @JoinTable(name = "user_skillset", joinColumns = {
        @JoinColumn(name = "skillset_id", referencedColumnName = "skillset_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToMany
    private Collection<User> userCollection;

    public Skillset() {
    }

    public Skillset(Integer skillsetId) {
        this.skillsetId = skillsetId;
    }

    public Skillset(Integer skillsetId, String skillsetDesc) {
        this.skillsetId = skillsetId;
        this.skillsetDesc = skillsetDesc;
    }

    public Integer getSkillsetId() {
        return skillsetId;
    }

    public void setSkillsetId(Integer skillsetId) {
        this.skillsetId = skillsetId;
    }

    public String getSkillsetDesc() {
        return skillsetDesc;
    }

    public void setSkillsetDesc(String skillsetDesc) {
        this.skillsetDesc = skillsetDesc;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillsetId != null ? skillsetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skillset)) {
            return false;
        }
        Skillset other = (Skillset) object;
        if ((this.skillsetId == null && other.skillsetId != null) || (this.skillsetId != null && !this.skillsetId.equals(other.skillsetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Skillset[ skillsetId=" + skillsetId + " ]";
    }
    
}
