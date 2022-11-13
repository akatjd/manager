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
import kr.co.manager.service.QgAutoUseService;

@RestController
@RequestMapping("/qgAutoUseApi")
public class QgAutoUseApiController {

    private static final Logger logger = LoggerFactory.getLogger(QgAutoUseApiController.class);
    
    @Autowired
    QgAutoUseService qgAutoUseService;
    
    @RequestMapping(value = "/getAutoUseList", method = RequestMethod.POST)
    public HashMap<String, Object> getAutoUseList(
            @RequestParam(value = "factoryId", required = false) Integer factoryId,
            @RequestParam(value = "areaId", required = false) Integer areaId,
            @RequestParam(value = "machineId", required = false) Integer machineId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate, HttpSession session) throws ParseException {
        HashMap<String, Object> viewObj = new HashMap<>();
        List<HashMap<String, Object>> qgAutoUseViewList = qgAutoUseService.getAutoUseViewList(factoryId, areaId, machineId, startDate, endDate);
        viewObj.put("qgAutoUseViewList", qgAutoUseViewList);
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
