package kr.co.manager.domain.view;

public class QgProgramCntView {

	public QgProgramCntView() {
		super();
		// TODO Auto-generated constructor stub
	}

	Integer machineId;
	
	String machineName;
	
	Integer programEditCnt;
	
	String programName;
	
	Integer programEditDetailCnt;
	
	Integer totalItemCnt;
	
	double calCnt;
	
	String areaName;

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

	public Integer getProgramEditCnt() {
		return programEditCnt;
	}

	public void setProgramEditCnt(Integer programEditCnt) {
		this.programEditCnt = programEditCnt;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Integer getProgramEditDetailCnt() {
		return programEditDetailCnt;
	}

	public void setProgramEditDetailCnt(Integer programEditDetailCnt) {
		this.programEditDetailCnt = programEditDetailCnt;
	}

	public Integer getTotalItemCnt() {
		return totalItemCnt;
	}

	public void setTotalItemCnt(Integer totalItemCnt) {
		this.totalItemCnt = totalItemCnt;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "QgProgramCntView [machineId=" + machineId + ", machineName=" + machineName + ", programEditCnt="
				+ programEditCnt + ", programName=" + programName + ", programEditDetailCnt=" + programEditDetailCnt
				+ ", totalItemCnt=" + totalItemCnt + ", calCnt=" + calCnt + ", areaName=" + areaName + "]";
	}

	public double getCalCnt() {
		return calCnt;
	}

	public void setCalCnt(double calCnt) {
		this.calCnt = calCnt;
	}

}
