/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 731866
 */
@Entity
@Table(name = "feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
    , @NamedQuery(name = "Feedback.findByFeedbackId", query = "SELECT f FROM Feedback f WHERE f.feedbackId = :feedbackId")
    , @NamedQuery(name = "Feedback.findByFeedbackDesc", query = "SELECT f FROM Feedback f WHERE f.feedbackDesc = :feedbackDesc")
    , @NamedQuery(name = "Feedback.findByFeedbackDate", query = "SELECT f FROM Feedback f WHERE f.feedbackDate = :feedbackDate")
    , @NamedQuery(name = "Feedback.findByIsreadDate", query = "SELECT f FROM Feedback f WHERE f.isreadDate = :isreadDate")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "feedback_id")
    private Integer feedbackId;
    @Basic(optional = false)
    @Column(name = "feedback_desc")
    private String feedbackDesc;
    @Column(name = "feedback_date")
    @Temporal(TemporalType.DATE)
    private Date feedbackDate;
    @Basic(optional = false)
    @Column(name = "isread_date")
    private short isreadDate;
    @JoinColumn(name = "artwork_id", referencedColumnName = "artwork_id")
    @ManyToOne(optional = false)
    private Artwork artworkId;

    public Feedback() {
    }

    public Feedback(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Feedback(Integer feedbackId, String feedbackDesc, short isreadDate) {
        this.feedbackId = feedbackId;
        this.feedbackDesc = feedbackDesc;
        this.isreadDate = isreadDate;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackDesc() {
        return feedbackDesc;
    }

    public void setFeedbackDesc(String feedbackDesc) {
        this.feedbackDesc = feedbackDesc;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public short getIsreadDate() {
        return isreadDate;
    }

    public void setIsreadDate(short isreadDate) {
        this.isreadDate = isreadDate;
    }

    public Artwork getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(Artwork artworkId) {
        this.artworkId = artworkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackId != null ? feedbackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedbackId == null && other.feedbackId != null) || (this.feedbackId != null && !this.feedbackId.equals(other.feedbackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Feedback[ feedbackId=" + feedbackId + " ]";
    }
    
}
