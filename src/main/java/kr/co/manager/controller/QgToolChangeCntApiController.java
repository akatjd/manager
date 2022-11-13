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

import kr.co.manager.domain.view.QgToolChangeCntView;
import kr.co.manager.service.QgToolChangeCntService;

@RestController
@RequestMapping("/qgToolChangeCntApi")
public class QgToolChangeCntApiController {
	
	private final static Logger logger = LoggerFactory.getLogger(QgToolChangeCntApiController.class);
	
	@Autowired
	QgToolChangeCntService qgToolChangeCntService; 
	
	@RequestMapping(value = "/getToolChangeCnt", method = RequestMethod.POST)
	public HashMap<String, Object> getToolChangeCntList(
			@RequestParam(value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("standard") Integer standard, HttpSession session) {
		
		List<QgToolChangeCntView> qgToolChangeCntViewList = qgToolChangeCntService.getToolChangeCntList(factoryId, areaId, machineId, startDate, endDate, standard);
		HashMap<String, Object> viewObj = new HashMap<>();
		viewObj.put("qgToolChangeCntViewList", qgToolChangeCntViewList);
		if(startDate.length() > 10 || endDate.length() > 10) {
            session.setAttribute("startDate", startDate.substring(0, 10));
            session.setAttribute("endDate", endDate.substring(0, 10));
        }else {
            session.setAttribute("startDate", startDate);
            session.setAttribute("endDate", endDate);
        }
		return viewObj;
	}
	
	@RequestMapping(value = "/getToolChangeCntDetail", method = RequestMethod.POST)
	public HashMap<String, Object> getToolChangeCntDetailList(
			@RequestParam(value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("standard") Integer standard) {
		
		List<QgToolChangeCntView> qgToolChangeCntDetailViewList = qgToolChangeCntService.getToolChangeCntDetailList(factoryId, machineId, startDate, endDate, standard);
		HashMap<String, Object> viewObj = new HashMap<>();
		viewObj.put("qgToolChangeCntDetailViewList", qgToolChangeCntDetailViewList);
		return viewObj;
	}
}
