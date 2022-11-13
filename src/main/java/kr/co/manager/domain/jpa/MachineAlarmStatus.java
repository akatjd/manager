package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MachineAlarmStatus {
	@Id
    @GeneratedValue
    @Column(name="MACHINE_ALARM_STATUS_ID")
    private Integer machineAlarmStatusId;
    
    @Column(name="MACHINE_ID")
    Integer machineId;
    
    @Column(name="STATUS_TYPE")
    String statusType;
    
    @Column(name="ETC1")
    String etc1;
    
    @Column(name="ETC2")
    String etc2;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;
    
    @Column(name="HOLDING_TIME")
    Integer holdingTime;

    public Integer getHoldingTime() {
        return holdingTime;
    }

    public void setHoldingTime(Integer holdingTime) {
        this.holdingTime = holdingTime;
    }

    public Integer getMachineAlarmStatusId() {
        return machineAlarmStatusId;
    }

    public void setMachineAlarmStatusId(Integer machineAlarmStatusId) {
        this.machineAlarmStatusId = machineAlarmStatusId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }
    
    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
