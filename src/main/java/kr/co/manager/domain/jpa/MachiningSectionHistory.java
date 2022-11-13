package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MachiningSectionHistory {

    @Id
    @GeneratedValue
    Integer machiningSectionHistoryId;
    
    Integer productId;

    Integer machiningSectionId;

    String basicRawData;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    String reason;

    String activeYn;
    
    String smartActiveYn;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date smartStartDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date smartEndDate;
    
    Integer smartSumCnt;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;

    public MachiningSectionHistory(MachiningSection ms) {
        this.productId = ms.getProduct().getProductId();
        this.machiningSectionId = ms.getMachiningSectionId();
        this.basicRawData = ms.getBasicRawData();
        this.regDate = new Date();
        this.reason = ms.getReason();
        this.activeYn = ms.getActiveYn();
        this.smartActiveYn = ms.getSmartActiveYn();
        this.smartStartDate = ms.getSmartStartDate();
        this.smartEndDate = ms.getSmartEndDate();
        this.smartSumCnt = ms.getSmartSumCnt();
        this.updateDate = ms.getUpdateDate();
    }

    
    public MachiningSectionHistory() {
        super();
    }

    public Integer getMachiningSectionHistoryId() {
        return machiningSectionHistoryId;
    }

    public void setMachiningSectionHistoryId(Integer machiningSectionHistoryId) {
        this.machiningSectionHistoryId = machiningSectionHistoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMachiningSectionId() {
        return machiningSectionId;
    }

    public void setMachiningSectionId(Integer machiningSectionId) {
        this.machiningSectionId = machiningSectionId;
    }

    public String getBasicRawData() {
        return basicRawData;
    }

    public void setBasicRawData(String basicRawData) {
        this.basicRawData = basicRawData;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(String activeYn) {
        this.activeYn = activeYn;
    }

    public String getSmartActiveYn() {
        return smartActiveYn;
    }

    public void setSmartActiveYn(String smartActiveYn) {
        this.smartActiveYn = smartActiveYn;
    }

    public Date getSmartStartDate() {
        return smartStartDate;
    }

    public void setSmartStartDate(Date smartStartDate) {
        this.smartStartDate = smartStartDate;
    }

    public Date getSmartEndDate() {
        return smartEndDate;
    }

    public void setSmartEndDate(Date smartEndDate) {
        this.smartEndDate = smartEndDate;
    }

    public Integer getSmartSumCnt() {
        return smartSumCnt;
    }

    public void setSmartSumCnt(Integer smartSumCnt) {
        this.smartSumCnt = smartSumCnt;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}