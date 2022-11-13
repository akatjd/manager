package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class SchedulePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer policyId;

    @ManyToOne
    @JoinColumn(name = "FACTORY_ID")
    Factory factory;

    String policyName;

//    Integer startWorkingTime;

//    Integer duration;

    String allowDayType;

//    String allowMachine;

//    String startWorkTime;

//    String endWorkTime;
    
    String dayBeginTime;
    String dayEndTime;
    String nightBeginTime;
    String nightEndTime;
    String dayUseYn;
    String nightUseYn;
    String scheduleUseYn;
    @Transient
    @JsonSerialize
    @JsonDeserialize
    Date startWorkTimeDate;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    Date endWorkTimeDate;


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

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Date getStartWorkTimeDate() {
        return startWorkTimeDate;
    }

    public void setStartWorkTimeDate(Date startWorkTimeDate) {
        this.startWorkTimeDate = startWorkTimeDate;
    }

    public Date getEndWorkTimeDate() {
        return endWorkTimeDate;
    }

    public void setEndWorkTimeDate(Date endWorkTimeDate) {
        this.endWorkTimeDate = endWorkTimeDate;
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

//    public Integer getStartWorkingTime() {
//        return startWorkingTime;
//    }
//
//    public void setStartWorkingTime(Integer startWorkingTime) {
//        this.startWorkingTime = startWorkingTime;
//    }
//
//    public Integer getDuration() {
//        return duration;
//    }
//
//    public void setDuration(Integer duration) {
//        this.duration = duration;
//    }

    public String getAllowDayType() {
        return allowDayType;
    }

    public void setAllowDayType(String allowDayType) {
        this.allowDayType = allowDayType;
    }

//    public String getAllowMachine() {
//        return allowMachine;
//    }
//
//    public void setAllowMachine(String allowMachine) {
//        this.allowMachine = allowMachine;
//    }
//
//
//    public String getStartWorkTime() {
//        return startWorkTime;
//    }
//
//    public void setStartWorkTime(String startWorkTime) {
//        this.startWorkTime = startWorkTime;
//    }
//
//    public String getEndWorkTime() {
//        return endWorkTime;
//    }
//
//    public void setEndWorkTime(String endWorkTime) {
//        this.endWorkTime = endWorkTime;
//    }
}