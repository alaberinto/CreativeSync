/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.CreativeSync.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 731866
 */
@Entity
@Table(name = "title")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Title.findAll", query = "SELECT t FROM Title t")
    , @NamedQuery(name = "Title.findByTitleId", query = "SELECT t FROM Title t WHERE t.titleId = :titleId")
    , @NamedQuery(name = "Title.findByName", query = "SELECT t FROM Title t WHERE t.name = :name")
    , @NamedQuery(name = "Title.findByStartDate", query = "SELECT t FROM Title t WHERE t.startDate = :startDate")
    , @NamedQuery(name = "Title.findByEndDate", query = "SELECT t FROM Title t WHERE t.endDate = :endDate")
    , @NamedQuery(name = "Title.findByIsActive", query = "SELECT t FROM Title t WHERE t.isActive = :isActive")
    , @NamedQuery(name = "Title.findByPriority", query = "SELECT t FROM Title t WHERE t.priority = :priority")
    , @NamedQuery(name = "Title.findByDesignInfo", query = "SELECT t FROM Title t WHERE t.designInfo = :designInfo")})
public class Title implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "title_id")
    private Integer titleId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "is_active")
    private short isActive;
    @Basic(optional = false)
    @Column(name = "priority")
    private short priority;
    @Basic(optional = false)
    @Column(name = "design_info")
    private String designInfo;
    @ManyToMany(mappedBy = "titleCollection")
    private Collection<Account> accountCollection;
    @ManyToMany(mappedBy = "titleCollection")
    private Collection<Genre> genreCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titleId")
    private Collection<Artwork> artworkCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "title")
    private Collection<Asset> assetCollection;

    public Title() {
    }

    public Title(Integer titleId) {
        this.titleId = titleId;
    }

    public Title(Integer titleId, String name, Date startDate, Date endDate, short isActive, short priority, String designInfo) {
        this.titleId = titleId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.priority = priority;
        this.designInfo = designInfo;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }

    public short getPriority() {
        return priority;
    }

    public void setPriority(short priority) {
        this.priority = priority;
    }

    public String getDesignInfo() {
        return designInfo;
    }

    public void setDesignInfo(String designInfo) {
        this.designInfo = designInfo;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @XmlTransient
    public Collection<Genre> getGenreCollection() {
        return genreCollection;
    }

    public void setGenreCollection(Collection<Genre> genreCollection) {
        this.genreCollection = genreCollection;
    }

    @XmlTransient
    public Collection<Artwork> getArtworkCollection() {
        return artworkCollection;
    }

    public void setArtworkCollection(Collection<Artwork> artworkCollection) {
        this.artworkCollection = artworkCollection;
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
        hash += (titleId != null ? titleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Title)) {
            return false;
        }
        Title other = (Title) object;
        if ((this.titleId == null && other.titleId != null) || (this.titleId != null && !this.titleId.equals(other.titleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Title[ titleId=" + titleId + " ]";
    }
    
}
