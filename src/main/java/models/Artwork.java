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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author Mason
 */
@Entity
@Table(name = "artwork")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artwork.findAll", query = "SELECT a FROM Artwork a")
    , @NamedQuery(name = "Artwork.findByArtworkId", query = "SELECT a FROM Artwork a WHERE a.artworkId = :artworkId")
    , @NamedQuery(name = "Artwork.findByArtworkName", query = "SELECT a FROM Artwork a WHERE a.artworkName = :artworkName")
    , @NamedQuery(name = "Artwork.findByArtworkRef", query = "SELECT a FROM Artwork a WHERE a.artworkRef = :artworkRef")
    , @NamedQuery(name = "Artwork.findByRating", query = "SELECT a FROM Artwork a WHERE a.rating = :rating")})
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
    @JoinTable(name = "artwork_has_round_artwork", joinColumns = {
        @JoinColumn(name = "ARTWORK_artwork_id", referencedColumnName = "artwork_id")}, inverseJoinColumns = {
        @JoinColumn(name = "ROUND_ARTWORK_round_id", referencedColumnName = "round_id")})
    @ManyToMany
    private Collection<RoundArtwork> roundArtworkCollection;
    @JoinTable(name = "style_has_artwork", joinColumns = {
        @JoinColumn(name = "ARTWORK_artwork_id", referencedColumnName = "artwork_id")}, inverseJoinColumns = {
        @JoinColumn(name = "STYLE_style_id", referencedColumnName = "style_id")})
    @ManyToMany
    private Collection<Style> styleCollection;
    @JoinColumn(name = "title_id", referencedColumnName = "title_id")
    @ManyToOne(optional = false)
    private Title titleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artwork")
    private Collection<Feedback> feedbackCollection;

    public Artwork() {
    }

    public Artwork(Integer artworkId) {
        this.artworkId = artworkId;
    }

    public Artwork(Integer artworkId, String artworkName, String artworkRef, int rating) {
        this.artworkId = artworkId;
        this.artworkName = artworkName;
        this.artworkRef = artworkRef;
        this.rating = rating;
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

    public Title getTitleId() {
        return titleId;
    }

    public void setTitleId(Title titleId) {
        this.titleId = titleId;
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
