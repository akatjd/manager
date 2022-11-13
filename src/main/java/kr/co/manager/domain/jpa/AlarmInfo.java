package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AlarmInfo {
	@Id
    @GeneratedValue
    @Column(name="ALARM_ID")
    private Integer alarmId;
    
    @Column(name="ALARM_TYPE")
    Integer alarmType;
    
    @Column(name="ALARM_NO")
    Integer alarmNo;
    
    @Column(name="ALARM_MSG_KR")
    String alarmMsgKr;
    
    @Column(name="ALARM_MSG_EN")
    String alarmMsgEn;
    
    @Column(name="ETC1")
    String etc1;
    
    @Column(name="ETC2")
    String etc2;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getAlarmNo() {
        return alarmNo;
    }

    public void setAlarmNo(Integer alarmNo) {
        this.alarmNo = alarmNo;
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

    public String getEtc1() {
        return etc1;
    }

    public void setEtc1(String etc1) {
        this.etc1 = etc1;
    }

    public String getEtc2() {
        return etc2;
    }

    public void setEtc2(String etc2) {
        this.etc2 = etc2;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
