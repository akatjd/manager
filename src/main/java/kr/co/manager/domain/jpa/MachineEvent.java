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

import kr.co.manager.domain.type.MachineEventType;

@Entity
public class MachineEvent {

	@Id
    @GeneratedValue
    Integer machineEventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MACHINE_ID")
    Machine machine;

    @Enumerated(EnumType.STRING)
    MachineEventType machineEventType;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    String eventMsg;
    
    String toolName;

    Integer currentProductId;
    
    String writeUser;
    
	public String getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(String writeUser) {
        this.writeUser = writeUser;
    }

    public Integer getCurrentProductId() {
        return currentProductId;
    }

    public void setCurrentProductId(Integer currentProductId) {
        this.currentProductId = currentProductId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Integer getMachineEventId() {
        return machineEventId;
    }

    public void setMachineEventId(Integer machineEventId) {
        this.machineEventId = machineEventId;
    }

    public MachineEventType getMachineEventType() {
        return machineEventType;
    }

    public void setMachineEventType(MachineEventType machineEventType) {
        this.machineEventType = machineEventType;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getEventMsg() {
        return eventMsg;
    }

    public void setEventMsg(String eventMsg) {
        this.eventMsg = eventMsg;
    }

    @Override
    public String toString() {
        return "MachineEventData{" +
                "machineEventId=" + machineEventId +
                ", machineEventType=" + machineEventType +
                ", regDate=" + regDate +
                ", eventMsg='" + eventMsg + '\'' +
                ", toolName='" + toolName + '\'' +
                ", currentProductId=" + currentProductId +
                '}';
    }
}
