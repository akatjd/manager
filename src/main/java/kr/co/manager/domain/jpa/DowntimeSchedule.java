package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DowntimeSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer dtSeq;
	
//	@ManyToOne
//    @JoinColumn(name = "FACTORY_ID")
//    Factory factory;

    Integer factoryId;
    
    String scheduleName;

    String startTime;
    
    String endTime;
    
    String autoUseYn;
    
    String scheduleUseYn;
    
    String etc1;
    
    String etc2;
    
    String dayOrNight;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    public Integer getDtSeq() {
        return dtSeq;
    }

    public void setDtSeq(Integer dtSeq) {
        this.dtSeq = dtSeq;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAutoUseYn() {
        return autoUseYn;
    }

    public void setAutoUseYn(String autoUseYn) {
        this.autoUseYn = autoUseYn;
    }

    public String getScheduleUseYn() {
        return scheduleUseYn;
    }

    public void setScheduleUseYn(String scheduleUseYn) {
        this.scheduleUseYn = scheduleUseYn;
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

	public String getDayOrNight() {
		return dayOrNight;
	}

	public void setDayOrNight(String dayOrNight) {
		this.dayOrNight = dayOrNight;
	}
}
