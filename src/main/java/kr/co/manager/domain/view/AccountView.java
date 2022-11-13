package kr.co.manager.domain.view;

import kr.co.manager.domain.jpa.Account;
import kr.co.manager.domain.jpa.AccountBcrypt;

public class AccountView {

	String accountId;

    String password;

    String role;

    String userName;

    String department;

    String allowFactory;

    String factoryName;

    Integer factoryId;
    
    String mainPageUrl;
    
    String approvalStatus;
    
    public AccountView(Account account) {
        this.accountId = account.getAccountId();
        this.password = account.getPassword();
        this.allowFactory = account.getAllowFactory();
        this.department = account.getDepartment();
        this.role = account.getRole();
        this.userName = account.getUserName();
        this.mainPageUrl = account.getMainPageUrl();
        this.approvalStatus = account.getApprovalStatus();
    }
    
    public AccountView(AccountBcrypt account) {
        this.accountId = account.getAccountId();
        this.password = account.getPassword();
        this.allowFactory = account.getAllowFactory();
        this.department = account.getDepartment();
        this.role = account.getRole();
        this.userName = account.getUserName();
        this.mainPageUrl = account.getMainPageUrl();
        this.approvalStatus = account.getApprovalStatus();
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

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public String getMainPageUrl() {
		return mainPageUrl;
	}

	public void setMainPageUrl(String mainPageUrl) {
		this.mainPageUrl = mainPageUrl;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
}
