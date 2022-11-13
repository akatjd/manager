package kr.co.manager.domain.view;

public class QgAlarmView {
	
	public QgAlarmView() {
		super();
		// TODO Auto-generated constructor stub
	}

	Integer areaId;
	
	String areaName;
	
	Integer machineId;
	
	String machineName;
	
	Integer machineHoldTime;
	
	Integer machineHoldCnt;
	
	Integer totalItemCnt;
	
	double calCnt;
	
	Integer alarmTypeId;
	
	String alarmMsg;
	
	Integer alarmId;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public Integer getMachineHoldTime() {
		return machineHoldTime;
	}

	public void setMachineHoldTime(Integer machineHoldTime) {
		this.machineHoldTime = machineHoldTime;
	}

	public Integer getMachineHoldCnt() {
		return machineHoldCnt;
	}

	public void setMachineHoldCnt(Integer machineHoldCnt) {
		this.machineHoldCnt = machineHoldCnt;
	}

	public Integer getTotalItemCnt() {
		return totalItemCnt;
	}

	public void setTotalItemCnt(Integer totalItemCnt) {
		this.totalItemCnt = totalItemCnt;
	}

	public double getCalCnt() {
		return calCnt;
	}

	public void setCalCnt(double calCnt) {
		this.calCnt = calCnt;
	}

	public String getAlarmMsg() {
		return alarmMsg;
	}

	public void setAlarmMsg(String alarmMsg) {
		this.alarmMsg = alarmMsg;
	}

	public Integer getAlarmTypeId() {
		return alarmTypeId;
	}

	public void setAlarmTypeId(Integer alarmTypeId) {
		this.alarmTypeId = alarmTypeId;
	}

	public Integer getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}

	@Override
	public String toString() {
		return "QgAlarmView [areaId=" + areaId + ", areaName=" + areaName + ", machineId=" + machineId
				+ ", machineName=" + machineName + ", machineHoldTime=" + machineHoldTime + ", machineHoldCnt="
				+ machineHoldCnt + ", totalItemCnt=" + totalItemCnt + ", calCnt=" + calCnt + ", alarmTypeId="
				+ alarmTypeId + ", alarmMsg=" + alarmMsg + ", alarmId=" + alarmId + "]";
	}

}
