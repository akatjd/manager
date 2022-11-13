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
public class InspectionTimer {

	@Id    
    @GeneratedValue
    Integer inspectionTimerId;

    @ManyToOne
    @JoinColumn(name="MACHINE_ID")
    Machine machine;

    Integer itemStopTime;
    
    Integer itemRunCnt;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date inspectionTimerOnDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date writeDate;
    
    String writeUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;

    String updateUser;
    
    String useYn;
    
    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Integer getInspectionTimerId() {
        return inspectionTimerId;
    }

    public void setInspectionTimerId(Integer inspectionTimerId) {
        this.inspectionTimerId = inspectionTimerId;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(String writeUser) {
        this.writeUser = writeUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getItemStopTime() {
        return itemStopTime;
    }

    public void setItemStopTime(Integer itemStopTime) {
        this.itemStopTime = itemStopTime;
    }

    public Integer getItemRunCnt() {
        return itemRunCnt;
    }

    public void setItemRunCnt(Integer itemRunCnt) {
        this.itemRunCnt = itemRunCnt;
    }

    public Date getInspectionTimerOnDate() {
        return inspectionTimerOnDate;
    }

    public void setInspectionTimerOnDate(Date inspectionTimerOnDate) {
        this.inspectionTimerOnDate = inspectionTimerOnDate;
    }
}
