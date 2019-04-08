/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mason
 */
@Entity
@Table(name = "title_has_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TitleHasAccount.findAll", query = "SELECT t FROM TitleHasAccount t")
    , @NamedQuery(name = "TitleHasAccount.findByTITLEtitleid", query = "SELECT t FROM TitleHasAccount t WHERE t.titleHasAccountPK.tITLEtitleid = :tITLEtitleid")
    , @NamedQuery(name = "TitleHasAccount.findByACCOUNTuserid", query = "SELECT t FROM TitleHasAccount t WHERE t.titleHasAccountPK.aCCOUNTuserid = :aCCOUNTuserid")
    , @NamedQuery(name = "TitleHasAccount.findBySTATUSstatusid", query = "SELECT t FROM TitleHasAccount t WHERE t.titleHasAccountPK.sTATUSstatusid = :sTATUSstatusid")})
public class TitleHasAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TitleHasAccountPK titleHasAccountPK;
    @JoinColumn(name = "ACCOUNT_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Account account;
    @JoinColumn(name = "STATUS_status_id", referencedColumnName = "status_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "TITLE_title_id", referencedColumnName = "title_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Title title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titleHasAccount")
    private List<Artwork> artworkList;

    public TitleHasAccount() {
    }

    public TitleHasAccount(TitleHasAccountPK titleHasAccountPK) {
        this.titleHasAccountPK = titleHasAccountPK;
    }

    public TitleHasAccount(int tITLEtitleid, int aCCOUNTuserid, int sTATUSstatusid) {
        this.titleHasAccountPK = new TitleHasAccountPK(tITLEtitleid, aCCOUNTuserid, sTATUSstatusid);
    }

    public TitleHasAccountPK getTitleHasAccountPK() {
        return titleHasAccountPK;
    }

    public void setTitleHasAccountPK(TitleHasAccountPK titleHasAccountPK) {
        this.titleHasAccountPK = titleHasAccountPK;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @XmlTransient
    public List<Artwork> getArtworkList() {
        return artworkList;
    }

    public void setArtworkList(List<Artwork> artworkList) {
        this.artworkList = artworkList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (titleHasAccountPK != null ? titleHasAccountPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TitleHasAccount)) {
            return false;
        }
        TitleHasAccount other = (TitleHasAccount) object;
        if ((this.titleHasAccountPK == null && other.titleHasAccountPK != null) || (this.titleHasAccountPK != null && !this.titleHasAccountPK.equals(other.titleHasAccountPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.TitleHasAccount[ titleHasAccountPK=" + titleHasAccountPK + " ]";
    }
    
}
