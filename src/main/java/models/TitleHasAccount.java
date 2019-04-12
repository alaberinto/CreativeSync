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

    /**
     * Constructor which takes the below arguments
     * @param titleHasAccountPK the TitleHasAccount primary key
     */
    public TitleHasAccount(TitleHasAccountPK titleHasAccountPK) {
        this.titleHasAccountPK = titleHasAccountPK;
    }

    /**
     * Constructor which takes the below arguments
     * @param tITLEtitleid the title id
     * @param aCCOUNTuserid the user id
     * @param sTATUSstatusid the status id
     */
    public TitleHasAccount(int tITLEtitleid, int aCCOUNTuserid, int sTATUSstatusid) {
        this.titleHasAccountPK = new TitleHasAccountPK(tITLEtitleid, aCCOUNTuserid, sTATUSstatusid);
    }

    /**
     * Method to get TitleHasAccount primary key
     * @return TitleHasAccountPK
     */
    public TitleHasAccountPK getTitleHasAccountPK() {
        return titleHasAccountPK;
    }

    /**
     * Method to set TitleHasAccountPK primary key
     * @param titleHasAccountPK the TitleHasAccountPK
     */
    public void setTitleHasAccountPK(TitleHasAccountPK titleHasAccountPK) {
        this.titleHasAccountPK = titleHasAccountPK;
    }

    /**
     * Method to get account
     * @return account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Method to set account
     * @param account  the account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Method to get status
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Method to set status
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Method to get title
     * @return title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Method to set title
     * @param title the title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * XML transient method to get list of Artwork
     * @return List of Artwork
     */
    @XmlTransient
    public List<Artwork> getArtworkList() {
        return artworkList;
    }

    /**
     * Method to set Artwork list
     * @param artworkList  List of Artwork
     */
    public void setArtworkList(List<Artwork> artworkList) {
        this.artworkList = artworkList;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (titleHasAccountPK != null ? titleHasAccountPK.hashCode() : 0);
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
        if (!(object instanceof TitleHasAccount)) {
            return false;
        }
        TitleHasAccount other = (TitleHasAccount) object;
        if ((this.titleHasAccountPK == null && other.titleHasAccountPK != null) || (this.titleHasAccountPK != null && !this.titleHasAccountPK.equals(other.titleHasAccountPK))) {
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
        return "models.TitleHasAccount[ titleHasAccountPK=" + titleHasAccountPK + " ]";
    }
    
}
