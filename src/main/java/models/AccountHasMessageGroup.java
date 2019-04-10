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
 *
 * @author 587568
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

    public AccountHasMessageGroup(AccountHasMessageGroupPK accountHasMessageGroupPK) {
        this.accountHasMessageGroupPK = accountHasMessageGroupPK;
    }

    public AccountHasMessageGroup(int aCCOUNTuserid, int mESSAGEGROUPmessagegroupid, int mESSAGEmessageid) {
        this.accountHasMessageGroupPK = new AccountHasMessageGroupPK(aCCOUNTuserid, mESSAGEGROUPmessagegroupid, mESSAGEmessageid);
    }

    public AccountHasMessageGroupPK getAccountHasMessageGroupPK() {
        return accountHasMessageGroupPK;
    }

    public void setAccountHasMessageGroupPK(AccountHasMessageGroupPK accountHasMessageGroupPK) {
        this.accountHasMessageGroupPK = accountHasMessageGroupPK;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MessageGroup getMessageGroup() {
        return messageGroup;
    }

    public void setMessageGroup(MessageGroup messageGroup) {
        this.messageGroup = messageGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountHasMessageGroupPK != null ? accountHasMessageGroupPK.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "models.AccountHasMessageGroup[ accountHasMessageGroupPK=" + accountHasMessageGroupPK + " ]";
    }
    
}
