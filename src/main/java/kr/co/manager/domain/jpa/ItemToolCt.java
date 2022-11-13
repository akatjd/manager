package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ItemToolCt {
    
    @Id
    @GeneratedValue
    @Column(name="item_ct_tool_seq")
    Integer itemCtToolSeq;
    
    @Column(name="item_ct_seq")
    Integer itemCtSeq;
    
    @Column(name="channel_name")
    String channelName;
    
    @Column(name="path")
    Integer path;
    
    @Column(name="section_number")
    Integer sectionNumber;
    
    @Column(name="tool_name")
    String toolName;
    
    @Column(name="section_data_size")
    Integer sectionDataSize;
    
    @Column(name="factory_id")
    Integer factoryId;
    
    @Column(name="qg_client_id")
    Integer qgClientId;
    
    @Column(name="machine_id")
    Integer machineId;
    
    @Column(name="product_id")
    Integer productId;
    
    @Column(name="pallets_no")
    Integer palletsNo;
    
    @Column(name="item_id")
    Integer itemId;
    
    @Column(name="item_type_id")
    Integer itemTypeId;
    
    @Column(name="section_type")
    String sectionType;
    
    @Column(name="etc1")
    String etc1;
    
    @Column(name="etc2")
    String etc2;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    public ItemToolCt() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ItemToolCt(ItemCt itemCt) {
        this.itemCtSeq  = itemCt.getItemCtSeq();
        this.factoryId  = itemCt.getFactoryId();
        this.qgClientId = itemCt.getQgClientId();
        this.machineId  = itemCt.getMachineId();
        this.productId  = itemCt.getProductId();
        this.palletsNo  = itemCt.getPalletsNo();
        this.itemId     = itemCt.getItemId();
        this.itemTypeId = itemCt.getItemTypeId();
        this.regDate    = itemCt.getRegDate();
    }

    public Integer getItemCtToolSeq() {
        return itemCtToolSeq;
    }

    public void setItemCtToolSeq(Integer itemCtToolSeq) {
        this.itemCtToolSeq = itemCtToolSeq;
    }

    public Integer getItemCtSeq() {
        return itemCtSeq;
    }

    public void setItemCtSeq(Integer itemCtSeq) {
        this.itemCtSeq = itemCtSeq;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
        this.path = path;
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

    public Integer getSectionDataSize() {
        return sectionDataSize;
    }

    public void setSectionDataSize(Integer sectionDataSize) {
        this.sectionDataSize = sectionDataSize;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getQgClientId() {
        return qgClientId;
    }

    public void setQgClientId(Integer qgClientId) {
        this.qgClientId = qgClientId;
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

    public Integer getPalletsNo() {
        return palletsNo;
    }

    public void setPalletsNo(Integer palletsNo) {
        this.palletsNo = palletsNo;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public String getEtc1() {
        return etc1;
    }

    public void setEtc1(String etc1) {
        this.etc1 = etc1;
    }

    public String getEtc2() {
        return etc2;
    }

    public void setEtc2(String etc2) {
        this.etc2 = etc2;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
