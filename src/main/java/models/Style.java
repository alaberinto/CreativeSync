/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
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
 * Style.java - Class describing all attributes and operations for a Style Link object.
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
@Table(name = "style")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Style.findAll", query = "SELECT s FROM Style s")
    , @NamedQuery(name = "Style.findByStyleId", query = "SELECT s FROM Style s WHERE s.styleId = :styleId")
    , @NamedQuery(name = "Style.findByStyleDesc", query = "SELECT s FROM Style s WHERE s.styleDesc = :styleDesc")})
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
    @ManyToMany(mappedBy = "styleList")
    private List<Artwork> artworkList;

    public Style() {
    }

    /**
     * COnstructor which takes the below arguments
     * @param styleId the style id
     */
    public Style(Integer styleId) {
        this.styleId = styleId;
    }

    /**
     * COnstructor which takes the below arguments
     * @param styleId the style id
     * @param styleDesc  the style description
     */
    public Style(Integer styleId, String styleDesc) {
        this.styleId = styleId;
        this.styleDesc = styleDesc;
    }

    /**
     * Method to get style id
     * @return style id
     */
    public Integer getStyleId() {
        return styleId;
    }

    /**
     * Method to set style id
     * @param styleId the style id
     */
    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    /**
     * Method to get style description
     * @return style description
     */
    public String getStyleDesc() {
        return styleDesc;
    }

    /**
     * Method to set style description
     * @param styleDesc the style description
     */
    public void setStyleDesc(String styleDesc) {
        this.styleDesc = styleDesc;
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
     * Method to set list of Artwork
     * @param artworkList List of Artwork
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
        hash += (styleId != null ? styleId.hashCode() : 0);
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
        if (!(object instanceof Style)) {
            return false;
        }
        Style other = (Style) object;
        if ((this.styleId == null && other.styleId != null) || (this.styleId != null && !this.styleId.equals(other.styleId))) {
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
        return "models.Style[ styleId=" + styleId + " ]";
    }
    
}
