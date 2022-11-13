package kr.co.manager.domain.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class InspectionPolicyGroup {

	@Id
    @GeneratedValue
    Integer inspectionGroupId;

    @ManyToOne
    @JoinColumn(name="SECTION_PIECE_ID")
    SectionPiece sectionPiece;
    
    @OneToMany(mappedBy="inspectionGroup")
    List<InspectionPolicy> inspectionPolicy;
    
    Integer productId;

    Integer machiningSectionId;

    String inspectionName;

    Integer sectionNumber;

    String toolName;

    String inspectionType;
    
    Integer startTick;
    
    Integer endTick;
    
    String inspectionSrc;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date endDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;
    
    String writeUser;
    
    String updateUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;
    
    @OneToOne
    @JoinColumn(name="inspection_config_id")
    AutoInspectionConfig autoInspectionConfig;
    

    @Transient
    @JsonSerialize
    @JsonDeserialize
    String searchDate;
    
    
    /**
     * @param paramMap
     */
//    public InspectionPolicyGroup(HashMap<String, Object> paramMap) {
//        this.productId = Integer.parseInt(paramMap.get("productId").toString());
//        this.inspectionName = (String) paramMap.get("inspectionName");
//        this.startTick = Integer.parseInt(paramMap.get("startTick").toString());
//        this.endTick = Integer.parseInt(paramMap.get("endTick").toString());
//        this.inspectionSrc = (String) paramMap.get("inspectionSrc");
//        this.inspectionType = (String) paramMap.get("inspectionType");
//        this.startDate = DateConverter.minStrToDate(paramMap.get("startDate").toString());
//        this.endDate = DateConverter.minStrToDate(paramMap.get("endDate").toString());
//    }


    public InspectionPolicyGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @param ipGroup
	 */
	public InspectionPolicyGroup(InspectionPolicyGroup ipGroup) {
      this.productId = ipGroup.productId;
      this.machiningSectionId = ipGroup.machiningSectionId;
      this.inspectionName = ipGroup.getInspectionName();
      this.toolName = ipGroup.getToolName();
      this.startTick = ipGroup.getStartTick();
      this.endTick = ipGroup.getEndTick();
      this.inspectionSrc = ipGroup.getInspectionSrc();
      this.inspectionType = ipGroup.getInspectionType();
      this.startDate = ipGroup.getStartDate();
      this.endDate = ipGroup.getEndDate();
      this.regDate = new Date();
	}


	public AutoInspectionConfig getAutoInspectionConfig() {
		return autoInspectionConfig;
	}

	public void setAutoInspectionConfig(AutoInspectionConfig autoInspectionConfig) {
		this.autoInspectionConfig = autoInspectionConfig;
	}

	public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(String writeUser) {
        this.writeUser = writeUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public Integer getStartTick() {
        return startTick;
    }

    public void setStartTick(Integer startTick) {
        this.startTick = startTick;
    }

    public Integer getEndTick() {
        return endTick;
    }

    public void setEndTick(Integer endTick) {
        this.endTick = endTick;
    }

    public String getInspectionSrc() {
        return inspectionSrc;
    }

    public void setInspectionSrc(String inspectionSrc) {
        this.inspectionSrc = inspectionSrc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<InspectionPolicy> getInspectionPolicy() {
        return inspectionPolicy;
    }

    public void setInspectionPolicy(List<InspectionPolicy> inspectionPolicy) {
        this.inspectionPolicy = inspectionPolicy;
    }

    public Integer getInspectionGroupId() {
        return inspectionGroupId;
    }

    public void setInspectionGroupId(Integer inspectionGroupId) {
        this.inspectionGroupId = inspectionGroupId;
    }

    public SectionPiece getSectionPiece() {
        return sectionPiece;
    }

    public void setSectionPiece(SectionPiece sectionPiece) {
        this.sectionPiece = sectionPiece;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMachiningSectionId() {
        return machiningSectionId;
    }

    public void setMachiningSectionId(Integer machiningSectionId) {
        this.machiningSectionId = machiningSectionId;
    }

    public String getInspectionName() {
        return inspectionName;
    }

    public void setInspectionName(String inspectionName) {
        this.inspectionName = inspectionName;
    }

    public Integer getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(Integer sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
