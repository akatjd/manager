package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AlarmStatus {
	@Id
    @GeneratedValue
    @Column(name="ALARM_STATUS_ID")
    private Integer alarmStatusId;
    
    @Column(name="MACHINE_ID")
    Integer machineId;
    
    @Column(name="ALARM_ID")
    Integer alarmId;
    
    @Column(name="ALARM_TYPE")
    Integer alarmType;
    
    @Column(name="ALARM_NO")
    Integer alarmNo;
    
    @Column(name="ALARM_AXIS")
    Integer alarmAxis;
    
    @Column(name="U_FLAG")
    Integer uFlag;
    
    @Column(name="ALARM_MSG")
    String alarmMSg;
    
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

    public Integer getuFlag() {
        return uFlag;
    }

    public void setuFlag(Integer uFlag) {
        this.uFlag = uFlag;
    }

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public Integer getAlarmStatusId() {
        return alarmStatusId;
    }

    public void setAlarmStatusId(Integer alarmStatusId) {
        this.alarmStatusId = alarmStatusId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Integer getAlarmAxis() {
        return alarmAxis;
    }

    public void setAlarmAxis(Integer alarmAxis) {
        this.alarmAxis = alarmAxis;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getAlarmNo() {
        return alarmNo;
    }

    public void setAlarmNo(Integer alarmNo) {
        this.alarmNo = alarmNo;
    }

    public String getAlarmMSg() {
        return alarmMSg;
    }

    public void setAlarmMSg(String alarmMSg) {
        this.alarmMSg = alarmMSg;
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
