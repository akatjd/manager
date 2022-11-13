package kr.co.manager.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import kr.co.manager.domain.jpa.AccountBcrypt;

@Service
public class MyPageService {
	
	// 접속자 정보 조회, 세션에 저장되어 있는 사용자 정보를 변환
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
    public AccountBcrypt getUserInfo(HttpSession session){
		AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        return account;
    }
}
