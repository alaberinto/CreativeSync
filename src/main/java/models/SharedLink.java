/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * SharedLink.java - Class describing all attributes and operations for a Shared Link object.
 * 
 * @author Mason Hill
 * @author Alvin Laberinto
 * @author Cooper Vasiliou
 * @author Arsal Butt
 * @author Brittany Low
 * @author Matthew Carmichael
 * @author Omurbek Kadyrov
 * 
 * @version 1.0r
 */
@Entity
@Table(name = "shared_link")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SharedLink.findAll", query = "SELECT s FROM SharedLink s")
    , @NamedQuery(name = "SharedLink.findBySharedLinkId", query = "SELECT s FROM SharedLink s WHERE s.sharedLinkId = :sharedLinkId")
    , @NamedQuery(name = "SharedLink.findBySharedLink", query = "SELECT s FROM SharedLink s WHERE s.sharedLink = :sharedLink")})
public class SharedLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "shared_link_id")
    private Integer sharedLinkId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "shared_link")
    private String sharedLink;

    public SharedLink() {
    }

    /**
     * Constructor which takes the below arguments
     * @param sharedLinkId the shared link id
     */
    public SharedLink(Integer sharedLinkId) {
        this.sharedLinkId = sharedLinkId;
    }

    /**
     * Constructor which takes the below arguments
     * @param sharedLinkId the shared link id
     * @param sharedLink the shared link
     */
    public SharedLink(Integer sharedLinkId, String sharedLink) {
        this.sharedLinkId = sharedLinkId;
        this.sharedLink = sharedLink;
    }

    /**
     * Method to get shared link id
     * @return shared link id
     */
    public Integer getSharedLinkId() {
        return sharedLinkId;
    }

    /**
     * Method to set shared link id
     * @param sharedLinkId the shared link id
     */
    public void setSharedLinkId(Integer sharedLinkId) {
        this.sharedLinkId = sharedLinkId;
    }

    /**
     * Method to get shared link
     * @return the shared link
     */
    public String getSharedLink() {
        return sharedLink;
    }

    /**
     * Method to set shared link
     * @param sharedLink the shared link
     */
    public void setSharedLink(String sharedLink) {
        this.sharedLink = sharedLink;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sharedLinkId != null ? sharedLinkId.hashCode() : 0);
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
        if (!(object instanceof SharedLink)) {
            return false;
        }
        SharedLink other = (SharedLink) object;
        if ((this.sharedLinkId == null && other.sharedLinkId != null) || (this.sharedLinkId != null && !this.sharedLinkId.equals(other.sharedLinkId))) {
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
        return "models.SharedLink[ sharedLinkId=" + sharedLinkId + " ]";
    }
    
}
