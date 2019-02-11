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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 697467
 */
@Entity
@Table(name = "message_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageGroup.findAll", query = "SELECT m FROM MessageGroup m")
    , @NamedQuery(name = "MessageGroup.findByMessageGroupId", query = "SELECT m FROM MessageGroup m WHERE m.messageGroupId = :messageGroupId")
    , @NamedQuery(name = "MessageGroup.findByChatlogRef", query = "SELECT m FROM MessageGroup m WHERE m.chatlogRef = :chatlogRef")})
public class MessageGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "message_group_id")
    private Integer messageGroupId;
    @Basic(optional = false)
    @Column(name = "chatlog_ref")
    private String chatlogRef;
    @JoinTable(name = "user_message_group", joinColumns = {
        @JoinColumn(name = "message_group_id", referencedColumnName = "message_group_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToMany
    private Collection<Account> accountCollection;

    public MessageGroup() {
    }

    public MessageGroup(Integer messageGroupId) {
        this.messageGroupId = messageGroupId;
    }

    public MessageGroup(Integer messageGroupId, String chatlogRef) {
        this.messageGroupId = messageGroupId;
        this.chatlogRef = chatlogRef;
    }

    public Integer getMessageGroupId() {
        return messageGroupId;
    }

    public void setMessageGroupId(Integer messageGroupId) {
        this.messageGroupId = messageGroupId;
    }

    public String getChatlogRef() {
        return chatlogRef;
    }

    public void setChatlogRef(String chatlogRef) {
        this.chatlogRef = chatlogRef;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageGroupId != null ? messageGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageGroup)) {
            return false;
        }
        MessageGroup other = (MessageGroup) object;
        if ((this.messageGroupId == null && other.messageGroupId != null) || (this.messageGroupId != null && !this.messageGroupId.equals(other.messageGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.MessageGroup[ messageGroupId=" + messageGroupId + " ]";
    }
    
}
