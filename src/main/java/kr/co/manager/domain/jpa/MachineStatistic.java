package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MachineStatistic {

    @Id
    @GeneratedValue
    Integer machineStatisticId;

    @ManyToOne
    @JoinColumn(name="MACHINE_ID")
    Machine machine;

    @Temporal(TemporalType.TIMESTAMP)
    Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date endDate;

    Integer lastTotalCnt;

    Long runTime;

    Long holdTime;

    Long stopTime;

    Long disTime;

    Long mtTime;

    Long clTime;

    Long gcTime;

    Long tcTime;

    Long failureTime;

    String toolChangeList;

    Integer schedulePolicyId;

    String statisticContent;

    String statisticDate;
    
    Integer dayType;


    public Integer getDayType() {
        return dayType;
    }

    public void setDayType(Integer dayType) {
        this.dayType = dayType;
    }

    public Integer getSchedulePolicyId() {
        return schedulePolicyId;
    }

    public void setSchedulePolicyId(Integer schedulePolicyId) {
        this.schedulePolicyId = schedulePolicyId;
    }

    public String getStatisticContent() {
        return statisticContent;
    }

    public void setStatisticContent(String statisticContent) {
        this.statisticContent = statisticContent;
    }

    public String getStatisticDate() {
        return statisticDate;
    }

    public void setStatisticDate(String statisticDate) {
        this.statisticDate = statisticDate;
    }

    public Integer getMachineStatisticId() {
        return machineStatisticId;
    }

    public void setMachineStatisticId(Integer machineStatisticId) {
        this.machineStatisticId = machineStatisticId;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getLastTotalCnt() {
        return lastTotalCnt;
    }

    public void setLastTotalCnt(Integer lastTotalCnt) {
        this.lastTotalCnt = lastTotalCnt;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public Long getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(Long holdTime) {
        this.holdTime = holdTime;
    }

    public Long getStopTime() {
        return stopTime;
    }

    public void setStopTime(Long stopTime) {
        this.stopTime = stopTime;
    }

    public Long getDisTime() {
        return disTime;
    }

    public void setDisTime(Long disTime) {
        this.disTime = disTime;
    }

    public Long getMtTime() {
        return mtTime;
    }

    public void setMtTime(Long mtTime) {
        this.mtTime = mtTime;
    }

    public Long getClTime() {
        return clTime;
    }

    public void setClTime(Long clTime) {
        this.clTime = clTime;
    }

    public Long getGcTime() {
        return gcTime;
    }

    public void setGcTime(Long gcTime) {
        this.gcTime = gcTime;
    }

    public Long getTcTime() {
        return tcTime;
    }

    public void setTcTime(Long tcTime) {
        this.tcTime = tcTime;
    }

    public Long getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Long failureTime) {
        this.failureTime = failureTime;
    }

    public String getToolChangeList() {
        return toolChangeList;
    }

    public void setToolChangeList(String toolChangeList) {
        this.toolChangeList = toolChangeList;
    }
}
