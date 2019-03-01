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
@Table(name = "asset_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AssetType.findAll", query = "SELECT a FROM AssetType a")
    , @NamedQuery(name = "AssetType.findByTypeId", query = "SELECT a FROM AssetType a WHERE a.typeId = :typeId")
    , @NamedQuery(name = "AssetType.findByTypeDesc", query = "SELECT a FROM AssetType a WHERE a.typeDesc = :typeDesc")})
public class AssetType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeId;
    @Basic(optional = false)
    @Column(name = "type_desc")
    private String typeDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assetType")
    private Collection<Asset> assetCollection;

    public AssetType() {
    }

    public AssetType(Integer typeId) {
        this.typeId = typeId;
    }

    public AssetType(Integer typeId, String typeDesc) {
        this.typeId = typeId;
        this.typeDesc = typeDesc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @XmlTransient
    public Collection<Asset> getAssetCollection() {
        return assetCollection;
    }

    public void setAssetCollection(Collection<Asset> assetCollection) {
        this.assetCollection = assetCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssetType)) {
            return false;
        }
        AssetType other = (AssetType) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.AssetType[ typeId=" + typeId + " ]";
    }
<<<<<<< HEAD

=======
    
>>>>>>> origin/master
}
