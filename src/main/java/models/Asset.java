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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    , @NamedQuery(name = "Asset.findByAssetId", query = "SELECT a FROM Asset a WHERE a.assetId = :assetId")
    , @NamedQuery(name = "Asset.findByTypeRef", query = "SELECT a FROM Asset a WHERE a.typeRef = :typeRef")})
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "asset_id")
    private Integer assetId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type_ref")
    private String typeRef;
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    @ManyToOne(optional = false)
    private AssetType typeId;
    @JoinColumn(name = "title_id", referencedColumnName = "title_id")
    @ManyToOne(optional = false)
    private Title titleId;

    public Asset() {
    }

    public Asset(Integer assetId) {
        this.assetId = assetId;
    }

    public Asset(Integer assetId, String typeRef) {
        this.assetId = assetId;
        this.typeRef = typeRef;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getTypeRef() {
        return typeRef;
    }

    public void setTypeRef(String typeRef) {
        this.typeRef = typeRef;
    }

    public AssetType getTypeId() {
        return typeId;
    }

    public void setTypeId(AssetType typeId) {
        this.typeId = typeId;
    }

    public Title getTitleId() {
        return titleId;
    }

    public void setTitleId(Title titleId) {
        this.titleId = titleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assetId != null ? assetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.assetId == null && other.assetId != null) || (this.assetId != null && !this.assetId.equals(other.assetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Asset[ assetId=" + assetId + " ]";
    }
    
}
