/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 697467
 */
@Entity
@Table(name = "artwork")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artwork.findAll", query = "SELECT a FROM Artwork a")
    , @NamedQuery(name = "Artwork.findByArtworkId", query = "SELECT a FROM Artwork a WHERE a.artworkPK.artworkId = :artworkId")
    , @NamedQuery(name = "Artwork.findByTitleId", query = "SELECT a FROM Artwork a WHERE a.artworkPK.titleId = :titleId")
    , @NamedQuery(name = "Artwork.findByArtworkName", query = "SELECT a FROM Artwork a WHERE a.artworkName = :artworkName")
    , @NamedQuery(name = "Artwork.findByArtworkRef", query = "SELECT a FROM Artwork a WHERE a.artworkRef = :artworkRef")
    , @NamedQuery(name = "Artwork.findByRating", query = "SELECT a FROM Artwork a WHERE a.rating = :rating")})
public class Artwork implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArtworkPK artworkPK;
    @Basic(optional = false)
    @Column(name = "artwork_name")
    private String artworkName;
    @Basic(optional = false)
    @Column(name = "artwork_ref")
    private String artworkRef;
    @Basic(optional = false)
    @Column(name = "rating")
    private int rating;
    @JoinTable(name = "round", joinColumns = {
        @JoinColumn(name = "artwork_id", referencedColumnName = "artwork_id")}, inverseJoinColumns = {
        @JoinColumn(name = "round_id", referencedColumnName = "round_id")})
    @ManyToMany
    private Collection<RoundArtwork> roundArtworkCollection;
    @JoinTable(name = "artwork_style", joinColumns = {
        @JoinColumn(name = "artwork_id", referencedColumnName = "artwork_id")}, inverseJoinColumns = {
        @JoinColumn(name = "style_id", referencedColumnName = "style_id")})
    @ManyToMany
    private Collection<Style> styleCollection;
    @JoinColumn(name = "title_id", referencedColumnName = "title_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Title title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artworkId")
    private Collection<Feedback> feedbackCollection;

    public Artwork() {
    }

    public Artwork(ArtworkPK artworkPK) {
        this.artworkPK = artworkPK;
    }

    public Artwork(ArtworkPK artworkPK, String artworkName, String artworkRef, int rating) {
        this.artworkPK = artworkPK;
        this.artworkName = artworkName;
        this.artworkRef = artworkRef;
        this.rating = rating;
    }

    public Artwork(int artworkId, int titleId) {
        this.artworkPK = new ArtworkPK(artworkId, titleId);
    }

    public ArtworkPK getArtworkPK() {
        return artworkPK;
    }

    public void setArtworkPK(ArtworkPK artworkPK) {
        this.artworkPK = artworkPK;
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

    @XmlTransient
    public Collection<RoundArtwork> getRoundArtworkCollection() {
        return roundArtworkCollection;
    }

    public void setRoundArtworkCollection(Collection<RoundArtwork> roundArtworkCollection) {
        this.roundArtworkCollection = roundArtworkCollection;
    }

    @XmlTransient
    public Collection<Style> getStyleCollection() {
        return styleCollection;
    }

    public void setStyleCollection(Collection<Style> styleCollection) {
        this.styleCollection = styleCollection;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @XmlTransient
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artworkPK != null ? artworkPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artwork)) {
            return false;
        }
        Artwork other = (Artwork) object;
        if ((this.artworkPK == null && other.artworkPK != null) || (this.artworkPK != null && !this.artworkPK.equals(other.artworkPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Artwork[ artworkPK=" + artworkPK + " ]";
    }
    
}
