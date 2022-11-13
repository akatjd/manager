package kr.co.manager.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.manager.domain.view.QgToolSettingView;
import kr.co.manager.exception.QgException;
import kr.co.manager.service.QgToolSettingService;

@RestController
@RequestMapping("/qgToolSettingApi")
public class QgToolSettingApiController {

	private static final Logger logger = LoggerFactory.getLogger(QgToolSettingApiController.class);
	
	@Autowired
	QgToolSettingService qgToolSettingService;
	
	@RequestMapping(value = "/getToolSettingList", method = RequestMethod.POST)
	public HashMap<String, Object> getToolSettingList(
			@RequestParam(value = "factoryId") Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId){
		HashMap<String, Object> viewObj = new HashMap<>();
		List<QgToolSettingView> toolSettingViewList = qgToolSettingService.getToolSettingViewList(factoryId, areaId, machineId); 
		viewObj.put("toolSettingViewList", toolSettingViewList);
		return viewObj;
	}
	
	@RequestMapping(value = "/updateToolSetting", method = RequestMethod.POST)
	public String updateToolSetting(
			@RequestParam() HashMap<String, Object> param) throws QgException {
		return qgToolSettingService.updateToolSetting(param);
	}
}
