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
 * AssetType.java - Class describing all attributes and operations for a AssetType object.
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
@Table(name = "asset_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AssetType.findAll", query = "SELECT a FROM AssetType a")
    , @NamedQuery(name = "AssetType.findByTypeId", query = "SELECT a FROM AssetType a WHERE a.typeId = :typeId")
    , @NamedQuery(name = "AssetType.findByTypeDesc", query = "SELECT a FROM AssetType a WHERE a.typeDesc = :typeDesc")})
public class AssetType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "type_desc")
    private String typeDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
    private List<Asset> assetList;

    public AssetType() {
    }

    /**
     * Constructor which take the below arguments
     * @param typeId the typeId
     */
    public AssetType(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * Constructor which take the below arguments
     * @param typeId the type id
     * @param typeDesc  the type description
     */
    public AssetType(Integer typeId, String typeDesc) {
        this.typeId = typeId;
        this.typeDesc = typeDesc;
    }

    /**
     * Method to get type id
     * @return typeId
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * Method to set type id
     * @param typeId  the typeId
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * Method to get type description
     * @return  the typeDesc
     */
    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * Method to set type description
     * @param typeDesc  the type description
     */
    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    /**
     * XML transient method to the asset list
     * @return  List of asset
     */
    @XmlTransient
    public List<Asset> getAssetList() {
        return assetList;
    }

    /**
     * Method to set list of asset
     * @param assetList the List of Asset
     */
    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
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
        if (!(object instanceof AssetType)) {
            return false;
        }
        AssetType other = (AssetType) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
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
        return "models.AssetType[ typeId=" + typeId + " ]";
    }
    
}
