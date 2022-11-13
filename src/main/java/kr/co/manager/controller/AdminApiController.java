package kr.co.manager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.domain.view.AccountView;
import kr.co.manager.service.AdminService;

@RestController
@RequestMapping("/adminApi")
public class AdminApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminApiController.class);
	
	@Autowired
	AdminService adminService;
	
	// 어드민 - 사용자 관리 리스트 불러오기
	@RequestMapping(value = "/getMemberList")
    public List<AccountView> getMemberList(@RequestParam(value = "factoryId", required = false) Integer factoryId, HttpSession session){
        List<AccountView> accountList = new ArrayList<AccountView>();
        accountList = adminService.getMembers(factoryId, session);
        return accountList;
    }
	
	// 어드민 - 사용자 관리 - 멤버 삭제
	@RequestMapping(value = "/deleteMember")
    public String deleteMember(@RequestParam(value = "accountId") String accountId){
        String resultMsg = adminService.deleteMember(accountId);
        return resultMsg;
    }
	
	// 어드민 - 사용자 관리 - 멤버 수정
	@RequestMapping(value = "/getMember")
    public AccountBcrypt getMember(@RequestParam(value = "accountId") String accountId){
		AccountBcrypt account = adminService.findAccount(accountId);
        return account;
    }
	
	// 어드민 - 사용자 관리 - 사용자 등록 - 중복 아이디 체크
	@RequestMapping(value = "/accountIdCheck")
    public String userIdCheck(@RequestParam(value = "accountId") String accountId) {
		AccountBcrypt account = adminService.findAccount(accountId);
        if (account == null) {
            return "TRUE";
        }
        return "FALSE";
    }
}
