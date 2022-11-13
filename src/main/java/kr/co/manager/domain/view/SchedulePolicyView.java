package kr.co.manager.domain.view;

import kr.co.manager.domain.jpa.SchedulePolicy;

public class SchedulePolicyView {

	Integer policyId;
	
    String policyName;
    
    Integer factoryId;
    
    String factoryName;

    String allowDayType;
    
    String allowDayTypeNm;
    
    String dayBeginTime;
    
    String dayEndTime;
    
    String nightBeginTime;
    
    String nightEndTime;
    
    String dayUseYn;
    
    String nightUseYn;
    
    String scheduleUseYn;

    public SchedulePolicyView(SchedulePolicy schedulePolicy) {
        this.policyId = schedulePolicy.getPolicyId();
        this.policyName = schedulePolicy.getPolicyName();
        this.factoryId = schedulePolicy.getFactory().getFactoryId();
        this.factoryName = schedulePolicy.getFactory().getFactoryName();
        this.dayBeginTime = schedulePolicy.getDayBeginTime();
        this.dayEndTime = schedulePolicy.getDayEndTime();
        this.nightBeginTime = schedulePolicy.getNightBeginTime();
        this.nightEndTime = schedulePolicy.getNightEndTime();
        this.dayUseYn = schedulePolicy.getDayUseYn();
        this.nightUseYn = schedulePolicy.getNightUseYn();
        this.scheduleUseYn = schedulePolicy.getScheduleUseYn();
        this.allowDayType = schedulePolicy.getAllowDayType();
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
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

    public String getAllowDayType() {
        return allowDayType;
    }

    public void setAllowDayType(String allowDayType) {
        this.allowDayType = allowDayType;
    }

    public String getAllowDayTypeNm() {
        return allowDayTypeNm;
    }

    public void setAllowDayTypeNm(String allowDayTypeNm) {
        this.allowDayTypeNm = allowDayTypeNm;
    }

    public String getDayBeginTime() {
        return dayBeginTime;
    }

    public void setDayBeginTime(String dayBeginTime) {
        this.dayBeginTime = dayBeginTime;
    }

    public String getDayEndTime() {
        return dayEndTime;
    }

    public void setDayEndTime(String dayEndTime) {
        this.dayEndTime = dayEndTime;
    }

    public String getNightBeginTime() {
        return nightBeginTime;
    }

    public void setNightBeginTime(String nightBeginTime) {
        this.nightBeginTime = nightBeginTime;
    }

    public String getNightEndTime() {
        return nightEndTime;
    }

    public void setNightEndTime(String nightEndTime) {
        this.nightEndTime = nightEndTime;
    }

    public String getDayUseYn() {
        return dayUseYn;
    }

    public void setDayUseYn(String dayUseYn) {
        this.dayUseYn = dayUseYn;
    }

    public String getNightUseYn() {
        return nightUseYn;
    }

    public void setNightUseYn(String nightUseYn) {
        this.nightUseYn = nightUseYn;
    }

    public String getScheduleUseYn() {
        return scheduleUseYn;
    }

    public void setScheduleUseYn(String scheduleUseYn) {
        this.scheduleUseYn = scheduleUseYn;
    }
}
