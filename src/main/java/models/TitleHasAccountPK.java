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
 * TitleHasAccount.java - Class describing all attributes and operations for a TitleHasAccount Link object.
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
@Embeddable
public class TitleHasAccountPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "TITLE_title_id")
    private int tITLEtitleid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNT_user_id")
    private int aCCOUNTuserid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS_status_id")
    private int sTATUSstatusid;

    public TitleHasAccountPK() {
    }

    /**
     * Constructor which takes the below arguments
     * @param tITLEtitleid the title id
     * @param aCCOUNTuserid the user id
     * @param sTATUSstatusid the status id
     */
    public TitleHasAccountPK(int tITLEtitleid, int aCCOUNTuserid, int sTATUSstatusid) {
        this.tITLEtitleid = tITLEtitleid;
        this.aCCOUNTuserid = aCCOUNTuserid;
        this.sTATUSstatusid = sTATUSstatusid;
    }

    /**
     * Method to get title id
     * @return title id
     */
    public int getTITLEtitleid() {
        return tITLEtitleid;
    }

    /**
     * Method to set title id
     * @param tITLEtitleid the title id
     */
    public void setTITLEtitleid(int tITLEtitleid) {
        this.tITLEtitleid = tITLEtitleid;
    }

    /**
     * Method to get account user id
     * @return account user id
     */
    public int getACCOUNTuserid() {
        return aCCOUNTuserid;
    }

    /**
     * Method to set account user id
     * @param aCCOUNTuserid the account user id
     */
    public void setACCOUNTuserid(int aCCOUNTuserid) {
        this.aCCOUNTuserid = aCCOUNTuserid;
    }

    /**
     * Method to get status id
     * @return status id
     */
    public int getSTATUSstatusid() {
        return sTATUSstatusid;
    }

    /**
     * Method to set status id
     * @param sTATUSstatusid  the status id
     */
    public void setSTATUSstatusid(int sTATUSstatusid) {
        this.sTATUSstatusid = sTATUSstatusid;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tITLEtitleid;
        hash += (int) aCCOUNTuserid;
        hash += (int) sTATUSstatusid;
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
        if (!(object instanceof TitleHasAccountPK)) {
            return false;
        }
        TitleHasAccountPK other = (TitleHasAccountPK) object;
        if (this.tITLEtitleid != other.tITLEtitleid) {
            return false;
        }
        if (this.aCCOUNTuserid != other.aCCOUNTuserid) {
            return false;
        }
        if (this.sTATUSstatusid != other.sTATUSstatusid) {
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
        return "models.TitleHasAccountPK[ tITLEtitleid=" + tITLEtitleid + ", aCCOUNTuserid=" + aCCOUNTuserid + ", sTATUSstatusid=" + sTATUSstatusid + " ]";
    }
    
}
