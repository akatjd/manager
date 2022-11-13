package kr.co.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;

import kr.co.manager.domain.jpa.AlarmType;
import kr.co.manager.domain.view.QgAlarmHistoryView;
import kr.co.manager.exception.rdb.AlarmEventStatusRepository;
import kr.co.manager.exception.rdb.AlarmEventStatusRepositorySupport;
import kr.co.manager.exception.rdb.AlarmStatusRepository;
import kr.co.manager.exception.rdb.AlarmTypeRepository;

@Service
public class QgAlarmHistoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(QgAlarmHistoryService.class);
	
	@Autowired
	AlarmStatusRepository alarmStatusRepository;
	
	@Autowired
	AlarmTypeRepository alarmTypeRepository;
	
	@Autowired
	AlarmEventStatusRepository alarmEventStatusRepository;
	
	@Autowired
	AlarmEventStatusRepositorySupport alarmEventStatusRepositorySupport;
	
	public List<QgAlarmHistoryView> getAlarmHistoryList(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, Integer alarmTypeId){
		List<QgAlarmHistoryView> qgAlarmHistoryViewList = new ArrayList<>();
//		List<Object[]> qgAlarmHistoryList = alarmEventStatusRepository.userFindAlarmHistoryByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, alarmTypeId);
		// 2022-01-20 querydsl로 변경
		List<Tuple> qgAlarmHistoryTupleList = alarmEventStatusRepositorySupport.userFindAlarmHistoryByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, alarmTypeId);
		
//		for(Object[] objArr : qgAlarmHistoryList) {
//			QgAlarmHistoryView qgAlarmHistoryView = new QgAlarmHistoryView();
//			qgAlarmHistoryView.setAreaId(objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0);
//			qgAlarmHistoryView.setAreaName(objArr[1] != null ? objArr[1].toString() : "");
//			qgAlarmHistoryView.setMachineId(objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : 0);
//			qgAlarmHistoryView.setMachineName(objArr[3] != null ? objArr[3].toString() : "");
//			qgAlarmHistoryView.setRegDate(objArr[4] != null ? objArr[4].toString() : "");
//			qgAlarmHistoryView.setUpdateDate(objArr[5] != null ? objArr[5].toString() : "");
//			qgAlarmHistoryView.setAlarmId(objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : 0);
//			qgAlarmHistoryView.setAlarmNo(objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
//			qgAlarmHistoryView.setAlarmAxis(objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
//			qgAlarmHistoryView.setAlarmMsg(objArr[10] != null ? objArr[10].toString() : "");
//			qgAlarmHistoryView.setStrAlarmType(objArr[11] != null ? objArr[11].toString() : "");
//			qgAlarmHistoryViewList.add(qgAlarmHistoryView);
//		}
		for(Tuple t : qgAlarmHistoryTupleList) {
			Object[] objArr = t.toArray();
			QgAlarmHistoryView qgAlarmHistoryView = new QgAlarmHistoryView();
			qgAlarmHistoryView.setAreaId(objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0);
			qgAlarmHistoryView.setAreaName(objArr[1] != null ? objArr[1].toString() : "");
			qgAlarmHistoryView.setMachineId(objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : 0);
			qgAlarmHistoryView.setMachineName(objArr[3] != null ? objArr[3].toString() : "");
			qgAlarmHistoryView.setRegDate(objArr[4] != null ? objArr[4].toString() : "");
			qgAlarmHistoryView.setUpdateDate(objArr[5] != null ? objArr[5].toString() : "");
			qgAlarmHistoryView.setAlarmId(objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : 0);
			qgAlarmHistoryView.setAlarmNo(objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
			qgAlarmHistoryView.setAlarmAxis(objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
			qgAlarmHistoryView.setAlarmMsg(objArr[10] != null ? objArr[10].toString() : "");
			qgAlarmHistoryView.setStrAlarmType(objArr[11] != null ? objArr[11].toString() : "");
			qgAlarmHistoryViewList.add(qgAlarmHistoryView);
		}
		
		return qgAlarmHistoryViewList;
	}
	
	public List<HashMap<String, Object>> getAlarmTypeList(Integer factoryId) {
		List<HashMap<String, Object>> resultList = new ArrayList<>();
		
		List<AlarmType> alarmTypeList = alarmTypeRepository.findByFactoryId(factoryId);
		HashMap<String, Object> allMap = new HashMap<>();
		allMap.put("alarmTypeId", null);
		allMap.put("alarmType", "-All-");
		resultList.add(allMap);
		for(AlarmType at : alarmTypeList) {
			HashMap<String, Object> alarmTypeMap = new HashMap<>();
			Integer alarmTypeId = at.getAlarmTypeId();
			String alarmType = at.getAlarmType();
			alarmTypeMap.put("alarmTypeId", alarmTypeId);
			alarmTypeMap.put("alarmType", alarmType);
			resultList.add(alarmTypeMap);
		}
		
		return resultList;
	}
	
}
