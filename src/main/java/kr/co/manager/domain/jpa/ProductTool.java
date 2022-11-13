package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProductTool {

    @Id
    @GeneratedValue
    Integer productToolId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID")
    Product product;

    String toolName;

    Integer toolSpec;

    Integer toolPrice;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date lastChangedDate;

    Integer useCnt;

    Integer presetCnt;

    Integer currentOffsetId;
    
    Float toolWearingMax;
    
    Float toolWearingNow;
    

    public Float getToolWearingMax() {
        return toolWearingMax;
    }

    public void setToolWearingMax(Float toolWearingMax) {
        this.toolWearingMax = toolWearingMax;
    }

    public Float getToolWearingNow() {
        return toolWearingNow;
    }

    public void setToolWearingNow(Float toolWearingNow) {
        this.toolWearingNow = toolWearingNow;
    }

    public Integer getProductToolId() {
        return productToolId;
    }

    public void setProductToolId(Integer productToolId) {
        this.productToolId = productToolId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public Integer getToolSpec() {
        return toolSpec;
    }

    public void setToolSpec(Integer toolSpec) {
        this.toolSpec = toolSpec;
    }

    public Integer getToolPrice() {
        return toolPrice;
    }

    public void setToolPrice(Integer toolPrice) {
        this.toolPrice = toolPrice;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getLastChangedDate() {
        return lastChangedDate;
    }

    public void setLastChangedDate(Date lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    public Integer getUseCnt() {
        return useCnt;
    }

    public void setUseCnt(Integer useCnt) {
        this.useCnt = useCnt;
    }

    public Integer getPresetCnt() {
        return presetCnt;
    }

    public void setPresetCnt(Integer presetCnt) {
        this.presetCnt = presetCnt;
    }

    public Integer getCurrentOffsetId() {
        return currentOffsetId;
    }

    public void setCurrentOffsetId(Integer currentOffsetId) {
        this.currentOffsetId = currentOffsetId;
    }
}