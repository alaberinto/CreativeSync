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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Position.java - Class describing all attributes and operations for a Position object.
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
@Table(name = "position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p")
    , @NamedQuery(name = "Position.findByPositionId", query = "SELECT p FROM Position p WHERE p.positionId = :positionId")
    , @NamedQuery(name = "Position.findByPositionDesc", query = "SELECT p FROM Position p WHERE p.positionDesc = :positionDesc")})
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "position_id")
    private Integer positionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "position_desc")
    private String positionDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
    private List<Account> accountList;

    public Position() {
    }

    /**
     * Constructor which takes the below arguments
     * @param positionId the position id
     */
    public Position(Integer positionId) {
        this.positionId = positionId;
    }

    /**
     * Constructor which takes the below arguments
     * @param positionId the position id
     * @param positionDesc the position description
     */
    public Position(Integer positionId, String positionDesc) {
        this.positionId = positionId;
        this.positionDesc = positionDesc;
    }

    /**
     * Method to get position id
     * @return  position id
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * Method to set position id
     * @param positionId the position id
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    /**
     * Method to get position description
     * @return position description
     */
    public String getPositionDesc() {
        return positionDesc;
    }

    /**
     * Method to set position description
     * @param positionDesc 
     */
    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
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
        hash += (positionId != null ? positionId.hashCode() : 0);
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
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.positionId == null && other.positionId != null) || (this.positionId != null && !this.positionId.equals(other.positionId))) {
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
        return "models.Position[ positionId=" + positionId + " ]";
    }
    
}
