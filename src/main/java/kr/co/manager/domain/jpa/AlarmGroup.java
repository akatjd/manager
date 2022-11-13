package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AlarmGroup {
    @Id
    @GeneratedValue
    @Column(name="ALARM_GROUP_ID")
    private Integer alarmGroupId;
    
    @Column(name="FACTORY_ID")
    Integer factoryId;
    
    @Column(name="ALARM_TYPE_ID")
    Integer alarmTypeId;
    
    @Column(name="GROUP_NAME")
    String groupName;
    
    @Column(name="USE_YN")
    String useYn;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    public Integer getAlarmGroupId() {
        return alarmGroupId;
    }

    public void setAlarmGroupId(Integer alarmGroupId) {
        this.alarmGroupId = alarmGroupId;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getAlarmTypeId() {
        return alarmTypeId;
    }

    public void setAlarmTypeId(Integer alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}

