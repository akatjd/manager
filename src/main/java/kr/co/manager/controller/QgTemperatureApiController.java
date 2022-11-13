package kr.co.manager.controller;

import java.text.ParseException;
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

import kr.co.manager.domain.view.QgTemperatureView;
import kr.co.manager.service.QgTemperatureService;

@RestController
@RequestMapping("/qgTemperatureApi")
public class QgTemperatureApiController {

	private static final Logger logger = LoggerFactory.getLogger(QgTemperatureApiController.class);

	@Autowired
	QgTemperatureService qgTemperatureService;

	@RequestMapping(value = "/getTemperatureList", method = RequestMethod.POST)
	public HashMap<String, Object> getTemperatureList(
			@RequestParam(value = "factoryId", required = false) Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, HttpSession session) throws ParseException {
		
		List<QgTemperatureView> qgTemperatureViewList = qgTemperatureService.getTemperatureList(factoryId, areaId, machineId, startDate, endDate);
		HashMap<String, Object> viewObj = new HashMap<>();
		viewObj.put("qgTemperatureViewList", qgTemperatureViewList);
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
