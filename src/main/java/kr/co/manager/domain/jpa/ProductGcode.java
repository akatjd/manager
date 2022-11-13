package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.co.manager.domain.type.GcodeType;

@Entity
public class ProductGcode {

    public ProductGcode() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue
    Integer productGcodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID")
    Product product;

    Integer machineId;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date changedDate;

    @Enumerated(EnumType.STRING)
    GcodeType gcodeType;

    String oid;

    String gcodeContent;

    Integer path;

    String hash;

    Integer exProductGcodeId = 0;

    public Integer getExProductGcodeId() {
        return exProductGcodeId;
    }

    public void setExProductGcodeId(Integer exProductGcodeId) {
        this.exProductGcodeId = exProductGcodeId;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Integer getProductGcodeId() {
        return productGcodeId;
    }

    public void setProductGcodeId(Integer productGcodeId) {
        this.productGcodeId = productGcodeId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
        this.path = path;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public GcodeType getGcodeType() {
        return gcodeType;
    }

    public void setGcodeType(GcodeType gcodeType) {
        this.gcodeType = gcodeType;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getGcodeContent() {
        return gcodeContent;
    }

    public void setGcodeContent(String gcodeContent) {
        this.gcodeContent = gcodeContent;
    }

	@Override
	public String toString() {
		return "ProductGcode [productGcodeId=" + productGcodeId + ", product=" + product + ", machineId=" + machineId
				+ ", regDate=" + regDate + ", changedDate=" + changedDate + ", gcodeType=" + gcodeType + ", oid=" + oid
				+ ", gcodeContent=" + gcodeContent + ", path=" + path + ", hash=" + hash + ", exProductGcodeId="
				+ exProductGcodeId + "]";
	}
}