package kr.co.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.service.SignUpService;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(SignUpController.class);
	
	@Autowired
	SignUpService signUpService;
	
	@RequestMapping("/signUpView")
	public String getSignUpView() {
		return "falcon/register";
	}
	
	@RequestMapping("/saveSignUp")
    public String saveSignUp(AccountBcrypt account){
        String url = "failSignUp";
        String resultStr = "fail";
        try {
            account.setRole("ROLE_USER");
            resultStr = signUpService.saveAccount(account);
        } catch (Exception e) {
            return "redirect:/login";
        }
        if("success".equals(resultStr)) url = "falcon/success-register";
        else url = "redirect:/login";
        return url;
    }
}