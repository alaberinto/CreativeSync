/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 731866
 */
@Entity
@Table(name = "asset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asset.findAll", query = "SELECT a FROM Asset a")
    , @NamedQuery(name = "Asset.findByTypeId", query = "SELECT a FROM Asset a WHERE a.assetPK.typeId = :typeId")
    , @NamedQuery(name = "Asset.findByTitleId", query = "SELECT a FROM Asset a WHERE a.assetPK.titleId = :titleId")
    , @NamedQuery(name = "Asset.findByAssetId", query = "SELECT a FROM Asset a WHERE a.assetPK.assetId = :assetId")
    , @NamedQuery(name = "Asset.findByTypeRef", query = "SELECT a FROM Asset a WHERE a.typeRef = :typeRef")})
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AssetPK assetPK;
    @Basic(optional = false)
    @Column(name = "type_ref")
    private String typeRef;
    @JoinColumn(name = "type_id", referencedColumnName = "type_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AssetType assetType;
    @JoinColumn(name = "title_id", referencedColumnName = "title_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Title title;

    public Asset() {
    }

    public Asset(AssetPK assetPK) {
        this.assetPK = assetPK;
    }

    public Asset(AssetPK assetPK, String typeRef) {
        this.assetPK = assetPK;
        this.typeRef = typeRef;
    }

    public Asset(int typeId, int titleId, int assetId) {
        this.assetPK = new AssetPK(typeId, titleId, assetId);
    }

    public AssetPK getAssetPK() {
        return assetPK;
    }

    public void setAssetPK(AssetPK assetPK) {
        this.assetPK = assetPK;
    }

    public String getTypeRef() {
        return typeRef;
    }

    public void setTypeRef(String typeRef) {
        this.typeRef = typeRef;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assetPK != null ? assetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.assetPK == null && other.assetPK != null) || (this.assetPK != null && !this.assetPK.equals(other.assetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Asset[ assetPK=" + assetPK + " ]";
    }
<<<<<<< HEAD

=======
    
>>>>>>> origin/master
}
