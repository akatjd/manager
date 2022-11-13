package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MachineToolHistory {

    @Id
    @GeneratedValue
    Integer machineToolHistoryId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MACHINE_TOOL_ID")
    MachineTool machineTool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MACHINE_ID")
    Machine machine;
    
    String toolName;
    
    Integer useCnt;

    Integer presetCnt;
    
    Integer machineOffsetId;
    
    Integer currentProductId;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date changedDate;
    
    String currentViewName;
    
    public String getCurrentViewName() {
        return currentViewName;
    }

    public void setCurrentViewName(String currentViewName) {
        this.currentViewName = currentViewName;
    }

    public Integer getCurrentProductId() {
        return currentProductId;
    }

    public void setCurrentProductId(Integer currentProductId) {
        this.currentProductId = currentProductId;
    }

    public Integer getMachineToolHistoryId() {
        return machineToolHistoryId;
    }

    public void setMachineToolHistoryId(Integer machineToolHistoryId) {
        this.machineToolHistoryId = machineToolHistoryId;
    }

    public MachineTool getMachineTool() {
        return machineTool;
    }

    public void setMachineTool(MachineTool machineTool) {
        this.machineTool = machineTool;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public Integer getUseCnt() {
        return useCnt;
    }

    public void setUseCnt(Integer useCnt) {
        this.useCnt = useCnt;
    }

    public Integer getPresetCnt() {
        return presetCnt;
    }

    public void setPresetCnt(Integer presetCnt) {
        this.presetCnt = presetCnt;
    }

    public Integer getMachineOffsetId() {
        return machineOffsetId;
    }

    public void setMachineOffsetId(Integer machineOffsetId) {
        this.machineOffsetId = machineOffsetId;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    @Override
    public String toString() {
        return "MachineToolHistory [machineToolHistoryId=" + machineToolHistoryId + ", machineTool=" + machineTool
                + ", machine=" + machine + ", toolName=" + toolName + ", useCnt=" + useCnt + ", presetCnt=" + presetCnt
                + ", machineOffsetId=" + machineOffsetId + ", currentProductId=" + currentProductId + ", changedDate="
                + changedDate + "]";
    }
}
