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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    , @NamedQuery(name = "Feedback.findByFeedbackId", query = "SELECT f FROM Feedback f WHERE f.feedbackPK.feedbackId = :feedbackId")
    , @NamedQuery(name = "Feedback.findByArtworkId", query = "SELECT f FROM Feedback f WHERE f.feedbackPK.artworkId = :artworkId")
    , @NamedQuery(name = "Feedback.findByFeedbackDesc", query = "SELECT f FROM Feedback f WHERE f.feedbackDesc = :feedbackDesc")
    , @NamedQuery(name = "Feedback.findByFeedbackDate", query = "SELECT f FROM Feedback f WHERE f.feedbackDate = :feedbackDate")
    , @NamedQuery(name = "Feedback.findByIsreadDate", query = "SELECT f FROM Feedback f WHERE f.isreadDate = :isreadDate")
    , @NamedQuery(name = "Feedback.findByIsread", query = "SELECT f FROM Feedback f WHERE f.isread = :isread")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FeedbackPK feedbackPK;
    @Basic(optional = false)
    @Column(name = "feedback_desc")
    private String feedbackDesc;
    @Basic(optional = false)
    @Column(name = "feedback_date")
    @Temporal(TemporalType.DATE)
    private Date feedbackDate;
    @Basic(optional = false)
    @Column(name = "isread_date")
    @Temporal(TemporalType.DATE)
    private Date isreadDate;
    @Basic(optional = false)
    @Column(name = "isread")
    private short isread;
    @JoinColumn(name = "artwork_id", referencedColumnName = "artwork_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artwork artwork;

    public Feedback() {
    }

    public Feedback(FeedbackPK feedbackPK) {
        this.feedbackPK = feedbackPK;
    }

    public Feedback(FeedbackPK feedbackPK, String feedbackDesc, Date feedbackDate, Date isreadDate, short isread) {
        this.feedbackPK = feedbackPK;
        this.feedbackDesc = feedbackDesc;
        this.feedbackDate = feedbackDate;
        this.isreadDate = isreadDate;
        this.isread = isread;
    }

    public Feedback(int feedbackId, int artworkId) {
        this.feedbackPK = new FeedbackPK(feedbackId, artworkId);
    }

    public FeedbackPK getFeedbackPK() {
        return feedbackPK;
    }

    public void setFeedbackPK(FeedbackPK feedbackPK) {
        this.feedbackPK = feedbackPK;
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

    public Date getIsreadDate() {
        return isreadDate;
    }

    public void setIsreadDate(Date isreadDate) {
        this.isreadDate = isreadDate;
    }

    public short getIsread() {
        return isread;
    }

    public void setIsread(short isread) {
        this.isread = isread;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackPK != null ? feedbackPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedbackPK == null && other.feedbackPK != null) || (this.feedbackPK != null && !this.feedbackPK.equals(other.feedbackPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Feedback[ feedbackPK=" + feedbackPK + " ]";
    }
<<<<<<< HEAD

=======
    
>>>>>>> origin/master
}
