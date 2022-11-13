package kr.co.manager.domain.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class AutoInspectionConfig {

	@Id
    @GeneratedValue
    Integer inspectionConfigId;
    
    @OneToOne
    @JoinColumn(name="inspection_group_id")
    @JsonBackReference 
    InspectionPolicyGroup inspectionPolicyGroup;
    
    Boolean autoSimulEnable;
    
    Integer highSensitivity;
    
    Integer lowSensitivity;
    
    Boolean mergeToolChangeEnable;
    
    Boolean longIdleEnable;
    
    Boolean autoInitEnable;
    
    Integer sampleMergeCnt;
    
    Integer itemCnt;
    
    Integer itemTotalCnt;
    
    Integer sampleMergeMaxCnt;
    
    Integer machineId;
    
    String sectionId;

    
    public Integer getItemTotalCnt() {
        return itemTotalCnt;
    }

    public void setItemTotalCnt(Integer itemTotalCnt) {
        this.itemTotalCnt = itemTotalCnt;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getSampleMergeMaxCnt() {
        return sampleMergeMaxCnt;
    }

    public void setSampleMergeMaxCnt(Integer sampleMergeMaxCnt) {
        this.sampleMergeMaxCnt = sampleMergeMaxCnt;
    }

    public Integer getInspectionConfigId() {
        return inspectionConfigId;
    }

    public void setInspectionConfigId(Integer inspectionConfigId) {
        this.inspectionConfigId = inspectionConfigId;
    }

    public InspectionPolicyGroup getInspectionPolicyGroup() {
        return inspectionPolicyGroup;
    }

    public void setInspectionPolicyGroup(InspectionPolicyGroup inspectionPolicyGroup) {
        this.inspectionPolicyGroup = inspectionPolicyGroup;
    }

    public Boolean getAutoSimulEnable() {
        return autoSimulEnable;
    }

    public void setAutoSimulEnable(Boolean autoSimulEnable) {
        this.autoSimulEnable = autoSimulEnable;
    }

    public Integer getHighSensitivity() {
        return highSensitivity;
    }

    public void setHighSensitivity(Integer highSensitivity) {
        this.highSensitivity = highSensitivity;
    }

    public Integer getLowSensitivity() {
        return lowSensitivity;
    }

    public void setLowSensitivity(Integer lowSensitivity) {
        this.lowSensitivity = lowSensitivity;
    }

    public Boolean getMergeToolChangeEnable() {
        return mergeToolChangeEnable;
    }

    public void setMergeToolChangeEnable(Boolean mergeToolChangeEnable) {
        this.mergeToolChangeEnable = mergeToolChangeEnable;
    }

    public Boolean getLongIdleEnable() {
        return longIdleEnable;
    }

    public void setLongIdleEnable(Boolean longIdleEnable) {
        this.longIdleEnable = longIdleEnable;
    }

    public Boolean getAutoInitEnable() {
        return autoInitEnable;
    }

    public void setAutoInitEnable(Boolean autoInitEnable) {
        this.autoInitEnable = autoInitEnable;
    }

    public Integer getSampleMergeCnt() {
        return sampleMergeCnt;
    }

    public void setSampleMergeCnt(Integer sampleMergeCnt) {
        this.sampleMergeCnt = sampleMergeCnt;
    }

    public Integer getItemCnt() {
        return itemCnt;
    }

    public void setItemCnt(Integer itemCnt) {
        this.itemCnt = itemCnt;
    }
}
