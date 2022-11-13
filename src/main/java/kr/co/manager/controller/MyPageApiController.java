package kr.co.manager.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.service.MachineService;
import kr.co.manager.service.MyPageService;

@RestController
@RequestMapping("/myPageApi")
public class MyPageApiController {

	private static final Logger logger = LoggerFactory.getLogger(MyPageApiController.class);

	@Autowired
	MyPageService myPageService;
	
	@Autowired
	MachineService machineService;

	// common.js에서 사용자 정보 얻기 위해 호출
	/**
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	public AccountBcrypt getUserInfo(HttpSession session) {
		return myPageService.getUserInfo(session);
	}

	/**
     * <pre>
     * 1. 개요 : 사용자 정보를 저장
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : saveUserInfo
     * @date : 2021. 08. 31.
     * @author : kms
     * @history : 
     *  -----------------------------------------------------------------------
     *  Date                Author                        Note
     *  ----------- ------------------- ---------------------------------------
     *  2021. 08. 31.     kms             Mypage 정보 저장 
     *  -----------------------------------------------------------------------
     * 
     * @param param      accountId     사용자 아이디
     *                   userName      사용자 이름
     *                   password      비밀번호
     *                   department    부서
     * @return String                  성공여부 fail or success
     * @throws QgException
     */
	@RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
	public String saveUserInfo(@RequestParam HashMap<String, Object> param) {
		return machineService.saveUserInfo(param);
	}
}
