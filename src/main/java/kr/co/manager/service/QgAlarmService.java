package kr.co.manager.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;

import kr.co.manager.domain.view.QgAlarmView;
import kr.co.manager.exception.rdb.AlarmEventStatusRepository;
import kr.co.manager.exception.rdb.AlarmEventStatusRepositorySupport;
import kr.co.manager.exception.rdb.AlarmStatusRepository;
import kr.co.manager.exception.rdb.MachineAlarmStatusRepository;
import kr.co.manager.exception.rdb.MachineRepository;

@Service
public class QgAlarmService {
	
	private static final Logger logger = LoggerFactory.getLogger(QgAlarmService.class);
	
	@Autowired
	MachineAlarmStatusRepository machineAlarmStatusRepository;
	
	@Autowired
	AlarmStatusRepository alarmStatusRepository;
	
	@Autowired
	MachineRepository machineRepository;
	
	@Autowired
	AlarmEventStatusRepository alarmEventStatusRepository;
	
	public List<QgAlarmView> getAlarmList(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, String filter, String division, Integer alarmTypeId) {
		List<QgAlarmView> qgAlarmViewList = new ArrayList<QgAlarmView>();
//		List<Object[]> alarmMachineList = null;
		List<Tuple> alarmMachineTupleList = null;
		
		String groupBy = "machineId";
		String orderBy = "total_holding_time";
		
		if(division.equals("machine")) {
			if(filter.equals("time")) {
//				alarmMachineList = alarmEventStatusRepository.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, alarmTypeId, groupBy);
				// 2022-01-18 querydsl 추가
				alarmMachineTupleList = alarmEventStatusRepositorySupport.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, filter, alarmTypeId, groupBy);
			}else {
				orderBy = "cnt";
//				alarmMachineList = alarmEventStatusRepository.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, alarmTypeId, groupBy);
				// 2022-01-18 querydsl 추가
				alarmMachineTupleList = alarmEventStatusRepositorySupport.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, filter, alarmTypeId, groupBy);
			}
//			for(Object[] objArr : alarmMachineList) {
//				QgAlarmView qgAlarmView = new QgAlarmView();
//				qgAlarmView.setAreaId(objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
//				qgAlarmView.setAreaName(objArr[1] != null ? objArr[1].toString() : null);
//				qgAlarmView.setMachineId(objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : null);
//				qgAlarmView.setMachineName(objArr[3] != null ? objArr[3].toString() : null);
//				qgAlarmView.setMachineHoldTime(objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : null);
//				qgAlarmView.setMachineHoldCnt(objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0);
//				qgAlarmView.setAlarmId(objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : 0);
//				qgAlarmViewList.add(qgAlarmView);
//			}
			for(Tuple t : alarmMachineTupleList) {
				Object[] objArr = t.toArray();
				QgAlarmView qgAlarmView = new QgAlarmView();
				qgAlarmView.setAreaId(objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
				qgAlarmView.setAreaName(objArr[1] != null ? objArr[1].toString() : null);
				qgAlarmView.setMachineId(objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : null);
				qgAlarmView.setMachineName(objArr[3] != null ? objArr[3].toString() : null);
				qgAlarmView.setMachineHoldTime(objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : null);
				qgAlarmView.setMachineHoldCnt(objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0);
				qgAlarmView.setAlarmId(objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : 0);
				qgAlarmViewList.add(qgAlarmView);
			}
		}else {
			// 알람별 그리드 차트 뷰 데이터
			groupBy = "alarmId";
			if(filter.equals("time")) {
//				alarmMachineList = alarmEventStatusRepository.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, alarmTypeId, groupBy);
				// 2022-01-18 querydsl 추가
				alarmMachineTupleList = alarmEventStatusRepositorySupport.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, filter, alarmTypeId, groupBy);
			}else {
				orderBy = "cnt";
//				alarmMachineList = alarmEventStatusRepository.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, alarmTypeId, groupBy);
				// 2022-01-18 querydsl 추가
				alarmMachineTupleList = alarmEventStatusRepositorySupport.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, filter, alarmTypeId, groupBy);
			}
//			for(Object[] objArr : alarmMachineList) {
//				Integer machineHoldCnt = objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0;
//				if(machineHoldCnt > 0) {
//					QgAlarmView qgAlarmView = new QgAlarmView();
//					qgAlarmView.setAlarmMsg(objArr[7] != null ? objArr[7].toString() : null);
//					qgAlarmView.setMachineHoldTime(objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : null);
//					qgAlarmView.setMachineHoldCnt(objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0);
//					qgAlarmView.setAlarmId(objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : 0);
//					qgAlarmViewList.add(qgAlarmView);
//				}
//			}
			for(Tuple t : alarmMachineTupleList) {
				Object[] objArr = t.toArray();
				Integer machineHoldCnt = objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0;
				if(machineHoldCnt > 0) {
					QgAlarmView qgAlarmView = new QgAlarmView();
					qgAlarmView.setAlarmMsg(objArr[7] != null ? objArr[7].toString() : null);
					qgAlarmView.setMachineHoldTime(objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : null);
					qgAlarmView.setMachineHoldCnt(objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0);
					qgAlarmView.setAlarmId(objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : 0);
					qgAlarmViewList.add(qgAlarmView);
				}
			}
		}
		return qgAlarmViewList;
	}
	
	// 알람 디테일 그리드 (설비별 구분 조회시)
	public List<HashMap<String, Object>> getAlarmDetailList(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, String filter, String division, Integer alarmTypeId){
		List<Integer> alarmIdList = new ArrayList<>();
		List<HashMap<String, Object>> mapList = new ArrayList<>();
//		List<Object[]> alarmDetailObjList = null;
		// 2022-01-18 querydsl 추가
		List<Tuple> alarmDetailTupleList = null;
		if(machineId == null) {
			List<Object[]> machineList = machineRepository.userFindMachineListByFactoryIdAndAreaId(factoryId, areaId);
			Integer firstMachineId = 0;
			if(machineList.size() > 0) {
				firstMachineId = machineList.get(0)[0] != null ? Integer.parseInt(machineList.get(0)[0].toString()) : 0;
			}
			alarmIdList = alarmEventStatusRepository.userFindAlarmIdByMachineIdAndRegDate(firstMachineId, startDate, endDate);
			List<String> dayList = machineRepository.userFindDayListByDbDate(startDate, endDate);
			if(filter.equals("time")) {
//				alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, firstMachineId, startDate, endDate, "total_holding_time", null);
				// 2022-01-18 querydsl 추가
				alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, firstMachineId, startDate, endDate, "total_holding_time", null);
				for(Integer alarmId : alarmIdList) {
					HashMap<String, Object> map = new HashMap<>();
					for(String day : dayList) {
						map.put(day, 0);
					}
					Integer sumTotal = 0;
					map.put("alarmId", alarmId);
//					for(Object[] objArr : alarmDetailObjList) {
//						if(alarmId == Integer.parseInt(objArr[3].toString())) {
//							map.put("machineId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
//							map.put("alarmNo", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : null);
//							map.put("alarmAxis", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null);
//							map.put("alarmMsg", objArr[7] != null ? objArr[7].toString() : null);
//							for(String day : dayList) {
//								if((objArr[10].toString()).equals(day) && firstMachineId == Integer.parseInt(objArr[0].toString())) {
//									map.put(day, objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
//									sumTotal += (objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
//								}
//							}
//							map.put("alarmTypeId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : null);
//							map.put("alarmType", objArr[12] != null ? objArr[12].toString() : null);
//							map.put("sumTotal", sumTotal);
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						if(alarmId == Integer.parseInt(objArr[3].toString())) {
							map.put("machineId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
							map.put("alarmNo", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : null);
							map.put("alarmAxis", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null);
							map.put("alarmMsg", objArr[7] != null ? objArr[7].toString() : null);
							for(String day : dayList) {
								if((objArr[10].toString()).equals(day) && firstMachineId == Integer.parseInt(objArr[0].toString())) {
									map.put(day, objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
									sumTotal += (objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
								}
							}
							map.put("alarmTypeId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : null);
							map.put("alarmType", objArr[12] != null ? objArr[12].toString() : null);
							map.put("sumTotal", sumTotal);
						}
					}
					mapList.add(map);
				}
			}else {
//				alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, firstMachineId, startDate, endDate, "cnt", null);
				// 2022-01-18 querydsl 추가
				alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, firstMachineId, startDate, endDate, "cnt", null);
				for(Integer alarmId : alarmIdList) {
					HashMap<String, Object> map = new HashMap<>();
					for(String day : dayList) {
						map.put(day, 0);
					}
					Integer sumTotal = 0;
					map.put("alarmId", alarmId);
//					for(Object[] objArr : alarmDetailObjList) {
//						if(alarmId == Integer.parseInt(objArr[3].toString())) {
//							map.put("machineId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
//							map.put("alarmNo", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : null);
//							map.put("alarmAxis", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null);
//							map.put("alarmMsg", objArr[7] != null ? objArr[7].toString() : null);
//							for(String day : dayList) {
//								if((objArr[10].toString()).equals(day) && firstMachineId == Integer.parseInt(objArr[0].toString())) {
//									map.put(day, objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
//									sumTotal += Integer.parseInt(objArr[9].toString());
//								}
//							}
//							map.put("alarmTypeId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : null);
//							map.put("alarmType", objArr[12] != null ? objArr[12].toString() : null);
//							map.put("sumTotal", sumTotal);
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						if(alarmId == Integer.parseInt(objArr[3].toString())) {
							map.put("machineId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
							map.put("alarmNo", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : null);
							map.put("alarmAxis", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null);
							map.put("alarmMsg", objArr[7] != null ? objArr[7].toString() : null);
							for(String day : dayList) {
								if((objArr[10].toString()).equals(day) && firstMachineId == Integer.parseInt(objArr[0].toString())) {
									map.put(day, objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
									sumTotal += Integer.parseInt(objArr[9].toString());
								}
							}
							map.put("alarmTypeId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : null);
							map.put("alarmType", objArr[12] != null ? objArr[12].toString() : null);
							map.put("sumTotal", sumTotal);
						}
					}
					mapList.add(map);
				}
			}
		}else {
			alarmIdList = alarmEventStatusRepository.userFindAlarmIdByMachineIdAndRegDate(machineId, startDate, endDate);
			List<String> dayList = machineRepository.userFindDayListByDbDate(startDate, endDate);
			if("time".equals(filter)) {
//				alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", null);
				// 2022-01-18 querydsl 추가
				alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", null);
				for(Integer alarmId : alarmIdList) {
					HashMap<String, Object> map = new HashMap<>();
					for(String day : dayList) {
						map.put(day, 0);
					}
					Integer sumTotal = 0;
					map.put("alarmId", alarmId);
//					for(Object[] objArr : alarmDetailObjList) {
//						if(alarmId == Integer.parseInt(objArr[3].toString())) {
//							map.put("machineId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
//							map.put("alarmNo", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : null);
//							map.put("alarmAxis", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null);
//							map.put("alarmMsg", objArr[7] != null ? objArr[7].toString() : null);
//							for(String day : dayList) {
//								if((objArr[10].toString()).equals(day) && machineId == Integer.parseInt(objArr[0].toString())) {
//									map.put(day, objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
//									sumTotal += (objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
//								}
//							}
//							map.put("alarmTypeId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : null);
//							map.put("alarmType", objArr[12] != null ? objArr[12].toString() : null);
//							map.put("sumTotal", sumTotal);
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						if(alarmTypeId == null) {
							if(alarmId == Integer.parseInt(objArr[3].toString())) {
								map.put("machineId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
								map.put("alarmNo", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : null);
								map.put("alarmAxis", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null);
								map.put("alarmMsg", objArr[7] != null ? objArr[7].toString() : null);
								for(String day : dayList) {
									if((objArr[10].toString()).equals(day) && machineId == Integer.parseInt(objArr[0].toString())) {
										map.put(day, objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
										sumTotal += (objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
									}
								}
								map.put("alarmTypeId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : null);
								map.put("alarmType", objArr[12] != null ? objArr[12].toString() : null);
								map.put("sumTotal", sumTotal);
							}
						}else {
							if(alarmId == Integer.parseInt(objArr[3].toString()) && alarmTypeId == Integer.parseInt(objArr[11].toString())) {
								map.put("machineId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
								map.put("alarmNo", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : null);
								map.put("alarmAxis", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null);
								map.put("alarmMsg", objArr[7] != null ? objArr[7].toString() : null);
								for(String day : dayList) {
									if((objArr[10].toString()).equals(day) && machineId == Integer.parseInt(objArr[0].toString())) {
										map.put(day, objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
										sumTotal += (objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
									}
								}
								map.put("alarmTypeId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : null);
								map.put("alarmType", objArr[12] != null ? objArr[12].toString() : null);
								map.put("sumTotal", sumTotal);
							}
						}
					}
					if(map.get("machineId") != null) {
						mapList.add(map);
					}
				}
			}else {
//				alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "cnt", null);
				// 2022-01-18 querydsl 추가
				alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "cnt", null);
				for(Integer alarmId : alarmIdList) {
					HashMap<String, Object> map = new HashMap<>();
					for(String day : dayList) {
						map.put(day, 0);
					}
					Integer sumTotal = 0;
					map.put("alarmId", alarmId);
//					for(Object[] objArr : alarmDetailObjList) {
//						if(alarmId == Integer.parseInt(objArr[3].toString())) {
//							map.put("machineId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
//							map.put("alarmNo", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : null);
//							map.put("alarmAxis", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null);
//							map.put("alarmMsg", objArr[7] != null ? objArr[7].toString() : null);
//							for(String day : dayList) {
//								if((objArr[10].toString()).equals(day) && machineId == Integer.parseInt(objArr[0].toString())) {
//									map.put(day, objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
//									sumTotal += Integer.parseInt(objArr[9].toString());
//								}
//							}
//							map.put("alarmTypeId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : null);
//							map.put("alarmType", objArr[12] != null ? objArr[12].toString() : null);
//							map.put("sumTotal", sumTotal);
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						
						
						if(alarmTypeId == null) {
							if(alarmId == Integer.parseInt(objArr[3].toString())) {
								map.put("machineId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
								map.put("alarmNo", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : null);
								map.put("alarmAxis", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null);
								map.put("alarmMsg", objArr[7] != null ? objArr[7].toString() : null);
								for(String day : dayList) {
									if((objArr[10].toString()).equals(day) && machineId == Integer.parseInt(objArr[0].toString())) {
										map.put(day, objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
										sumTotal += Integer.parseInt(objArr[9].toString());
									}
								}
								map.put("alarmTypeId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : null);
								map.put("alarmType", objArr[12] != null ? objArr[12].toString() : null);
								map.put("sumTotal", sumTotal);
							}
						}else {
							if(alarmId == Integer.parseInt(objArr[3].toString()) && alarmTypeId == Integer.parseInt(objArr[11].toString())) {
								map.put("machineId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null);
								map.put("alarmNo", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : null);
								map.put("alarmAxis", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null);
								map.put("alarmMsg", objArr[7] != null ? objArr[7].toString() : null);
								for(String day : dayList) {
									if((objArr[10].toString()).equals(day) && machineId == Integer.parseInt(objArr[0].toString())) {
										map.put(day, objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
										sumTotal += Integer.parseInt(objArr[9].toString());
									}
								}
								map.put("alarmTypeId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : null);
								map.put("alarmType", objArr[12] != null ? objArr[12].toString() : null);
								map.put("sumTotal", sumTotal);
							}
						}
					}
					if(map.get("machineId") != null) {
						mapList.add(map);
					}
				}
			}
		}
		return mapList;
	}
	
	// 알람 디테일 그리드 (알람별 구분 조회시)
	public List<HashMap<String, Object>> getAlarmDetailData(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, String filter, String division, Integer alarmTypeId, Integer alarmId){
//		List<Object[]> alarmDetailObjList = null;
		// 2022-01-18 querydsl 추가
		List<Tuple> alarmDetailTupleList = null;
		
		List<HashMap<String, Object>> mapList = new ArrayList<>();
		List<Integer> machineIdList = new ArrayList<>();
		List<String> dayList = machineRepository.userFindDayListByDbDate(startDate, endDate);
		if(alarmId == null) {
			if("time".equals(filter)) {
				List<Integer> alarmIdList = new ArrayList<>();
//				alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", alarmId);
				// 2022-01-18 querydsl 추가
				alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", alarmId);
//				for(Object[] objArr : alarmDetailObjList) {
//					machineIdList.add(Integer.parseInt(objArr[0].toString()));
//					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
//				}
				for(Tuple t : alarmDetailTupleList) {
					Object[] objArr = t.toArray();
					machineIdList.add(Integer.parseInt(objArr[0].toString()));
					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
				}
				
				machineIdList = machineIdList.stream().distinct().collect(Collectors.toList());
				alarmIdList = alarmIdList.stream().distinct().collect(Collectors.toList());
				Integer firstAlarmId = null;
				if(alarmIdList.size() > 0) {
					firstAlarmId = alarmIdList.get(0);
				}
				for(Integer id : machineIdList) {
					HashMap<String, Object> map = new HashMap<>();
					for(String day : dayList) {
						map.put(day, 0);
					}
					Integer sumTotal = 0;
					map.put("machineId", id);
					
//					for(Object[] objArr : alarmDetailObjList) {
//						if(id == Integer.parseInt(objArr[0].toString())) {
//							map.put("alarmId", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : null);
//							for(String day : dayList) {
//								if((objArr[10].toString()).equals(day) && firstAlarmId == Integer.parseInt(objArr[3].toString())) {
//									map.put(day, objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
//									sumTotal += (objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
//								}
//							}
//							map.put("machineName", objArr[13] != null ? objArr[13].toString() : null);
//							map.put("sumTotal", sumTotal);
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						if(id == Integer.parseInt(objArr[0].toString())) {
							map.put("alarmId", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : null);
							for(String day : dayList) {
								if((objArr[10].toString()).equals(day) && firstAlarmId == Integer.parseInt(objArr[3].toString())) {
									map.put(day, objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
									sumTotal += (objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
								}
							}
							map.put("machineName", objArr[13] != null ? objArr[13].toString() : null);
							map.put("sumTotal", sumTotal);
						}
					}
					
					if(sumTotal > 0) {
						mapList.add(map);
					}
				}
			}else {
//				alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "cnt", alarmId);
				// 2022-01-18 querydsl 추가
				alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "cnt", alarmId);
				
				List<Integer> alarmIdList = new ArrayList<>();
				
//				for(Object[] objArr : alarmDetailObjList) {
//					machineIdList.add(Integer.parseInt(objArr[0].toString()));
//					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
//				}
				for(Tuple t : alarmDetailTupleList) {
					Object[] objArr= t.toArray();
					machineIdList.add(Integer.parseInt(objArr[0].toString()));
					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
				}
				
				machineIdList = machineIdList.stream().distinct().collect(Collectors.toList());
				alarmIdList = alarmIdList.stream().distinct().collect(Collectors.toList());
				Integer firstAlarmId = null;
				if(alarmIdList.size() > 0) {
					firstAlarmId = alarmIdList.get(0);
				}
				
				for(Integer id : machineIdList) {
					HashMap<String, Object> map = new HashMap<>();
					for(String day : dayList) {
						map.put(day, 0);
					}
					Integer sumTotal = 0;
					map.put("machineId", id);
					
//					for(Object[] objArr : alarmDetailObjList) {
//						if(id == Integer.parseInt(objArr[0].toString())) {
//							map.put("alarmId", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : null);
//							for(String day : dayList) {
//								if((objArr[10].toString()).equals(day) && firstAlarmId == Integer.parseInt(objArr[3].toString())) {
//									map.put(day, objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
//									sumTotal += Integer.parseInt(objArr[9].toString());
//								}
//							}
//							map.put("machineName", objArr[13] != null ? objArr[13].toString() : null);
//							map.put("sumTotal", sumTotal);
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						if(id == Integer.parseInt(objArr[0].toString())) {
							map.put("alarmId", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : null);
							for(String day : dayList) {
								if((objArr[10].toString()).equals(day) && firstAlarmId == Integer.parseInt(objArr[3].toString())) {
									map.put(day, objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
									sumTotal += Integer.parseInt(objArr[9].toString());
								}
							}
							map.put("machineName", objArr[13] != null ? objArr[13].toString() : null);
							map.put("sumTotal", sumTotal);
						}
					}
					
					if(sumTotal > 0) {
						mapList.add(map);
					}
				}
			}
		}else {
			if("time".equals(filter)) {
//				alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", alarmId);
				// 2022-01-18 querydsl 추가
				alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", alarmId);
//				for(Object[] objArr : alarmDetailObjList) {
//					machineIdList.add(Integer.parseInt(objArr[0].toString()));
//				}
				for(Tuple t : alarmDetailTupleList) {
					Object[] objArr = t.toArray();
					machineIdList.add(Integer.parseInt(objArr[0].toString()));
				}
				
				machineIdList = machineIdList.stream().distinct().collect(Collectors.toList());
				for(Integer id : machineIdList) {
					HashMap<String, Object> map = new HashMap<>();
					for(String day : dayList) {
						map.put(day, 0);
					}
					Integer sumTotal = 0;
					map.put("machineId", id);
					
//					for(Object[] objArr : alarmDetailObjList) {
//						if(id == Integer.parseInt(objArr[0].toString())) {
//							map.put("alarmId", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : null);
//							for(String day : dayList) {
//								if((objArr[10].toString()).equals(day) && alarmId == Integer.parseInt(objArr[3].toString())) {
//									map.put(day, objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
//									sumTotal += (objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
//								}
//							}
//							map.put("machineName", objArr[13] != null ? objArr[13].toString() : null);
//							map.put("sumTotal", sumTotal);
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						if(id == Integer.parseInt(objArr[0].toString())) {
							map.put("alarmId", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : null);
							for(String day : dayList) {
								if((objArr[10].toString()).equals(day) && alarmId == Integer.parseInt(objArr[3].toString())) {
									map.put(day, objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
									sumTotal += (objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
								}
							}
							map.put("machineName", objArr[13] != null ? objArr[13].toString() : null);
							map.put("sumTotal", sumTotal);
						}
					}
					
					mapList.add(map);
				}
			}else {
//				alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "cnt", alarmId);
				// 2022-01-18 querydsl 추가
				alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "cnt", alarmId);
//				for(Object[] objArr : alarmDetailObjList) {
//					machineIdList.add(Integer.parseInt(objArr[0].toString()));
//				}
				for(Tuple t : alarmDetailTupleList) {
					Object[] objArr = t.toArray();
					machineIdList.add(Integer.parseInt(objArr[0].toString()));
				}
				
				machineIdList = machineIdList.stream().distinct().collect(Collectors.toList());
				for(Integer id : machineIdList) {
					HashMap<String, Object> map = new HashMap<>();
					for(String day : dayList) {
						map.put(day, 0);
					}
					Integer sumTotal = 0;
					map.put("machineId", id);
					
//					for(Object[] objArr : alarmDetailObjList) {
//						if(id == Integer.parseInt(objArr[0].toString())) {
//							map.put("alarmId", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : null);
//							for(String day : dayList) {
//								if((objArr[10].toString()).equals(day) && alarmId == Integer.parseInt(objArr[3].toString())) {
//									map.put(day, objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
//									sumTotal += Integer.parseInt(objArr[9].toString());
//								}
//							}
//							map.put("machineName", objArr[13] != null ? objArr[13].toString() : null);
//							map.put("sumTotal", sumTotal);
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						if(id == Integer.parseInt(objArr[0].toString())) {
							map.put("alarmId", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : null);
							for(String day : dayList) {
								if((objArr[10].toString()).equals(day) && alarmId == Integer.parseInt(objArr[3].toString())) {
									map.put(day, objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
									sumTotal += Integer.parseInt(objArr[9].toString());
								}
							}
							map.put("machineName", objArr[13] != null ? objArr[13].toString() : null);
							map.put("sumTotal", sumTotal);
						}
					}
					
					mapList.add(map);
				}
			}
		}
		return mapList;
	}
	
	// 날짜별 리스트 뽑기 (startDate, endDate 구간안에서)
	public List<String> getDayList(String startDate, String endDate){
		List<String> dayList = machineRepository.userFindDayListByDbDate(startDate, endDate);
		return dayList;
	}
	
	@Autowired
	AlarmEventStatusRepositorySupport alarmEventStatusRepositorySupport;
	
	// 알람 차트 데이터
	public HashMap<String, Object> getAlarmChartData(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, String filter, String division, Integer alarmTypeId){
		HashMap<String, Object> alarmChartDataMap = new HashMap<>();
		List<HashMap<String ,Object>> alarmChartLevelOne = new ArrayList<>();
		List<HashMap<String ,Object>> alarmChartLevelTwo= new ArrayList<>();
		
		String groupBy = "machineId";
		String orderBy = "total_holding_time";
		
		if(division.equals("machine")) {
			if(filter.equals("time")) {
				// 1단계 차트 데이터 추출
//				List<Object[]> alarmMachineList = alarmEventStatusRepository.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, alarmTypeId, groupBy);
				
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmMachineTupleList = alarmEventStatusRepositorySupport.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, filter, alarmTypeId, groupBy);
//				for(Object[] objArr : alarmMachineList) {
//					HashMap<String, Object> map = new HashMap<>();
//					// 00:00:00로 표기
//					Integer sec = objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : 0;
//					map.put("name", objArr[3] != null ? objArr[3].toString() : "");
//					map.put("y", sec);
//					map.put("level", 1);
//					map.put("drilldown", objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : 0);
//					alarmChartLevelOne.add(map);
//				}
				for(Tuple t : alarmMachineTupleList) {
					Object[] objArr = t.toArray();
					HashMap<String, Object> map = new HashMap<>();
					// 00:00:00로 표기
					Integer sec = objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : 0;
					Long cnt = objArr[5] != null ? Long.parseLong(objArr[5].toString()) : 0;
					map.put("name", objArr[3] != null ? objArr[3].toString() : "");
					map.put("y", sec);
					map.put("level", 1);
					map.put("drilldown", objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : 0);
					map.put("cnt", cnt);
					alarmChartLevelOne.add(map);
				}
				
				// 2단계 차트 데이터 추출
//				List<Object[]> alarmMsgList = alarmEventStatusRepository.userFindAlarmMsgListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, groupBy);
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmMsgTupleList = alarmEventStatusRepositorySupport.userFindAlarmMsgListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, groupBy);
				
				List<Integer> machineIdList = new ArrayList<>();
				List<Integer> alarmIdList = new ArrayList<>();
				
//				for(Object[] objArr : alarmMsgList) {
//					machineIdList.add(Integer.parseInt(objArr[0].toString()));
//					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
//				}
				for(Tuple t : alarmMsgTupleList) {
					Object[] objArr = t.toArray();
					machineIdList.add(Integer.parseInt(objArr[0].toString()));
					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
				}
				
				machineIdList = machineIdList.stream().distinct().collect(Collectors.toList());
				alarmIdList = alarmIdList.stream().distinct().collect(Collectors.toList());
				for(Integer id : machineIdList) {
					HashMap<String, Object> map = new HashMap<>();
					List<HashMap<String, Object>> dataList = new ArrayList<>();
					map.put("name", "시간");
					map.put("level", 2);
					map.put("id", id);
					
//					for(Object[] objArr : alarmMsgList) {
//						if(Integer.parseInt(objArr[0].toString()) == id) {
//							HashMap<String, Object> dataMap = new HashMap<>();
//							// 00:00:00로 표기
//							Integer sec = objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0;
//							dataMap.put("name", objArr[7] != null ? objArr[7].toString() : "");
//							dataMap.put("y", sec);
//							dataMap.put("drilldown", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) + "M" + id : 0);
//							dataList.add(dataMap);
//						}
//					}
					for(Tuple t : alarmMsgTupleList) {
						Object[] objArr = t.toArray();
						if(Integer.parseInt(objArr[0].toString()) == id) {
							HashMap<String, Object> dataMap = new HashMap<>();
							// 00:00:00로 표기
							Integer sec = objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0;
							Long cnt = objArr[9] != null ? Long.parseLong(objArr[9].toString()) : 0;
							dataMap.put("name", objArr[7] != null ? objArr[7].toString() : "");
							dataMap.put("y", sec);
							dataMap.put("drilldown", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) + "M" + id : 0);
							dataMap.put("cnt", cnt);
							dataList.add(dataMap);
						}
					}
					
					// y기준으로 integer 내림차순 정렬
					Collections.sort(dataList, new Comparator<HashMap<String, Object>>() {
						@Override
						public int compare(HashMap<String, Object> o1, HashMap<String, Object> o2) {
							Integer y1 = (Integer) o1.get("y");
							Integer y2 = (Integer) o2.get("y");
							return y2.compareTo(y1);
						}
					});
					
					// y기준으로 string 내림차순 정렬
//					dataList.stream().sorted((o1, o2) -> o1.get("y").toString().compareTo(o2.get("y").toString()) ).collect(Collectors.toList());
					
					map.put("data", dataList);
					alarmChartLevelTwo.add(map);
				}
				
				// 3단계 차트 데이터 추출 (levelTwo에 같이 묶어서 전달)
				List<String> dayList = machineRepository.userFindDayListByDbDate(startDate, endDate);
//				List<Object[]> alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", null);
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", null);
				
				List<String> lvThreeIdList = new ArrayList<>();
				
//				for(Object[] objArr : alarmDetailObjList) {
//					Integer objMachineId = Integer.parseInt(objArr[0].toString());
//					Integer objAlarmId = Integer.parseInt(objArr[3].toString());
//					lvThreeIdList.add(objAlarmId + "M" + objMachineId);
//				}
				for(Tuple t : alarmDetailTupleList) {
					Object[] objArr = t.toArray();
					Integer objMachineId = Integer.parseInt(objArr[0].toString());
					Integer objAlarmId = Integer.parseInt(objArr[3].toString());
					lvThreeIdList.add(objAlarmId + "M" + objMachineId);
				}
				
				lvThreeIdList = lvThreeIdList.stream().distinct().collect(Collectors.toList());
				for(String idMap : lvThreeIdList) {
					HashMap<String, Object> map = new HashMap<>();
					map.put("name", "시간");
					map.put("level", 3);
					map.put("id", idMap);
					List<HashMap<String, Object>> dayMapList = new ArrayList<>();
					for(String day : dayList) {
						HashMap<String, Object> dayMap = new HashMap<>();
						dayMap.put("name", day);
						dayMap.put("y", 0);
						dayMapList.add(dayMap);
					}
					
//					for(Object[] objArr : alarmDetailObjList) {
//						Integer objMachineId = objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0;
//						Integer objAlarmId = objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : 0;
//						if(idMap.equals(objAlarmId+"M"+objMachineId)) {
//							for(HashMap<String, Object> dayMap : dayMapList) {
//								if(dayMap.get("name").equals(objArr[10].toString())) {
//									// 00:00:00로 표기
//									Integer sec = objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0;
//									dayMap.put("y", sec);
//								}
//							}
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						Integer objMachineId = objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0;
						Integer objAlarmId = objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : 0;
						if(idMap.equals(objAlarmId+"M"+objMachineId)) {
							for(HashMap<String, Object> dayMap : dayMapList) {
								if(dayMap.get("name").equals(objArr[10].toString())) {
									// 00:00:00로 표기
									Integer sec = objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0;
									Long cnt = objArr[9] != null ? Long.parseLong(objArr[9].toString()) : 0;
									dayMap.put("y", sec);
									dayMap.put("cnt", cnt);
								}
							}
						}
					}
					
					map.put("data", dayMapList);
					alarmChartLevelTwo.add(map);
				}
				alarmChartDataMap.put("levelOne", alarmChartLevelOne);
				alarmChartDataMap.put("levelTwo", alarmChartLevelTwo);
			}else {
				// 1단계 차트 데이터 추출
				orderBy = "cnt";
//				List<Object[]> alarmMachineList = alarmEventStatusRepository.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, alarmTypeId, groupBy);
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmMachineTupleList = alarmEventStatusRepositorySupport.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, filter, alarmTypeId, groupBy);
//				for(Object[] objArr : alarmMachineList) {
//					HashMap<String, Object> map = new HashMap<>();
//					map.put("name", objArr[3] != null ? objArr[3].toString() : "");
//					if(objArr[4] == null && objArr[5] != null) {
//						map.put("y", 0);
//					}else if(objArr[5] != null) {
//						map.put("y", Integer.parseInt(objArr[5].toString()));
//					}
//					map.put("level", 1);
//					map.put("drilldown", objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : 0);
//					alarmChartLevelOne.add(map);
//				}
				for(Tuple t : alarmMachineTupleList) {
					Object[] objArr = t.toArray();
					HashMap<String, Object> map = new HashMap<>();
					map.put("name", objArr[3] != null ? objArr[3].toString() : "");
					if(objArr[4] == null && objArr[5] != null) {
						map.put("y", 0);
					}else if(objArr[5] != null) {
						map.put("y", Integer.parseInt(objArr[5].toString()));
					}
					map.put("level", 1);
					map.put("drilldown", objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : 0);
					map.put("time", objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : 0);
					alarmChartLevelOne.add(map);
				}
				
				// 2단계 차트 데이터 추출
//				List<Object[]> alarmMsgList = alarmEventStatusRepository.userFindAlarmMsgListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, groupBy);
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmMsgTupleList = alarmEventStatusRepositorySupport.userFindAlarmMsgListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, groupBy);
				
				List<Integer> machineIdList = new ArrayList<>();
				List<Integer> alarmIdList = new ArrayList<>();
				
//				for(Object[] objArr : alarmMsgList) {
//					machineIdList.add(Integer.parseInt(objArr[0].toString()));
//					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
//				}
				for(Tuple t : alarmMsgTupleList) {
					Object[] objArr = t.toArray();
					machineIdList.add(Integer.parseInt(objArr[0].toString()));
					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
				}
				
				machineIdList = machineIdList.stream().distinct().collect(Collectors.toList());
				alarmIdList = alarmIdList.stream().distinct().collect(Collectors.toList());
				for(Integer id : machineIdList) {
					HashMap<String, Object> map = new HashMap<>();
					List<HashMap<String, Object>> dataList = new ArrayList<>();
					map.put("name", "건수");
					map.put("level", 2);
					map.put("id", id);
					
//					for(Object[] objArr : alarmMsgList) {
//						if(Integer.parseInt(objArr[0].toString()) == id) {
//							HashMap<String, Object> dataMap = new HashMap<>();
//							dataMap.put("name", objArr[7] != null ? objArr[7].toString() : "");
//							dataMap.put("y", objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
//							dataMap.put("drilldown", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) + "M" + id : 0);
//							dataList.add(dataMap);
//						}
//					}
					for(Tuple t : alarmMsgTupleList) {
						Object[] objArr = t.toArray();
						if(Integer.parseInt(objArr[0].toString()) == id) {
							HashMap<String, Object> dataMap = new HashMap<>();
							dataMap.put("name", objArr[7] != null ? objArr[7].toString() : "");
							dataMap.put("y", objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
							dataMap.put("drilldown", objArr[3] != null ? Integer.parseInt(objArr[3].toString()) + "M" + id : 0);
							dataMap.put("time", objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
							dataList.add(dataMap);
						}
					}
					
					// y기준으로 integer 내림차순 정렬
					Collections.sort(dataList, new Comparator<HashMap<String, Object>>() {
						@Override
						public int compare(HashMap<String, Object> o1, HashMap<String, Object> o2) {
							Integer y1 = (Integer) o1.get("y");
							Integer y2 = (Integer) o2.get("y");
							return y2.compareTo(y1);
						}
					});
					
					map.put("data", dataList);
					alarmChartLevelTwo.add(map);
				}
				
				// 3단계 차트 데이터 추출 (levelTwo에 같이 묶어서 전달)
				List<String> dayList = machineRepository.userFindDayListByDbDate(startDate, endDate);
//				List<Object[]> alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate);
				// 2022-01-19 querydsl 적용
				List<Tuple> alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate);
				List<String> lvThreeIdList = new ArrayList<>();
				
//				for(Object[] objArr : alarmDetailObjList) {
//					Integer objMachineId = Integer.parseInt(objArr[0].toString());
//					Integer objAlarmId = Integer.parseInt(objArr[3].toString());
//					lvThreeIdList.add(objAlarmId + "M" + objMachineId);
//				}
				for(Tuple t : alarmDetailTupleList) {
					Object[] objArr = t.toArray();
					Integer objMachineId = Integer.parseInt(objArr[0].toString());
					Integer objAlarmId = Integer.parseInt(objArr[3].toString());
					lvThreeIdList.add(objAlarmId + "M" + objMachineId);
				}
				
				lvThreeIdList = lvThreeIdList.stream().distinct().collect(Collectors.toList());
				
				for(String idMap : lvThreeIdList) {
					HashMap<String, Object> map = new HashMap<>();
					map.put("name", "건수");
					map.put("level", 3);
					map.put("id", idMap);
					List<HashMap<String, Object>> dayMapList = new ArrayList<>();
					for(String day : dayList) {
						HashMap<String, Object> dayMap = new HashMap<>();
						dayMap.put("name", day);
						dayMap.put("y", 0);
						dayMapList.add(dayMap);
					}
					
//					for(Object[] objArr : alarmDetailObjList) {
//						Integer objMachineId = objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0;
//						Integer objAlarmId = objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : 0;
//						if(idMap.equals(objAlarmId+"M"+objMachineId)) {
//							for(HashMap<String, Object> dayMap : dayMapList) {
//								if(dayMap.get("name").equals(objArr[10].toString())) {
//									dayMap.put("y", Integer.parseInt(objArr[9].toString()));
//									dayMap.put("time", objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
//								}
//							}
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						Integer objMachineId = objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0;
						Integer objAlarmId = objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : 0;
						if(idMap.equals(objAlarmId+"M"+objMachineId)) {
							for(HashMap<String, Object> dayMap : dayMapList) {
								if(dayMap.get("name").equals(objArr[10].toString())) {
									dayMap.put("y", Integer.parseInt(objArr[9].toString()));
									dayMap.put("time", objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
								}
							}
						}
					}
					
					map.put("data", dayMapList);
					alarmChartLevelTwo.add(map);
				}
				alarmChartDataMap.put("levelOne", alarmChartLevelOne);
				alarmChartDataMap.put("levelTwo", alarmChartLevelTwo);
			}
		}else {
			if(filter.equals("time")) {
				// 1단계 차트 데이터 추출
				groupBy = "alarmId";
//				List<Object[]> alarmMachineList = alarmEventStatusRepository.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, alarmTypeId, groupBy);
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmMachineTupleList = alarmEventStatusRepositorySupport.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, filter, alarmTypeId, groupBy);
//				for(Object[] objArr : alarmMachineList) {
//					HashMap<String, Object> map = new HashMap<>();
//					Integer holdCnt = objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0;
//					if(holdCnt > 0) {
//						Integer sec = objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : 0; // total_holding_time
//						map.put("name", objArr[7] != null ? objArr[7].toString() : ""); // alarm_msg
//						map.put("y", sec);
//						map.put("level", 1);
//						map.put("drilldown", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : 0); // alarm_id
//						alarmChartLevelOne.add(map);
//					}
//				}
				for(Tuple t : alarmMachineTupleList) {
					Object[] objArr = t.toArray();
					HashMap<String, Object> map = new HashMap<>();
					Integer holdCnt = objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0;
					if(holdCnt > 0) {
						Integer sec = objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : 0; // total_holding_time
						map.put("name", objArr[7] != null ? objArr[7].toString() : ""); // alarm_msg
						map.put("y", sec);
						map.put("level", 1);
						map.put("drilldown", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : 0); // alarm_id
						map.put("cnt", holdCnt);
						alarmChartLevelOne.add(map);
					}
				}
				
				// 2단계 차트 데이터 추출
//				List<Object[]> alarmMsgList = alarmEventStatusRepository.userFindAlarmDataLevelTwo(factoryId, areaId, machineId, startDate, endDate, "total_holding_time");
//				List<Object[]> alarmMsgList = alarmEventStatusRepository.userFindAlarmMsgListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, groupBy);
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmMsgTupleList = alarmEventStatusRepositorySupport.userFindAlarmMsgListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, groupBy);
				List<Integer> machineIdList = new ArrayList<>();
				List<Integer> alarmIdList = new ArrayList<>();
				
//				for(Object[] objArr : alarmMsgList) {
//					machineIdList.add(Integer.parseInt(objArr[0].toString()));
//					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
//				}
				for(Tuple t : alarmMsgTupleList) {
					Object[] objArr = t.toArray();
					machineIdList.add(Integer.parseInt(objArr[0].toString()));
					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
				}
				
				machineIdList = machineIdList.stream().distinct().collect(Collectors.toList());
				alarmIdList = alarmIdList.stream().distinct().collect(Collectors.toList());
				
				// 알람유형별 2단계
				for(Integer id : alarmIdList) {
					HashMap<String, Object> map = new HashMap<>();
					List<HashMap<String, Object>> dataList = new ArrayList<>();
					map.put("name", "시간");
					map.put("level", 2);
					map.put("id", id);
					
//					for(Object[] objArr : alarmMsgList) {
//						if(Integer.parseInt(objArr[3].toString()) == id) {
//							HashMap<String, Object> dataMap = new HashMap<>();
//							
//							// 00:00:00로 표기
//							Integer sec = objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0;
//							dataMap.put("name", objArr[11] != null ? objArr[11].toString() : "");
//							dataMap.put("y", sec);
//							dataMap.put("drilldown", objArr[3] != null ? id + "M" + Integer.parseInt(objArr[0].toString()) : 0);
//							dataList.add(dataMap);
//						}
//					}
					for(Tuple t : alarmMsgTupleList) {
						Object[] objArr = t.toArray();
						if(Integer.parseInt(objArr[3].toString()) == id) {
							HashMap<String, Object> dataMap = new HashMap<>();
							
							// 00:00:00로 표기
							Integer sec = objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0;
							Long cnt = objArr[9] != null ? Long.parseLong(objArr[9].toString()) : 0;
							dataMap.put("name", objArr[11] != null ? objArr[11].toString() : "");
							dataMap.put("y", sec);
							dataMap.put("drilldown", objArr[3] != null ? id + "M" + Integer.parseInt(objArr[0].toString()) : 0);
							dataMap.put("cnt", cnt);
							dataList.add(dataMap);
						}
					}
					
					// y기준으로 integer 내림차순 정렬
					Collections.sort(dataList, new Comparator<HashMap<String, Object>>() {
						@Override
						public int compare(HashMap<String, Object> o1, HashMap<String, Object> o2) {
							Integer y1 = (Integer) o1.get("y");
							Integer y2 = (Integer) o2.get("y");
							return y2.compareTo(y1);
						}
					});
					
					map.put("data", dataList);
					alarmChartLevelTwo.add(map);
				}
				
				// 3단계 차트 데이터 추출 (levelTwo에 같이 묶어서 전달)
				List<String> dayList = machineRepository.userFindDayListByDbDate(startDate, endDate);
//				List<Object[]> alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", null);
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", null);
				
				List<String> lvThreeIdList = new ArrayList<>();
				
//				for(Object[] objArr : alarmDetailObjList) {
//					Integer objMachineId = Integer.parseInt(objArr[0].toString());
//					Integer objAlarmId = Integer.parseInt(objArr[3].toString());
//					lvThreeIdList.add(objAlarmId + "M" + objMachineId);
//				}
				for(Tuple t : alarmDetailTupleList) {
					Object[] objArr = t.toArray();
					Integer objMachineId = Integer.parseInt(objArr[0].toString());
					Integer objAlarmId = Integer.parseInt(objArr[3].toString());
					lvThreeIdList.add(objAlarmId + "M" + objMachineId);
				}
				
				lvThreeIdList = lvThreeIdList.stream().distinct().collect(Collectors.toList());
				for(String idMap : lvThreeIdList) {
					HashMap<String, Object> map = new HashMap<>();
					map.put("name", "시간");
					map.put("level", 3);
					map.put("id", idMap);
					List<HashMap<String, Object>> dayMapList = new ArrayList<>();
					for(String day : dayList) {
						HashMap<String, Object> dayMap = new HashMap<>();
						dayMap.put("name", day);
						dayMap.put("y", 0);
						dayMapList.add(dayMap);
					}
					
//					for(Object[] objArr : alarmDetailObjList) {
//						Integer objMachineId = objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0;
//						Integer objAlarmId = objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : 0;
//						if(idMap.equals(objAlarmId+"M"+objMachineId)) {
//							for(HashMap<String, Object> dayMap : dayMapList) {
//								if(dayMap.get("name").equals(objArr[10].toString())) {
//									// 00:00:00로 표기
//									Integer sec = objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0;
//									dayMap.put("y", sec);
//								}
//							}
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						Integer objMachineId = objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0;
						Integer objAlarmId = objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : 0;
						if(idMap.equals(objAlarmId+"M"+objMachineId)) {
							for(HashMap<String, Object> dayMap : dayMapList) {
								if(dayMap.get("name").equals(objArr[10].toString())) {
									// 00:00:00로 표기
									Integer sec = objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0;
									Long cnt = objArr[9] != null ? Long.parseLong(objArr[9].toString()) : 0;
									dayMap.put("y", sec);
									dayMap.put("cnt", cnt);
								}
							}
						}
					}
					
					map.put("data", dayMapList);
					alarmChartLevelTwo.add(map);
				}
				alarmChartDataMap.put("levelOne", alarmChartLevelOne);
				alarmChartDataMap.put("levelTwo", alarmChartLevelTwo);
			}else {
				// 1단계 차트 데이터 추출
				orderBy= "cnt";
				groupBy = "alarmId";
//				List<Object[]> alarmMachineList = alarmEventStatusRepository.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, alarmTypeId, groupBy);
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmMachineTupleList = alarmEventStatusRepositorySupport.userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, filter, alarmTypeId, groupBy);
//				for(Object[] objArr : alarmMachineList) {
//					HashMap<String, Object> map = new HashMap<>();
//					Integer holdCnt = objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0;
//					if(holdCnt > 0) {
//						map.put("name", objArr[7] != null ? objArr[7].toString() : ""); // alarm_msg
//						map.put("y", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0); // cnt
//						map.put("level", 1);
//						map.put("drilldown", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : 0); // alarm_id
//						alarmChartLevelOne.add(map);
//					}
//				}
				for(Tuple t : alarmMachineTupleList) {
					Object[] objArr = t.toArray();
					HashMap<String, Object> map = new HashMap<>();
					Integer holdCnt = objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0;
					Integer time = objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : 0;
					if(holdCnt > 0) {
						map.put("name", objArr[7] != null ? objArr[7].toString() : ""); // alarm_msg
						map.put("y", objArr[5] != null ? Integer.parseInt(objArr[5].toString()) : 0); // cnt
						map.put("level", 1);
						map.put("drilldown", objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : 0); // alarm_id
						map.put("time", time);
						alarmChartLevelOne.add(map);
					}
				}
				
				// 2단계 차트 데이터 추출
//				List<Object[]> alarmMsgList = alarmEventStatusRepository.userFindAlarmDataLevelTwo(factoryId, areaId, machineId, startDate, endDate, "cnt");
//				List<Object[]> alarmMsgList = alarmEventStatusRepository.userFindAlarmMsgListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, groupBy);
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmMsgTupleList = alarmEventStatusRepositorySupport.userFindAlarmMsgListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, orderBy, groupBy);
				
				List<Integer> machineIdList = new ArrayList<>();
				List<Integer> alarmIdList = new ArrayList<>();
				
//				for(Object[] objArr : alarmMsgList) {
//					machineIdList.add(Integer.parseInt(objArr[0].toString()));
//					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
//				}
				for(Tuple t : alarmMsgTupleList) {
					Object[] objArr = t.toArray();
					machineIdList.add(Integer.parseInt(objArr[0].toString()));
					alarmIdList.add(Integer.parseInt(objArr[3].toString()));
				}
				
				machineIdList = machineIdList.stream().distinct().collect(Collectors.toList());
				alarmIdList = alarmIdList.stream().distinct().collect(Collectors.toList());
				
				for(Integer id : alarmIdList) {
					HashMap<String, Object> map = new HashMap<>();
					List<HashMap<String, Object>> dataList = new ArrayList<>();
					map.put("name", "건수");
					map.put("level", 2);
					map.put("id", id);
					
//					for(Object[] objArr : alarmMsgList) {
//						if(Integer.parseInt(objArr[3].toString()) == id) {
//							HashMap<String, Object> dataMap = new HashMap<>();
//							dataMap.put("name", objArr[11] != null ? objArr[11].toString() : "");
//							dataMap.put("y", objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
//							dataMap.put("drilldown", objArr[3] != null ? id + "M" + Integer.parseInt(objArr[0].toString()) : 0);
//							dataList.add(dataMap);
//						}
//					}
					for(Tuple t : alarmMsgTupleList) {
						Object[] objArr = t.toArray();
						if(Integer.parseInt(objArr[3].toString()) == id) {
							HashMap<String, Object> dataMap = new HashMap<>();
							dataMap.put("name", objArr[11] != null ? objArr[11].toString() : "");
							dataMap.put("y", objArr[9] != null ? Integer.parseInt(objArr[9].toString()) : 0);
							dataMap.put("drilldown", objArr[3] != null ? id + "M" + Integer.parseInt(objArr[0].toString()) : 0);
							dataMap.put("time", objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
							dataList.add(dataMap);
						}
					}
					
					// y기준으로 integer 내림차순 정렬
					Collections.sort(dataList, new Comparator<HashMap<String, Object>>() {
						@Override
						public int compare(HashMap<String, Object> o1, HashMap<String, Object> o2) {
							Integer y1 = (Integer) o1.get("y");
							Integer y2 = (Integer) o2.get("y");
							return y2.compareTo(y1);
						}
					});
					
					map.put("data", dataList);
					alarmChartLevelTwo.add(map);
				}
				
				// 3단계 차트 데이터 추출 (levelTwo에 같이 묶어서 전달)
				List<String> dayList = machineRepository.userFindDayListByDbDate(startDate, endDate);
//				List<Object[]> alarmDetailObjList = alarmEventStatusRepository.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", null);
				// 2022-01-18 querydsl 추가
				List<Tuple> alarmDetailTupleList = alarmEventStatusRepositorySupport.userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(factoryId, areaId, machineId, startDate, endDate, "total_holding_time", null);
				
				List<String> lvThreeIdList = new ArrayList<>();
				
//				for(Object[] objArr : alarmDetailObjList) {
//					Integer objMachineId = Integer.parseInt(objArr[0].toString());
//					Integer objAlarmId = Integer.parseInt(objArr[3].toString());
//					lvThreeIdList.add(objAlarmId + "M" + objMachineId);
//				}
				for(Tuple t : alarmDetailTupleList) {
					Object[] objArr = t.toArray();
					Integer objMachineId = Integer.parseInt(objArr[0].toString());
					Integer objAlarmId = Integer.parseInt(objArr[3].toString());
					lvThreeIdList.add(objAlarmId + "M" + objMachineId);
				}
				
				lvThreeIdList = lvThreeIdList.stream().distinct().collect(Collectors.toList());
				for(String idMap : lvThreeIdList) {
					HashMap<String, Object> map = new HashMap<>();
					map.put("name", "건수");
					map.put("level", 3);
					map.put("id", idMap);
					List<HashMap<String, Object>> dayMapList = new ArrayList<>();
					for(String day : dayList) {
						HashMap<String, Object> dayMap = new HashMap<>();
						dayMap.put("name", day);
						dayMap.put("y", 0);
						dayMapList.add(dayMap);
					}
					
//					for(Object[] objArr : alarmDetailObjList) {
//						Integer objMachineId = objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0;
//						Integer objAlarmId = objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : 0;
//						if(idMap.equals(objAlarmId+"M"+objMachineId)) {
//							for(HashMap<String, Object> dayMap : dayMapList) {
//								if(dayMap.get("name").equals(objArr[10].toString())) {
//									dayMap.put("y", Integer.parseInt(objArr[9].toString()));
//								}
//							}
//						}
//					}
					for(Tuple t : alarmDetailTupleList) {
						Object[] objArr = t.toArray();
						Integer objMachineId = objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0;
						Integer objAlarmId = objArr[3] != null ? Integer.parseInt(objArr[3].toString()) : 0;
						if(idMap.equals(objAlarmId+"M"+objMachineId)) {
							for(HashMap<String, Object> dayMap : dayMapList) {
								if(dayMap.get("name").equals(objArr[10].toString())) {
									dayMap.put("y", Integer.parseInt(objArr[9].toString()));
									dayMap.put("time", Integer.parseInt(objArr[8].toString()));
								}
							}
						}
					}
					
					map.put("data", dayMapList);
					alarmChartLevelTwo.add(map);
				}
				alarmChartDataMap.put("levelTwo", alarmChartLevelTwo);
				alarmChartDataMap.put("levelOne", alarmChartLevelOne);
			}
		}
		return alarmChartDataMap;
	}
}
