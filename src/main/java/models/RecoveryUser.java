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
 * @author Mason
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

    public RecoveryUser(String email) {
        this.email = email;
    }

    public RecoveryUser(String email, Date timeStamp, String recoveryId) {
        this.email = email;
        this.timeStamp = timeStamp;
        this.recoveryId = recoveryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRecoveryId() {
        return recoveryId;
    }

    public void setRecoveryId(String recoveryId) {
        this.recoveryId = recoveryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "models.RecoveryUser[ email=" + email + " ]";
    }
    
}
