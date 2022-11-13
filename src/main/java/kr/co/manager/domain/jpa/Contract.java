package kr.co.manager.domain.jpa;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contract {

	@ManyToOne
    @JoinColumn(name="FACTORY_ID")
    Factory factory;
    
    @OneToMany(mappedBy="contract", fetch = FetchType.LAZY)    
    List<ContractFile> contractFile;
    
    @Id
    @GeneratedValue
    Integer contractId;

    String manager;

    String contractType;

    BigInteger contractFees;

    Byte[] contractContent;

    @Temporal(TemporalType.TIMESTAMP)
    Date regDate;

    public List<ContractFile> getContractFile() {
        return contractFile;
    }

    public void setContractFile(List<ContractFile> contractFile) {
        this.contractFile = contractFile;
    }

    public BigInteger getContractFees() {
        return contractFees;
    }

    public void setContractFees(BigInteger contractFees) {
        this.contractFees = contractFees;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Byte[] getContractContent() {
        return contractContent;
    }

    public void setContractContent(Byte[] contractContent) {
        this.contractContent = contractContent;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "factory=" + factory +
                ", contractId=" + contractId +
                ", manager='" + manager + '\'' +
                ", contractType='" + contractType + '\'' +
                ", contractFees='" + contractFees + '\'' +
                ", contractContent=" + Arrays.toString(contractContent) +
                ", regDate=" + regDate +
                '}';
    }
}
