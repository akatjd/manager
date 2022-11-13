package kr.co.manager.domain.view;

import java.util.Date;

import kr.co.manager.domain.type.MachineStatusType;
import kr.co.manager.domain.type.WarnStatus;

public class QgMonitoringView {

	Integer machineId;

    String machineName;

    Integer currentProductId;

    MachineStatusType machineStatusType;

    WarnStatus warnStatus;

    Float realTimeEfficiency;

    Date lastProductEventTime;

    String productName;

    float cycleTime;

    Integer todayCnt;

    String viewYn;
    
    String warnMsg;
    
    Integer sortSeq;
    
    String inspectionUse;
    
    String stopUse;
    
    String inspectionActive;
    
    Integer areaId;
    
    Integer statusDelay;
    
    String productChangeStopUse;
    
    String productChangeInspectionUse;
    
    Float totalProductionRate;
    
    Integer goalCnt;
    
    Integer infoCnt;
    
    Integer errorCnt;
    
    Integer stopCnt;
    
    Integer goodCnt;
    
    Integer badsyncCnt;
    
    Date lastStatusDate;
    
    Integer basicSectionAlarm;
    
    // 민성 추가 2021-08-13 표준파형용 날짜
    String standardRegDate;
    
    // 민성 추가 2021-08-13 검사/섹션개수 str
    String inspectionStr;
    
    // 민성 추가 2021-08-20
    String isN1111Detect;

	String isN9999Detect;

	public QgMonitoringView() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "QgMonitoringView [machineId=" + machineId + ", machineName=" + machineName + ", currentProductId="
				+ currentProductId + ", machineStatusType=" + machineStatusType + ", warnStatus=" + warnStatus
				+ ", realTimeEfficiency=" + realTimeEfficiency + ", lastProductEventTime=" + lastProductEventTime
				+ ", productName=" + productName + ", cycleTime=" + cycleTime + ", todayCnt=" + todayCnt + ", viewYn="
				+ viewYn + ", warnMsg=" + warnMsg + ", sortSeq=" + sortSeq + ", inspectionUse=" + inspectionUse
				+ ", stopUse=" + stopUse + ", inspectionActive=" + inspectionActive + ", areaId=" + areaId
				+ ", statusDelay=" + statusDelay + ", productChangeStopUse=" + productChangeStopUse
				+ ", productChangeInspectionUse=" + productChangeInspectionUse + ", totalProductionRate="
				+ totalProductionRate + ", goalCnt=" + goalCnt + ", infoCnt=" + infoCnt + ", errorCnt=" + errorCnt
				+ ", stopCnt=" + stopCnt + ", goodCnt=" + goodCnt + ", badsyncCnt=" + badsyncCnt + ", lastStatusDate="
				+ lastStatusDate + ", basicSectionAlarm=" + basicSectionAlarm + ", standardRegDate=" + standardRegDate
				+ ", inspectionStr=" + inspectionStr + ", isN1111Detect=" + isN1111Detect + ", isN9999Detect="
				+ isN9999Detect + "]";
	}
    
    public QgMonitoringView(Object[] objArr) {
        this.machineId = (Integer) objArr[0];
        this.machineName = (String) objArr[1];
        this.machineStatusType = MachineStatusType.valueOf((String) objArr[2]);
        this.warnStatus = WarnStatus.valueOf((String) objArr[3]);
        this.realTimeEfficiency = (Float) (objArr[4] == null ? 0.0f : objArr[4]);
        this.viewYn = (String) objArr[5];
        this.warnMsg = (String) objArr[6];
        this.sortSeq = (Integer) objArr[7];
        this.inspectionUse = (String) objArr[8];
        this.stopUse = (String) objArr[9];
        this.lastProductEventTime = (Date) objArr[10];
        this.lastStatusDate = (Date) objArr[11];
        this.productName = (String) objArr[12];
        this.cycleTime = (Float) (objArr[13] == null ? 0.0f : objArr[13]);
        this.todayCnt = (Integer) (objArr[14] == null ? 0 : objArr[14]);
        this.inspectionActive = (String) objArr[15];
        this.areaId = (Integer) objArr[16];
        this.currentProductId = (Integer) objArr[17];
        this.totalProductionRate = (Float) (objArr[18] == null ? 0.0f : objArr[18]);
        this.goalCnt = (Integer) objArr[19];
        this.statusDelay = (Integer) objArr[20];
        this.productChangeStopUse = (String) objArr[21];
        this.productChangeInspectionUse = (String) objArr[22];
        this.basicSectionAlarm = (Integer) objArr[23];
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

	public Integer getCurrentProductId() {
		return currentProductId;
	}

	public void setCurrentProductId(Integer currentProductId) {
		this.currentProductId = currentProductId;
	}

	public MachineStatusType getMachineStatusType() {
		return machineStatusType;
	}

	public void setMachineStatusType(MachineStatusType machineStatusType) {
		this.machineStatusType = machineStatusType;
	}

	public WarnStatus getWarnStatus() {
		return warnStatus;
	}

	public void setWarnStatus(WarnStatus warnStatus) {
		this.warnStatus = warnStatus;
	}

	public Float getRealTimeEfficiency() {
		return realTimeEfficiency;
	}

	public void setRealTimeEfficiency(Float realTimeEfficiency) {
		this.realTimeEfficiency = realTimeEfficiency;
	}

	public Date getLastProductEventTime() {
		return lastProductEventTime;
	}

	public void setLastProductEventTime(Date lastProductEventTime) {
		this.lastProductEventTime = lastProductEventTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getCycleTime() {
		return cycleTime;
	}

	public void setCycleTime(float cycleTime) {
		this.cycleTime = cycleTime;
	}

	public Integer getTodayCnt() {
		return todayCnt;
	}

	public void setTodayCnt(Integer todayCnt) {
		this.todayCnt = todayCnt;
	}

	public String getViewYn() {
		return viewYn;
	}

	public void setViewYn(String viewYn) {
		this.viewYn = viewYn;
	}

	public String getWarnMsg() {
		return warnMsg;
	}

	public void setWarnMsg(String warnMsg) {
		this.warnMsg = warnMsg;
	}

	public Integer getSortSeq() {
		return sortSeq;
	}

	public void setSortSeq(Integer sortSeq) {
		this.sortSeq = sortSeq;
	}

	public String getInspectionUse() {
		return inspectionUse;
	}

	public void setInspectionUse(String inspectionUse) {
		this.inspectionUse = inspectionUse;
	}

	public String getStopUse() {
		return stopUse;
	}

	public void setStopUse(String stopUse) {
		this.stopUse = stopUse;
	}

	public String getInspectionActive() {
		return inspectionActive;
	}

	public void setInspectionActive(String inspectionActive) {
		this.inspectionActive = inspectionActive;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getStatusDelay() {
		return statusDelay;
	}

	public void setStatusDelay(Integer statusDelay) {
		this.statusDelay = statusDelay;
	}

	public String getProductChangeStopUse() {
		return productChangeStopUse;
	}

	public void setProductChangeStopUse(String productChangeStopUse) {
		this.productChangeStopUse = productChangeStopUse;
	}

	public String getProductChangeInspectionUse() {
		return productChangeInspectionUse;
	}

	public void setProductChangeInspectionUse(String productChangeInspectionUse) {
		this.productChangeInspectionUse = productChangeInspectionUse;
	}

	public Float getTotalProductionRate() {
		return totalProductionRate;
	}

	public void setTotalProductionRate(Float totalProductionRate) {
		this.totalProductionRate = totalProductionRate;
	}

	public Integer getGoalCnt() {
		return goalCnt;
	}

	public void setGoalCnt(Integer goalCnt) {
		this.goalCnt = goalCnt;
	}

	public Integer getInfoCnt() {
		return infoCnt;
	}

	public void setInfoCnt(Integer infoCnt) {
		this.infoCnt = infoCnt;
	}

	public Integer getErrorCnt() {
		return errorCnt;
	}

	public void setErrorCnt(Integer errorCnt) {
		this.errorCnt = errorCnt;
	}

	public Integer getStopCnt() {
		return stopCnt;
	}

	public void setStopCnt(Integer stopCnt) {
		this.stopCnt = stopCnt;
	}

	public Integer getGoodCnt() {
		return goodCnt;
	}

	public void setGoodCnt(Integer goodCnt) {
		this.goodCnt = goodCnt;
	}

	public Integer getBadsyncCnt() {
		return badsyncCnt;
	}

	public void setBadsyncCnt(Integer badsyncCnt) {
		this.badsyncCnt = badsyncCnt;
	}

	public Date getLastStatusDate() {
		return lastStatusDate;
	}

	public void setLastStatusDate(Date lastStatusDate) {
		this.lastStatusDate = lastStatusDate;
	}

	public Integer getBasicSectionAlarm() {
		return basicSectionAlarm;
	}

	public void setBasicSectionAlarm(Integer basicSectionAlarm) {
		this.basicSectionAlarm = basicSectionAlarm;
	}

	public String getStandardRegDate() {
		return standardRegDate;
	}

	public void setStandardRegDate(String standardRegDate) {
		this.standardRegDate = standardRegDate;
	}

	public String getInspectionStr() {
		return inspectionStr;
	}

	public void setInspectionStr(String inspectionStr) {
		this.inspectionStr = inspectionStr;
	}
	
	public String getIsN1111Detect() {
		return isN1111Detect;
	}

	public void setIsN1111Detect(String isN1111Detect) {
		this.isN1111Detect = isN1111Detect;
	}

	public String getIsN9999Detect() {
		return isN9999Detect;
	}

	public void setIsN9999Detect(String isN9999Detect) {
		this.isN9999Detect = isN9999Detect;
	}
}