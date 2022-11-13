package kr.co.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.service.MyPageService;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

	private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);
	
	@Autowired
	MyPageService myPageService;
	
	@RequestMapping(value = "/getMyPageView", method = RequestMethod.GET)
	public String getMyPageView(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		AccountBcrypt account = myPageService.getUserInfo(session);
		model.addAttribute("account", account);
		return "falcon/mypage";
	}
}
