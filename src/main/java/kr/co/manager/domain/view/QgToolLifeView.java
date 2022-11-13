package kr.co.manager.domain.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QgToolLifeView {

	public QgToolLifeView() {
		super();
		// TODO Auto-generated constructor stub
	}

	Integer machineId;
	
	String machineName;
	
	String toolName;
	
	Integer minLife;
	
	Integer avgLife;
	
	Integer maxLife;
	
	Integer minSectionCnt;
	
	Integer avgSectionCnt;
	
	Integer maxSectionCnt;
	
	String areaName;
	
	Integer nowPresetCnt;
	
	Integer factoryId;
	
	String factoryName;
	
	Integer[] arrDistributionCnt;
	
	Integer[] arrDistributionSection;
	
	List<Object> presetArr;
	
	List<HashMap<String, Object>> distributionMapList;
	
	Integer gapStandardAvg;
	
	Integer sumUseCnt;

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

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public Integer getMinLife() {
		return minLife;
	}

	public void setMinLife(Integer minLife) {
		this.minLife = minLife;
	}

	public Integer getAvgLife() {
		return avgLife;
	}

	public void setAvgLife(Integer avgLife) {
		this.avgLife = avgLife;
	}

	public Integer getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(Integer maxLife) {
		this.maxLife = maxLife;
	}

	public Integer getMinSectionCnt() {
		return minSectionCnt;
	}

	public void setMinSectionCnt(Integer minSectionCnt) {
		this.minSectionCnt = minSectionCnt;
	}

	public Integer getAvgSectionCnt() {
		return avgSectionCnt;
	}

	public void setAvgSectionCnt(Integer avgSectionCnt) {
		this.avgSectionCnt = avgSectionCnt;
	}

	public Integer getMaxSectionCnt() {
		return maxSectionCnt;
	}

	public void setMaxSectionCnt(Integer maxSectionCnt) {
		this.maxSectionCnt = maxSectionCnt;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getNowPresetCnt() {
		return nowPresetCnt;
	}

	public void setNowPresetCnt(Integer nowPresetCnt) {
		this.nowPresetCnt = nowPresetCnt;
	}

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

	public Integer[] getArrDistributionCnt() {
		return arrDistributionCnt;
	}

	public void setArrDistributionCnt(Integer[] arrDistributionCnt) {
		this.arrDistributionCnt = arrDistributionCnt;
	}

	public Integer[] getArrDistributionSection() {
		return arrDistributionSection;
	}

	public void setArrDistributionSection(Integer[] arrDistributionSection) {
		this.arrDistributionSection = arrDistributionSection;
	}

	public List<HashMap<String, Object>> getDistributionMapList() {
		return distributionMapList;
	}

	public void setDistributionMapList(List<HashMap<String, Object>> distributionMapList) {
		this.distributionMapList = distributionMapList;
	}

	public List<Object> getPresetArr() {
		return presetArr;
	}

	public void setPresetArr(List<Object> presetArr) {
		this.presetArr = presetArr;
	}

	public Integer getGapStandardAvg() {
		return gapStandardAvg;
	}

	public void setGapStandardAvg(Integer gapStandardAvg) {
		this.gapStandardAvg = gapStandardAvg;
	}

	public Integer getSumUseCnt() {
		return sumUseCnt;
	}

	public void setSumUseCnt(Integer sumUseCnt) {
		this.sumUseCnt = sumUseCnt;
	}

	@Override
	public String toString() {
		return "QgToolLifeView [machineId=" + machineId + ", machineName=" + machineName + ", toolName=" + toolName
				+ ", minLife=" + minLife + ", avgLife=" + avgLife + ", maxLife=" + maxLife + ", minSectionCnt="
				+ minSectionCnt + ", avgSectionCnt=" + avgSectionCnt + ", maxSectionCnt=" + maxSectionCnt
				+ ", areaName=" + areaName + ", nowPresetCnt=" + nowPresetCnt + ", factoryId=" + factoryId
				+ ", factoryName=" + factoryName + ", arrDistributionCnt=" + Arrays.toString(arrDistributionCnt)
				+ ", arrDistributionSection=" + Arrays.toString(arrDistributionSection) + ", presetArr=" + presetArr
				+ ", distributionMapList=" + distributionMapList + ", gapStandardAvg=" + gapStandardAvg + ", sumUseCnt="
				+ sumUseCnt + "]";
	}

}
