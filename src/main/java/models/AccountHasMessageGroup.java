/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * AccountHasMessagegroup.java - Class describing all attributes and operations for a Account object.
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
@Table(name = "account_has_message_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountHasMessageGroup.findAll", query = "SELECT a FROM AccountHasMessageGroup a")
    , @NamedQuery(name = "AccountHasMessageGroup.findByACCOUNTuserid", query = "SELECT a FROM AccountHasMessageGroup a WHERE a.accountHasMessageGroupPK.aCCOUNTuserid = :aCCOUNTuserid")
    , @NamedQuery(name = "AccountHasMessageGroup.findByMESSAGEGROUPmessagegroupid", query = "SELECT a FROM AccountHasMessageGroup a WHERE a.accountHasMessageGroupPK.mESSAGEGROUPmessagegroupid = :mESSAGEGROUPmessagegroupid")
    , @NamedQuery(name = "AccountHasMessageGroup.findByMESSAGEmessageid", query = "SELECT a FROM AccountHasMessageGroup a WHERE a.accountHasMessageGroupPK.mESSAGEmessageid = :mESSAGEmessageid")})
public class AccountHasMessageGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccountHasMessageGroupPK accountHasMessageGroupPK;
    @JoinColumn(name = "ACCOUNT_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Account account;
    @JoinColumn(name = "MESSAGE_message_id", referencedColumnName = "message_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Message message;
    @JoinColumn(name = "MESSAGE_GROUP_message_group_id", referencedColumnName = "message_group_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MessageGroup messageGroup;

    public AccountHasMessageGroup() {
    }

    /**
     * Constructor which takes AccountHasMessageGroupPK primary key as argument
     * @param accountHasMessageGroupPK 
     */
    public AccountHasMessageGroup(AccountHasMessageGroupPK accountHasMessageGroupPK) {
        this.accountHasMessageGroupPK = accountHasMessageGroupPK;
    }

    /**
     * Constructor which takes the below arguments
     * @param aCCOUNTuserid the userid of account
     * @param mESSAGEGROUPmessagegroupid messageGroup id
     * @param mESSAGEmessageid the message id 
     */
    public AccountHasMessageGroup(int aCCOUNTuserid, int mESSAGEGROUPmessagegroupid, int mESSAGEmessageid) {
        this.accountHasMessageGroupPK = new AccountHasMessageGroupPK(aCCOUNTuserid, mESSAGEGROUPmessagegroupid, mESSAGEmessageid);
    }

    /**
     * Method to return AccountHasMessageGroupPK primary key
     * @return AccountHasMessageGroupPK
     */
    public AccountHasMessageGroupPK getAccountHasMessageGroupPK() {
        return accountHasMessageGroupPK;
    }

    /**
     * Method to set AccountHasMessageGroupPK primary key
     * @param accountHasMessageGroupPK the AccountHasMessageGroupPK
     */
    public void setAccountHasMessageGroupPK(AccountHasMessageGroupPK accountHasMessageGroupPK) {
        this.accountHasMessageGroupPK = accountHasMessageGroupPK;
    }

    /**
     * Method to get the user account
     * @return Account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Method to set Account
     * @param account  the Account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Method to get Message
     * @return Message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Method to set message 
     * @param message the Message
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Method to get message group
     * @return MessageGroup
     */
    public MessageGroup getMessageGroup() {
        return messageGroup;
    }

    /**
     * Method to set message group
     * @param messageGroup  the MessageGroup
     */
    public void setMessageGroup(MessageGroup messageGroup) {
        this.messageGroup = messageGroup;
    }

    /**
     * Overridden method of hashCode
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountHasMessageGroupPK != null ? accountHasMessageGroupPK.hashCode() : 0);
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
        if (!(object instanceof AccountHasMessageGroup)) {
            return false;
        }
        AccountHasMessageGroup other = (AccountHasMessageGroup) object;
        if ((this.accountHasMessageGroupPK == null && other.accountHasMessageGroupPK != null) || (this.accountHasMessageGroupPK != null && !this.accountHasMessageGroupPK.equals(other.accountHasMessageGroupPK))) {
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
        return "models.AccountHasMessageGroup[ accountHasMessageGroupPK=" + accountHasMessageGroupPK + " ]";
    }
    
}
