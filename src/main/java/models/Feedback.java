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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Feedback.java - Class describing all attributes and operations for a Feedback object.
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
@Table(name = "feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
    , @NamedQuery(name = "Feedback.findByFeedbackId", query = "SELECT f FROM Feedback f WHERE f.feedbackId = :feedbackId")
    , @NamedQuery(name = "Feedback.findByFeedbackDesc", query = "SELECT f FROM Feedback f WHERE f.feedbackDesc = :feedbackDesc")
    , @NamedQuery(name = "Feedback.findByFeedbackDate", query = "SELECT f FROM Feedback f WHERE f.feedbackDate = :feedbackDate")
    , @NamedQuery(name = "Feedback.findByIsreadDate", query = "SELECT f FROM Feedback f WHERE f.isreadDate = :isreadDate")
    , @NamedQuery(name = "Feedback.findByIsread", query = "SELECT f FROM Feedback f WHERE f.isread = :isread")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feedback_id")
    private Integer feedbackId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "feedback_desc")
    private String feedbackDesc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feedback_date")
    @Temporal(TemporalType.DATE)
    private Date feedbackDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isread_date")
    @Temporal(TemporalType.DATE)
    private Date isreadDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isread")
    private short isread;
    @JoinColumn(name = "artwork_id", referencedColumnName = "artwork_id")
    @ManyToOne(optional = false)
    private Artwork artworkId;

    public Feedback() {
    }

    /**
     * Constructor which takes the below arguments
     * @param feedbackId the feedback id
     */
    public Feedback(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * Constructor which takes the below arguments
     * @param feedbackId the feedback id
     * @param feedbackDesc the feedback description
     * @param feedbackDate the feedback date
     * @param isreadDate the read date
     * @param isread is read status
     */
    public Feedback(Integer feedbackId, String feedbackDesc, Date feedbackDate, Date isreadDate, short isread) {
        this.feedbackId = feedbackId;
        this.feedbackDesc = feedbackDesc;
        this.feedbackDate = feedbackDate;
        this.isreadDate = isreadDate;
        this.isread = isread;
    }

    /**
     * Method to get feedback id
     * @return feedback id
     */
    public Integer getFeedbackId() {
        return feedbackId;
    }

    /**
     * Method to set feedback id
     * @param feedbackId  the feedback id
     */
    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * Method to get feedback description
     * @return feedback description
     */
    public String getFeedbackDesc() {
        return feedbackDesc;
    }

    /**
     * Method to set feedback description
     * @param feedbackDesc  the feedback description
     */
    public void setFeedbackDesc(String feedbackDesc) {
        this.feedbackDesc = feedbackDesc;
    }

    /**
     * Method to get feedback date
     * @return  the feedback date
     */
    public Date getFeedbackDate() {
        return feedbackDate;
    }

    /**
     * Method to set feedback date
     * @param feedbackDate the feedback date
     */
    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    /**
     * Method to return the read date
     * @return read date
     */
    public Date getIsreadDate() {
        return isreadDate;
    }

    /**
     * Method to set read date
     * @param isreadDate  the read date
     */
    public void setIsreadDate(Date isreadDate) {
        this.isreadDate = isreadDate;
    }

    /**
     * Method to get read status
     * @return status of read
     */
    public short getIsread() {
        return isread;
    }

    /**
     * Method to set read status
     * @param isread the status of read
     */
    public void setIsread(short isread) {
        this.isread = isread;
    }

    /**
     * Method to get artwork id
     * @return artwork id
     */
    public Artwork getArtworkId() {
        return artworkId;
    }

    /**
     * Method to set artwork id
     * @param artworkId the artwork id
     */
    public void setArtworkId(Artwork artworkId) {
        this.artworkId = artworkId;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackId != null ? feedbackId.hashCode() : 0);
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
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedbackId == null && other.feedbackId != null) || (this.feedbackId != null && !this.feedbackId.equals(other.feedbackId))) {
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
        return "models.Feedback[ feedbackId=" + feedbackId + " ]";
    }
    
}
