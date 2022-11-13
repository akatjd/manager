package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class DamageDetectionHistory {

	    @Id
	    @GeneratedValue
	    private Integer detectionId;

	    Integer machineId;

	    Integer productId;

	    String sectionId;

	    Integer sectionPieceId;
	    
	    String inspectionDetailId;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    Date regDate;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    Date detectionDate;

	    String writeUser;
	    
	    Integer itemId;
	    
	    String channelName;
	    
	    Integer sectionNumber;

	    @Transient
	    @JsonSerialize
	    @JsonDeserialize
	    String tag;
	    
	    
	    public String getTag() {
	        return tag;
	    }

	    public void setTag(String tag) {
	        this.tag = tag;
	    }

	    public Date getDetectionDate() {
	        return detectionDate;
	    }

	    public void setDetectionDate(Date detectionDate) {
	        this.detectionDate = detectionDate;
	    }

	    public Integer getItemId() {
	        return itemId;
	    }

	    public void setItemId(Integer itemId) {
	        this.itemId = itemId;
	    }

	    public String getChannelName() {
	        return channelName;
	    }

	    public void setChannelName(String channelName) {
	        this.channelName = channelName;
	    }

	    public Integer getSectionNumber() {
	        return sectionNumber;
	    }

	    public void setSectionNumber(Integer sectionNumber) {
	        this.sectionNumber = sectionNumber;
	    }

	    public String getInspectionDetailId() {
	        return inspectionDetailId;
	    }

	    public void setInspectionDetailId(String inspectionDetailId) {
	        this.inspectionDetailId = inspectionDetailId;
	    }

	    public Integer getDetectionId() {
	        return detectionId;
	    }

	    public void setDetectionId(Integer detectionId) {
	        this.detectionId = detectionId;
	    }

	    public Integer getMachineId() {
	        return machineId;
	    }

	    public void setMachineId(Integer machineId) {
	        this.machineId = machineId;
	    }

	    public Integer getProductId() {
	        return productId;
	    }

	    public void setProductId(Integer productId) {
	        this.productId = productId;
	    }

	    public String getSectionId() {
	        return sectionId;
	    }

	    public void setSectionId(String sectionId) {
	        this.sectionId = sectionId;
	    }

	    public Integer getSectionPieceId() {
	        return sectionPieceId;
	    }

	    public void setSectionPieceId(Integer sectionPieceId) {
	        this.sectionPieceId = sectionPieceId;
	    }

	    public Date getRegDate() {
	        return regDate;
	    }

	    public void setRegDate(Date regDate) {
	        this.regDate = regDate;
	    }

	    public String getWriteUser() {
	        return writeUser;
	    }

	    public void setWriteUser(String writeUser) {
	        this.writeUser = writeUser;
	    }
}
