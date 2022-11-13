package kr.co.manager.domain.jpa;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class MachiningSectionConfig {

    @Id
    @GeneratedValue
    Integer machiningSectionConfigId;

    Integer machineProductCycleType;

    Integer machineSectionCycleType;
    
    Integer machineStartCode;
    
    Integer machineEndCode;
    
    Integer machineSyncTick;
    
    Integer machineEndHoldTime;
    
    @OneToOne
    @JoinColumn(name="machining_section_id")
    @JsonBackReference
    MachiningSection machiningSection;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;
    
    String writeUser;
    
    String updateUser;

    public MachiningSectionConfig() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MachiningSectionConfig(Map<String, Integer> msConfigMap) {
        // TODO Auto-generated constructor stub
        this.machineProductCycleType = msConfigMap.get("machine_product_cycle_type");
        this.machineSectionCycleType = msConfigMap.get("machine_section_cycle_type");
        this.machineStartCode = msConfigMap.get("machine_start_code");
        this.machineEndCode = msConfigMap.get("machine_end_code");
        this.machineSyncTick = msConfigMap.get("machine_sync_tick");
        this.machineEndHoldTime = msConfigMap.get("machine_end_hold_time");
    }

    public Integer getMachineStartCode() {
        return machineStartCode;
    }

    public void setMachineStartCode(Integer machineStartCode) {
        this.machineStartCode = machineStartCode;
    }

    public Integer getMachineEndCode() {
        return machineEndCode;
    }

    public void setMachineEndCode(Integer machineEndCode) {
        this.machineEndCode = machineEndCode;
    }

    public Integer getMachiningSectionConfigId() {
        return machiningSectionConfigId;
    }

    public void setMachiningSectionConfigId(Integer machiningSectionConfigId) {
        this.machiningSectionConfigId = machiningSectionConfigId;
    }

    public Integer getMachineProductCycleType() {
        return machineProductCycleType;
    }

    public void setMachineProductCycleType(Integer machineProductCycleType) {
        this.machineProductCycleType = machineProductCycleType;
    }

    public Integer getMachineSectionCycleType() {
        return machineSectionCycleType;
    }

    public void setMachineSectionCycleType(Integer machineSectionCycleType) {
        this.machineSectionCycleType = machineSectionCycleType;
    }

    public Integer getMachineSyncTick() {
        return machineSyncTick;
    }

    public void setMachineSyncTick(Integer machineSyncTick) {
        this.machineSyncTick = machineSyncTick;
    }

    public Integer getMachineEndHoldTime() {
        return machineEndHoldTime;
    }

    public void setMachineEndHoldTime(Integer machineEndHoldTime) {
        this.machineEndHoldTime = machineEndHoldTime;
    }

    public MachiningSection getMachiningSection() {
        return machiningSection;
    }

    public void setMachiningSection(MachiningSection machiningSection) {
        this.machiningSection = machiningSection;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(String writeUser) {
        this.writeUser = writeUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}