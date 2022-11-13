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

import kr.co.manager.domain.view.QgToolChangeTimeView;
import kr.co.manager.service.QgToolChangeTimeService;

@RestController
@RequestMapping("/qgToolChangeTimeApi")
public class QgToolChangeTimeApiController {
	
	private final static Logger logger = LoggerFactory.getLogger(QgToolChangeTimeApiController.class);
	
	@Autowired
	QgToolChangeTimeService qgToolChangeTimeService;
	
	@RequestMapping(value = "/qgToolChangeTime", method = RequestMethod.POST)
	public HashMap<String, Object> getToolChangeTimeList(
			@RequestParam(value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, 
			@RequestParam("standardMaxCt") Integer standardMaxCt,
			HttpSession session) {
		
		List<QgToolChangeTimeView> qgToolChangeTimeViewList = qgToolChangeTimeService.getToolChangeTimeViewList(factoryId, areaId, machineId, startDate, endDate, standardMaxCt);
		HashMap<String, Object> viewObj = new HashMap<>();
		viewObj.put("qgToolChangeTimeViewList", qgToolChangeTimeViewList);
		if(startDate.length() > 10 || endDate.length() > 10) {
            session.setAttribute("startDate", startDate.substring(0, 10));
            session.setAttribute("endDate", endDate.substring(0, 10));
        }else {
            session.setAttribute("startDate", startDate);
            session.setAttribute("endDate", endDate);
        }
		return viewObj;
	}
	
	@RequestMapping(value = "/qgToolChangeTimeDetail", method = RequestMethod.POST)
	public HashMap<String, Object> getToolChangeTimeDetailList(
			@RequestParam("machineId") Integer machineId,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("standardMaxCt") Integer standardMaxCt) {
		
		List<QgToolChangeTimeView> qgToolChangeTimeDetailViewList = qgToolChangeTimeService.getToolChangeTimeDetailViewList(machineId, startDate, endDate, standardMaxCt);
		HashMap<String, Object> viewObj = new HashMap<>();
		viewObj.put("qgToolChangeTimeDetailViewList", qgToolChangeTimeDetailViewList);
		return viewObj;
	}
}
