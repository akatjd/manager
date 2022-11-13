package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Product {
    
    @Id
    @GeneratedValue
    @Column(name="PRODUCT_ID")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name="MACHINE_ID")
    Machine machine;

    @Column(length = 300)
    String productName;

    @Column(length = 20)
    String productOid;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    Integer todayCnt;

    Float cycleTime;

    Float staticCycleTime;

    Float dynamicCycleTime;

    Integer barfeederSeq;

    Integer barfeederMaxSeq;

    Integer lastProductionCnt;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date lastProductionDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date resetTime;
    
    String activeYn;
    
    // 민성 추가 2021-08-19 isN1111detect, isN9999detect
    @Column(name="is_N1111_detect")
    Integer isN1111Detect;
    
    @Column(name="is_N9999_detect")
    Integer isN9999Detect;
    
    public Integer getIsN1111Detect() {
		return isN1111Detect;
	}

	public void setIsN1111Detect(Integer isN1111Detect) {
		this.isN1111Detect = isN1111Detect;
	}

	public Integer getIsN9999Detect() {
		return isN9999Detect;
	}

	public void setIsN9999Detect(Integer isN9999Detect) {
		this.isN9999Detect = isN9999Detect;
	}
    
    public String getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(String activeYn) {
        this.activeYn = activeYn;
    }

    public Date getResetTime() {
        return resetTime;
    }

    public void setResetTime(Date resetTime) {
        this.resetTime = resetTime;
    }

    public Integer getLastProductionCnt() {
        return lastProductionCnt;
    }

    public void setLastProductionCnt(Integer lastProductionCnt) {
        this.lastProductionCnt = lastProductionCnt;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductOid() {
        return productOid;
    }

    public void setProductOid(String productOid) {
        this.productOid = productOid;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getTodayCnt() {
        return todayCnt;
    }

    public void setTodayCnt(Integer todayCnt) {
        this.todayCnt = todayCnt;
    }

    public Float getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(Float cycleTime) {
        this.cycleTime = cycleTime;
    }

    public Float getStaticCycleTime() {
        return staticCycleTime;
    }

    public void setStaticCycleTime(Float staticCycleTime) {
        this.staticCycleTime = staticCycleTime;
    }

    public Integer getBarfeederSeq() {
        return barfeederSeq;
    }

    public void setBarfeederSeq(Integer barfeederSeq) {
        this.barfeederSeq = barfeederSeq;
    }

    public Integer getBarfeederMaxSeq() {
        return barfeederMaxSeq;
    }

    public void setBarfeederMaxSeq(Integer barfeederMaxSeq) {
        this.barfeederMaxSeq = barfeederMaxSeq;
    }

    public Float getDynamicCycleTime() {
        return dynamicCycleTime;
    }

    public void setDynamicCycleTime(Float dynamicCycleTime) {
        this.dynamicCycleTime = dynamicCycleTime;
    }

    public Date getLastProductionDate() {
        return lastProductionDate;
    }

    public void setLastProductionDate(Date lastProductionDate) {
        this.lastProductionDate = lastProductionDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", machine=" + machine +
                ", productName='" + productName + '\'' +
                ", productOid='" + productOid + '\'' +
                ", regDate=" + regDate +
                ", todayCnt=" + todayCnt +
                ", cycleTime=" + cycleTime +
                ", staticCycleTime=" + staticCycleTime +
                ", dynamicCycleTime=" + dynamicCycleTime +
                ", barfeederSeq=" + barfeederSeq +
                ", barfeederMaxSeq=" + barfeederMaxSeq +
                ", lastProductionCnt=" + lastProductionCnt +
                ", lastProductionDate=" + lastProductionDate +
                ", isN1111Detect=" + isN1111Detect +
                ", isN9999Detect=" + isN9999Detect +
                '}';
    }
}