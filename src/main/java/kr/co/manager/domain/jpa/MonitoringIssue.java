package kr.co.manager.domain.jpa;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.co.manager.domain.type.IssueStatus;

@Entity
public class MonitoringIssue {

    @Id
    @GeneratedValue
    Integer issueId;
    
    Integer machineId;
    
    Integer goodCnt;
    
    Integer infoCnt;
    
    Integer warnCnt;
    
    Integer errorCnt;

    Integer stopCnt;
    
    Integer badsyncCnt;
    
    Integer gcodeChangeCnt;
    
    Integer productChangeCnt;
    
    String contents;
    
    @Enumerated(EnumType.STRING)
    IssueStatus issueStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date endDate;
    
    String writeUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date writeDate;
    
    String updateUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;

    public MonitoringIssue(HashMap<String, Object> monitoringIssueMap) {
        this.machineId        = (Integer )monitoringIssueMap.get("machineId");
        this.goodCnt          = (Integer) monitoringIssueMap.get("goodCnt");
        this.infoCnt          = (Integer) monitoringIssueMap.get("infoCnt");
        this.warnCnt          = (Integer) monitoringIssueMap.get("warnCnt");
        this.errorCnt         = (Integer) monitoringIssueMap.get("errorCnt");
        this.stopCnt          = (Integer) monitoringIssueMap.get("stopCnt");
        this.badsyncCnt       = (Integer) monitoringIssueMap.get("badsyncCnt");
        this.gcodeChangeCnt   = (Integer) monitoringIssueMap.get("productChangeCnt");
        this.productChangeCnt = (Integer) monitoringIssueMap.get("gcodeChangeCnt");
        this.contents         = (String)  monitoringIssueMap.get("contents");
    }
    
    public MonitoringIssue() {
        super();
    }

    public Integer getBadsyncCnt() {
        return badsyncCnt;
    }

    public void setBadsyncCnt(Integer badsyncCnt) {
        this.badsyncCnt = badsyncCnt;
    }

    public Integer getGoodCnt() {
        return goodCnt;
    }

    public void setGoodCnt(Integer goodCnt) {
        this.goodCnt = goodCnt;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
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

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Integer getInfoCnt() {
        return infoCnt;
    }

    public void setInfoCnt(Integer infoCnt) {
        this.infoCnt = infoCnt;
    }

    public Integer getWarnCnt() {
        return warnCnt;
    }

    public void setWarnCnt(Integer warnCnt) {
        this.warnCnt = warnCnt;
    }
    
    public Integer getErrorCnt() {
        return errorCnt;
    }
    
    public void setErrorCnt(Integer errorCnt) {
        this.errorCnt = errorCnt;
    }
    
    public Integer getStopCnt() {
        return stopCnt;
    }

    public void setStopCnt(Integer stopCnt) {
        this.stopCnt = stopCnt;
    }

    public Integer getGcodeChangeCnt() {
        return gcodeChangeCnt;
    }

    public void setGcodeChangeCnt(Integer gcodeChangeCnt) {
        this.gcodeChangeCnt = gcodeChangeCnt;
    }

    public Integer getProductChangeCnt() {
        return productChangeCnt;
    }

    public void setProductChangeCnt(Integer productChangeCnt) {
        this.productChangeCnt = productChangeCnt;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
}