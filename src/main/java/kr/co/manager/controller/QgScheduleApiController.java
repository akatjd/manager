package kr.co.manager.controller;

import java.util.ArrayList;
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

import kr.co.manager.domain.jpa.DowntimeSchedule;
import kr.co.manager.domain.jpa.SchedulePolicy;
import kr.co.manager.domain.view.QgDowntimeScheduleView;
import kr.co.manager.domain.view.SchedulePolicyView;
import kr.co.manager.exception.QgException;
import kr.co.manager.service.QgScheduleService;

@RestController
@RequestMapping("/qgScheduleApi")
public class QgScheduleApiController {

	private static final Logger logger = LoggerFactory.getLogger(QgScheduleApiController.class);

	@Autowired
	QgScheduleService qgScheduleService;
	
	@RequestMapping(value = "/getSchedule")
    public SchedulePolicy getSchedule(@RequestParam(value = "scheduleId") Integer scheduleId){
        SchedulePolicy schedulePolicy = qgScheduleService.findSchedule(scheduleId);
        return schedulePolicy;
    }

	@RequestMapping(value = "/getScheduleList", method = RequestMethod.POST)
	public List<SchedulePolicyView> searchScheduleList(
			@RequestParam(value = "factoryId", required = false) Integer factoryId, HttpSession session) {
		List<SchedulePolicyView> schedulePolicyList = new ArrayList<SchedulePolicyView>();
		try {
			schedulePolicyList = qgScheduleService.getSchedulePolicy(factoryId, session);
		} catch (QgException e) {
			e.printStackTrace();
		}
		return schedulePolicyList;
	}
	
	@RequestMapping(value = "/changeActiveSchedule")
    public String changeActiveSchedule(@RequestParam(value = "factoryId") Integer factoryId,
                                       @RequestParam(value = "scheduleId") Integer scheduleId,
                                       @RequestParam(value = "active") String active){
        String resultMsg = qgScheduleService.changeActiveSchedule(factoryId, scheduleId, active);
        return resultMsg;
    }
	
	@RequestMapping(value = "/changeActiveDowntimeSchedule")
    public String changeActiveDowntimeSchedule(@RequestParam(value = "factoryId") Integer factoryId,
                                       @RequestParam(value = "dtSeq") Integer dtSeq,
                                       @RequestParam(value = "active") String active){
        String resultMsg = qgScheduleService.changeActiveDowntimeSchedule(factoryId, dtSeq, active);
        return resultMsg;
    }

	@RequestMapping(value = "/changeActiveDayNight")
    public String changeActiveDayNight(@RequestParam(value = "scheduleId") Integer scheduleId,
                                       @RequestParam(value = "active") String active,
                                       @RequestParam(value = "dayType") String dayType){
        String resultMsg = qgScheduleService.changeActiveDayNight(scheduleId, active, dayType);
        return resultMsg;
    }
	
	// 비가동 스케줄 - 무인가동 사용 여부
	@RequestMapping(value = "/changeAutoUseActive")
    public String changeAutoUseActive(@RequestParam(value = "dtSeq") Integer dtSeq,
                                       @RequestParam(value = "active") String active){
        String resultMsg = qgScheduleService.changeAutoUseActive(dtSeq, active);
        return resultMsg;
    }
	
	@RequestMapping(value = "/saveSchedule")
    public String saveSchedule(@RequestParam HashMap<String, Object> param){
        String resultMsg = qgScheduleService.saveSchedule(param);
        return resultMsg;
    }
	
	@RequestMapping(value = "/deleteSchedule")
    public String deleteSchedule(@RequestParam(value = "scheduleId") Integer scheduleId){
        String resultMsg = qgScheduleService.deleteSchedule(scheduleId);
        return resultMsg;
    }
	
	// 비가동 스케줄 리스트 호출
	@RequestMapping(value = "/getDowntimeScheduleList", method = RequestMethod.POST)
	public List<QgDowntimeScheduleView> searchDowntimeScheduleList(
			@RequestParam(value = "factoryId", required = false) Integer factoryId, HttpSession session) {
		List<QgDowntimeScheduleView> downtimeScheduleList = new ArrayList<QgDowntimeScheduleView>();
		try {
			downtimeScheduleList = qgScheduleService.getDowntimeSchedulePolicy(factoryId, session);
		} catch (QgException e) {
			e.printStackTrace();
		}
		return downtimeScheduleList;
	}
	
	@RequestMapping(value = "/saveDowntimeSchedule")
    public String saveDowntimeSchedule(@RequestParam HashMap<String, Object> param){
        String resultMsg = qgScheduleService.saveDowntimeSchedule(param);
        return resultMsg;
    }
	
	@RequestMapping(value = "/deleteDowntimeSchedule")
    public String deleteDowntimeSchedule(@RequestParam(value = "dtSeq") Integer dtSeq){
        String resultMsg = qgScheduleService.deleteDowntimeSchedule(dtSeq);
        return resultMsg;
    }
	
	@RequestMapping(value = "/getDowntimeSchedule")
    public DowntimeSchedule getDowntimeSchedule(@RequestParam(value = "dtSeq") Integer dtSeq){
		DowntimeSchedule downtimeSchedule = qgScheduleService.findDowntimeSchedule(dtSeq);
        return downtimeSchedule;
    }
}
