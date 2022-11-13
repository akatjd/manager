package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.co.manager.domain.type.WarnStatus;


@Entity
public class Item {

	@ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    Product product;

    @Id
    @GeneratedValue
    Integer itemId;

    Float cycleTime;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    Integer machiningSectionId;

    String toolUseInfo;

    Integer machineCnt;

    Integer todayCnt;

    @Enumerated(EnumType.STRING)
    WarnStatus itemType;

    String cycleType;

    String inspectionResult;
    
    String itemTag;
    
    Integer palletsNo;
    
    
    public Integer getPalletsNo() {
        return palletsNo == null ? 0 : palletsNo;
    }

    public void setPalletsNo(Integer palletsNo) {
        this.palletsNo = palletsNo;
    }

    public String getItemTag() {
        return itemTag;
    }
    
    public void setItemTag(String itemTag) {
        this.itemTag = itemTag;
    }

    public Integer getTodayCnt() {
        return todayCnt;
    }

    public void setTodayCnt(Integer todayCnt) {
        this.todayCnt = todayCnt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Float getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(Float cycleTime) {
        this.cycleTime = cycleTime;
    }

    public Date getRegDate() {
        return regDate;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getMachiningSectionId() {
        return machiningSectionId;
    }

    public void setMachiningSectionId(Integer machiningSectionId) {
        this.machiningSectionId = machiningSectionId;
    }

    public String getToolUseInfo() {
        return toolUseInfo;
    }

    public void setToolUseInfo(String toolUseInfo) {
        this.toolUseInfo = toolUseInfo;
    }

    public Integer getMachineCnt() {
        return machineCnt;
    }

    public void setMachineCnt(Integer machineCnt) {
        this.machineCnt = machineCnt;
    }

    public WarnStatus getItemType() {
        return itemType;
    }

    public void setItemType(WarnStatus itemType) {
        this.itemType = itemType;
    }

    public String getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(String inspectionResult) {
        this.inspectionResult = inspectionResult;
    }
}
