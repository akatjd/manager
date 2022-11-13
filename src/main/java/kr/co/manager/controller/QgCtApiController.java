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

import kr.co.manager.domain.view.QgCtView;
import kr.co.manager.service.QgCtService;

@RestController
@RequestMapping("/qgCtApi")
public class QgCtApiController {
	
	private final static Logger logger = LoggerFactory.getLogger(QgCtApiController.class);
	
	@Autowired
	QgCtService qgCtService;
	
	@RequestMapping(value = "/getCtViewList", method = RequestMethod.POST)
	public HashMap<String, Object> getCtViewList(
			@RequestParam(value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam(value = "productId", required = false) Integer productId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate,
			@RequestParam(value = "standardMaxCt") Integer standardMaxCt, HttpSession session) {
		HashMap<String, Object> viewObj = new HashMap<>();
		List<QgCtView> qgCtViewList = qgCtService.getQgCtViewList(factoryId, areaId, machineId, productId, startDate, endDate, standardMaxCt);
		viewObj.put("qgCtViewList", qgCtViewList);
		if(startDate.length() > 10 || endDate.length() > 10) {
		    session.setAttribute("startDate", startDate.substring(0, 10));
	        session.setAttribute("endDate", endDate.substring(0, 10));
		}else {
		    session.setAttribute("startDate", startDate);
	        session.setAttribute("endDate", endDate);
		}
		return viewObj;
	}
	
	@RequestMapping(value = "/getCtDetailViewList", method = RequestMethod.POST)
	public HashMap<String, Object> getCtDetailViewList(
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam(value = "productId", required = false) Integer productId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate) {
		HashMap<String, Object> viewObj = new HashMap<>();
		List<QgCtView> qgCtDetailViewList = qgCtService.getQgCtDetailViewList(machineId, productId, startDate, endDate);
		viewObj.put("qgCtDetailViewList", qgCtDetailViewList);
		return viewObj;
	}
}
