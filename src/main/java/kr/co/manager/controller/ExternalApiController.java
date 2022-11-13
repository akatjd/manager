package kr.co.manager.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.manager.service.ExternalService;

@RestController
@RequestMapping("/external")
public class ExternalApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExternalApiController.class);
	
	@Autowired
	ExternalService externalService;
	
	// KMS 2022-02-16
	@RequestMapping(value = "/getAlarmHistory/{factoryId}/{startDate}/{endDate}", method = RequestMethod.GET)
	public List<Map<String, Object>> getAlarmHistory(@PathVariable("factoryId") Integer factoryId, 
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		return externalService.getAlarmHistory(factoryId, startDate, endDate);
	}
	
	@RequestMapping(value = "/getAlarmHistory/{startDate}/{endDate}", method = RequestMethod.GET)
	public List<Map<String, Object>> getAlarmHistoryWithFactoryId(
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		return externalService.getAlarmHistory(startDate, endDate);
	}
}
