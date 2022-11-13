package kr.co.manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.domain.jpa.Area;
import kr.co.manager.domain.jpa.Factory;
import kr.co.manager.domain.type.IssueStatus;
import kr.co.manager.domain.type.MachineStatusType;
import kr.co.manager.domain.type.WarnStatus;
import kr.co.manager.domain.view.MachineView;
import kr.co.manager.exception.rdb.AreaRepository;
import kr.co.manager.exception.rdb.FactoryRepository;
import kr.co.manager.service.MachineService;
import kr.co.manager.util.ProcessUtil;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    FactoryRepository factoryRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    MachineService machineService;

    // root 페이지 호출 (로그인 페이지 리다이렉트)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "redirect:/main/login";
    }

    // 로그인 페이지 호출
    @RequestMapping(value = "/main/login", method = RequestMethod.GET)
    String login(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        return "falcon/login";
    }

    // 설비 모니터링 페이지 호출
    @RequestMapping(value = "/main/qgMonitoring", method = RequestMethod.GET)
    public String getMonitoringView(Model model, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {

        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }

        logger.info("account :: " + account);

        HashMap<String, Object> viewObj = new HashMap<>();
        List<MachineView> machines = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        List<Factory> factoryList = new ArrayList<>();
//		Integer allowFactory = Integer.parseInt(session.getAttribute("allowFactory").toString());
        Integer selArea = null;
        Integer selFactory = null;

//		String userRole = account.getRole();

        // qgMonitoring MainController 관련 시작
//		if ("ROLE_ADMIN".equals(userRole)) {
//			factoryList = factoryRepository.findAll(Sort.by(Sort.Direction.ASC, "factoryName"));
//			Factory ft = new Factory();
//			ft.setFactoryId(null);
//			ft.setFactoryName("-All-");
//			factoryList.add(0, ft);
//		} else {
//			Factory factory = new Factory();
//			factory.setFactoryId(allowFactory);
//			factoryList.add(factoryRepository.findByFactoryId(allowFactory));
//		}

        factoryList = getUserFatoryInfo(account);

        List<HashMap<String, String>> warnStatusList = new ArrayList<>();
        for (WarnStatus ws : WarnStatus.values()) {
            HashMap<String, String> warnStatusMap = new HashMap<>();
            warnStatusMap.put("code", ws.getCode());
            warnStatusMap.put("text", ws.getText());
            warnStatusList.add(warnStatusMap);
        }

        List<HashMap<String, String>> machineStatusTypeList = new ArrayList<>();
        for (MachineStatusType mt : MachineStatusType.values()) {
            HashMap<String, String> machineStatusTypeMap = new HashMap<>();
            machineStatusTypeMap.put("code", mt.getCode());
            machineStatusTypeMap.put("text", mt.getText());
            machineStatusTypeList.add(machineStatusTypeMap);
        }

        List<HashMap<String, String>> issueStatusList = new ArrayList<>();
        for (IssueStatus is : IssueStatus.values()) {
            HashMap<String, String> issueStatusMap = new HashMap<>();
            issueStatusMap.put("code", is.getCode());
            issueStatusMap.put("text", is.getText());
            issueStatusList.add(issueStatusMap);
        }

        viewObj.put("warnStatusList", warnStatusList);
        viewObj.put("machineStatusTypeList", machineStatusTypeList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("issueStatusList", issueStatusList);
        // qgMonitoring MainController 관련 끝

        Integer totalCnt = machines.size();
        session.setAttribute("machines", machines);
        session.setAttribute("areaList", areaList);
        session.setAttribute("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("selAreaId", selArea);
        viewObj.put("selFactory", selFactory);
        viewObj.put("machines", machines);
        viewObj.put("tStatus", ProcessUtil.getTotalStatusMachineView(machines, totalCnt));
        model.addAllAttributes(viewObj);

        return "falcon/qg-monitoring";
    }

    // 설비 온도 페이지 호출
    @RequestMapping(value = "/main/qgTemperature", method = RequestMethod.GET)
    public String getTemperatureView(Model model, HttpSession session) throws IOException {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-temperature";
    }

    // 프로그램 수정 횟수 페이지 호출
    @RequestMapping(value = "/main/qgProgramCnt", method = RequestMethod.GET)
    public String getProgramCntView(Model model, HttpSession session) throws IOException {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-program-count";
    }

    // 알람 정지 횟수 페이지 호출
    @RequestMapping(value = "/main/qgStopCnt", method = RequestMethod.GET)
    public String getStopCntView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-stop-count";
    }

    // 공구 수명 페이지 호출
    @RequestMapping(value = "/main/qgToolLife", method = RequestMethod.GET)
    public String getToolLifeView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-tool-life";
    }

    // 공구 교환 횟수 페이지 호출
    @RequestMapping(value = "/main/qgToolChangeCnt", method = RequestMethod.GET)
    public String getToolChangeCntView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-tool-change-count";
    }

    // 공구 교환 시간 페이지 호출
    @RequestMapping(value = "/main/qgToolChangeTime", method = RequestMethod.GET)
    public String getToolChangeTimeView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-tool-change-time";
    }

    // CT 페이지 호출
    @RequestMapping(value = "/main/qgCt", method = RequestMethod.GET)
    public String getCtView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-ct";
    }

    // 스케줄 정책 (주, 야간 스케줄) 페이지 호출
    @RequestMapping(value = "/main/qgSchedule", method = RequestMethod.GET)
    public String getScheduleView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-schedule";
    }

    // 비가동 스케줄 정책 페이지 호출
    @RequestMapping(value = "/main/qgDowntimeSchedule", method = RequestMethod.GET)
    public String getDowntimeScheduleView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-schedule-downtime";
    }

    // 공구 교환 횟수 페이지 호출
    @RequestMapping(value = "/main/qgAutoUse", method = RequestMethod.GET)
    public String getAutoUseView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-auto-use";
    }

    // 제품 관리 페이지 호출
    @RequestMapping(value = "/main/qgProductSetting", method = RequestMethod.GET)
    public String getProductSettingView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-product-setting";
    }

    // 공구 관리 페이지 호출
    @RequestMapping(value = "/main/qgToolSetting", method = RequestMethod.GET)
    public String getToolSettingView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-tool-setting";
    }

//	// 알람 페이지 호출
//	@RequestMapping(value = "/main/qgAlarm", method = RequestMethod.GET)
//	public String getAlarmView(Model model, HttpSession session) {
//		AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
//		HashMap<String, Object> viewObj = new HashMap<>();
//		List<Factory> factoryList = getUserFatoryInfo(account);
//		List<Area> areaList = new ArrayList<>();
//		session.setAttribute("factoryList", factoryList);
//		session.setAttribute("areaList", areaList);
//		viewObj.put("factoryList", factoryList);
//		viewObj.put("areaList", areaList);
//		model.addAllAttributes(viewObj);
//		
//		return "falcon/qg-alarm";
//	}

    // 알람 페이지 호출
    @RequestMapping(value = "/main/qgAlarm", method = RequestMethod.GET)
    public String getAlarmView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        List<Area> alarmTypeList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        session.setAttribute("alarmTypeList", alarmTypeList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        viewObj.put("alarmTypeList", alarmTypeList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-alarm";
    }

    // 알람이력 페이지 호출
    @RequestMapping(value = "/main/qgAlarmHistory", method = RequestMethod.GET)
    public String getAlarmHistoryView(Model model, HttpSession session) {
        AccountBcrypt account = (AccountBcrypt) session.getAttribute("account");
        if (account == null) {
            return "falcon/login";
        }
        HashMap<String, Object> viewObj = new HashMap<>();
        List<Factory> factoryList = getUserFatoryInfo(account);
        List<Area> areaList = new ArrayList<>();
        List<Area> alarmTypeList = new ArrayList<>();
        session.setAttribute("factoryList", factoryList);
        session.setAttribute("areaList", areaList);
        session.setAttribute("alarmTypeList", alarmTypeList);
        viewObj.put("factoryList", factoryList);
        viewObj.put("areaList", areaList);
        viewObj.put("alarmTypeList", alarmTypeList);
        model.addAllAttributes(viewObj);

        return "falcon/qg-alarm-history";
    }

    // 유저 공장리스트(allowFactory) 정보 반환 모듈
    public List<Factory> getUserFatoryInfo(AccountBcrypt account) {
        List<Factory> factoryList = new ArrayList<>();
        Integer allowFactory = Integer.parseInt(account.getAllowFactory());
        String userRole = account.getRole();

        if ("ROLE_ADMIN".equals(userRole)) {
            factoryList = factoryRepository.findAll(Sort.by(Sort.Direction.ASC, "factoryName"));
//			Factory ft = new Factory();
//			ft.setFactoryId(null);
//			ft.setFactoryName("-All-");
//			factoryList.add(0, ft);
        } else {
            Factory factory = new Factory();
            factory.setFactoryId(allowFactory);
            factoryList.add(factoryRepository.findByFactoryId(allowFactory));
        }

        return factoryList;
    }
}