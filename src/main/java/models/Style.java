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
@Table(name = "style")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Style.findAll", query = "SELECT s FROM Style s"),
    @NamedQuery(name = "Style.findByStyleId", query = "SELECT s FROM Style s WHERE s.styleId = :styleId"),
    @NamedQuery(name = "Style.findByStyleDesc", query = "SELECT s FROM Style s WHERE s.styleDesc = :styleDesc")})
public class Style implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "style_id")
    private Integer styleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "style_desc")
    private String styleDesc;
    @ManyToMany(mappedBy = "styleCollection")
    private Collection<Artwork> artworkCollection;

    public Style() {
    }

    public Style(Integer styleId) {
        this.styleId = styleId;
    }

    public Style(Integer styleId, String styleDesc) {
        this.styleId = styleId;
        this.styleDesc = styleDesc;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getStyleDesc() {
        return styleDesc;
    }

    public void setStyleDesc(String styleDesc) {
        this.styleDesc = styleDesc;
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
        hash += (styleId != null ? styleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Style)) {
            return false;
        }
        Style other = (Style) object;
        if ((this.styleId == null && other.styleId != null) || (this.styleId != null && !this.styleId.equals(other.styleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Style[ styleId=" + styleId + " ]";
    }
    
}
