package kr.co.manager.domain.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QgCtView {

	public QgCtView() {
		super();
		// TODO Auto-generated constructor stub
	}

	Integer factoryId;
	
	String factoryName;
	
	Integer areaId;
	
	String areaName;
	
	Integer machineId;
	
	String machineName;
	
	Integer productId;
	
	String productName;
	
	Integer minCt;
	
	double doubleMinCt;
	
	Integer avgCt;
	
	Integer maxCt;
	
	Integer standardCt;
	
	double doubleStandardCt;
	
	Integer[] distributionCt;
	
	Integer gapStandardAvg;
	
	double doubleGapStandardAvg;
	
	Integer sectionNumber;
	
	String toolName;
	
	Integer minCtDetail;
	
	Integer avgCtDetail;
	
	Integer maxCtDetail;
	
	Integer[] distributionDetailCt;
	
	List<Object> ctArr;
	
	List<HashMap<String, Object>> distributionMapList;
	
	Integer itemCnt;
	
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

	public Integer getMinCt() {
		return minCt;
	}

	public void setMinCt(Integer minCt) {
		this.minCt = minCt;
	}

	public Integer getAvgCt() {
		return avgCt;
	}

	public void setAvgCt(Integer avgCt) {
		this.avgCt = avgCt;
	}

	public Integer getMaxCt() {
		return maxCt;
	}

	public void setMaxCt(Integer maxCt) {
		this.maxCt = maxCt;
	}

	public Integer getStandardCt() {
		return standardCt;
	}

	public void setStandardCt(Integer standardCt) {
		this.standardCt = standardCt;
	}

	public Integer[] getDistributionCt() {
		return distributionCt;
	}

	public void setDistributionCt(Integer[] distributionCt) {
		this.distributionCt = distributionCt;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getGapStandardAvg() {
		return gapStandardAvg;
	}

	public void setGapStandardAvg(Integer gapStandardAvg) {
		this.gapStandardAvg = gapStandardAvg;
	}

	public Integer getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(Integer sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public Integer getMinCtDetail() {
		return minCtDetail;
	}

	public void setMinCtDetail(Integer minCtDetail) {
		this.minCtDetail = minCtDetail;
	}

	public Integer getAvgCtDetail() {
		return avgCtDetail;
	}

	public void setAvgCtDetail(Integer avgCtDetail) {
		this.avgCtDetail = avgCtDetail;
	}

	public Integer getMaxCtDetail() {
		return maxCtDetail;
	}

	public void setMaxCtDetail(Integer maxCtDetail) {
		this.maxCtDetail = maxCtDetail;
	}

	public Integer[] getDistributionDetailCt() {
		return distributionDetailCt;
	}

	public void setDistributionDetailCt(Integer[] distributionDetailCt) {
		this.distributionDetailCt = distributionDetailCt;
	}

	public List<HashMap<String, Object>> getDistributionMapList() {
		return distributionMapList;
	}

	public void setDistributionMapList(List<HashMap<String, Object>> distributionMapList) {
		this.distributionMapList = distributionMapList;
	}

	public List<Object> getCtArr() {
		return ctArr;
	}

	public void setCtArr(List<Object> ctArr) {
		this.ctArr = ctArr;
	}

	public Integer getItemCnt() {
		return itemCnt;
	}

	public void setItemCnt(Integer itemCnt) {
		this.itemCnt = itemCnt;
	}

	@Override
	public String toString() {
		return "QgCtView [factoryId=" + factoryId + ", factoryName=" + factoryName + ", areaId=" + areaId
				+ ", areaName=" + areaName + ", machineId=" + machineId + ", machineName=" + machineName
				+ ", productId=" + productId + ", productName=" + productName + ", minCt=" + minCt + ", avgCt=" + avgCt
				+ ", maxCt=" + maxCt + ", standardCt=" + standardCt + ", distributionCt="
				+ Arrays.toString(distributionCt) + ", gapStandardAvg=" + gapStandardAvg + ", sectionNumber="
				+ sectionNumber + ", toolName=" + toolName + ", minCtDetail=" + minCtDetail + ", avgCtDetail="
				+ avgCtDetail + ", maxCtDetail=" + maxCtDetail + ", distributionDetailCt="
				+ Arrays.toString(distributionDetailCt) + ", ctArr=" + ctArr + ", distributionMapList="
				+ distributionMapList + ", itemCnt=" + itemCnt + "]";
	}

	public double getDoubleMinCt() {
		return doubleMinCt;
	}

	public void setDoubleMinCt(double doubleMinCt) {
		this.doubleMinCt = doubleMinCt;
	}

	public double getDoubleStandardCt() {
		return doubleStandardCt;
	}

	public void setDoubleStandardCt(double doubleStandardCt) {
		this.doubleStandardCt = doubleStandardCt;
	}

	public double getDoubleGapStandardAvg() {
		return doubleGapStandardAvg;
	}

	public void setDoubleGapStandardAvg(double doubleGapStandardAvg) {
		this.doubleGapStandardAvg = doubleGapStandardAvg;
	}
}
