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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

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
@Table(name = "backup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Backup.findAll", query = "SELECT b FROM Backup b")
    , @NamedQuery(name = "Backup.findByBackupId", query = "SELECT b FROM Backup b WHERE b.backupId = :backupId")
    , @NamedQuery(name = "Backup.findByBackupName", query = "SELECT b FROM Backup b WHERE b.backupName = :backupName")
    , @NamedQuery(name = "Backup.findByBackupDate", query = "SELECT b FROM Backup b WHERE b.backupDate = :backupDate")})
public class Backup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "backup_id")
    private Integer backupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "backup_name")
    private String backupName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "backup_date")
    @Temporal(TemporalType.DATE)
    private Date backupDate;

    public Backup() {
    }

    /**
     * Constructor which takes the below arguments
     * @param backupId the backup id
     */
    public Backup(Integer backupId) {
        this.backupId = backupId;
    }

    /**
     * Constructor which takes the below arguments
     * @param backupId the backup id
     * @param backupName the backup name
     * @param backupDate  the backup date
     */
    public Backup(Integer backupId, String backupName, Date backupDate) {
        this.backupId = backupId;
        this.backupName = backupName;
        this.backupDate = backupDate;
    }

    /**
     * Method to get the backup id
     * @return backup id
     */
    public Integer getBackupId() {
        return backupId;
    }

    /**
     * Method to set backup id
     * @param backupId the backup id
     */
    public void setBackupId(Integer backupId) {
        this.backupId = backupId;
    }

    /**
     * Method to get backup name
     * @return the backup name
     */
    public String getBackupName() {
        return backupName;
    }

    /**
     * Method to set backup name 
     * @param backupName the backup name
     */
    public void setBackupName(String backupName) {
        this.backupName = backupName;
    }

    /**
     * Method to get backup date
     * @return the backup date
     */
    public Date getBackupDate() {
        return backupDate;
    }

    /**
     * Method to set backup date
     * @param backupDate  the backup date
     */
    public void setBackupDate(Date backupDate) {
        this.backupDate = backupDate;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (backupId != null ? backupId.hashCode() : 0);
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
        if (!(object instanceof Backup)) {
            return false;
        }
        Backup other = (Backup) object;
        if ((this.backupId == null && other.backupId != null) || (this.backupId != null && !this.backupId.equals(other.backupId))) {
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
        return "models.Backup[ backupId=" + backupId + " ]";
    }
    
}
