/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 731866
 */
@Entity
@Table(name = "artwork")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artwork.findAll", query = "SELECT a FROM Artwork a")
    , @NamedQuery(name = "Artwork.findByArtworkId", query = "SELECT a FROM Artwork a WHERE a.artworkId = :artworkId")
    , @NamedQuery(name = "Artwork.findByArtworkName", query = "SELECT a FROM Artwork a WHERE a.artworkName = :artworkName")
    , @NamedQuery(name = "Artwork.findByArtworkRef", query = "SELECT a FROM Artwork a WHERE a.artworkRef = :artworkRef")
    , @NamedQuery(name = "Artwork.findByRating", query = "SELECT a FROM Artwork a WHERE a.rating = :rating")
    , @NamedQuery(name = "Artwork.findByArtworkStatus", query = "SELECT a FROM Artwork a WHERE a.artworkStatus = :artworkStatus")
    , @NamedQuery(name = "Artwork.findByRound", query = "SELECT a FROM Artwork a WHERE a.round = :round")})
public class Artwork implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "artwork_id")
    private Integer artworkId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "artwork_name")
    private String artworkName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "artwork_ref")
    private String artworkRef;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating")
    private int rating;
    @Basic(optional = false)
    @NotNull
    @Column(name = "artwork_status")
    private short artworkStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "round")
    private int round;
    @JoinTable(name = "style_has_artwork", joinColumns = {
        @JoinColumn(name = "ARTWORK_artwork_id", referencedColumnName = "artwork_id")}, inverseJoinColumns = {
        @JoinColumn(name = "STYLE_style_id", referencedColumnName = "style_id")})
    @ManyToMany
    private List<Style> styleList;
    @JoinColumns({
        @JoinColumn(name = "title", referencedColumnName = "TITLE_title_id")
        , @JoinColumn(name = "account", referencedColumnName = "ACCOUNT_user_id")
        , @JoinColumn(name = "status", referencedColumnName = "STATUS_status_id")})
    @ManyToOne(optional = false)
    private TitleHasAccount titleHasAccount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artworkId")
    private List<Feedback> feedbackList;

    public Artwork() {
    }

    public Artwork(Integer artworkId) {
        this.artworkId = artworkId;
    }

    public Artwork(Integer artworkId, String artworkName, String artworkRef, int rating, short artworkStatus, int round) {
        this.artworkId = artworkId;
        this.artworkName = artworkName;
        this.artworkRef = artworkRef;
        this.rating = rating;
        this.artworkStatus = artworkStatus;
        this.round = round;
    }

    public Integer getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(Integer artworkId) {
        this.artworkId = artworkId;
    }

    public String getArtworkName() {
        return artworkName;
    }

    public void setArtworkName(String artworkName) {
        this.artworkName = artworkName;
    }

    public String getArtworkRef() {
        return artworkRef;
    }

    public void setArtworkRef(String artworkRef) {
        this.artworkRef = artworkRef;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public short getArtworkStatus() {
        return artworkStatus;
    }

    public void setArtworkStatus(short artworkStatus) {
        this.artworkStatus = artworkStatus;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    @XmlTransient
    public List<Style> getStyleList() {
        return styleList;
    }

    public void setStyleList(List<Style> styleList) {
        this.styleList = styleList;
    }

    public TitleHasAccount getTitleHasAccount() {
        return titleHasAccount;
    }

    public void setTitleHasAccount(TitleHasAccount titleHasAccount) {
        this.titleHasAccount = titleHasAccount;
    }

    @XmlTransient
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artworkId != null ? artworkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artwork)) {
            return false;
        }
        Artwork other = (Artwork) object;
        if ((this.artworkId == null && other.artworkId != null) || (this.artworkId != null && !this.artworkId.equals(other.artworkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Artwork[ artworkId=" + artworkId + " ]";
    }
    
}
