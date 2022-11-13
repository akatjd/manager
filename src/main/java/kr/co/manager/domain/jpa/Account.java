package kr.co.manager.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
    @Column(length = 25)
    String accountId;

    String password;

    String role;

    String userName;

    String department;

    String allowFactory;
    
    String mainPageUrl;
    
    String approvalStatus;

    String apiToken;

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setAppovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getMainPageUrl() {
        return mainPageUrl;
    }

    public void setMainPageUrl(String mainPageUrl) {
        this.mainPageUrl = mainPageUrl;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAllowFactory() {
        return allowFactory;
    }

    public void setAllowFactory(String allowFactory) {
        this.allowFactory = allowFactory;
    }

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", password=" + password + ", role=" + role + ", userName="
                + userName + ", department=" + department + ", allowFactory=" + allowFactory + ", mainPageUrl="
                + mainPageUrl + ", approvalStatus=" + approvalStatus + "]";
    }
}
