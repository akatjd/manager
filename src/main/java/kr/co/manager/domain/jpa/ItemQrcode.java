package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ItemQrcode {

	@Id
    @GeneratedValue
    Integer qrcodeSeq;
    
    String qrcodeId;

    Integer itemId;
    
    Integer factoryId;
    
    Integer machineId;
    
    Integer productId;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    public Integer getQrcodeSeq() {
        return qrcodeSeq;
    }

    public void setQrcodeSeq(Integer qrcodeSeq) {
        this.qrcodeSeq = qrcodeSeq;
    }

    public String getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
