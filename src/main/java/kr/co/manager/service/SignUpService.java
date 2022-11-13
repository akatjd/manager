package kr.co.manager.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.exception.rdb.AccountBcryptRepository;
import kr.co.manager.exception.rdb.AccountRepository;


@Service
public class SignUpService {
	
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SignUpService.class);

    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    AccountBcryptRepository accountBcryptRepository;

    public String accountIdChk(String accountId){
        //사용가능(true), 사용중(false), 승인요청중(req)
        String resultStr = "false";
        AccountBcrypt account = accountBcryptRepository.findByAccountId(accountId);
        if(account == null){
            resultStr = "true";
        }else{
            if("req".equals(account.getApprovalStatus())){
                resultStr = "req";
            }
        }
        return resultStr;
    }
    
    public String saveAccount(AccountBcrypt account){
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	String password = encoder.encode(account.getPassword());
    	account.setPassword(password);
        String resultStr = "fail";
        account.setAppovalStatus("req");
        AccountBcrypt resultAc = accountBcryptRepository.save(account);
        if(resultAc != null){
            resultStr = "success";
        }
        return resultStr;
    }
}

