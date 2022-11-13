package kr.co.manager.domain.view;

import java.util.HashMap;
import java.util.List;

public class QgTemperatureView {
	
	public QgTemperatureView() {
		super();
		// TODO Auto-generated constructor stub
	}

	String minTemp;
	
	String avgTemp;
	
	String maxTemp;
	
	String machineId;
	
	String machineName;
	
	String areaName;
	
	List<Integer> sparkLineData;
	
	List<HashMap<String, Object>> sparkLineDataMapList;

	public String getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}

	public String getAvgTemp() {
		return avgTemp;
	}

	public void setAvgTemp(String avgTemp) {
		this.avgTemp = avgTemp;
	}

	public String getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public List<Integer> getSparkLineData() {
		return sparkLineData;
	}

	public void setSparkLineData(List<Integer> sparkLineData) {
		this.sparkLineData = sparkLineData;
	}

	public List<HashMap<String, Object>> getSparkLineDataMapList() {
		return sparkLineDataMapList;
	}

	public void setSparkLineDataMapList(List<HashMap<String, Object>> sparkLineDataMapList) {
		this.sparkLineDataMapList = sparkLineDataMapList;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "QgTemperatureView [minTemp=" + minTemp + ", avgTemp=" + avgTemp + ", maxTemp=" + maxTemp
				+ ", machineId=" + machineId + ", machineName=" + machineName + ", areaName=" + areaName
				+ ", sparkLineData=" + sparkLineData + ", sparkLineDataMapList=" + sparkLineDataMapList + "]";
	}

}
