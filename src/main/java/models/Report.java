/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Report.java - Class describing all attributes and operations for a Report object.
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
@Table(name = "report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findByReportId", query = "SELECT r FROM Report r WHERE r.reportId = :reportId")
    , @NamedQuery(name = "Report.findByReportStatus", query = "SELECT r FROM Report r WHERE r.reportStatus = :reportStatus")
    , @NamedQuery(name = "Report.findByCreationDate", query = "SELECT r FROM Report r WHERE r.creationDate = :creationDate")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "report_id")
    private Integer reportId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "report_status")
    private String reportStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @JoinColumn(name = "u_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Account uId;

    public Report() {
    }

    /**
     * Constructor which takes the below arguments
     * @param reportId the report id
     */
    public Report(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * Constructor which takes the below arguments
     * @param reportId the report id
     * @param reportStatus the report status
     * @param creationDate the created date
     */
    public Report(Integer reportId, String reportStatus, Date creationDate) {
        this.reportId = reportId;
        this.reportStatus = reportStatus;
        this.creationDate = creationDate;
    }

    /**
     * Method to get report id
     * @return report id
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * Method to set report id
     * @param reportId  the report id
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * Method to get report status
     * @return report status
     */
    public String getReportStatus() {
        return reportStatus;
    }

    /**
     * Method to set report status
     * @param reportStatus the report status
     */
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * Method to get created date
     * @return created date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Method to set creation date
     * @param creationDate the created date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Method to get account
     * @return account
     */
    public Account getUId() {
        return uId;
    }

    /**
     * Method to set account
     * @param uId the account
     */
    public void setUId(Account uId) {
        this.uId = uId;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportId != null ? reportId.hashCode() : 0);
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
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
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
        return "models.Report[ reportId=" + reportId + " ]";
    }
    
}
