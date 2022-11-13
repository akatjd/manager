package kr.co.manager.domain.view;

import java.util.List;

public class QgAutoUseView {
	
	public QgAutoUseView() {
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
	
	String dayOrNight;
	
	String scheduleName;
	
	List<String> scheduleNameArr;
	
	String scheduleStartTime;
	
	String scheduleEndTime;
	
	Integer autoCnt;
	
	Integer dayRestCnt01;
	
	Integer dayRestRate01;
	
	Integer dayRestCnt02;
	
	Integer dayRestRate02;
	
	Integer dayMealCnt;
	
	Integer dayMealRate;
	
	Integer dayChangeCnt;
	
	Integer dayChangeRate;
	
	Integer nightRestCnt01;
	
	Integer nightRestRate01;
	
	Integer nightRestCnt02;
	
	Integer nightRestRate02;
	
	Integer nightMealCnt;
	
	Integer nightMealRate;
	
	Integer nightOvertimeCnt;
	
	Integer nightOvertimeRate;
	
	Integer autoRate;

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

	public String getDayOrNight() {
		return dayOrNight;
	}

	public void setDayOrNight(String dayOrNight) {
		this.dayOrNight = dayOrNight;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String getScheduleStartTime() {
		return scheduleStartTime;
	}

	public void setScheduleStartTime(String scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}

	public String getScheduleEndTime() {
		return scheduleEndTime;
	}

	public void setScheduleEndTime(String scheduleEndTime) {
		this.scheduleEndTime = scheduleEndTime;
	}

	public Integer getAutoCnt() {
		return autoCnt;
	}

	public void setAutoCnt(Integer autoCnt) {
		this.autoCnt = autoCnt;
	}

	public Integer getAutoRate() {
		return autoRate;
	}

	public void setAutoRate(Integer autoRate) {
		this.autoRate = autoRate;
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

	public List<String> getScheduleNameArr() {
		return scheduleNameArr;
	}

	public void setScheduleNameArr(List<String> scheduleNameArr) {
		this.scheduleNameArr = scheduleNameArr;
	}

	public Integer getDayRestCnt01() {
		return dayRestCnt01;
	}

	public void setDayRestCnt01(Integer dayRestCnt01) {
		this.dayRestCnt01 = dayRestCnt01;
	}

	public Integer getDayRestCnt02() {
		return dayRestCnt02;
	}

	public void setDayRestCnt02(Integer dayRestCnt02) {
		this.dayRestCnt02 = dayRestCnt02;
	}

	public Integer getDayMealCnt() {
		return dayMealCnt;
	}

	public void setDayMealCnt(Integer dayMealCnt) {
		this.dayMealCnt = dayMealCnt;
	}

	public Integer getDayChangeCnt() {
		return dayChangeCnt;
	}

	public void setDayChangeCnt(Integer dayChangeCnt) {
		this.dayChangeCnt = dayChangeCnt;
	}

	public Integer getNightRestCnt01() {
		return nightRestCnt01;
	}

	public void setNightRestCnt01(Integer nightRestCnt01) {
		this.nightRestCnt01 = nightRestCnt01;
	}

	public Integer getNightRestCnt02() {
		return nightRestCnt02;
	}

	public void setNightRestCnt02(Integer nightRestCnt02) {
		this.nightRestCnt02 = nightRestCnt02;
	}

	public Integer getNightMealCnt() {
		return nightMealCnt;
	}

	public void setNightMealCnt(Integer nightMealCnt) {
		this.nightMealCnt = nightMealCnt;
	}

	public Integer getNightOvertimeCnt() {
		return nightOvertimeCnt;
	}

	public void setNightOvertimeCnt(Integer nightOvertimeCnt) {
		this.nightOvertimeCnt = nightOvertimeCnt;
	}

	public Integer getDayRestRate01() {
		return dayRestRate01;
	}

	public void setDayRestRate01(Integer dayRestRate01) {
		this.dayRestRate01 = dayRestRate01;
	}

	public Integer getDayRestRate02() {
		return dayRestRate02;
	}

	public void setDayRestRate02(Integer dayRestRate02) {
		this.dayRestRate02 = dayRestRate02;
	}

	public Integer getDayMealRate() {
		return dayMealRate;
	}

	public void setDayMealRate(Integer dayMealRate) {
		this.dayMealRate = dayMealRate;
	}

	public Integer getDayChangeRate() {
		return dayChangeRate;
	}

	public void setDayChangeRate(Integer dayChangeRate) {
		this.dayChangeRate = dayChangeRate;
	}

	public Integer getNightRestRate01() {
		return nightRestRate01;
	}

	public void setNightRestRate01(Integer nightRestRate01) {
		this.nightRestRate01 = nightRestRate01;
	}

	public Integer getNightRestRate02() {
		return nightRestRate02;
	}

	public void setNightRestRate02(Integer nightRestRate02) {
		this.nightRestRate02 = nightRestRate02;
	}

	public Integer getNightMealRate() {
		return nightMealRate;
	}

	public void setNightMealRate(Integer nightMealRate) {
		this.nightMealRate = nightMealRate;
	}

	public Integer getNightOvertimeRate() {
		return nightOvertimeRate;
	}

	public void setNightOvertimeRate(Integer nightOvertimeRate) {
		this.nightOvertimeRate = nightOvertimeRate;
	}

	@Override
	public String toString() {
		return "QgAutoUseView [factoryId=" + factoryId + ", factoryName=" + factoryName + ", areaId=" + areaId
				+ ", areaName=" + areaName + ", machineId=" + machineId + ", machineName=" + machineName
				+ ", productId=" + productId + ", productName=" + productName + ", dayOrNight=" + dayOrNight
				+ ", scheduleName=" + scheduleName + ", scheduleNameArr=" + scheduleNameArr + ", scheduleStartTime="
				+ scheduleStartTime + ", scheduleEndTime=" + scheduleEndTime + ", autoCnt=" + autoCnt
				+ ", dayRestCnt01=" + dayRestCnt01 + ", dayRestRate01=" + dayRestRate01 + ", dayRestCnt02="
				+ dayRestCnt02 + ", dayRestRate02=" + dayRestRate02 + ", dayMealCnt=" + dayMealCnt + ", dayMealRate="
				+ dayMealRate + ", dayChangeCnt=" + dayChangeCnt + ", dayChangeRate=" + dayChangeRate
				+ ", nightRestCnt01=" + nightRestCnt01 + ", nightRestRate01=" + nightRestRate01 + ", nightRestCnt02="
				+ nightRestCnt02 + ", nightRestRate02=" + nightRestRate02 + ", nightMealCnt=" + nightMealCnt
				+ ", nightMealRate=" + nightMealRate + ", nightOvertimeCnt=" + nightOvertimeCnt + ", nightOvertimeRate="
				+ nightOvertimeRate + ", autoRate=" + autoRate + "]";
	}
}
