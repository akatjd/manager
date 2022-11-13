package kr.co.manager.domain.view;

import java.util.Date;

import kr.co.manager.domain.jpa.Product;

public class ProductView {

    Integer productId;
    
    String productName;
    
    String productOid;
    
    Date regDate;
    
    Integer todayCnt;

    Float cycleTime;

    Float staticCycleTime;

    Float dynamicCycleTime;

    Integer barfeederSeq;

    Integer barfeederMaxSeq;

    Integer lastProductionCnt;
    
    Date lastProductionDate;

    Date resetTime;
    
    String activeYn;

    public ProductView(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productOid = product.getProductOid();
        this.regDate = product.getRegDate();
        this.todayCnt = product.getTodayCnt();
        this.cycleTime = product.getCycleTime();
        this.staticCycleTime = product.getStaticCycleTime();
        this.dynamicCycleTime = product.getDynamicCycleTime();
        this.barfeederSeq = product.getBarfeederSeq();
        this.barfeederMaxSeq = product.getBarfeederMaxSeq();
        this.lastProductionCnt = product.getLastProductionCnt();
        this.lastProductionDate = product.getLastProductionDate();
        this.resetTime = product.getResetTime();
        this.activeYn = product.getActiveYn();
    }

    /**
     * 
     */
    public ProductView() {
        // TODO Auto-generated constructor stub
    }

    
    public String getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(String activeYn) {
        this.activeYn = activeYn;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Float getDynamicCycleTime() {
        return dynamicCycleTime;
    }

    public void setDynamicCycleTime(Float dynamicCycleTime) {
        this.dynamicCycleTime = dynamicCycleTime;
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

    public Integer getLastProductionCnt() {
        return lastProductionCnt;
    }

    public void setLastProductionCnt(Integer lastProductionCnt) {
        this.lastProductionCnt = lastProductionCnt;
    }

    public Date getLastProductionDate() {
        return lastProductionDate;
    }

    public void setLastProductionDate(Date lastProductionDate) {
        this.lastProductionDate = lastProductionDate;
    }

    public Date getResetTime() {
        return resetTime;
    }

    public void setResetTime(Date resetTime) {
        this.resetTime = resetTime;
    }
}
