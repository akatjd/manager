package kr.co.manager.domain.view;

public class QgToolChangeCntView {

	public QgToolChangeCntView() {
		super();
		// TODO Auto-generated constructor stub
	}

	Integer factoryId;
	
	String factoryName;
	
	Integer areaId;
	
	String areaName;
	
	Integer machineId;
	
	String machineName;
	
	Integer totalToolChangeCnt;
	
	Integer toolId;
	
	String toolName;
	
	Integer toolChangeCnt;
	
	Integer totalItemCnt;
	
	double calCnt;
	
	Integer presetCnt;

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

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

	public Integer getTotalToolChangeCnt() {
		return totalToolChangeCnt;
	}

	public void setTotalToolChangeCnt(Integer totalToolChangeCnt) {
		this.totalToolChangeCnt = totalToolChangeCnt;
	}

	public Integer getToolId() {
		return toolId;
	}

	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public Integer getToolChangeCnt() {
		return toolChangeCnt;
	}

	public void setToolChangeCnt(Integer toolChangeCnt) {
		this.toolChangeCnt = toolChangeCnt;
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

	public Integer getPresetCnt() {
		return presetCnt;
	}

	public void setPresetCnt(Integer presetCnt) {
		this.presetCnt = presetCnt;
	}

	@Override
	public String toString() {
		return "QgToolChangeCntView [factoryId=" + factoryId + ", factoryName=" + factoryName + ", areaId=" + areaId
				+ ", areaName=" + areaName + ", machineId=" + machineId + ", machineName=" + machineName
				+ ", totalToolChangeCnt=" + totalToolChangeCnt + ", toolId=" + toolId + ", toolName=" + toolName
				+ ", toolChangeCnt=" + toolChangeCnt + ", totalItemCnt=" + totalItemCnt + ", calCnt=" + calCnt
				+ ", presetCnt=" + presetCnt + "]";
	}
}
