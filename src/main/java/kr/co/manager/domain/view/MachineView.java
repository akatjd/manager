package kr.co.manager.domain.view;

import java.util.Date;
import java.util.List;

import kr.co.manager.domain.jpa.Machine;
import kr.co.manager.domain.jpa.MachineStatus;
import kr.co.manager.domain.jpa.QgClient;
import kr.co.manager.domain.type.FeederType;
import kr.co.manager.domain.type.MachineStatusType;
import kr.co.manager.domain.type.WarnStatus;

public class MachineView {

//    Factory factory;

    QgClient qgClient;

    Integer machineId;

    String machineType;

    String cncVendor;

    String cncVersion;

    String cncSerial;

    String machineName;

    Date regDate;

    Integer currentProductId;

    String cncType;

    FeederType feederType;

    MachineStatusType machineStatusType;

    WarnStatus warnStatus;

    private List<MachineStatus> machineStatus;

    Float realTimeEfficiency;

    String useMachineCount;

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
    
    String operateMessage;
    
    Integer feedRate;
    
    Integer spindleSpeed;
    
    Integer rapidOverride;
    
    String machineAlarm;
    
    Integer qgClientId;
    
    String clientName;
    
    String factoryName;
    
    Integer factoryId;
    
    Float totalProductionRate;
    
    Integer goalCnt;
    
    Integer statusDelay;
    
    String productChangeStopUse;
    
    String productChangeInspectionUse;
    
    Integer infoCnt;
    
    Integer warnCnt;
    
    Integer errorCnt;
    
    Integer stopCnt;
    
    Integer goodCnt;
    
    Integer badsyncCnt;
    
    Integer gcodeChangeCnt;
    
    Integer productChangeCnt;
    
    Date issueEndDate;
    
    Integer issueId;
    
    String writeUser;
    
    List<MachineToolView> toolList;
    
    Date lastStatusDate;
    
    Integer palletsCnt;
    
    String areaName;
    
    Integer processId;
    
    String processName;
    
    Float realTimeCycleTime;
    
    String ncprogramChangeStopUse;
    
    String ncprogramChangeInspectionUse;
    
    String totalTargetYn;
    
    Integer temp;
    
    Integer basicSectionAlarm;
    
    // 민성 표준파형용 날짜 추가
    String standardRegDate;
    
    // 민성 검사/섹션개수 str 추가
    String inspectionStr;

	public String getInspectionStr() {
		return inspectionStr;
	}

	public void setInspectionStr(String inspectionStr) {
		this.inspectionStr = inspectionStr;
	}

	public String getStandardRegDate() {
		return standardRegDate;
	}

	public void setStandardRegDate(String standardRegDate) {
		this.standardRegDate = standardRegDate;
	}

	public MachineView(Machine machine) {
        this.machineId = machine.getMachineId();
        this.machineType = machine.getMachineType();
        this.cncVendor = machine.getCncVendor();
        this.cncVersion = machine.getCncVersion();
        this.cncSerial = machine.getCncSerial();
        this.machineName = machine.getMachineName();
        this.regDate = machine.getRegDate();
        this.currentProductId = machine.getCurrentProductId();
        this.cncType = machine.getCncType();
        this.feederType = machine.getFeederType();
        this.machineStatusType = machine.getMachineStatusType();
        this.warnStatus = machine.getWarnStatus();
        this.machineStatus = machine.getMachineStatus();
        this.realTimeEfficiency = machine.getRealTimeEfficiency();
        this.useMachineCount = machine.getUseMachineCount();
        this.viewYn = machine.getViewYn();
        this.warnMsg = machine.getWarnMsg();
        this.sortSeq = machine.getSortSeq();
        this.inspectionUse = machine.getInspectionUse();
        this.stopUse = machine.getStopUse();
        this.operateMessage = machine.getOperateMessage();
        this.feedRate = machine.getFeedRate();
        this.spindleSpeed = machine.getSpindleSpeed();
        this.rapidOverride = machine.getRapidOverride();
        this.machineAlarm = machine.getMachineAlarm();
        if(machine.getQgClient() != null){
            this.qgClientId = machine.getQgClient().getQgClientId();
            this.clientName = machine.getQgClient().getClientName();
            if(machine.getQgClient().getFactory() != null){
                this.factoryName = machine.getQgClient().getFactory().getFactoryName();
            }
        }
        this.areaId = machine.getAreaId();
        this.totalProductionRate = machine.getTotalProductionRate();
        this.goalCnt = machine.getGoalCnt();
        this.statusDelay = machine.getStatusDelay();
        this.productChangeStopUse = machine.getProductChangeStopUse();
        this.productChangeInspectionUse = machine.getProductChangeInspectionUse();
        this.lastStatusDate = machine.getLastStatusDate();
        this.palletsCnt = machine.getPalletsCnt();
        this.realTimeCycleTime = machine.getRealTimeCycleTime();
        this.ncprogramChangeInspectionUse = machine.getNcprogramChangeInspectionUse();
        this.ncprogramChangeStopUse = machine.getNcprogramChangeStopUse();
        this.totalTargetYn = machine.getTotalTargetYn();
        this.temp = machine.getTemp();
    }
    
    public MachineView(Object[] objArr) {
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

    public MachineView() {
        // TODO Auto-generated constructor stub
    }

    public Integer getBasicSectionAlarm() {
        return basicSectionAlarm;
    }

    public void setBasicSectionAlarm(Integer basicSectionAlarm) {
        this.basicSectionAlarm = basicSectionAlarm;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public String getTotalTargetYn() {
        return totalTargetYn;
    }

    public void setTotalTargetYn(String totalTargetYn) {
        this.totalTargetYn = totalTargetYn;
    }

    public Integer getRapidOverride() {
        return rapidOverride;
    }

    public void setRapidOverride(Integer rapidOverride) {
        this.rapidOverride = rapidOverride;
    }

    public String getNcprogramChangeStopUse() {
        return ncprogramChangeStopUse;
    }

    public void setNcprogramChangeStopUse(String ncprogramChangeStopUse) {
        this.ncprogramChangeStopUse = ncprogramChangeStopUse;
    }

    public String getNcprogramChangeInspectionUse() {
        return ncprogramChangeInspectionUse;
    }

    public void setNcprogramChangeInspectionUse(String ncprogramChangeInspectionUse) {
        this.ncprogramChangeInspectionUse = ncprogramChangeInspectionUse;
    }

    public Float getRealTimeCycleTime() {
        return realTimeCycleTime;
    }

    public void setRealTimeCycleTime(Float realTimeCycleTime) {
        this.realTimeCycleTime = realTimeCycleTime;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Integer getPalletsCnt() {
        return palletsCnt;
    }

    public void setPalletsCnt(Integer palletsCnt) {
        this.palletsCnt = palletsCnt;
    }

    public Date getLastStatusDate() {
        return lastStatusDate;
    }

    public void setLastStatusDate(Date lastStatusDate) {
        this.lastStatusDate = lastStatusDate;
    }

    public List<MachineToolView> getToolList() {
        return toolList;
    }

    public void setToolList(List<MachineToolView> toolList) {
        this.toolList = toolList;
    }

    public Integer getBadsyncCnt() {
        return badsyncCnt;
    }

    public void setBadsyncCnt(Integer badsyncCnt) {
        this.badsyncCnt = badsyncCnt;
    }

    public Integer getGoodCnt() {
        return goodCnt;
    }

    public void setGoodCnt(Integer goodCnt) {
        this.goodCnt = goodCnt;
    }

    public String getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(String writeUser) {
        this.writeUser = writeUser;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public Date getIssueEndDate() {
        return issueEndDate;
    }

    public void setIssueEndDate(Date issueEndDate) {
        this.issueEndDate = issueEndDate;
    }

    public Integer getGcodeChangeCnt() {
        return gcodeChangeCnt;
    }

    public void setGcodeChangeCnt(Integer gcodeChangeCnt) {
        this.gcodeChangeCnt = gcodeChangeCnt;
    }

    public Integer getProductChangeCnt() {
        return productChangeCnt;
    }

    public void setProductChangeCnt(Integer productChangeCnt) {
        this.productChangeCnt = productChangeCnt;
    }

    public Integer getInfoCnt() {
        return infoCnt;
    }

    public void setInfoCnt(Integer infoCnt) {
        this.infoCnt = infoCnt;
    }


    public Integer getWarnCnt() {
        return warnCnt;
    }

    public void setWarnCnt(Integer warnCnt) {
        this.warnCnt = warnCnt;
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

    public Integer getStatusDelay() {
        return statusDelay;
    }

    public void setStatusDelay(Integer statusDelay) {
        this.statusDelay = statusDelay;
    }

    public Float getTotalProductionRate() {
        return totalProductionRate;
    }

    public Integer getGoalCnt() {
        return goalCnt;
    }

    public void setGoalCnt(Integer goalCnt) {
        this.goalCnt = goalCnt;
    }

    public void setTotalProductionRate(Float totalProductionRate) {
        this.totalProductionRate = totalProductionRate;
    }

    public Integer getQgClientId() {
        return qgClientId;
    }

    public void setQgClientId(Integer qgClientId) {
        this.qgClientId = qgClientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getOperateMessage() {
        return operateMessage;
    }

    public void setOperateMessage(String operateMessage) {
        this.operateMessage = operateMessage;
    }

    public Integer getFeedRate() {
        return feedRate;
    }

    public void setFeedRate(Integer feedRate) {
        this.feedRate = feedRate;
    }

    public Integer getSpindleSpeed() {
        return spindleSpeed;
    }

    public void setSpindleSpeed(Integer spindleSpeed) {
        this.spindleSpeed = spindleSpeed;
    }

    public String getMachineAlarm() {
        return machineAlarm;
    }

    public void setMachineAlarm(String machineAlarm) {
        this.machineAlarm = machineAlarm;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getInspectionActive() {
        return inspectionActive;
    }

    public void setInspectionActive(String inspectionActive) {
        this.inspectionActive = inspectionActive;
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

    public String getWarnMsg() {
        return warnMsg;
    }

    public void setWarnMsg(String warnMsg) {
        this.warnMsg = warnMsg;
    }
    
    public String getViewYn() {
        return viewYn;
    }

    public void setViewYn(String viewYn) {
        this.viewYn = viewYn;
    }


    //    public Factory getFactory() {
//        return factory;
//    }
//
//    public void setFactory(Factory factory) {
//        this.factory = factory;
//    }

    public QgClient getQgClient() {
        return qgClient;
    }

    public void setQgClient(QgClient qgClient) {
        this.qgClient = qgClient;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getCncVendor() {
        return cncVendor;
    }

    public void setCncVendor(String cncVendor) {
        this.cncVendor = cncVendor;
    }

    public String getCncVersion() {
        return cncVersion;
    }

    public void setCncVersion(String cncVersion) {
        this.cncVersion = cncVersion;
    }

    public String getCncSerial() {
        return cncSerial;
    }

    public void setCncSerial(String cncSerial) {
        this.cncSerial = cncSerial;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getCurrentProductId() {
        return currentProductId;
    }

    public void setCurrentProductId(Integer currentProductId) {
        this.currentProductId = currentProductId;
    }

    public String getCncType() {
        return cncType;
    }

    public void setCncType(String cncType) {
        this.cncType = cncType;
    }

    public FeederType getFeederType() {
        return feederType;
    }

    public void setFeederType(FeederType feederType) {
        this.feederType = feederType;
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

    public List<MachineStatus> getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(List<MachineStatus> machineStatus) {
        this.machineStatus = machineStatus;
    }

    public Float getRealTimeEfficiency() {
        return realTimeEfficiency;
    }

    public void setRealTimeEfficiency(Float realTimeEfficiency) {
        this.realTimeEfficiency = realTimeEfficiency;
    }

    public String getUseMachineCount() {
        return useMachineCount;
    }

    public void setUseMachineCount(String useMachineCount) {
        this.useMachineCount = useMachineCount;
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
}
