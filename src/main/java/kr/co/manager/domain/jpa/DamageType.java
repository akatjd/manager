package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DamageType {

	@Id
    @GeneratedValue
    Integer damageTypeId;
    
    String damageType;
    
    String contents;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date writeDate;
    
    String writeUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;
    
    String updateUser;

    public Integer getDamageTypeId() {
        return damageTypeId;
    }

    public void setDamageTypeId(Integer damageTypeId) {
        this.damageTypeId = damageTypeId;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(String writeUser) {
        this.writeUser = writeUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
