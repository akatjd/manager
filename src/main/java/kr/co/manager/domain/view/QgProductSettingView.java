package kr.co.manager.domain.view;

public class QgProductSettingView {
	
	public QgProductSettingView() {
		super();
		// TODO Auto-generated constructor stub
	}

	Integer areaId;
	
	String areaName;
	
	Integer machineId;
	
	String machineName;
	
	Integer productId;
	
	String productOid;
	
	String productName;
	
	Float staticCycleTime;
	
	char activeYn;

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

	public char getActiveYn() {
		return activeYn;
	}

	public void setActiveYn(char activeYn) {
		this.activeYn = activeYn;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductOid() {
		return productOid;
	}

	public void setProductOid(String productOid) {
		this.productOid = productOid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getStaticCycleTime() {
		return staticCycleTime;
	}

	public void setStaticCycleTime(Float staticCycleTime) {
		this.staticCycleTime = staticCycleTime;
	}

	@Override
	public String toString() {
		return "QgProductSettingView [areaId=" + areaId + ", areaName=" + areaName + ", machineId=" + machineId
				+ ", machineName=" + machineName + ", productId=" + productId + ", productOid=" + productOid
				+ ", productName=" + productName + ", staticCycleTime=" + staticCycleTime + ", activeYn=" + activeYn
				+ "]";
	}

}
