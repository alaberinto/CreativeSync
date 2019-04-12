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
 * DropboxToken.java - Class describing all attributes and operations for a DropboxToken object.
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
@Table(name = "dropbox_token")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DropboxToken.findAll", query = "SELECT d FROM DropboxToken d")
    , @NamedQuery(name = "DropboxToken.findByTokenId", query = "SELECT d FROM DropboxToken d WHERE d.tokenId = :tokenId")})
public class DropboxToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TOKEN_ID")
    private String tokenId;

    public DropboxToken() {
    }

    /**
     * Constructor which takes the below arguments
     * @param tokenId  the token id
     */
    public DropboxToken(String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * Method to get token id
     * @return the token id
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * Method to set token id
     * @param tokenId  the token in
     */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tokenId != null ? tokenId.hashCode() : 0);
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
        if (!(object instanceof DropboxToken)) {
            return false;
        }
        DropboxToken other = (DropboxToken) object;
        if ((this.tokenId == null && other.tokenId != null) || (this.tokenId != null && !this.tokenId.equals(other.tokenId))) {
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
        return "models.DropboxToken[ tokenId=" + tokenId + " ]";
    }

}
