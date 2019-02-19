/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.CreativeSync.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author 731866
 */
@Embeddable
public class FeedbackPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "feedback_id")
    private int feedbackId;
    @Basic(optional = false)
    @Column(name = "artwork_id")
    private int artworkId;

    public FeedbackPK() {
    }

    public FeedbackPK(int feedbackId, int artworkId) {
        this.feedbackId = feedbackId;
        this.artworkId = artworkId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) feedbackId;
        hash += (int) artworkId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedbackPK)) {
            return false;
        }
        FeedbackPK other = (FeedbackPK) object;
        if (this.feedbackId != other.feedbackId) {
            return false;
        }
        if (this.artworkId != other.artworkId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.FeedbackPK[ feedbackId=" + feedbackId + ", artworkId=" + artworkId + " ]";
    }
    
}
