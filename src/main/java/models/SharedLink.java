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
 *
 * @author Mason
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

    public SharedLink(Integer sharedLinkId) {
        this.sharedLinkId = sharedLinkId;
    }

    public SharedLink(Integer sharedLinkId, String sharedLink) {
        this.sharedLinkId = sharedLinkId;
        this.sharedLink = sharedLink;
    }

    public Integer getSharedLinkId() {
        return sharedLinkId;
    }

    public void setSharedLinkId(Integer sharedLinkId) {
        this.sharedLinkId = sharedLinkId;
    }

    public String getSharedLink() {
        return sharedLink;
    }

    public void setSharedLink(String sharedLink) {
        this.sharedLink = sharedLink;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sharedLinkId != null ? sharedLinkId.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "models.SharedLink[ sharedLinkId=" + sharedLinkId + " ]";
    }
    
}
