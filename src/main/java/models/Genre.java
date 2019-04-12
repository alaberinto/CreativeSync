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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Genre.java - Class describing all attributes and operations for a Genre object.
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
@Table(name = "genre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g")
    , @NamedQuery(name = "Genre.findByGenreId", query = "SELECT g FROM Genre g WHERE g.genreId = :genreId")
    , @NamedQuery(name = "Genre.findByGenreDesc", query = "SELECT g FROM Genre g WHERE g.genreDesc = :genreDesc")})
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "genre_id")
    private Integer genreId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "genre_desc")
    private String genreDesc;
    @JoinTable(name = "genre_has_title", joinColumns = {
        @JoinColumn(name = "GENRE_genre_id", referencedColumnName = "genre_id")}, inverseJoinColumns = {
        @JoinColumn(name = "TITLE_title_id", referencedColumnName = "title_id")})
    @ManyToMany
    private List<Title> titleList;
    @ManyToMany(mappedBy = "genreList")
    private List<Account> accountList;

    public Genre() {
    }

    /**
     * Constructor which takes the below arguments
     * @param genreId  the genre id
     */
    public Genre(Integer genreId) {
        this.genreId = genreId;
    }

    /**
     * Constructor which takes the below arguments
     * @param genreId the genre id
     * @param genreDesc  the genre description
     */
    public Genre(Integer genreId, String genreDesc) {
        this.genreId = genreId;
        this.genreDesc = genreDesc;
    }

    /**
     * Method to get genre id
     * @return genre id
     */
    public Integer getGenreId() {
        return genreId;
    }

    /**
     * Method to set genre id
     * @param genreId the genre id
     */
    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    /**
     * Method to get genre description
     * @return  the genre description
     */
    public String getGenreDesc() {
        return genreDesc;
    }

    /**
     * Method to set genre description
     * @param genreDesc the genre description
     */
    public void setGenreDesc(String genreDesc) {
        this.genreDesc = genreDesc;
    }

    /**
     * XML transient method to get list of titles
     * @return  List of Title
     */
    @XmlTransient
    public List<Title> getTitleList() {
        return titleList;
    }

    /**
     * Method to set List of Title
     * @param titleList List of Title
     */
    public void setTitleList(List<Title> titleList) {
        this.titleList = titleList;
    }

    /**
     * XML transient method to get list of accounts
     * @return  List of Account
     */
    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    /**
     * Method to set List of accounts
     * @param accountList List of Account
     */
    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genreId != null ? genreId.hashCode() : 0);
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
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.genreId == null && other.genreId != null) || (this.genreId != null && !this.genreId.equals(other.genreId))) {
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
        return "models.Genre[ genreId=" + genreId + " ]";
    }
    
}
