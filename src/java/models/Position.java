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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 731866
 */
@Entity
@Table(name = "position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p")
    , @NamedQuery(name = "Position.findByPositionId", query = "SELECT p FROM Position p WHERE p.positionId = :positionId")
    , @NamedQuery(name = "Position.findByPositionDesc", query = "SELECT p FROM Position p WHERE p.positionDesc = :positionDesc")})
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "position_id")
    private Integer positionId;
    @Basic(optional = false)
    @Column(name = "position_desc")
    private String positionDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "positionId")
    private Collection<Account> accountCollection;

    public Position() {
    }

    public Position(Integer positionId) {
        this.positionId = positionId;
    }

    public Position(Integer positionId, String positionDesc) {
        this.positionId = positionId;
        this.positionDesc = positionDesc;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionId != null ? positionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.positionId == null && other.positionId != null) || (this.positionId != null && !this.positionId.equals(other.positionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Position[ positionId=" + positionId + " ]";
    }
<<<<<<< HEAD

=======
    
>>>>>>> origin/master
}
