/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "round_artwork")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoundArtwork.findAll", query = "SELECT r FROM RoundArtwork r")
    , @NamedQuery(name = "RoundArtwork.findByRoundId", query = "SELECT r FROM RoundArtwork r WHERE r.roundId = :roundId")
    , @NamedQuery(name = "RoundArtwork.findByRoundNumber", query = "SELECT r FROM RoundArtwork r WHERE r.roundNumber = :roundNumber")
    , @NamedQuery(name = "RoundArtwork.findByRoundDescription", query = "SELECT r FROM RoundArtwork r WHERE r.roundDescription = :roundDescription")})
public class RoundArtwork implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "round_id")
    private Integer roundId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "round_number")
    private int roundNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "round_description")
    private String roundDescription;
    @ManyToMany(mappedBy = "roundArtworkCollection")
    private Collection<Artwork> artworkCollection;

    public RoundArtwork() {
    }

    public RoundArtwork(Integer roundId) {
        this.roundId = roundId;
    }

    public RoundArtwork(Integer roundId, int roundNumber, String roundDescription) {
        this.roundId = roundId;
        this.roundNumber = roundNumber;
        this.roundDescription = roundDescription;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public String getRoundDescription() {
        return roundDescription;
    }

    public void setRoundDescription(String roundDescription) {
        this.roundDescription = roundDescription;
    }

    @XmlTransient
    public Collection<Artwork> getArtworkCollection() {
        return artworkCollection;
    }

    public void setArtworkCollection(Collection<Artwork> artworkCollection) {
        this.artworkCollection = artworkCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roundId != null ? roundId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoundArtwork)) {
            return false;
        }
        RoundArtwork other = (RoundArtwork) object;
        if ((this.roundId == null && other.roundId != null) || (this.roundId != null && !this.roundId.equals(other.roundId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.RoundArtwork[ roundId=" + roundId + " ]";
    }
    
}
