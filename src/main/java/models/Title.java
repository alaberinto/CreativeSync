/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mason
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
    , @NamedQuery(name = "Title.findByDesignInfo", query = "SELECT t FROM Title t WHERE t.designInfo = :designInfo")
    , @NamedQuery(name = "Title.findByNumberOfFreelancers", query = "SELECT t FROM Title t WHERE t.numberOfFreelancers = :numberOfFreelancers")
    , @NamedQuery(name = "Title.findByDesignLeadId", query = "SELECT t FROM Title t WHERE t.designLeadId = :designLeadId")
    , @NamedQuery(name = "Title.findByCoordinatorId", query = "SELECT t FROM Title t WHERE t.coordinatorId = :coordinatorId")
    , @NamedQuery(name = "Title.findByMaxNumberOfFreelancers", query = "SELECT t FROM Title t WHERE t.maxNumberOfFreelancers = :maxNumberOfFreelancers")
    , @NamedQuery(name = "Title.findByCompleted", query = "SELECT t FROM Title t WHERE t.completed = :completed")})
public class Title implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "title_id")
    private Integer titleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private short isActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "priority")
    private short priority;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "design_info")
    private String designInfo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_freelancers")
    private int numberOfFreelancers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "design_lead_id")
    private int designLeadId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coordinator_id")
    private int coordinatorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_number_of_freelancers")
    private int maxNumberOfFreelancers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "completed")
    private short completed;
    @ManyToMany(mappedBy = "titleList")
    private List<Genre> genreList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "title")
    private List<TitleHasAccount> titleHasAccountList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titleId")
    private List<Artwork> artworkList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titleId")
    private List<Asset> assetList;

    public Title() {
    }

    public Title(Integer titleId) {
        this.titleId = titleId;
    }

    public Title(Integer titleId, String name, Date startDate, Date endDate, short isActive, short priority, String designInfo, int numberOfFreelancers, int designLeadId, int coordinatorId, int maxNumberOfFreelancers, short completed) {
        this.titleId = titleId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.priority = priority;
        this.designInfo = designInfo;
        this.numberOfFreelancers = numberOfFreelancers;
        this.designLeadId = designLeadId;
        this.coordinatorId = coordinatorId;
        this.maxNumberOfFreelancers = maxNumberOfFreelancers;
        this.completed = completed;
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

    public int getNumberOfFreelancers() {
        return numberOfFreelancers;
    }

    public void setNumberOfFreelancers(int numberOfFreelancers) {
        this.numberOfFreelancers = numberOfFreelancers;
    }

    public int getDesignLeadId() {
        return designLeadId;
    }

    public void setDesignLeadId(int designLeadId) {
        this.designLeadId = designLeadId;
    }

    public int getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public int getMaxNumberOfFreelancers() {
        return maxNumberOfFreelancers;
    }

    public void setMaxNumberOfFreelancers(int maxNumberOfFreelancers) {
        this.maxNumberOfFreelancers = maxNumberOfFreelancers;
    }

    public short getCompleted() {
        return completed;
    }

    public void setCompleted(short completed) {
        this.completed = completed;
    }

    @XmlTransient
    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    @XmlTransient
    public List<TitleHasAccount> getTitleHasAccountList() {
        return titleHasAccountList;
    }

    public void setTitleHasAccountList(List<TitleHasAccount> titleHasAccountList) {
        this.titleHasAccountList = titleHasAccountList;
    }

    @XmlTransient
    public List<Artwork> getArtworkList() {
        return artworkList;
    }

    public void setArtworkList(List<Artwork> artworkList) {
        this.artworkList = artworkList;
    }

    @XmlTransient
    public List<Asset> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
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
