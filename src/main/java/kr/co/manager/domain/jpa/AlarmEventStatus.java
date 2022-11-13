package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AlarmEventStatus {
	@Id
    @GeneratedValue
    @Column(name="ALARM_STATUS_ID")
	Integer alarmStatusId;
	
	@Column(name="FACTORY_ID")
	Integer factoryId;
	
	@Column(name="MACHINE_ID")
	Integer machineId;
	
	@Column(name="ALARM_ID")
	Integer alarmId;
	
	@Column(name="ALARM_TYPE_ID")
	Integer alarmTypeId;
	
	@Column(name="MACHINE_ALARM_TYPE")
	Integer machineAlarmType;
	
	@Column(name="MACHINE_ALARM_NO")
	Integer machineAlarmNo;
	
	@Column(name="MACHINE_ALARM_AXIS")
	Integer machineAlarmAxis;
	
	@Column(name="ALARM_MSG")
	String alarmMsg;
	
	@Column(name="HOLDING_TIME")
	Integer holdingTime;
	
	@Column(name="U_FLAG")
	Integer uFlag;
	
	@Temporal(TemporalType.TIMESTAMP)
    Date regDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;

	public Integer getAlarmStatusId() {
		return alarmStatusId;
	}

	public void setAlarmStatusId(Integer alarmStatusId) {
		this.alarmStatusId = alarmStatusId;
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public Integer getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}

	public Integer getAlarmTypeId() {
		return alarmTypeId;
	}

	public void setAlarmTypeId(Integer alarmTypeId) {
		this.alarmTypeId = alarmTypeId;
	}

	public Integer getMachineAlarmType() {
		return machineAlarmType;
	}

	public void setMachineAlarmType(Integer machineAlarmType) {
		this.machineAlarmType = machineAlarmType;
	}

	public Integer getMachineAlarmNo() {
		return machineAlarmNo;
	}

	public void setMachineAlarmNo(Integer machineAlarmNo) {
		this.machineAlarmNo = machineAlarmNo;
	}

	public Integer getMachineAlarmAxis() {
		return machineAlarmAxis;
	}

	public void setMachineAlarmAxis(Integer machineAlarmAxis) {
		this.machineAlarmAxis = machineAlarmAxis;
	}

	public String getAlarmMsg() {
		return alarmMsg;
	}

	public void setAlarmMsg(String alarmMsg) {
		this.alarmMsg = alarmMsg;
	}

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
