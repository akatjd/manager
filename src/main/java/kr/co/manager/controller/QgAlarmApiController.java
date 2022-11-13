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
import kr.co.manager.service.QgAlarmService;

@RestController
@RequestMapping("/qgAlarmApi")
public class QgAlarmApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(QgAlarmApiController.class);
	
	@Autowired
	QgAlarmService qgAlarmService;
	
	@RequestMapping(value = "/getAlarmList", method = RequestMethod.POST)
	public HashMap<String, Object> getAlarmList(
			@RequestParam(value = "factoryId") Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate,
			@RequestParam(value = "filter") String filter,
			@RequestParam(value = "division") String division,
			@RequestParam(value = "alarmTypeId", required = false) Integer alarmTypeId,
			@RequestParam(value = "alarmId", required = false) Integer alarmId, HttpSession session){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<QgAlarmView> qgAlarmViewList = qgAlarmService.getAlarmList(factoryId, areaId, machineId, startDate, endDate, filter, division, alarmTypeId);
		List<HashMap<String, Object>> qgAlarmDetailViewList = null;
		if(division.equals("machine")) {
			qgAlarmDetailViewList = qgAlarmService.getAlarmDetailList(factoryId, areaId, machineId, startDate, endDate, filter, division, alarmTypeId);
		}else {
			qgAlarmDetailViewList = qgAlarmService.getAlarmDetailData(factoryId, areaId, machineId, startDate, endDate, filter, division, alarmTypeId, alarmId);
		}
		HashMap<String, Object> qgAlarmChartDataList = qgAlarmService.getAlarmChartData(factoryId, areaId, machineId, startDate, endDate, filter, division, alarmTypeId);
		List<String> dayList = qgAlarmService.getDayList(startDate, endDate);
		resultMap.put("qgAlarmViewList", qgAlarmViewList);
		resultMap.put("qgAlarmDetailViewList", qgAlarmDetailViewList);
		resultMap.put("qgAlarmChartDataList", qgAlarmChartDataList);
		resultMap.put("dayList", dayList);
		if(startDate.length() > 10 || endDate.length() > 10) {
            session.setAttribute("startDate", startDate.substring(0, 10));
            session.setAttribute("endDate", endDate.substring(0, 10));
        }else {
            session.setAttribute("startDate", startDate);
            session.setAttribute("endDate", endDate);
        }
		return resultMap;
	}
}
