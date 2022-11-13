package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.co.manager.domain.type.MachiningStatusType;

@Entity
public class MachiningStatus {
    
    @Id
    @GeneratedValue
    private Integer machiningStatusId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MACHINE_ID")
    private Machine machine;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    
    @Enumerated(EnumType.STRING)    
    private MachiningStatusType machiningStatusType;
    
    String alarmMessage;

    
    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    public Integer getMachiningStatusId() {
        return machiningStatusId;
    }

    public void setMachiningStatusId(Integer machiningStatusId) {
        this.machiningStatusId = machiningStatusId;
    }

    public MachiningStatusType getMachiningStatusType() {
        return machiningStatusType;
    }

    public void setMachiningStatusType(MachiningStatusType machiningStatusType) {
        this.machiningStatusType = machiningStatusType;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
//        if(!machine.getStatus().contains(this)){
//            machine.getStatus().add(this);
//        }
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
}