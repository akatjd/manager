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
public class MachineTool {

    @Id
    @GeneratedValue
    Integer machineToolId;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    String toolName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MACHINE_ID")
    private Machine machine;

    Integer presetCnt;
    
    Integer useCnt;
    
    Integer machineOffsetId;
    
    Float toolWearingMax;
    
    Float toolWearingNow;
    
    String toolSpec;
    
    Integer toolPrice;
    
    Integer path;
    
    String useYn;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date changedDate;

    String viewName;
    
    Integer sortSeq;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Integer getSortSeq() {
        return sortSeq;
    }

    public void setSortSeq(Integer sortSeq) {
        this.sortSeq = sortSeq;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public Integer getMachineToolId() {
        return machineToolId;
    }

    public void setMachineToolId(Integer machineToolId) {
        this.machineToolId = machineToolId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public Integer getPresetCnt() {
        return presetCnt;
    }

    public void setPresetCnt(Integer presetCnt) {
        this.presetCnt = presetCnt;
    }

    public Integer getUseCnt() {
        return useCnt;
    }

    public void setUseCnt(Integer useCnt) {
        this.useCnt = useCnt;
    }

    public Integer getMachineOffsetId() {
        return machineOffsetId;
    }

    public void setMachineOffsetId(Integer machineOffsetId) {
        this.machineOffsetId = machineOffsetId;
    }

    public Float getToolWearingMax() {
        return toolWearingMax;
    }

    public void setToolWearingMax(Float toolWearingMax) {
        this.toolWearingMax = toolWearingMax;
    }

    public Float getToolWearingNow() {
        return toolWearingNow;
    }

    public void setToolWearingNow(Float toolWearingNow) {
        this.toolWearingNow = toolWearingNow;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public String getToolSpec() {
        return toolSpec;
    }

    public void setToolSpec(String toolSpec) {
        this.toolSpec = toolSpec;
    }

    public Integer getToolPrice() {
        return toolPrice;
    }

    public void setToolPrice(Integer toolPrice) {
        this.toolPrice = toolPrice;
    }

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
        this.path = path;
    }

}