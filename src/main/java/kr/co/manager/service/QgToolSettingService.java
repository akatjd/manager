package kr.co.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.jpa.MachineTool;
import kr.co.manager.domain.view.QgToolSettingView;
import kr.co.manager.exception.QgException;
import kr.co.manager.exception.rdb.MachineToolRepository;
import kr.co.manager.util.JsonConverter;

@Service
public class QgToolSettingService {
	
	private static final Logger logger = LoggerFactory.getLogger(QgToolSettingService.class);
	
	@Autowired
	MachineToolRepository machineToolRepository;
	
	public List<QgToolSettingView> getToolSettingViewList(Integer factoryId, Integer areaId, Integer machineId){
		List<QgToolSettingView> qgToolSettingViewList = new ArrayList<>();
		List<Object[]> toolSettingList = machineToolRepository.userFindToolSettingListByFactoryIdAndAreaIdAndMachineId(factoryId, areaId, machineId);
		for(Object[] objArr : toolSettingList) {
			QgToolSettingView tsView = new QgToolSettingView();
			tsView.setAreaId((objArr[0] != null) ? Integer.parseInt(objArr[0].toString()) : null);
			tsView.setAreaName((objArr[1] != null) ? objArr[1].toString() : null);
			tsView.setMachineId((objArr[2] != null) ? Integer.parseInt(objArr[2].toString()) : null);
			tsView.setMachineName((objArr[3] != null) ? objArr[3].toString() : null);
			tsView.setMachineToolId((objArr[4] != null) ? Integer.parseInt(objArr[4].toString()) : null);
			tsView.setToolName((objArr[5] != null) ? objArr[5].toString() : null);
			tsView.setUseCnt((objArr[6] != null) ? Integer.parseInt(objArr[6].toString()) : null);
			tsView.setPresetCnt((objArr[7] != null) ? Integer.parseInt(objArr[7].toString()) : null);
			tsView.setToolSpec((objArr[8] != null) ? objArr[8].toString() : null);
			tsView.setUseYn((objArr[9] != null) ? objArr[9].toString().charAt(0) : null);
			tsView.setSortSeq((objArr[10] != null) ? Integer.parseInt(objArr[10].toString()) : null);
			tsView.setViewName((objArr[11] != null) ? objArr[11].toString() : null);
			qgToolSettingViewList.add(tsView);
		}
		return qgToolSettingViewList;
	}
	
	public String updateToolSetting(HashMap<String, Object> param) throws QgException {
		String msg = "fail";
		
		List<Object> tList = JsonConverter.convertToList((String) param.get("toolJson"));
		
		for(Object obj : tList) {
			String jsonStr = JsonConverter.convertToJson(obj);
			Map<String, Object> tMap = JsonConverter.convert(jsonStr);
			
			MachineTool machineTool = machineToolRepository.findByMachineToolId(Integer.parseInt(tMap.get("machineToolId").toString()));
			if(tMap.get("viewName") != null && tMap.get("viewName") != ""){
				machineTool.setViewName(tMap.get("viewName").toString());
            }else {
            	machineTool.setViewName(null);
            }
			if(tMap.get("toolSpec") != null && tMap.get("toolSpec") != ""){
				machineTool.setToolSpec((tMap.get("toolSpec").toString()));
            }else {
            	machineTool.setToolSpec(null);
            }
			machineTool.setUseYn((tMap.get("useYn").toString()));
			machineToolRepository.save(machineTool);
		}
		
		msg = "success";
		return msg;
	}
}
