package kr.co.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.domain.jpa.DowntimeSchedule;
import kr.co.manager.domain.jpa.Factory;
import kr.co.manager.domain.jpa.SchedulePolicy;
import kr.co.manager.domain.view.QgDowntimeScheduleView;
import kr.co.manager.domain.view.SchedulePolicyView;
import kr.co.manager.exception.QgException;
import kr.co.manager.exception.rdb.DownTimeScheduleRepository;
import kr.co.manager.exception.rdb.FactoryRepository;
import kr.co.manager.exception.rdb.SchedulePolicyRepository;
import kr.co.manager.util.JsonConverter;

@Service
public class QgScheduleService {
	
	private static final Logger logger = LoggerFactory.getLogger(QgScheduleService.class);
	
	@Autowired
    FactoryRepository factoryRepository;
	
	@Autowired
	SchedulePolicyRepository schedulePolicyRepository;
	
	@Autowired
	DownTimeScheduleRepository downtimeScheduleRepository;
	
	public List<SchedulePolicyView> getSchedulePolicy(Integer factoryId, HttpSession session) throws QgException{
        List<SchedulePolicy> schedulePolicyList = new ArrayList<SchedulePolicy>();
        List<SchedulePolicyView> schedulePolicyViewList = new ArrayList<SchedulePolicyView>();
        Factory factory = new Factory();
        if(factoryId == null) {
            AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
            Integer allowFactory = Integer.parseInt(session.getAttribute("allowFactory").toString());
            String userRole = account.getRole();
            if("ROLE_ADMIN".equals(userRole)){
                schedulePolicyList = schedulePolicyRepository.findAll();
            }else{
                factory.setFactoryId(allowFactory);
                schedulePolicyList = schedulePolicyRepository.findByFactory(factory);
            }
        }else {
            factory = factoryRepository.findByFactoryId(factoryId);
            schedulePolicyList = schedulePolicyRepository.findByFactory(factory);
        }
        for(SchedulePolicy schedulePolicy : schedulePolicyList){
            List<Integer> scheduleDayList = new ArrayList<Integer>();
            if(schedulePolicy.getAllowDayType() != null && schedulePolicy.getAllowDayType() !="")
                scheduleDayList = JsonConverter.convertToIntegerList(schedulePolicy.getAllowDayType());
            int num = 0;
            String allowDayStr = "";
            for(Integer dayType : scheduleDayList){
                if(num > 0)
                    allowDayStr += ", ";
                switch (dayType) {
                    case 1:
                        allowDayStr += "일";
                        break;
                    case 2:
                        allowDayStr += "월";
                        break;
                    case 3:
                        allowDayStr += "화";
                        break;
                    case 4:
                        allowDayStr += "수";
                        break;
                    case 5:
                        allowDayStr += "목";
                        break;
                    case 6:
                        allowDayStr += "금";
                        break;
                    case 7:
                        allowDayStr += "토";
                        break;
                    default:
                        break;
                }
                num++;
            }
            SchedulePolicyView schedulePolicyView = new SchedulePolicyView(schedulePolicy);
            schedulePolicyView.setAllowDayTypeNm(allowDayStr);
            schedulePolicyViewList.add(schedulePolicyView);
        }
        return schedulePolicyViewList;
    }
	
	// 비가동 스케줄 리스트 가져오기
	public List<QgDowntimeScheduleView> getDowntimeSchedulePolicy(Integer factoryId, HttpSession session) throws QgException{
        List<Object[]> downtimeScheduleList = new ArrayList<>();
        List<QgDowntimeScheduleView> downtimeScheduleViewList = new ArrayList<QgDowntimeScheduleView>();
        if(factoryId == null) {
            AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
            Integer allowFactory = Integer.parseInt(session.getAttribute("allowFactory").toString());
            String userRole = account.getRole();

            if("ROLE_ADMIN".equals(userRole)){
            	downtimeScheduleList = downtimeScheduleRepository.userFindDownTimeScheduleListByFactoryId(factoryId);
            }else{
                downtimeScheduleList = downtimeScheduleRepository.userFindDownTimeScheduleListByFactoryId(allowFactory);
            }
        }else {
            downtimeScheduleList = downtimeScheduleRepository.userFindDownTimeScheduleListByFactoryId(factoryId);
        }
        for(Object[] downtimeSchedule : downtimeScheduleList){
        	QgDowntimeScheduleView downtimeScheduleView = new QgDowntimeScheduleView();
        	downtimeScheduleView.setFactoryName(downtimeSchedule[0].toString());
        	downtimeScheduleView.setDtSeq(Integer.parseInt(downtimeSchedule[1].toString()));
        	downtimeScheduleView.setFactoryId(Integer.parseInt(downtimeSchedule[2].toString()));
        	downtimeScheduleView.setScheduleName(downtimeSchedule[3].toString());
        	downtimeScheduleView.setStartTime(downtimeSchedule[4].toString());
        	downtimeScheduleView.setEndTime(downtimeSchedule[5].toString());
        	downtimeScheduleView.setAutoUseYn(downtimeSchedule[6].toString());
        	downtimeScheduleView.setScheduleUseYn(downtimeSchedule[7].toString());
        	downtimeScheduleView.setDayOrNight(downtimeSchedule[8].toString());
            downtimeScheduleViewList.add(downtimeScheduleView);
        }

        return downtimeScheduleViewList;
    }
	
	public String changeActiveSchedule(Integer factoryId, Integer scheduleId, String active) {
        String resultMsg = "fail";
        if("y".equals(active)) {
            Factory factory = factoryRepository.findByFactoryId(factoryId);
            List<SchedulePolicy> spList = schedulePolicyRepository.findByFactory(factory);
            for(SchedulePolicy sp : spList) {
                Integer policyId = sp.getPolicyId();
                if(policyId.equals(scheduleId)) {
                    sp.setScheduleUseYn(active);
                }else {
                    sp.setScheduleUseYn("n");
                }
                schedulePolicyRepository.save(sp);
            }
        }else {
            SchedulePolicy sp = schedulePolicyRepository.findByPolicyId(scheduleId);
            sp.setScheduleUseYn(active);
            schedulePolicyRepository.save(sp);
        }
        
        resultMsg = "success";
        return resultMsg;
    }
	
	// 비가동 스케줄 사용, 미사용 설정
	public String changeActiveDowntimeSchedule(Integer factoryId, Integer dtSeq, String active) {
		String resultMsg = "fail";
        DowntimeSchedule ds = downtimeScheduleRepository.findByDtSeq(dtSeq);
        ds.setScheduleUseYn(active);
        downtimeScheduleRepository.save(ds);
        resultMsg = "success";
        return resultMsg;
    }
	
	public String changeActiveDayNight(Integer scheduleId, String active, String dayType) {
        String resultMsg = "fail";
        SchedulePolicy sp = schedulePolicyRepository.findByPolicyId(scheduleId);
        if("day".equals(dayType)) {
            sp.setDayUseYn(active);
        }else {
            sp.setNightUseYn(active);
        }
        schedulePolicyRepository.save(sp);
        resultMsg = "success";
        return resultMsg;
    }
	
	// 비가동 무인가공 사용, 미사용 여부
	public String changeAutoUseActive(Integer dtSeq, String active) {
		String resultMsg = "fail";
		DowntimeSchedule ds = downtimeScheduleRepository.findByDtSeq(dtSeq);
		ds.setAutoUseYn(active);
		downtimeScheduleRepository.save(ds);
        resultMsg = "success";
        return resultMsg;
    }
	
	//schedule rest edit list
    public SchedulePolicy findSchedule(Integer scheduleId){
        return schedulePolicyRepository.findByPolicyId(scheduleId);
    }
    
    //schedule delete
    public String deleteSchedule(Integer policyId){
        String resultMsg = "fail";
        schedulePolicyRepository.deleteById(policyId);
        resultMsg = "success";
        return resultMsg;
    }
    
    public String saveSchedule(HashMap<String, Object> param){
        String resultMsg = "fail";
        Integer policyId;
        SchedulePolicy sp = new SchedulePolicy();
        if(param.get("policyId") == null || "".equals(param.get("policyId"))) {
            policyId = null;
        }else {
            policyId = Integer.parseInt(param.get("policyId").toString());
            sp = schedulePolicyRepository.findByPolicyId(policyId);
        }
        Integer factoryId = Integer.parseInt(param.get("factoryId").toString());
        Factory f = factoryRepository.findByFactoryId(factoryId);
        
        String scheduleUseYn = (String) param.get("scheduleUseYn");
        if("y".equals(scheduleUseYn)) {
            List<SchedulePolicy> spList = schedulePolicyRepository.findByFactory(f);
            for(SchedulePolicy s : spList) {
                s.setScheduleUseYn("n");
                schedulePolicyRepository.save(s);
            }
        }
        sp.setFactory(f);
        sp.setPolicyName((String) param.get("policyName"));
        sp.setAllowDayType((String) param.get("allowDayType"));
        sp.setScheduleUseYn(scheduleUseYn);
        sp.setDayUseYn((String) param.get("dayUseYn"));
        sp.setDayBeginTime((String) param.get("dayBeginTime"));
        sp.setDayEndTime((String) param.get("dayEndTime"));
        sp.setNightUseYn((String) param.get("nightUseYn"));
        sp.setNightBeginTime((String) param.get("nightBeginTime"));
        sp.setNightEndTime((String) param.get("nigtEndTime"));
        
        schedulePolicyRepository.save(sp);
        
        resultMsg = "success";
        return resultMsg;
    }
    
    public String saveDowntimeSchedule(HashMap<String, Object> param){
        String resultMsg = "fail";
        Integer dtSeq;
        Date date = new Date();
        DowntimeSchedule ds = new DowntimeSchedule();
        if(param.get("policyId") == null || "".equals(param.get("policyId"))) {
        	dtSeq = null;
        }else {
            dtSeq = Integer.parseInt(param.get("policyId").toString());
            ds = downtimeScheduleRepository.findByDtSeq(dtSeq);
        }
        Integer factoryId = Integer.parseInt(param.get("factoryId").toString());
        if(param.get("policyId") != null && (!("").equals(param.get("policyId").toString()))) {
        	ds.setDtSeq(Integer.parseInt(param.get("policyId").toString()));
        }
        ds.setFactoryId(factoryId);
        ds.setScheduleName((String) param.get("policyName"));
        ds.setStartTime((String) param.get("beginTime"));
        ds.setEndTime((String) param.get("endTime"));
        ds.setAutoUseYn((String) param.get("autoUseYn"));
        ds.setScheduleUseYn((String) param.get("scheduleUseYn"));
        ds.setRegDate(date);
        ds.setDayOrNight((String) param.get("dayOrNight"));
        downtimeScheduleRepository.save(ds);
        resultMsg = "success";
        return resultMsg;
    }
    
    // downtime schedule delete
    public String deleteDowntimeSchedule(Integer dtSeq){
        String resultMsg = "fail";
        downtimeScheduleRepository.deleteById(dtSeq);
        resultMsg = "success";
        return resultMsg;
    }
    
  //schedule rest edit list
    public DowntimeSchedule findDowntimeSchedule(Integer dtSeq){
        return downtimeScheduleRepository.findByDtSeq(dtSeq);
    }
    
}
