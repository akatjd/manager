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

import kr.co.manager.domain.view.QgAlarmHistoryView;
import kr.co.manager.service.QgAlarmHistoryService;

@RestController
@RequestMapping("/qgAlarmHistoryApi")
public class QgAlarmHistoryApiController {

	private static final Logger logger = LoggerFactory.getLogger(QgAlarmHistoryApiController.class);
	
	@Autowired
	QgAlarmHistoryService qgAlarmHistoryService;
	
	@RequestMapping(value = "/getAlarmHistoryList", method = RequestMethod.POST)
	public HashMap<String, Object> getAlarmHistoryList(
			@RequestParam(value = "factoryId") Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate,
			@RequestParam(value = "alarmTypeId", required = false) Integer alarmTypeId,
			HttpSession session){
		HashMap<String, Object> resultMap = new HashMap<>();
		List<QgAlarmHistoryView> qgAlarmHistoryViewList = qgAlarmHistoryService.getAlarmHistoryList(factoryId, areaId, machineId, startDate, endDate, alarmTypeId);
		resultMap.put("qgAlarmHistoryViewList", qgAlarmHistoryViewList);
		if(startDate.length() > 10 || endDate.length() > 10) {
            session.setAttribute("startDate", startDate.substring(0, 10));
            session.setAttribute("endDate", endDate.substring(0, 10));
        }else {
            session.setAttribute("startDate", startDate);
            session.setAttribute("endDate", endDate);
        }
		return resultMap;
	}
	
	@RequestMapping(value = "/getAlarmTypeList", method = RequestMethod.POST)
	public List<HashMap<String, Object>> getAlarmTypeList(
			@RequestParam(value = "factoryId") Integer factoryId, HttpSession session){
		List<HashMap<String, Object>> alarmTypeList = qgAlarmHistoryService.getAlarmTypeList(factoryId);
		return alarmTypeList;
	}
}
