/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mason
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")
    , @NamedQuery(name = "Message.findByMessageId", query = "SELECT m FROM Message m WHERE m.messageId = :messageId")
    , @NamedQuery(name = "Message.findByMessageText", query = "SELECT m FROM Message m WHERE m.messageText = :messageText")
    , @NamedQuery(name = "Message.findByUsersMessage", query = "SELECT m FROM Message m WHERE m.usersMessage = :usersMessage")
    , @NamedQuery(name = "Message.findByTimeOfMessage", query = "SELECT m FROM Message m WHERE m.timeOfMessage = :timeOfMessage")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "message_id")
    private Integer messageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "message_text")
    private String messageText;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "users_message")
    private String usersMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_of_message")
    @Temporal(TemporalType.DATE)
    private Date timeOfMessage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "message")
    private List<AccountHasMessageGroup> accountHasMessageGroupList;

    public Message() {
    }

    public Message(Integer messageId) {
        this.messageId = messageId;
    }

    public Message(Integer messageId, String messageText, String usersMessage, Date timeOfMessage) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.usersMessage = usersMessage;
        this.timeOfMessage = timeOfMessage;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getUsersMessage() {
        return usersMessage;
    }

    public void setUsersMessage(String usersMessage) {
        this.usersMessage = usersMessage;
    }

    public Date getTimeOfMessage() {
        return timeOfMessage;
    }

    public void setTimeOfMessage(Date timeOfMessage) {
        this.timeOfMessage = timeOfMessage;
    }

    @XmlTransient
    public List<AccountHasMessageGroup> getAccountHasMessageGroupList() {
        return accountHasMessageGroupList;
    }

    public void setAccountHasMessageGroupList(List<AccountHasMessageGroup> accountHasMessageGroupList) {
        this.accountHasMessageGroupList = accountHasMessageGroupList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Message[ messageId=" + messageId + " ]";
    }
    
}
