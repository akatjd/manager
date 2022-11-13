package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Factory {

	@Id
    @GeneratedValue
    Integer factoryId;

    String factoryName;

    String company;

    String installType;

    String factoryManager;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    String address;

    String engineerName;

    Integer contractQuantity;


    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInstallType() {
        return installType;
    }

    public void setInstallType(String installType) {
        this.installType = installType;
    }

    public String getFactoryManager() {
        return factoryManager;
    }

    public void setFactoryManager(String factoryManager) {
        this.factoryManager = factoryManager;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEngineerName() {
        return engineerName;
    }

    public void setEngineerName(String engineerName) {
        this.engineerName = engineerName;
    }

    public Integer getContractQuantity() {
        return contractQuantity;
    }

    public void setContractQuantity(Integer contractQuantity) {
        this.contractQuantity = contractQuantity;
    }

    @Override
    public String toString() {
        return "Factory [factoryId=" + factoryId + ", factoryName=" + factoryName + ", company=" + company
                + ", installType=" + installType + ", factoryManager=" + factoryManager + ", regDate=" + regDate
                + ", address=" + address + ", engineerName=" + engineerName + ", contractQuantity=" + contractQuantity
                + "]";
    }
}
