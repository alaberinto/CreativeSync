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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mason
 */
@Entity
@Table(name = "status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s")
    , @NamedQuery(name = "Status.findByStatusId", query = "SELECT s FROM Status s WHERE s.statusId = :statusId")
    , @NamedQuery(name = "Status.findByStatusDesc", query = "SELECT s FROM Status s WHERE s.statusDesc = :statusDesc")})
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_id")
    private Integer statusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status_desc")
    private String statusDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<TitleHasAccount> titleHasAccountList;

    public Status() {
    }

    public Status(Integer statusId) {
        this.statusId = statusId;
    }

    public Status(Integer statusId, String statusDesc) {
        this.statusId = statusId;
        this.statusDesc = statusDesc;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @XmlTransient
    public List<TitleHasAccount> getTitleHasAccountList() {
        return titleHasAccountList;
    }

    public void setTitleHasAccountList(List<TitleHasAccount> titleHasAccountList) {
        this.titleHasAccountList = titleHasAccountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Status[ statusId=" + statusId + " ]";
    }
    
}