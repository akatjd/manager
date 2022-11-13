package kr.co.manager.domain.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.co.manager.domain.type.FeederType;
import kr.co.manager.domain.type.MachineStatusType;
import kr.co.manager.domain.type.WarnStatus;

@Entity
public class Machine {

	@Id    
    @GeneratedValue
    Integer machineId;

    @ManyToOne
    @JoinColumn(name="QG_CLIENT_ID")
    QgClient qgClient;

    @Column(length = 10)
    String machineType;

    @Column(length = 20)
    String cncVendor;

    @Column(length = 10)
    String cncVersion;

    @Column(length = 30)
    String cncSerial;

    @Column(length = 20)
    String machineName;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    Integer currentProductId;

    String cncType;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    FeederType feederType;

    @Enumerated(EnumType.STRING)
    MachineStatusType machineStatusType;

    @Enumerated(EnumType.STRING)
    WarnStatus warnStatus;
    
    @OneToMany(mappedBy="machine", fetch = FetchType.LAZY)    
    private List<MachineStatus> machineStatus;

    @Column(length = 10)
    Float realTimeEfficiency;
    
    @Column(length = 10)
    Float realTimeCycleTime;

    String useMachineCount;

    String warnMsg;
    
    Integer areaId;

    String viewYn;
    
    Integer sortSeq;
    
    String inspectionUse;
    
    String stopUse;
    
    String operateMessage;
    
    Integer feedRate;
    
    Integer spindleSpeed;
    
    String machineAlarm;

    @Temporal(TemporalType.TIMESTAMP)
    Date lastProductDate;
    
    Float totalProductionRate;


    Integer goalCnt;
    
    Integer statusDelay;
    
    String productChangeStopUse;
    
    String productChangeInspectionUse;
    
    Integer warnItemId;
    
    Integer warnSectionPieceId;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date lastStatusDate;
    
    Integer todayCnt;
    
    Integer palletsCnt;
    @Transient
    @JsonSerialize
    @JsonDeserialize
    Integer insCnt;

    Integer processId;
    
    String ncprogramChangeStopUse;
    
    String ncprogramChangeInspectionUse;
    
    Integer rapidOverride;
    
    String totalTargetYn;
    
    Integer machineCnt;
    
    Integer temp;
    
    Integer lastMachiningStatusId;
    
    Integer lastMachineStatusId;
    
    Integer basicSectionAlarm;
    
    public Integer getBasicSectionAlarm() {
        return basicSectionAlarm;
    }

    public void setBasicSectionAlarm(Integer basicSectionAlarm) {
        this.basicSectionAlarm = basicSectionAlarm;
    }

    public Integer getMachineCnt() {
        return machineCnt;
    }

    public void setMachineCnt(Integer machineCnt) {
        this.machineCnt = machineCnt;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getLastMachineStatusId() {
        return lastMachineStatusId;
    }

    public void setLastMachineStatusId(Integer lastMachineStatusId) {
        this.lastMachineStatusId = lastMachineStatusId;
    }

    public Integer getLastMachiningStatusId() {
        return lastMachiningStatusId;
    }

    public void setLastMachiningStatusId(Integer lastMachiningStatusId) {
        this.lastMachiningStatusId = lastMachiningStatusId;
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

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getPalletsCnt() {
        return palletsCnt;
    }

    public void setPalletsCnt(Integer palletsCnt) {
        this.palletsCnt = palletsCnt;
    }

    public Integer getTodayCnt() {
        return todayCnt;
    }

    public void setTodayCnt(Integer todayCnt) {
        this.todayCnt = todayCnt;
    }

    public Date getLastStatusDate() {
        return lastStatusDate;
    }

    public void setLastStatusDate(Date lastStatusDate) {
        this.lastStatusDate = lastStatusDate;
    }

    public Integer getWarnItemId() {
        return warnItemId;
    }

    public void setWarnItemId(Integer warnItemId) {
        this.warnItemId = warnItemId;
    }

    public Integer getWarnSectionPieceId() {
        return warnSectionPieceId;
    }

    public void setWarnSectionPieceId(Integer warnSectionPieceId) {
        this.warnSectionPieceId = warnSectionPieceId;
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

    public void setTotalProductionRate(Float totalProductionRate) {
        this.totalProductionRate = totalProductionRate;
    }

    public Date getLastProductDate() {
        return lastProductDate;
    }

    public void setLastProductDate(Date lastProductDate) {
        this.lastProductDate = lastProductDate;
    }

    public String getMachineAlarm() {
        return machineAlarm;
    }

    public void setMachineAlarm(String machineAlarm) {
        this.machineAlarm = machineAlarm;
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

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getCncType() {
        return cncType;
    }

    public void setCncType(String cncType) {
        this.cncType = cncType;
    }

    public String getUseMachineCount() {
        return useMachineCount;
    }

    public void setUseMachineCount(String useMachineCount) {
        this.useMachineCount = useMachineCount;
    }

//    public Factory getFactory() {
//        return factory;
//    }
//
//    public void setFactory(Factory factory) {
//        this.factory = factory;
//    }

    public Integer getMachineId() {

        return machineId;
    }

    public QgClient getQgClient() {
        return qgClient;
    }

    public void setQgClient(QgClient qgClient) {
        this.qgClient = qgClient;
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

    public List<MachineStatus> getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(List<MachineStatus> machineStatus) {
        this.machineStatus = machineStatus;
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

    public Integer getGoalCnt() {
        return goalCnt;
    }

    public void setGoalCnt(Integer goalCnt) {
        this.goalCnt = goalCnt;
    }

	@Override
	public String toString() {
		return "Machine [machineId=" + machineId + "\n qgClient=" + qgClient + "\n machineType=" + machineType
				+ "\n cncVendor=" + cncVendor + "\n cncVersion=" + cncVersion + "\n cncSerial=" + cncSerial
				+ "\n machineName=" + machineName + "\n regDate=" + regDate + "\n currentProductId=" + currentProductId
				+ "\n cncType=" + cncType + "\n feederType=" + feederType + "\n machineStatusType=" + machineStatusType
				+ "\n warnStatus=" + warnStatus + "\n machineStatus=" + machineStatus + "\n realTimeEfficiency="
				+ realTimeEfficiency + "\n useMachineCount=" + useMachineCount + "\n warnMsg=" + warnMsg + "\n areaId="
				+ areaId + "\n viewYn=" + viewYn + "\n sortSeq=" + sortSeq + "\n inspectionUse=" + inspectionUse
				+ "\n stopUse=" + stopUse + "\n operateMessage=" + operateMessage + "\n feedRate=" + feedRate
				+ "\n spindleSpeed=" + spindleSpeed + "\n machineAlarm=" + machineAlarm + "\n lastProductDate="
				+ lastProductDate + "\n totalProductionRate=" + totalProductionRate + "\n goalCnt=" + goalCnt
				+ "\n statusDelay=" + statusDelay + "\n productChangeStopUse=" + productChangeStopUse
				+ "\n productChangeInspectionUse=" + productChangeInspectionUse + "\n warnItemId=" + warnItemId
				+ "\n warnSectionPieceId=" + warnSectionPieceId + "\n lastStatusDate=" + lastStatusDate + "\n todayCnt="
				+ todayCnt + "\n palletsCnt=" + palletsCnt + "\n insCnt=" + insCnt + "\n processId=" + processId + "]";
	}
}
