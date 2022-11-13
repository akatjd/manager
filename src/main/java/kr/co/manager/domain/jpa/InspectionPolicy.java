package kr.co.manager.domain.jpa;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class InspectionPolicy {

	@Id
    @GeneratedValue
    Integer inspectionPolicyId;

//    @ManyToOne
//    @JoinColumn(name="SECTION_PIECE_ID")
//    SectionPiece sectionPiece;
    
    @ManyToOne
    @JoinColumn(name="INSPECTION_GROUP_ID")
    @JsonBackReference
    InspectionPolicyGroup inspectionGroup;

//    Integer productId;

//    Integer machiningSectionId;

//    String inspectionName;

//    Integer sectionNumber;

//    String toolName;

//    Integer startTick;
//
//    Integer endTick;

    String stopType;

    Integer respiteCnt;

//    String inspectionSrc;

    Float highThreshold;
    
    Float lowThreshold;
    
//    Float tunnelHighValue;
//    
//    Float tunnelLowValue;
    
//    String inspectionType;
    
//    String writeUser;
    
//    String updateUser;

    String activeYn;

    Integer lvType;
    
//    @Temporal(TemporalType.TIMESTAMP)
//    Date startDate;
//    
//    @Temporal(TemporalType.TIMESTAMP)
//    Date endDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;
    
//    @Temporal(TemporalType.TIMESTAMP)
//    Date updateDate;

//    @Transient
//    @JsonSerialize
//    @JsonDeserialize
//    String searchDate;
    
    public InspectionPolicy() {
    }

    public InspectionPolicy(HashMap<String, Object>paramMap) {
        this.stopType      = (String) paramMap.get("stopType");
        this.respiteCnt    = Integer.parseInt(paramMap.get("respiteCnt").toString());
        this.highThreshold = Float.parseFloat(paramMap.get("highThreshold").toString());
        this.lowThreshold  = Float.parseFloat(paramMap.get("lowThreshold").toString());
        this.lvType        = Integer.parseInt(paramMap.get("lvType").toString());
    }
    
    public InspectionPolicy(InspectionPolicy i) {
//        this.productId = i.productId;
//        this.machiningSectionId = i.machiningSectionId;
//        this.inspectionName = i.getInspectionName();
//        this.toolName = i.getToolName();
//        this.startTick = i.getStartTick();
//        this.endTick = i.getEndTick();
        this.stopType = i.getStopType();
        this.respiteCnt = i.getRespiteCnt();
//        this.inspectionSrc = i.getInspectionSrc();
        this.highThreshold = i.getHighThreshold();
        this.lowThreshold = i.getLowThreshold();
        this.lvType = i.getLvType();
//        this.tunnelHighValue = i.getTunnelHighValue();
//        this.tunnelLowValue = i.getTunnelLowValue();
//        this.inspectionType = i.getInspectionType();
//        this.startDate = i.getStartDate();
//        this.endDate = i.getEndDate();
        this.regDate = new Date();
    }
    
    public InspectionPolicyGroup getInspectionGroup() {
        return inspectionGroup;
    }

    public Integer getLvType() {
        return lvType;
    }

    public void setLvType(Integer lvType) {
        this.lvType = lvType;
    }

//    public String getWriteUser() {
//        return writeUser;
//    }
//
//    public void setWriteUser(String writeUser) {
//        this.writeUser = writeUser;
//    }
//
//    public String getUpdateUser() {
//        return updateUser;
//    }
//
//    public void setUpdateUser(String updateUser) {
//        this.updateUser = updateUser;
//    }
//
//    public Date getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(Date updateDate) {
//        this.updateDate = updateDate;
//    }

//    public Float getTunnelHighValue() {
//        return tunnelHighValue;
//    }
//
//    public void setTunnelHighValue(Float tunnelHighValue) {
//        this.tunnelHighValue = tunnelHighValue;
//    }
//
//    public Float getTunnelLowValue() {
//        return tunnelLowValue;
//    }
//
//    public void setTunnelLowValue(Float tunnelLowValue) {
//        this.tunnelLowValue = tunnelLowValue;
//    }

//    public String getSearchDate() {
//        return searchDate;
//    }
//
//    public void setSearchDate(String searchDate) {
//        this.searchDate = searchDate;
//    }

//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(String activeYn) {
        this.activeYn = activeYn;
    }

//    public SectionPiece getSectionPiece() {
//        return sectionPiece;
//    }
//
//    public void setSectionPiece(SectionPiece sectionPiece) {
//        this.sectionPiece = sectionPiece;
//    }
//
//    public String getInspectionType() {
//        return inspectionType;
//    }
//
//    public void setInspectionType(String inspectionType) {
//        inspectionType = inspectionType;
//    }

    public Float getHighThreshold() {
        return highThreshold;
    }

    public void setHighThreshold(Float highThreshold) {
        this.highThreshold = highThreshold;
    }

    public Float getLowThreshold() {
        return lowThreshold;
    }

    public void setLowThreshold(Float lowThreshold) {
        this.lowThreshold = lowThreshold;
    }

    public Integer getInspectionPolicyId() {
        return inspectionPolicyId;
    }

    public void setInspectionPolicyId(Integer inspectionPolicyId) {
        this.inspectionPolicyId = inspectionPolicyId;
    }

//    public Integer getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Integer productId) {
//        this.productId = productId;
//    }
//
//    public Integer getMachiningSectionId() {
//        return machiningSectionId;
//    }
//
//    public void setMachiningSectionId(Integer machiningSectionId) {
//        this.machiningSectionId = machiningSectionId;
//    }
//
//    public String getInspectionName() {
//        return inspectionName;
//    }
//
//    public void setInspectionName(String inspectionName) {
//        this.inspectionName = inspectionName;
//    }
//
//    public Integer getSectionNumber() {
//        return sectionNumber;
//    }
//
//    public void setSectionNumber(Integer sectionNumber) {
//        this.sectionNumber = sectionNumber;
//    }
//
//    public String getToolName() {
//        return toolName;
//    }
//
//    public void setToolName(String toolName) {
//        this.toolName = toolName;
//    }

//    public Integer getStartTick() {
//        return startTick;
//    }
//
//    public void setStartTick(Integer startTick) {
//        this.startTick = startTick;
//    }
//
//    public Integer getEndTick() {
//        return endTick;
//    }
//
//    public void setEndTick(Integer endTick) {
//        this.endTick = endTick;
//    }

    public String getStopType() {
        return stopType;
    }

    public void setStopType(String stopType) {
        this.stopType = stopType;
    }

    public Integer getRespiteCnt() {
        return respiteCnt;
    }

    public void setRespiteCnt(Integer respiteCnt) {
        this.respiteCnt = respiteCnt;
    }

//    public String getInspectionSrc() {
//        return inspectionSrc;
//    }
//
//    public void setInspectionSrc(String inspectionSrc) {
//        this.inspectionSrc = inspectionSrc;
//    }
}
