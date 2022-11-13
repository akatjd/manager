package kr.co.manager.domain.view;

public class QgToolSettingView {
	
	public QgToolSettingView() {
		super();
		// TODO Auto-generated constructor stub
	}

	Integer areaId;
	
	String areaName;
	
	Integer machineId;
	
	String machineName;
	
	Integer machineToolId;
	
	String toolName;
	
	Integer useCnt;
	
	Integer presetCnt;
	
	String toolSpec;
	
	char useYn;
	
	Integer sortSeq;
	
	String viewName;

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

	public Integer getMachineToolId() {
		return machineToolId;
	}

	public void setMachineToolId(Integer machineToolId) {
		this.machineToolId = machineToolId;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public Integer getUseCnt() {
		return useCnt;
	}

	public void setUseCnt(Integer useCnt) {
		this.useCnt = useCnt;
	}

	public Integer getPresetCnt() {
		return presetCnt;
	}

	public void setPresetCnt(Integer presetCnt) {
		this.presetCnt = presetCnt;
	}

	public String getToolSpec() {
		return toolSpec;
	}

	public void setToolSpec(String toolSpec) {
		this.toolSpec = toolSpec;
	}

	public char getUseYn() {
		return useYn;
	}

	public void setUseYn(char useYn) {
		this.useYn = useYn;
	}

	public Integer getSortSeq() {
		return sortSeq;
	}

	public void setSortSeq(Integer sortSeq) {
		this.sortSeq = sortSeq;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	@Override
	public String toString() {
		return "QgToolSettingView [areaId=" + areaId + ", areaName=" + areaName + ", machineId=" + machineId
				+ ", machineName=" + machineName + ", machineToolId=" + machineToolId + ", toolName=" + toolName
				+ ", useCnt=" + useCnt + ", presetCnt=" + presetCnt + ", toolSpec=" + toolSpec + ", useYn=" + useYn
				+ ", sortSeq=" + sortSeq + ", viewName=" + viewName + "]";
	}
}
