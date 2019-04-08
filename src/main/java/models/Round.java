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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mason
 */
@Entity
@Table(name = "round")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Round.findAll", query = "SELECT r FROM Round r")
    , @NamedQuery(name = "Round.findByRound", query = "SELECT r FROM Round r WHERE r.round = :round")})
public class Round implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "round")
    private Integer round;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "round")
    private List<Artwork> artworkList;

    public Round() {
    }

    public Round(Integer round) {
        this.round = round;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
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
        hash += (round != null ? round.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Round)) {
            return false;
        }
        Round other = (Round) object;
        if ((this.round == null && other.round != null) || (this.round != null && !this.round.equals(other.round))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Round[ round=" + round + " ]";
    }
    
}
