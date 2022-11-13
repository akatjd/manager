package kr.co.manager.domain.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DamageItem {

	@Id
    @GeneratedValue
    Integer damageItemId;
    
    Integer itemId;

    Integer damageTypeId;
    
    String contents;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date writeDate;
    
    String writeUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date updateDate;
    
    String updateUser;

    public Integer getDamageItemId() {
        return damageItemId;
    }

    public void setDamageItemId(Integer damageItemId) {
        this.damageItemId = damageItemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getDamageTypeId() {
        return damageTypeId;
    }

    public void setDamageTypeId(Integer damageTypeId) {
        this.damageTypeId = damageTypeId;
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
