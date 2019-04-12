/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 731866
 */
@Embeddable
public class AccountHasMessageGroupPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNT_user_id")
    private int aCCOUNTuserid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MESSAGE_GROUP_message_group_id")
    private int mESSAGEGROUPmessagegroupid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MESSAGE_message_id")
    private int mESSAGEmessageid;

    public AccountHasMessageGroupPK() {
    }

    public AccountHasMessageGroupPK(int aCCOUNTuserid, int mESSAGEGROUPmessagegroupid, int mESSAGEmessageid) {
        this.aCCOUNTuserid = aCCOUNTuserid;
        this.mESSAGEGROUPmessagegroupid = mESSAGEGROUPmessagegroupid;
        this.mESSAGEmessageid = mESSAGEmessageid;
    }

    public int getACCOUNTuserid() {
        return aCCOUNTuserid;
    }

    public void setACCOUNTuserid(int aCCOUNTuserid) {
        this.aCCOUNTuserid = aCCOUNTuserid;
    }

    public int getMESSAGEGROUPmessagegroupid() {
        return mESSAGEGROUPmessagegroupid;
    }

    public void setMESSAGEGROUPmessagegroupid(int mESSAGEGROUPmessagegroupid) {
        this.mESSAGEGROUPmessagegroupid = mESSAGEGROUPmessagegroupid;
    }

    public int getMESSAGEmessageid() {
        return mESSAGEmessageid;
    }

    public void setMESSAGEmessageid(int mESSAGEmessageid) {
        this.mESSAGEmessageid = mESSAGEmessageid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) aCCOUNTuserid;
        hash += (int) mESSAGEGROUPmessagegroupid;
        hash += (int) mESSAGEmessageid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountHasMessageGroupPK)) {
            return false;
        }
        AccountHasMessageGroupPK other = (AccountHasMessageGroupPK) object;
        if (this.aCCOUNTuserid != other.aCCOUNTuserid) {
            return false;
        }
        if (this.mESSAGEGROUPmessagegroupid != other.mESSAGEGROUPmessagegroupid) {
            return false;
        }
        if (this.mESSAGEmessageid != other.mESSAGEmessageid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.AccountHasMessageGroupPK[ aCCOUNTuserid=" + aCCOUNTuserid + ", mESSAGEGROUPmessagegroupid=" + mESSAGEGROUPmessagegroupid + ", mESSAGEmessageid=" + mESSAGEmessageid + " ]";
    }
    
}
