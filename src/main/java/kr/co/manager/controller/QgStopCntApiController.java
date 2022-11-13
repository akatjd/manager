package kr.co.manager.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.manager.domain.view.QgAlarmView;
import kr.co.manager.service.QgStopCntService;

@RestController
@RequestMapping("/qgStopCntApi")
public class QgStopCntApiController {

	private static final Logger logger = LoggerFactory.getLogger(QgStopCntApiController.class);
	
	@Autowired
	QgStopCntService qgStopCntService; 

	@RequestMapping(value = "/getStopCntList", method = RequestMethod.POST)
	public HashMap<String, Object> getStopCntList(
			@RequestParam(value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate,
			@RequestParam(value = "standard") Integer standard, HttpSession session) {
		
		List<QgAlarmView> stopCntViewList = qgStopCntService.getStopCntList(factoryId, areaId, machineId, startDate, endDate, standard); 
		HashMap<String, Object> viewObj = new HashMap<>();
		viewObj.put("stopCntViewList", stopCntViewList);
		if(startDate.length() > 10 || endDate.length() > 10) {
            session.setAttribute("startDate", startDate.substring(0, 10));
            session.setAttribute("endDate", endDate.substring(0, 10));
        }else {
            session.setAttribute("startDate", startDate);
            session.setAttribute("endDate", endDate);
        }
		return viewObj;
	}
}
