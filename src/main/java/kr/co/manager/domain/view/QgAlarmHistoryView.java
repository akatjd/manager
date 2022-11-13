package kr.co.manager.domain.view;

public class QgAlarmHistoryView {
	
	public QgAlarmHistoryView() {
		super();
		// TODO Auto-generated constructor stub
	}

	Integer areaId;
	
	String areaName;

	Integer machineId;
	
	String machineName;
	
	String regDate;
	
	String updateDate;
	
	Integer alarmId;
	
	Integer alarmType;
	
	String strAlarmType;
	
	Integer alarmNo;
	
	Integer alarmAxis;
	
	String alarmMsg;

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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
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

	public Integer getAlarmAxis() {
		return alarmAxis;
	}

	public void setAlarmAxis(Integer alarmAxis) {
		this.alarmAxis = alarmAxis;
	}

	public String getAlarmMsg() {
		return alarmMsg;
	}

	public void setAlarmMsg(String alarmMsg) {
		this.alarmMsg = alarmMsg;
	}

	@Override
	public String toString() {
		return "QgAlarmHistoryView [areaId=" + areaId + ", areaName=" + areaName + ", machineId=" + machineId
				+ ", machineName=" + machineName + ", regDate=" + regDate + ", updateDate=" + updateDate + ", alarmId="
				+ alarmId + ", alarmType=" + alarmType + ", alarmNo=" + alarmNo + ", alarmAxis=" + alarmAxis
				+ ", alarmMsg=" + alarmMsg + "]";
	}

	public String getStrAlarmType() {
		return strAlarmType;
	}

	public void setStrAlarmType(String strAlarmType) {
		this.strAlarmType = strAlarmType;
	}
}
