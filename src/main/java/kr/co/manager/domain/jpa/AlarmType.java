package kr.co.manager.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AlarmType {
	@Id
    @GeneratedValue
    @Column(name="ALARM_TYPE_ID")
    private Integer alarmTypeId;
	
	@Column(name="FACTORY_ID")
	Integer factoryId;
	
	@Column(name="ALARM_TYPE")
	String alarmType;
	
	@Column(name="USE_YN")
	String useYn;

	public Integer getAlarmTypeId() {
		return alarmTypeId;
	}

	public void setAlarmTypeId(Integer alarmTypeId) {
		this.alarmTypeId = alarmTypeId;
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
