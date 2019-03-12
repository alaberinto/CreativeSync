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
 *
 * @author Mason
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

    public Genre(Integer genreId) {
        this.genreId = genreId;
    }

    public Genre(Integer genreId, String genreDesc) {
        this.genreId = genreId;
        this.genreDesc = genreDesc;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getGenreDesc() {
        return genreDesc;
    }

    public void setGenreDesc(String genreDesc) {
        this.genreDesc = genreDesc;
    }

    @XmlTransient
    public List<Title> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<Title> titleList) {
        this.titleList = titleList;
    }

    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genreId != null ? genreId.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "models.Genre[ genreId=" + genreId + " ]";
    }
    
}
