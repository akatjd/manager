package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ItemCt {
    
    @Id
    @GeneratedValue
    Integer itemCtSeq;

    Integer factoryId;
    
    Integer qgClientId;
    
    Integer machineId;
    
    Integer productId;
    
    Integer palletsNo;
    
    Integer itemId;
    
    Integer cycleTime;
    
    Integer itemTypeId;
    
    String etc1;
    
    String etc2;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemCtSeq() {
        return itemCtSeq;
    }

    public void setItemCtSeq(Integer itemCtSeq) {
        this.itemCtSeq = itemCtSeq;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getQgClientId() {
        return qgClientId;
    }

    public void setQgClientId(Integer qgClientId) {
        this.qgClientId = qgClientId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(Integer cycleTime) {
        this.cycleTime = cycleTime;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getEtc1() {
        return etc1;
    }

    public void setEtc1(String etc1) {
        this.etc1 = etc1;
    }

    public String getEtc2() {
        return etc2;
    }

    public void setEtc2(String etc2) {
        this.etc2 = etc2;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getPalletsNo() {
        return palletsNo == null ? 0 : palletsNo;
    }

    public void setPalletsNo(Integer palletsNo) {
        this.palletsNo = palletsNo;
    }
}
