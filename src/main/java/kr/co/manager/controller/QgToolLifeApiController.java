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

import kr.co.manager.domain.view.QgToolLifeView;
import kr.co.manager.service.QgToolLifeService;

@RestController
@RequestMapping("/qgToolLifeApi")
public class QgToolLifeApiController {

	private static final Logger logger = LoggerFactory.getLogger(QgToolLifeApiController.class);
	
	@Autowired
	QgToolLifeService qgToolLifeService;
	
	@RequestMapping(value = "/getToolLifeViewList", method = RequestMethod.POST)
	public HashMap<String, Object> getToolLifeViewList(
			@RequestParam(value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate,
			@RequestParam(value = "minSection") Integer minSection,
			@RequestParam(value = "maxSection") Integer maxSection, HttpSession session) {
		List<QgToolLifeView> qgToolLifeViewList = qgToolLifeService.getQgToolLifeViewList(factoryId, areaId, machineId, minSection, maxSection, startDate, endDate);
		HashMap<String, Object> viewObj = new HashMap<>();
		viewObj.put("qgToolLifeViewList", qgToolLifeViewList);
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