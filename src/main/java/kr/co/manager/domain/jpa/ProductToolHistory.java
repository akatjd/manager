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
public class ProductToolHistory {

    @Id
    @GeneratedValue
    Integer productToolHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_TOOL_ID")
    ProductTool productTool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID")
    Product product;

    String toolName;

    @Temporal(TemporalType.TIMESTAMP)
    Date changedDate;

    Integer useCnt;

    Integer presetCnt;

    Integer endOffsetId;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductToolHistoryId() {
        return productToolHistoryId;
    }

    public void setProductToolHistoryId(Integer productToolHistoryId) {
        this.productToolHistoryId = productToolHistoryId;
    }

    public ProductTool getProductTool() {
        return productTool;
    }

    public void setProductTool(ProductTool productTool) {
        this.productTool = productTool;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
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

    public Integer getEndOffsetId() {
        return endOffsetId;
    }

    public void setEndOffsetId(Integer endOffsetId) {
        this.endOffsetId = endOffsetId;
    }
}