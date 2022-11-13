package kr.co.manager.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.domain.jpa.Factory;
import kr.co.manager.domain.view.AccountView;
import kr.co.manager.exception.rdb.AccountBcryptRepository;
import kr.co.manager.exception.rdb.AccountRepository;
import kr.co.manager.exception.rdb.FactoryRepository;

@Component
public class AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

	@Autowired
	FactoryRepository factoryRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountBcryptRepository accountBcryptRepository;

	// factory role
	public List<Factory> getFactoryList(HttpSession session) {
		AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
		Integer allowFactory = Integer.parseInt(session.getAttribute("allowFactory").toString());
		Factory factory = new Factory();
		String userRole = account.getRole();
		List<Factory> factoryList = new ArrayList<Factory>();
		if ("ROLE_ADMIN".equals(userRole)) {
			factoryList = factoryRepository.findAll(Sort.by(Sort.Direction.ASC, "factoryName"));
		} else {
			factory = factoryRepository.findByFactoryId(allowFactory);
			factoryList.add(factory);
		}
		return factoryList;
	}
	
	// AdminApiController에서 accountList 호출
	public List<AccountView> getMembers(Integer factoryId, HttpSession session){
        List<AccountBcrypt> accountList = new ArrayList<>();
        if(factoryId == null) {
        	AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
            String allowFactory = session.getAttribute("allowFactory").toString();
            String userRole = account.getRole();
            if("ROLE_ADMIN".equals(userRole)){
                accountList = accountBcryptRepository.findAll();
            }else{
                accountList = accountBcryptRepository.findByAllowFactory(allowFactory);
            }
        }else {
            accountList = accountBcryptRepository.findByAllowFactory(factoryId.toString());
        }
        List<AccountView> accountViewList = new ArrayList<>();
        for(AccountBcrypt account : accountList){
            AccountView accountView = new AccountView(account);
            if(account.getAllowFactory() != null){
                Factory factory = factoryRepository.findByFactoryId(Integer.valueOf(account.getAllowFactory()));
                if(factory != null){
                    accountView.setFactoryName(factory.getFactoryName());
                    accountView.setFactoryId(factory.getFactoryId());
                }
            }
            accountViewList.add(accountView);
        }
        return accountViewList;
    }
	
	// 어드민 - 사용자 관리 - 사용자 삭제
	@Transactional
    public String deleteMember(String accountId) {
        String resultMsg = "fail";
        if(accountBcryptRepository.userDeleteByAccountId(accountId) == 1) {
        	resultMsg = "success";
        }
        return resultMsg;
    }
	
	// 어드민 - 사용자 관리 - 사용자 수정 - 사용자 account 정보 가져오기
    public AccountBcrypt findAccount(String accountId) {
        return accountBcryptRepository.findByAccountId(accountId);
    }
    
    // 어드민 - 사용자 관리 - 사용자 정보 수정
    public void editMember(AccountBcrypt account){
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	String password = encoder.encode(account.getPassword());
    	account.setPassword(password);
    	accountBcryptRepository.save(account);
    }
    
 // 어드민 - 사용자 관리 - 사용자 등록
    public void addMember(AccountBcrypt account) {
    	// 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        account.setPassword(passwordEncoder.encode(account.getPassword()));
    	accountBcryptRepository.save(account);
    }
}
