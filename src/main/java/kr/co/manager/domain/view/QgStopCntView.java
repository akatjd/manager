package kr.co.manager.domain.view;

public class QgStopCntView {
	
	public QgStopCntView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	Integer areaId;
	
	String areaName;

	Integer machineId;
	
	String machineName;
	
	Integer stopCnt;
	
	Integer totalItemCnt;
	
	double calCnt;

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

	public Integer getStopCnt() {
		return stopCnt;
	}

	public void setStopCnt(Integer stopCnt) {
		this.stopCnt = stopCnt;
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

	@Override
	public String toString() {
		return "QgStopCntView [areaId=" + areaId + ", areaName=" + areaName + ", machineId=" + machineId
				+ ", machineName=" + machineName + ", stopCnt=" + stopCnt + ", totalItemCnt=" + totalItemCnt
				+ ", calCnt=" + calCnt + "]";
	}
}
