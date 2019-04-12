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
 * RecoveryUser.java - Class describing all attributes and operations for a RecoveryUser object.
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
@Table(name = "recovery_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecoveryUser.findAll", query = "SELECT r FROM RecoveryUser r")
    , @NamedQuery(name = "RecoveryUser.findByEmail", query = "SELECT r FROM RecoveryUser r WHERE r.email = :email")
    , @NamedQuery(name = "RecoveryUser.findByTimeStamp", query = "SELECT r FROM RecoveryUser r WHERE r.timeStamp = :timeStamp")
    , @NamedQuery(name = "RecoveryUser.findByRecoveryId", query = "SELECT r FROM RecoveryUser r WHERE r.recoveryId = :recoveryId")})
public class RecoveryUser implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "recovery_id")
    private String recoveryId;

    public RecoveryUser() {
    }

    /**
     * Constructor which takes the below arguments
     * @param email the email
     */
    public RecoveryUser(String email) {
        this.email = email;
    }

    /**
     * Constructor which takes the below arguments
     * @param email the email
     * @param timeStamp the timestamp
     * @param recoveryId  the recovery email id
     */
    public RecoveryUser(String email, Date timeStamp, String recoveryId) {
        this.email = email;
        this.timeStamp = timeStamp;
        this.recoveryId = recoveryId;
    }

    /**
     * Method to get email id
     * @return email id
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to set email
     * @param email the email id
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get timestamp
     * @return timestamp
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * Method to set timestamp
     * @param timeStamp the timestamp
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Method to get recovery email id
     * @return recovery email id
     */
    public String getRecoveryId() {
        return recoveryId;
    }

    /**
     * Method to set recovery email id
     * @param recoveryId the recovery email id
     */
    public void setRecoveryId(String recoveryId) {
        this.recoveryId = recoveryId;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
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
        if (!(object instanceof RecoveryUser)) {
            return false;
        }
        RecoveryUser other = (RecoveryUser) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
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
        return "models.RecoveryUser[ email=" + email + " ]";
    }
    
}
