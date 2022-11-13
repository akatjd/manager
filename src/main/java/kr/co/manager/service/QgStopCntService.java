package kr.co.manager.service;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;

import kr.co.manager.domain.view.QgAlarmView;
import kr.co.manager.exception.rdb.AlarmEventStatusRepository;
import kr.co.manager.exception.rdb.AlarmEventStatusRepositorySupport;
import kr.co.manager.exception.rdb.AlarmStatusRepository;
import kr.co.manager.exception.rdb.ItemCtRepository;
import kr.co.manager.exception.rdb.ItemRepository;
import kr.co.manager.exception.rdb.MachineRepository;

@Service
public class QgStopCntService {

	private static final Logger logger = LoggerFactory.getLogger(QgStopCntService.class);
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	MachineRepository machineRepository;
	
	@Autowired
	ItemCtRepository itemCtRepository;
	
	@Autowired
	AlarmStatusRepository alarmStatusRepository;
	
	@Autowired
	AlarmEventStatusRepository alarmEventStatusRepository;
	
	@Autowired
	AlarmEventStatusRepositorySupport alarmEventStatusRepositorySupport;
	
	// 위험 정지 횟수 페이지 stopCntList 뽑기
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<QgAlarmView> getStopCntList(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, Integer standard) {
//		List<QgStopCntView> stopCntViewList = new ArrayList<>();
//		List<Object[]> stopCntList = itemRepository.userFindStopCntByFactoryIdAndAreaId(factoryId, areaId, machineId, startDate, endDate);
//		List<Object[]> machineList = machineRepository.userFindProgramCntMachineListByFactoryIdAndAreaId(factoryId, areaId, machineId);
//		for(Object[] machineObjArr : machineList) {
//			QgStopCntView scView = new QgStopCntView();
//			Integer tempMachineId = Integer.parseInt(machineObjArr[0].toString());
//			Integer totalItemCnt = itemCtRepository.userFindItemCntByFactoryIdAndMachineIdAndStartDateAndEndDate(factoryId, tempMachineId, startDate, endDate);
//			String tempMachineName = machineObjArr[1].toString();
//			Integer tempStopCnt = 0;
//			for(Object[] objArr : stopCntList) {
//				if(tempMachineId.equals(Integer.parseInt(objArr[0].toString()))) {
//					tempStopCnt = Integer.parseInt(objArr[2].toString());
//				}
//			}
//			scView.setMachineId(tempMachineId);
//			scView.setMachineName(tempMachineName);
//			scView.setStopCnt(tempStopCnt);
//			scView.setTotalItemCnt(totalItemCnt);
//			stopCntViewList.add(scView);
//		}
//		return stopCntViewList;
		
		List<QgAlarmView> qgAlarmViewList = new ArrayList<>();
		
//		List<Object[]> alarmMachineList = alarmStatusRepository.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate);
//		List<Object[]> alarmMachineList = alarmEventStatusRepository.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "cnt", null, "machineId");
		
		String filter = "cnt";
		Integer alarmTypeId = null;
		String groupBy = "machineId";
		
		// 2022-01-18 querydsl 추가
		List<Tuple> alarmMachineTupleList = alarmEventStatusRepositorySupport.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, filter, alarmTypeId, groupBy);
		
//		for(Object[] objArr : alarmMachineList) {
//			Integer machineHoldCnt = objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0;
//			Integer objMachineId = objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : null;
//			Integer totalItemCnt = itemCtRepository.userFindItemCntByFactoryIdAndMachineIdAndStartDateAndEndDate(factoryId, objMachineId, startDate, endDate);
//			totalItemCnt = (totalItemCnt == 0) ? 1 : totalItemCnt;
//			double calCnt = (double)(machineHoldCnt * standard) / totalItemCnt;
//			QgAlarmView qgAlarmView = new QgAlarmView();
//			qgAlarmView.setAreaId(objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
//			qgAlarmView.setAreaName(objArr[1] != null ? objArr[1].toString() : null);
//			qgAlarmView.setMachineId(objMachineId);
//			qgAlarmView.setMachineName(objArr[3] != null ? objArr[3].toString() : null);
//			qgAlarmView.setMachineHoldCnt(machineHoldCnt);
//			qgAlarmView.setTotalItemCnt(totalItemCnt);
//			qgAlarmView.setCalCnt(calCnt);
//			qgAlarmViewList.add(qgAlarmView);
//		}
		for(Tuple t : alarmMachineTupleList) {
			Object[] objArr = t.toArray();
			Integer machineHoldCnt = objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0;
			Integer objMachineId = objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : null;
			Integer totalItemCnt = itemCtRepository.userFindItemCntByFactoryIdAndMachineIdAndStartDateAndEndDate(factoryId, objMachineId, startDate, endDate);
			totalItemCnt = (totalItemCnt == 0) ? 1 : totalItemCnt;
			double calCnt = (double)(machineHoldCnt * standard) / totalItemCnt;
			QgAlarmView qgAlarmView = new QgAlarmView();
			qgAlarmView.setAreaId(objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
			qgAlarmView.setAreaName(objArr[1] != null ? objArr[1].toString() : null);
			qgAlarmView.setMachineId(objMachineId);
			qgAlarmView.setMachineName(objArr[3] != null ? objArr[3].toString() : null);
			qgAlarmView.setMachineHoldCnt(machineHoldCnt);
			qgAlarmView.setTotalItemCnt(totalItemCnt);
			qgAlarmView.setCalCnt(calCnt);
			qgAlarmViewList.add(qgAlarmView);
		}
		
		return qgAlarmViewList;
	}
}
