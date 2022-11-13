package kr.co.manager.domain.view;

import kr.co.manager.domain.jpa.Process;

public class ProcessView {

	Integer processId;

    String processName;

    String useYn;

    String areaName;
    
    Integer areaId;
    
    String factoryName;
    
    Integer factoryId;

    public ProcessView() {
        super();
    }

    public ProcessView(Process p) {
        this.processId = p.getProcessId();
        this.processName = p.getProcessName();
        this.useYn = p.getUseYn();
        this.areaName = p.getArea().getAreaName();
        this.areaId = p.getArea().getAreaId();
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
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

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
