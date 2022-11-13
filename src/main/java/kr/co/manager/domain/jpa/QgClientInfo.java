package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class QgClientInfo {

    @Id
    @GeneratedValue
    Integer clientInfoId;
    
    Integer qgClientId;

    String ipAddress;

    String macAddress;
    
    Integer windowOsVersion;
    
    Integer windowOsBit;
    
    Double cpuUsage; 
    
    Double memoryUsage;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    public Integer getClientInfoId() {
        return clientInfoId;
    }

    public void setClientInfoId(Integer clientInfoId) {
        this.clientInfoId = clientInfoId;
    }

    public Integer getQgClientId() {
        return qgClientId;
    }

    public void setQgClientId(Integer qgClientId) {
        this.qgClientId = qgClientId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Integer getWindowOsVersion() {
        return windowOsVersion;
    }

    public void setWindowOsVersion(Integer windowOsVersion) {
        this.windowOsVersion = windowOsVersion;
    }

    public Integer getWindowOsBit() {
        return windowOsBit;
    }

    public void setWindowOsBit(Integer windowOsBit) {
        this.windowOsBit = windowOsBit;
    }

    public Double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(Double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}