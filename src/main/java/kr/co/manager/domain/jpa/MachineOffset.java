package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MachineOffset {

    @Id
    @GeneratedValue
    Integer machineOffsetId;

    Integer machineId;
    
    Integer offsetNo;
    
    String offsetLocationType;
    
    String offsetValueType;
    
    Integer offsetValue;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    public String getOffsetLocationType() {
        return offsetLocationType;
    }

    public void setOffsetLocationType(String offsetLocationType) {
        this.offsetLocationType = offsetLocationType;
    }

    public String getOffsetValueType() {
        return offsetValueType;
    }

    public void setOffsetValueType(String offsetValueType) {
        this.offsetValueType = offsetValueType;
    }

    public Integer getMachineOffsetId() {
        return machineOffsetId;
    }

    public void setMachineOffsetId(Integer machineOffsetId) {
        this.machineOffsetId = machineOffsetId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Integer getOffsetNo() {
        return offsetNo;
    }

    public void setOffsetNo(Integer offsetNo) {
        this.offsetNo = offsetNo;
    }

    public Integer getOffsetValue() {
        return offsetValue;
    }

    public void setOffsetValue(Integer offsetValue) {
        this.offsetValue = offsetValue;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}