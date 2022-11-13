package kr.co.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.manager.exception.QgException;
import kr.co.manager.service.SignUpService;

@RestController
@RequestMapping("/signUpApi")
public class SignUpApiController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(SignUpApiController.class);

	@Autowired
	SignUpService signUpService;

	@RequestMapping(value = "/accountIdChk" , method = RequestMethod.POST)
	public String accountIdChk(@RequestParam(value = "accountId") String accountId) throws QgException {
		return signUpService.accountIdChk(accountId);
	}
}
