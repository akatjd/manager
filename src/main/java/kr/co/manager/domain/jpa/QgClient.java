package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.co.manager.domain.type.ApprovalStatus;

@Entity
public class QgClient {

    @Id
    @GeneratedValue
    Integer qgClientId;

    @ManyToOne
    @JoinColumn(name="FACTORY_ID")
    Factory factory;

    String ipAddress;

    String clientSerial;

    String clientType;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    ApprovalStatus approvalStatus;

    Integer allowMachineCnt;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date approvalDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date expiryDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date connectTime;

    String token;

    Date tokenExpiryDate;

    String clientName;
    
    String clientVersion;

    @Temporal(TemporalType.TIMESTAMP)
    Date versionRegDate;
    
    String macAddress;
    
    Integer windowOsVersion;
    
    Integer windowOsBit;
    
    Double cpuUsage; 
    
    Double memoryUsage;
    
//    public QgClient(QgClientSaveView qgClientSaveView) {
//        this.clientName = qgClientSaveView.getClientName();
//        this.qgClientId = qgClientSaveView.getQgClientId();
//        this.ipAddress = qgClientSaveView.getIpAddress();
//        this.clientSerial = qgClientSaveView.getClientSerial();
//        this.clientType = qgClientSaveView.getClientType();
//        this.approvalStatus = qgClientSaveView.getApprovalStatus();
//        this.allowMachineCnt = qgClientSaveView.getAllowMachineCnt();
//        if(!"".equals(qgClientSaveView.getRegDate()) && qgClientSaveView.getRegDate() != null){
//            this.regDate = DateConverter.minStrToDate(qgClientSaveView.getRegDate());
//        }else{
//            this.regDate = new Date();
//        }
//        if(!"".equals(qgClientSaveView.getRegDate()) && qgClientSaveView.getApprovalDate() != null){
//            this.approvalDate = DateConverter.minStrToDate(qgClientSaveView.getApprovalDate());
//        }
//        if(!"".equals(qgClientSaveView.getRegDate()) && qgClientSaveView.getExpiryDate() != null){
//            this.expiryDate = DateConverter.minStrToDate(qgClientSaveView.getExpiryDate());
//        }
//        if(!"".equals(qgClientSaveView.getRegDate()) && qgClientSaveView.getConnectTime() != null){
//            this.connectTime = DateConverter.minStrToDate(qgClientSaveView.getConnectTime());
//        }
//        if(!"".equals(qgClientSaveView.getRegDate()) && qgClientSaveView.getTokenExpiryDate() != null){
//            this.tokenExpiryDate = DateConverter.minStrToDate(qgClientSaveView.getTokenExpiryDate());
//        }
//        this.token = qgClientSaveView.getToken();
//        this.clientVersion = qgClientSaveView.getClientVersion();
//    }
    
    public QgClient() {
        // TODO Auto-generated constructor stub
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

    public Date getVersionRegDate() {
        return versionRegDate;
    }

    public void setVersionRegDate(Date versionRegDate) {
        this.versionRegDate = versionRegDate;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpiryDate() {
        return tokenExpiryDate;
    }

    public void setTokenExpiryDate(Date tokenExpiryDate) {
        this.tokenExpiryDate = tokenExpiryDate;
    }

    public Integer getQgClientId() {
        return qgClientId;
    }

    public void setQgClientId(Integer qgClientId) {
        this.qgClientId = qgClientId;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getClientSerial() {
        return clientSerial;
    }

    public void setClientSerial(String clientSerial) {
        this.clientSerial = clientSerial;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Integer getAllowMachineCnt() {
        return allowMachineCnt;
    }

    public void setAllowMachineCnt(Integer allowMachineCnt) {
        this.allowMachineCnt = allowMachineCnt;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(Date connectTime) {
        this.connectTime = connectTime;
    }

    @Override
    public String toString() {
        return "QgClient{" +
                "qgClientId=" + qgClientId +
                ", factory=" + factory +
                ", ipAddress='" + ipAddress + '\'' +
                ", clientSerial='" + clientSerial + '\'' +
                ", clientType='" + clientType + '\'' +
                ", approvalStatus=" + approvalStatus +
                ", allowMachineCnt=" + allowMachineCnt +
                ", regDate=" + regDate +
                ", approvalDate=" + approvalDate +
                ", expiryDate=" + expiryDate +
                ", connectTime=" + connectTime +
                ", token='" + token + '\'' +
                ", tokenExpiryDate=" + tokenExpiryDate +
                '}';
    }
}