package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MachiningSection {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID")
    Product product;

    @Id
    @GeneratedValue
    Integer machiningSectionId;

    String basicRawData;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    String reason;

    String activeYn;
    
    String smartActiveYn;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date smartStartDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date smartEndDate;
    
    Integer smartSumCnt;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;
    
    Integer palletsNo;
    
    Integer itemId;
    
    @OneToOne
    @JoinColumn(name="machining_section_config_id")
    MachiningSectionConfig machiningSectionConfig;
    
    /**
     * @param ms
     */
    public MachiningSection(MachiningSection ms) {
        this.product = ms.getProduct();
        this.machiningSectionId = ms.getMachiningSectionId();
        this.basicRawData = ms.getBasicRawData();
        this.regDate = ms.getRegDate();
        this.reason = ms.getReason();
        this.activeYn = ms.getActiveYn();
        this.smartActiveYn = ms.getSmartActiveYn();
        this.smartStartDate = ms.getSmartStartDate();
        this.smartEndDate = ms.getSmartEndDate();
        this.smartSumCnt = ms.getSmartSumCnt();
        this.updateDate = ms.getUpdateDate();
        this.palletsNo = ms.getPalletsNo();
        this.itemId = ms.getItemId();
    }

    
    
    public MachiningSection() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public MachiningSectionConfig getMachiningSectionConfig() {
        return machiningSectionConfig;
    }

    public void setMachiningSectionConfig(MachiningSectionConfig machiningSectionConfig) {
        this.machiningSectionConfig = machiningSectionConfig;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getPalletsNo() {
        return palletsNo;
    }

    public void setPalletsNo(Integer palletsNo) {
        this.palletsNo = palletsNo;
    }
    
    public Product getProduct() {
        return product;
    }

    public String getSmartActiveYn() {
        return smartActiveYn;
    }

    public void setSmartActiveYn(String smartActiveYn) {
        this.smartActiveYn = smartActiveYn;
    }

    public Date getSmartStartDate() {
        return smartStartDate;
    }

    public void setSmartStartDate(Date smartStartDate) {
        this.smartStartDate = smartStartDate;
    }

    public Date getSmartEndDate() {
        return smartEndDate;
    }

    public void setSmartEndDate(Date smartEndDate) {
        this.smartEndDate = smartEndDate;
    }

    public Integer getSmartSumCnt() {
        return smartSumCnt;
    }

    public void setSmartSumCnt(Integer smartSumCnt) {
        this.smartSumCnt = smartSumCnt;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getMachiningSectionId() {
        return machiningSectionId;
    }

    public void setMachiningSectionId(Integer machiningSectionId) {
        this.machiningSectionId = machiningSectionId;
    }

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

    public String getBasicRawData() {
        return basicRawData;
    }

    public void setBasicRawData(String basicRawData) {
        this.basicRawData = basicRawData;
    }

    @Override
    public String toString() {
        return "MachiningSection [product=" + product + ", machiningSectionId=" + machiningSectionId + ", basicRawData="
                + basicRawData + ", regDate=" + regDate + ", reason=" + reason + ", activeYn=" + activeYn
                + ", smartActiveYn=" + smartActiveYn + ", smartStartDate=" + smartStartDate + ", smartEndDate="
                + smartEndDate + ", smartSumCnt=" + smartSumCnt + ", updateDate=" + updateDate + "]";
    }
}