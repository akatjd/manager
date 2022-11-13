package kr.co.manager.domain.view;

import kr.co.manager.domain.jpa.MachineTool;

import java.util.Date;

public class MachineToolView {

    Integer machineToolId;

    Date regDate;

    String toolName;

    Integer presetCnt;
    
    Integer useCnt;
    
    Integer machineOffsetId;
    
    Float toolWearingMax;
    
    Float toolWearingNow;
    
    String toolSpec;
    
    Integer toolPrice;
    
    Integer path;
    
    String useYn;
    
    Date changedDate;

    String viewName;
    
    Integer sortSeq;

    public MachineToolView(MachineTool machineTool) {
        this.machineToolId = machineTool.getMachineToolId();
        this.regDate = machineTool.getRegDate();
        this.toolName = machineTool.getToolName();
        this.presetCnt = machineTool.getPresetCnt();
        this.useCnt = machineTool.getUseCnt();
        this.machineOffsetId = machineTool.getMachineOffsetId();
        this.toolWearingMax = machineTool.getToolWearingMax();
        this.toolWearingNow = machineTool.getToolWearingNow();
        this.toolSpec = machineTool.getToolSpec();
        this.toolPrice = machineTool.getToolPrice();
        this.path = machineTool.getPath();
        this.useYn = machineTool.getUseYn();
        this.changedDate = machineTool.getChangedDate();
        this.viewName = machineTool.getViewName();
        this.sortSeq = machineTool.getSortSeq();
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
}
