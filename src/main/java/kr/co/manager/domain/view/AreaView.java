package kr.co.manager.domain.view;

import java.util.List;

import kr.co.manager.domain.jpa.Area;

public class AreaView {

	Integer areaId;

    String areaName;

    Integer factoryId;

    String factoryName;
    
    List<ProcessView> pViewList;
    
    public AreaView() {
        super();
    }

    public AreaView(Area area) {
        this.areaId = area.getAreaId();
        this.areaName = area.getAreaName();

    }

    public List<ProcessView> getpViewList() {
        return pViewList;
    }

    public void setpViewList(List<ProcessView> pViewList) {
        this.pViewList = pViewList;
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
}
