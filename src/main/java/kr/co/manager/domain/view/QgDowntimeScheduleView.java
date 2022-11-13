package kr.co.manager.domain.view;

import kr.co.manager.domain.jpa.DowntimeSchedule;

public class QgDowntimeScheduleView {

	public QgDowntimeScheduleView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public QgDowntimeScheduleView(DowntimeSchedule downtimeSchedule) {
        this.dtSeq = downtimeSchedule.getDtSeq();
        this.factoryId = downtimeSchedule.getFactoryId();
        this.scheduleName = downtimeSchedule.getScheduleName();
        this.startTime = downtimeSchedule.getStartTime();
        this.endTime = downtimeSchedule.getEndTime();
        this.autoUseYn = downtimeSchedule.getAutoUseYn();
        this.scheduleUseYn = downtimeSchedule.getScheduleUseYn();
        this.dayOrNight = downtimeSchedule.getDayOrNight();
    }

	Integer dtSeq;
	
	Integer factoryId;
	
	String factoryName;
	
	String scheduleName;
	
	String startTime;
	
	String endTime;
	
	String autoUseYn;
	
	String scheduleUseYn;
	
	String dayOrNight;
	
	public Integer getDtSeq() {
		return dtSeq;
	}

	public void setDtSeq(Integer dtSeq) {
		this.dtSeq = dtSeq;
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

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public String getDayOrNight() {
		return dayOrNight;
	}

	public void setDayOrNight(String dayOrNight) {
		this.dayOrNight = dayOrNight;
	}

	@Override
	public String toString() {
		return "QgDowntimeScheduleView [dtSeq=" + dtSeq + ", factoryId=" + factoryId + ", factoryName=" + factoryName
				+ ", scheduleName=" + scheduleName + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", autoUseYn=" + autoUseYn + ", scheduleUseYn=" + scheduleUseYn + ", dayOrNight=" + dayOrNight + "]";
	}
}
