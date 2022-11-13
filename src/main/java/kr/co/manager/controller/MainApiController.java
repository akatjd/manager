package kr.co.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainApi")
public class MainApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainApiController.class);
	
	@RequestMapping(value = "/getThemeColor", method = RequestMethod.POST)
	public String getThemeColor(String theme) {
		String value = theme;
		return value;
	}
}
