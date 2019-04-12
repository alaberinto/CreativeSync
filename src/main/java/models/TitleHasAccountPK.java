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

    public TitleHasAccountPK(int tITLEtitleid, int aCCOUNTuserid, int sTATUSstatusid) {
        this.tITLEtitleid = tITLEtitleid;
        this.aCCOUNTuserid = aCCOUNTuserid;
        this.sTATUSstatusid = sTATUSstatusid;
    }

    public int getTITLEtitleid() {
        return tITLEtitleid;
    }

    public void setTITLEtitleid(int tITLEtitleid) {
        this.tITLEtitleid = tITLEtitleid;
    }

    public int getACCOUNTuserid() {
        return aCCOUNTuserid;
    }

    public void setACCOUNTuserid(int aCCOUNTuserid) {
        this.aCCOUNTuserid = aCCOUNTuserid;
    }

    public int getSTATUSstatusid() {
        return sTATUSstatusid;
    }

    public void setSTATUSstatusid(int sTATUSstatusid) {
        this.sTATUSstatusid = sTATUSstatusid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tITLEtitleid;
        hash += (int) aCCOUNTuserid;
        hash += (int) sTATUSstatusid;
        return hash;
    }

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

    @Override
    public String toString() {
        return "models.TitleHasAccountPK[ tITLEtitleid=" + tITLEtitleid + ", aCCOUNTuserid=" + aCCOUNTuserid + ", sTATUSstatusid=" + sTATUSstatusid + " ]";
    }
    
}
