package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AlarmDictionary {
	@Id
    @GeneratedValue
    @Column(name="ALARM_ID")
    private Integer alarmId;
    
    @Column(name="FACTORY_ID")
    Integer factoryId;
    
    @Column(name="ALARM_TYPE_ID")
    Integer alarmTypeId;
    
    @Column(name="ALARM_GROUP_ID")
    Integer alarmGroupId;
    
    @Column(name="ALARM_MSG_KR")
    String alarmMsgKr;
    
    @Column(name="ALARM_MSG_EN")
    String alarmMsgEn;
    
    @Column(name="USE_YN")
    String useYn;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
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

    public Integer getAlarmGroupId() {
        return alarmGroupId;
    }

    public void setAlarmGroupId(Integer alarmGroupId) {
        this.alarmGroupId = alarmGroupId;
    }

    public String getAlarmMsgKr() {
        return alarmMsgKr;
    }

    public void setAlarmMsgKr(String alarmMsgKr) {
        this.alarmMsgKr = alarmMsgKr;
    }

    public String getAlarmMsgEn() {
        return alarmMsgEn;
    }

    public void setAlarmMsgEn(String alarmMsgEn) {
        this.alarmMsgEn = alarmMsgEn;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
