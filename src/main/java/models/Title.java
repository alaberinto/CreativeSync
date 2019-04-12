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
 * Title.java - Class describing all attributes and operations for a Title Link object.
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
    private List<Asset> assetList;

    public Title() {
    }

    /**
     * Constructor which takes the below arguments
     * @param titleId the title id
     */
    public Title(Integer titleId) {
        this.titleId = titleId;
    }

    /**
     * Constructor which takes the below arguments
     * @param titleId the title id
     * @param name the name
     * @param startDate the start date
     * @param endDate the end date
     * @param isActive the status of the title
     * @param priority the priority
     * @param designInfo the design info
     * @param numberOfFreelancers the number of freelancers
     * @param designLeadId the design lead id
     * @param coordinatorId the coordinator id
     * @param maxNumberOfFreelancers the max number of freelancers
     * @param completed the status of completion
     */
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

    /**
     * Method to get title id
     * @return title id
     */
    public Integer getTitleId() {
        return titleId;
    }

    /**
     * Method to set title id
     * @param titleId the title id
     */
    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    /**
     * Method to get name
     * @return name of title
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set name
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get start date
     * @return start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Method to set start date
     * @param startDate  the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Method to get end date
     * @return end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Method to set end date
     * @param endDate the end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Method to get the status
     * @return status
     */
    public short getIsActive() {
        return isActive;
    }

    /**
     * Method to set status
     * @param isActive the status
     */
    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }

    /**
     * Method to get priority
     * @return priority
     */
    public short getPriority() {
        return priority;
    }

    /**
     * Method to set priority
     * @param priority the priority
     */
    public void setPriority(short priority) {
        this.priority = priority;
    }

    /**
     * Method to get design info
     * @return design info
     */
    public String getDesignInfo() {
        return designInfo;
    }

    /**
     * Method to set design info
     * @param designInfo the design info
     */
    public void setDesignInfo(String designInfo) {
        this.designInfo = designInfo;
    }

    /**
     * Method to get the number of freeLancers
     * @return number of freelancers 
     */
    public int getNumberOfFreelancers() {
        return numberOfFreelancers;
    }

    /**
     * Method to set number of freelancers
     * @param numberOfFreelancers  the number of freelancers
     */
    public void setNumberOfFreelancers(int numberOfFreelancers) {
        this.numberOfFreelancers = numberOfFreelancers;
    }

    /**
     * Method to get design lead id
     * @return design lead id
     */
    public int getDesignLeadId() {
        return designLeadId;
    }

    /**
     * Method to set design lead id
     * @param designLeadId the design lead id
     */
    public void setDesignLeadId(int designLeadId) {
        this.designLeadId = designLeadId;
    }

    /**
     * Method to get coordinator id
     * @return coordinator id
     */
    public int getCoordinatorId() {
        return coordinatorId;
    }

    /**
     * Method to set coordinator id
     * @param coordinatorId 
     */
    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    /**
     * Method to get max number of freelancers
     * @return max number of freelancers
     */
    public int getMaxNumberOfFreelancers() {
        return maxNumberOfFreelancers;
    }

    /**
     * Method to set max number of freelancers
     * @param maxNumberOfFreelancers 
     */
    public void setMaxNumberOfFreelancers(int maxNumberOfFreelancers) {
        this.maxNumberOfFreelancers = maxNumberOfFreelancers;
    }

    /**
     * Method to get completed
     * @return completed
     */
    public short getCompleted() {
        return completed;
    }

    /**
     * Method to set completed
     * @param completed the completed
     */
    public void setCompleted(short completed) {
        this.completed = completed;
    }

    /**
     * XML transient method to get list of Genre
     * @return List of Genre
     */
    @XmlTransient
    public List<Genre> getGenreList() {
        return genreList;
    }

    /**
     * Method to set genre list
     * @param genreList  List of Genre
     */
    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    /**
     * XML transient method to get list of TitleHasAccount
     * @return List of TitleHasAccount
     */
    @XmlTransient
    public List<TitleHasAccount> getTitleHasAccountList() {
        return titleHasAccountList;
    }

    /**
     * Method to set TitleHasAccount list
     * @param titleHasAccountList  List of TitleHasAccount
     */
    public void setTitleHasAccountList(List<TitleHasAccount> titleHasAccountList) {
        this.titleHasAccountList = titleHasAccountList;
    }

    /**
     * XML transient method to get list of Asset
     * @return List of Asset
     */
    @XmlTransient
    public List<Asset> getAssetList() {
        return assetList;
    }

    /**
     * Method to set Asset list
     * @param assetList  List of Asset
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
        hash += (titleId != null ? titleId.hashCode() : 0);
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
        if (!(object instanceof Title)) {
            return false;
        }
        Title other = (Title) object;
        if ((this.titleId == null && other.titleId != null) || (this.titleId != null && !this.titleId.equals(other.titleId))) {
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
        return "models.Title[ titleId=" + titleId + " ]";
    }
    
}
