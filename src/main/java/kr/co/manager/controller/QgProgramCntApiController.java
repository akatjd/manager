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

import kr.co.manager.domain.view.QgProgramCntView;
import kr.co.manager.service.QgProgramCntService;

@RestController
@RequestMapping("/qgProgramCntApi")
public class QgProgramCntApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(QgProgramCntApiController.class);
	
	@Autowired
	QgProgramCntService qgProgramCntService;
	
	@RequestMapping(value = "/getProgramCntList", method = RequestMethod.POST)
	public HashMap<String, Object> getProgramCntList(
			@RequestParam(value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate,
			@RequestParam(value = "standard") Integer standard, HttpSession session) {
		
		List<QgProgramCntView> programCntViewList = qgProgramCntService.getProgramCntList(factoryId, areaId, machineId, startDate, endDate, standard);
		HashMap<String, Object> viewObj = new HashMap<>();
		viewObj.put("programCntViewList", programCntViewList);
		if(startDate.length() > 10 || endDate.length() > 10) {
            session.setAttribute("startDate", startDate.substring(0, 10));
            session.setAttribute("endDate", endDate.substring(0, 10));
        }else {
            session.setAttribute("startDate", startDate);
            session.setAttribute("endDate", endDate);
        }
		return viewObj;
	}
	
	@RequestMapping(value = "/getProgramCntDetailList", method = RequestMethod.POST)
	public HashMap<String, Object> getProgramCntDetailList(
			@RequestParam(value = "factoryId") Integer factoryId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate,
			@RequestParam(value = "standard") Integer standard) {
		
		List<QgProgramCntView> programCntDetailViewList = qgProgramCntService.getProgramCntDetailList(factoryId, machineId, startDate, endDate, standard);
		HashMap<String, Object> viewObj = new HashMap<>();
		viewObj.put("programCntDetailViewList", programCntDetailViewList);
		return viewObj;
	}
}
