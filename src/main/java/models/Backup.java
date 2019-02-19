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
 *
 * @author 731866, 587568
 */
@Entity
@Table(name = "backup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Backup.findAll", query = "SELECT b FROM Backup b")
    , @NamedQuery(name = "Backup.findByBackupId", query = "SELECT b FROM Backup b WHERE b.backupId = :backupId")
    , @NamedQuery(name = "Backup.findByBackupRef", query = "SELECT b FROM Backup b WHERE b.backupRef = :backupRef")
    , @NamedQuery(name = "Backup.findByBackupDate", query = "SELECT b FROM Backup b WHERE b.backupDate = :backupDate")})
public class Backup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "backup_id")
    private Integer backupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "backup_ref")
    private String backupRef;
    @Basic(optional = false)
    @NotNull
    @Column(name = "backup_date")
    @Temporal(TemporalType.DATE)
    private Date backupDate;

    public Backup() {
    }

    public Backup(Integer backupId) {
        this.backupId = backupId;
    }

    public Backup(Integer backupId, String backupRef, Date backupDate) {
        this.backupId = backupId;
        this.backupRef = backupRef;
        this.backupDate = backupDate;
    }

    public Integer getBackupId() {
        return backupId;
    }

    public void setBackupId(Integer backupId) {
        this.backupId = backupId;
    }

    public String getBackupRef() {
        return backupRef;
    }

    public void setBackupRef(String backupRef) {
        this.backupRef = backupRef;
    }

    public Date getBackupDate() {
        return backupDate;
    }

    public void setBackupDate(Date backupDate) {
        this.backupDate = backupDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (backupId != null ? backupId.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "models.Backup[ backupId=" + backupId + " ]";
    }
    
}
