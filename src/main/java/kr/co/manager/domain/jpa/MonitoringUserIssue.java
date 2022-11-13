package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MonitoringUserIssue {

    @Id
    @GeneratedValue
    Integer userIssueId;
    
    @ManyToOne
    @JoinColumn(name="ISSUE_ID")
    MonitoringIssue monitoringIssue;
    
    String accountId;
    
    String writeUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date writeDate;
    
    String updateUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getUserIssueId() {
        return userIssueId;
    }

    public void setUserIssueId(Integer userIssueId) {
        this.userIssueId = userIssueId;
    }

    public MonitoringIssue getMonitoringIssue() {
        return monitoringIssue;
    }

    public void setMonitoringIssue(MonitoringIssue monitoringIssue) {
        this.monitoringIssue = monitoringIssue;
    }

    public String getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(String writeUser) {
        this.writeUser = writeUser;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}