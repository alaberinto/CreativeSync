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
 * @author 731866
 */
@Embeddable
public class AssetPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "type_id")
    private int typeId;
    @Basic(optional = false)
    @Column(name = "title_id")
    private int titleId;
    @Basic(optional = false)
    @Column(name = "asset_id")
    private int assetId;

    public AssetPK() {
    }

    public AssetPK(int typeId, int titleId, int assetId) {
        this.typeId = typeId;
        this.titleId = titleId;
        this.assetId = assetId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) typeId;
        hash += (int) titleId;
        hash += (int) assetId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssetPK)) {
            return false;
        }
        AssetPK other = (AssetPK) object;
        if (this.typeId != other.typeId) {
            return false;
        }
        if (this.titleId != other.titleId) {
            return false;
        }
        if (this.assetId != other.assetId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.AssetPK[ typeId=" + typeId + ", titleId=" + titleId + ", assetId=" + assetId + " ]";
    }
<<<<<<< HEAD

=======
    
>>>>>>> origin/master
}
