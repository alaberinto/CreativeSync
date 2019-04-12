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
 * Location.java - Class describing all attributes and operations for a Location object.
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
@Table(name = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
    , @NamedQuery(name = "Location.findByLocationId", query = "SELECT l FROM Location l WHERE l.locationId = :locationId")
    , @NamedQuery(name = "Location.findByLocationCode", query = "SELECT l FROM Location l WHERE l.locationCode = :locationCode")
    , @NamedQuery(name = "Location.findByLocationDesc", query = "SELECT l FROM Location l WHERE l.locationDesc = :locationDesc")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "location_id")
    private Integer locationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "location_code")
    private String locationCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "location_desc")
    private String locationDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<Account> accountList;

    public Location() {
    }

    /**
     * Constructor which takes the below arguments
     * @param locationId the location id
     */
    public Location(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * Constructor which takes the below arguments
     * @param locationId the location id
     * @param locationCode the location code
     * @param locationDesc  the location description
     */
    public Location(Integer locationId, String locationCode, String locationDesc) {
        this.locationId = locationId;
        this.locationCode = locationCode;
        this.locationDesc = locationDesc;
    }

    /**
     * Method to get location id
     * @return location id
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * Method to set location id
     * @param locationId the location id
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * Method to get location code
     * @return location code
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * Method to set location code
     * @param locationCode the location code
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    /**
     * Method to get location description
     * @return location description
     */
    public String getLocationDesc() {
        return locationDesc;
    }

    /**
     * Method to set location description
     * @param locationDesc the location description
     */
    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
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
        hash += (locationId != null ? locationId.hashCode() : 0);
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
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
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
        return "models.Location[ locationId=" + locationId + " ]";
    }
    
}
