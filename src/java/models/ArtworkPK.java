/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author 697467
 */
@Embeddable
public class ArtworkPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "artwork_id")
    private int artworkId;
    @Basic(optional = false)
    @Column(name = "title_id")
    private int titleId;

    public ArtworkPK() {
    }

    public ArtworkPK(int artworkId, int titleId) {
        this.artworkId = artworkId;
        this.titleId = titleId;
    }

    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) artworkId;
        hash += (int) titleId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtworkPK)) {
            return false;
        }
        ArtworkPK other = (ArtworkPK) object;
        if (this.artworkId != other.artworkId) {
            return false;
        }
        if (this.titleId != other.titleId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.ArtworkPK[ artworkId=" + artworkId + ", titleId=" + titleId + " ]";
    }
    
}
