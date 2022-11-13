package kr.co.manager.domain.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QgToolChangeTimeView {

	public QgToolChangeTimeView() {
		super();
		// TODO Auto-generated constructor stub
	}

	Integer factoryId;
	
	String factoryName;
	
	Integer areaId;
	
	String areaName;
	
	Integer machineId;
	
	String machineName;
	
	Integer minCycleTime;
	
	Integer avgCycleTime;
	
	Integer maxCycleTime;
	
	Integer[] ctDistribution;
	
	Integer machineToolId;
	
	String toolName;
	
	Integer minCycleTimeDetail;
	
	Integer avgCycleTimeDetail;
	
	Integer maxCycleTimeDetail;
	
	Integer[] ctDistributionDetail;
	
	List<Object> changedTimeArr;
	
	List<int[]> changedTimeArrList;
	
	List<HashMap<String, Object>> distributionMapList;
	
	Integer changeToolCnt;

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

	public Integer getMinCycleTime() {
		return minCycleTime;
	}

	public void setMinCycleTime(Integer minCycleTime) {
		this.minCycleTime = minCycleTime;
	}

	public Integer getAvgCycleTime() {
		return avgCycleTime;
	}

	public void setAvgCycleTime(Integer avgCycleTime) {
		this.avgCycleTime = avgCycleTime;
	}

	public Integer getMaxCycleTime() {
		return maxCycleTime;
	}

	public void setMaxCycleTime(Integer maxCycleTime) {
		this.maxCycleTime = maxCycleTime;
	}

	public Integer[] getCtDistribution() {
		return ctDistribution;
	}

	public void setCtDistribution(Integer[] ctDistribution) {
		this.ctDistribution = ctDistribution;
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

	public Integer getMinCycleTimeDetail() {
		return minCycleTimeDetail;
	}

	public void setMinCycleTimeDetail(Integer minCycleTimeDetail) {
		this.minCycleTimeDetail = minCycleTimeDetail;
	}

	public Integer getAvgCycleTimeDetail() {
		return avgCycleTimeDetail;
	}

	public void setAvgCycleTimeDetail(Integer avgCycleTimeDetail) {
		this.avgCycleTimeDetail = avgCycleTimeDetail;
	}

	public Integer getMaxCycleTimeDetail() {
		return maxCycleTimeDetail;
	}

	public void setMaxCycleTimeDetail(Integer maxCycleTimeDetail) {
		this.maxCycleTimeDetail = maxCycleTimeDetail;
	}

	public Integer[] getCtDistributionDetail() {
		return ctDistributionDetail;
	}

	public void setCtDistributionDetail(Integer[] ctDistributionDetail) {
		this.ctDistributionDetail = ctDistributionDetail;
	}

	@Override
	public String toString() {
		return "QgToolChangeTimeView [factoryId=" + factoryId + ", factoryName=" + factoryName + ", areaId=" + areaId
				+ ", areaName=" + areaName + ", machineId=" + machineId + ", machineName=" + machineName
				+ ", minCycleTime=" + minCycleTime + ", avgCycleTime=" + avgCycleTime + ", maxCycleTime=" + maxCycleTime
				+ ", ctDistribution=" + Arrays.toString(ctDistribution) + ", machineToolId=" + machineToolId
				+ ", toolName=" + toolName + ", minCycleTimeDetail=" + minCycleTimeDetail + ", avgCycleTimeDetail="
				+ avgCycleTimeDetail + ", maxCycleTimeDetail=" + maxCycleTimeDetail + ", ctDistributionDetail="
				+ Arrays.toString(ctDistributionDetail) + ", changedTimeArr=" + changedTimeArr
				+ ", distributionMapList=" + distributionMapList + "]";
	}

	public List<HashMap<String, Object>> getDistributionMapList() {
		return distributionMapList;
	}

	public void setDistributionMapList(List<HashMap<String, Object>> distributionMapList) {
		this.distributionMapList = distributionMapList;
	}

	public List<Object> getChangedTimeArr() {
		return changedTimeArr;
	}

	public void setChangedTimeArr(List<Object> changedTimeArr) {
		this.changedTimeArr = changedTimeArr;
	}

	public Integer getChangeToolCnt() {
		return changeToolCnt;
	}

	public void setChangeToolCnt(Integer changeToolCnt) {
		this.changeToolCnt = changeToolCnt;
	}

	public List<int[]> getChangedTimeArrList() {
		return changedTimeArrList;
	}

	public void setChangedTimeArrList(List<int[]> changedTimeArrList) {
		this.changedTimeArrList = changedTimeArrList;
	}
}
