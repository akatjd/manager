package kr.co.manager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.view.QgToolChangeTimeView;
import kr.co.manager.exception.rdb.MachineRepository;
import kr.co.manager.exception.rdb.MachineToolHistoryRepository;

@Service
public class QgToolChangeTimeService {
	
	private final static Logger logger = LoggerFactory.getLogger(QgToolChangeTimeService.class);
	
	@Autowired
	MachineToolHistoryRepository machineToolHistoryRepository;
	
	@Autowired
	MachineRepository machineRepository;
	
	// 공구 교환 시간 리스트
	public List<QgToolChangeTimeView> getToolChangeTimeViewList(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, Integer standardMaxCt) {
		List<QgToolChangeTimeView> toolChangeTimeViewList = new ArrayList<>();
		List<Object[]> toolChangeTimeList = machineToolHistoryRepository.userFindToolChangeTimeListByFactoryIdAndAreaIdAndMachineIdAndStartDateAndEndDate(factoryId, areaId, machineId, startDate, endDate);
		List<Object[]> machineObjList = machineRepository.userFindMachineObjListByFactoryIdAndAreaIdAndMachineId(factoryId, areaId, machineId);
		for(Object[] machineObjArr : machineObjList) {
			QgToolChangeTimeView qgToolChangeTimeView = new QgToolChangeTimeView();
			Integer tempMinCt = 0;
			Integer tempAvgCt = 0;
			Integer tempMaxCt = 0;
			Integer tempTotalCt = 0;
			Integer cnt = 0;
			Integer changeToolCnt = 0;
			Integer[] arrSection = new Integer[10];
			String[] arrSectionStr = new String[10];
			for(int i=0; i<arrSection.length; i++) {
				arrSection[i] = 0;
			}
			List<Object> changedTimeArr = new ArrayList<>();
			List<List<Integer>> scatterResult = new ArrayList<>();
			List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
			for(Object[] objArr : toolChangeTimeList) { // objArr[6] : mth.changed_time
				if(Integer.parseInt(machineObjArr[0].toString()) == Integer.parseInt(objArr[4].toString())) { // machineObjArr[0] : machineId
					Integer changedTime = Integer.parseInt(objArr[6].toString());
					if(cnt == 0) {
						tempMinCt = changedTime;
						tempMaxCt = changedTime;
					}else {
						if(tempMinCt > changedTime) {
							tempMinCt = changedTime;
						}
						if(tempMaxCt < changedTime) {
							tempMaxCt = changedTime;
						}
					}
					
					tempTotalCt += Integer.parseInt(objArr[6].toString());
					cnt++;
				}
			}
			double gapValue = 0;
			if(standardMaxCt < tempMinCt) {
				gapValue = standardMaxCt;
			}else {
				gapValue = standardMaxCt - tempMinCt;
			}
			for(Object[] objArr : toolChangeTimeList) {
				if(Integer.parseInt(machineObjArr[0].toString()) == Integer.parseInt(objArr[4].toString())) { // machineObjArr[0] : machineId
					Integer changedTime = Integer.parseInt(objArr[6].toString());
					if(gapValue == standardMaxCt) {
						if(changedTime >= 0 && changedTime < (gapValue/9)*1) {
							arrSection[0]++;
						}else if(changedTime >= ((gapValue/9)*1) && changedTime < ((gapValue/9)*2)) {
							arrSection[1]++;
						}else if(changedTime >= ((gapValue/9)*2) && changedTime < ((gapValue/9)*3)) {
							arrSection[2]++;
						}else if(changedTime >= ((gapValue/9)*3) && changedTime < ((gapValue/9)*4)) {
							arrSection[3]++;
						}else if(changedTime >= ((gapValue/9)*4) && changedTime < ((gapValue/9)*5)) {
							arrSection[4]++;
						}else if(changedTime >= ((gapValue/9)*5) && changedTime < ((gapValue/9)*6)) {
							arrSection[5]++;
						}else if(changedTime >= ((gapValue/9)*6) && changedTime < ((gapValue/9)*7)) {
							arrSection[6]++;
						}else if(changedTime >= ((gapValue/9)*7) && changedTime < ((gapValue/9)*8)) {
							arrSection[7]++;
						}else if(changedTime >= ((gapValue/9)*8) && changedTime < gapValue) {
							arrSection[8]++;
						}else if(changedTime >= gapValue) {
							arrSection[9]++;
						}
						arrSectionStr[0] = "0~" + (Integer.toString((int)((gapValue/9)*1))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
						arrSectionStr[1] = Integer.toString((int)((gapValue/9)*1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*2)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
						arrSectionStr[2] = Integer.toString((int)((gapValue/9)*2)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*3)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[3] = Integer.toString((int)((gapValue/9)*3)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*4)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[4] = Integer.toString((int)((gapValue/9)*4)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*5)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[5] = Integer.toString((int)((gapValue/9)*5)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*6)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[6] = Integer.toString((int)((gapValue/9)*6)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*7)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[7] = Integer.toString((int)((gapValue/9)*7)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*8)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[8] = Integer.toString((int)((gapValue/9)*8)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*9)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[9] = Integer.toString((int)((gapValue/9)*9)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + "(s)";
					}else {
						if(changedTime >= Math.floor(tempMinCt) && changedTime < (tempMinCt + ((gapValue/9)*1))) {
							arrSection[0]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*1)) && changedTime < (tempMinCt + ((gapValue/9)*2))) {
							arrSection[1]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*2)) && changedTime < (tempMinCt + ((gapValue/9)*3))) {
							arrSection[2]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*3)) && changedTime < (tempMinCt + ((gapValue/9)*4))) {
							arrSection[3]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*4)) && changedTime < (tempMinCt + ((gapValue/9)*5))) {
							arrSection[4]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*5)) && changedTime < (tempMinCt + ((gapValue/9)*6))) {
							arrSection[5]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*6)) && changedTime < (tempMinCt + ((gapValue/9)*7))) {
							arrSection[6]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*7)) && changedTime < (tempMinCt + ((gapValue/9)*8))) {
							arrSection[7]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*8)) && changedTime < (tempMinCt + gapValue)) {
							arrSection[8]++;
						}else if(changedTime >= (tempMinCt + gapValue)) {
							arrSection[9]++;
						}
						arrSectionStr[0] = (Integer.toString((int)tempMinCt)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + (Integer.toString((int)(tempMinCt + ((gapValue/9)*1)))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
						arrSectionStr[1] = Integer.toString((int)(tempMinCt + ((gapValue/9)*1))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*2))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
						arrSectionStr[2] = Integer.toString((int)(tempMinCt + ((gapValue/9)*2))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*3))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[3] = Integer.toString((int)(tempMinCt + ((gapValue/9)*3))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*4))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[4] = Integer.toString((int)(tempMinCt + ((gapValue/9)*4))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*5))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[5] = Integer.toString((int)(tempMinCt + ((gapValue/9)*5))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*6))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[6] = Integer.toString((int)(tempMinCt + ((gapValue/9)*6))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*7))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[7] = Integer.toString((int)(tempMinCt + ((gapValue/9)*7))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*8))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[8] = Integer.toString((int)(tempMinCt + ((gapValue/9)*8))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*9))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[9] = Integer.toString((int)(tempMinCt + ((gapValue/9)*9))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + "(s)";
					}
					
//					Integer gapMaxMin = tempMaxCt - tempMinCt;
//					if(changedTime > 0 && changedTime < (gapMaxMin*0.1)) {
//						arrSection[0]++;
//					}else if(changedTime >= (gapMaxMin*0.1) && changedTime < (gapMaxMin*0.2)) {
//						arrSection[1]++;
//					}else if(changedTime >= (gapMaxMin*0.2) && changedTime < (gapMaxMin*0.3)) {
//						arrSection[2]++;
//					}else if(changedTime >= (gapMaxMin*0.3) && changedTime < (gapMaxMin*0.4)) {
//						arrSection[3]++;
//					}else if(changedTime >= (gapMaxMin*0.4) && changedTime < (gapMaxMin*0.5)) {
//						arrSection[4]++;
//					}else if(changedTime >= (gapMaxMin*0.5) && changedTime < (gapMaxMin*0.6)) {
//						arrSection[5]++;
//					}else if(changedTime >= (gapMaxMin*0.6) && changedTime < (gapMaxMin*0.7)) {
//						arrSection[6]++;
//					}else if(changedTime >= (gapMaxMin*0.7) && changedTime < (gapMaxMin*0.8)) {
//						arrSection[7]++;
//					}else if(changedTime >= (gapMaxMin*0.8) && changedTime < (gapMaxMin*0.9)) {
//						arrSection[8]++;
//					}else if(changedTime >= (gapMaxMin*0.9)) {
//						arrSection[9]++;
//					}
//					
//					arrSectionStr[0] = "0~" + Integer.toString((int)(Math.floor(gapMaxMin*0.1))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[1] = Integer.toString((int)(Math.floor(gapMaxMin*0.1)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.2)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[2] = Integer.toString((int)(Math.floor(gapMaxMin*0.2)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.3)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[3] = Integer.toString((int)(Math.floor(gapMaxMin*0.3)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.4)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[4] = Integer.toString((int)(Math.floor(gapMaxMin*0.4)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.5)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[5] = Integer.toString((int)(Math.floor(gapMaxMin*0.5)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.6)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[6] = Integer.toString((int)(Math.floor(gapMaxMin*0.6)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.7)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[7] = Integer.toString((int)(Math.floor(gapMaxMin*0.7)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.8)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[8] = Integer.toString((int)(Math.floor(gapMaxMin*0.8)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.9)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[9] = Integer.toString((int)(Math.floor(gapMaxMin*0.9)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + " (s)";
					
					Date temp = (Date) objArr[7];
					List<Object> hcDataArr = new ArrayList<>();
					List<Integer> scatterList = new ArrayList<>();
					scatterList.add((int)(temp.getTime()/1000));
					scatterList.add(changedTime);
					scatterResult.add(scatterList);
					hcDataArr.add(temp.getTime()/1000);
					hcDataArr.add(changedTime);
					changedTimeArr.add(hcDataArr);
					changeToolCnt++;
				}
			}
			
			// 2차원 배열 정렬을 위해 (Tool이 합쳐지다보니 툴이 바뀔때 교체 시간이 뒤섞여있음)
			int[][] arr = new int[scatterResult.size()][2];
			for(int i=0; i<scatterResult.size(); i++) {
				arr[i][0] = scatterResult.get(i).get(0);
				arr[i][1] = scatterResult.get(i).get(1);
			}
			Arrays.sort(arr, (o1, o2) -> {
				if(o1[0] == o2[0]) {
					return Integer.compare(o1[1], o2[1]);
				}else {
					return Integer.compare(o1[0], o2[0]);
				}
			});
			List<int[]> arrList = Arrays.asList(arr);
			if(cnt != 0) {
				tempAvgCt = tempTotalCt / cnt;
			}
			for(int i=0; i<arrSection.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				if(arrSectionStr[i] == null) {
					arrSectionStr[i] = "-";
				}
				map.put("name", arrSectionStr[i]);
				map.put("y", arrSection[i]);
				mapList.add(map);
			}
			qgToolChangeTimeView.setDistributionMapList(mapList);
			qgToolChangeTimeView.setFactoryId(Integer.parseInt(machineObjArr[2].toString())); // machineObjArr[2] : f.factory_id
			qgToolChangeTimeView.setFactoryName(machineObjArr[3].toString()); // machineObjArr[3] : f.factory_name
			qgToolChangeTimeView.setAreaId(Integer.parseInt(machineObjArr[4].toString())); // machineObjArr[4] : m.area_id
			qgToolChangeTimeView.setAreaName(machineObjArr[5].toString()); // machineObjArr[5] : a.area_name
			qgToolChangeTimeView.setMachineId(Integer.parseInt(machineObjArr[0].toString())); // machineObjArr[0] : mth.machine_id
			qgToolChangeTimeView.setMachineName(machineObjArr[1].toString()); // machineObjArr[1] : m.machine_name
			qgToolChangeTimeView.setMinCycleTime(tempMinCt);
			qgToolChangeTimeView.setAvgCycleTime(tempAvgCt);
			qgToolChangeTimeView.setMaxCycleTime(tempMaxCt);
			qgToolChangeTimeView.setCtDistribution(arrSection);
			qgToolChangeTimeView.setChangedTimeArr(changedTimeArr);
			qgToolChangeTimeView.setChangedTimeArrList(arrList);
			qgToolChangeTimeView.setChangeToolCnt(changeToolCnt);
			toolChangeTimeViewList.add(qgToolChangeTimeView);
		}
		return toolChangeTimeViewList;
	}
	
	// 공구 교환 시간 detail 리스트
	public List<QgToolChangeTimeView> getToolChangeTimeDetailViewList(Integer machineId, String startDate, String endDate, Integer standardMaxCt) {
		List<QgToolChangeTimeView> toolChangeTimeDetailViewList = new ArrayList<>();
		List<Object[]> toolChangeTimeDetailList = machineToolHistoryRepository.userFindToolChangeTimeDetailListByMachineIdAndStartDateAndEndDate(machineId, startDate, endDate);
		List<String> toolNameList = new ArrayList<>();
		List<String> streamToolNameList = new ArrayList<>();
		for(Object[] oneObj : toolChangeTimeDetailList) {
			String toolName = oneObj[1].toString();
			toolNameList.add(toolName);
		}
		// stream으로 중복값 제거
		streamToolNameList = toolNameList.stream().distinct().collect(Collectors.toList());
		for(String streamToolName : streamToolNameList) {
			QgToolChangeTimeView qgToolChangeTimeDetailView = new QgToolChangeTimeView();
			Integer machineToolId = 0;
			Integer tempMinCt = 0;
			Integer tempAvgCt = 0;
			Integer tempMaxCt = 0;
			Integer tempTotalCt = 0;
			Integer cnt = 0;
			Integer changeToolCnt = 0;
			Integer[] arrSection = new Integer[10];
			for(int i=0; i<arrSection.length; i++) {
				arrSection[i] = 0;
			}
			String[] arrSectionStr = new String[10];
			List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
			for(Object[] objArr : toolChangeTimeDetailList) {
				if(streamToolName.equals(objArr[1].toString())) {
					Integer changedTime = Integer.parseInt(objArr[2].toString());
					if(cnt == 0) {
						machineToolId = Integer.parseInt(objArr[0].toString());
						tempMinCt = changedTime;
						tempMaxCt = changedTime;
					}else {
						if(tempMinCt > changedTime) {
							tempMinCt = changedTime;
						}
						if(tempMaxCt < changedTime) {
							tempMaxCt = changedTime;
						}
					}
					
					tempTotalCt += Integer.parseInt(objArr[2].toString());
					cnt++;
				}
			}
			double gapValue = 0;
			if(standardMaxCt < tempMinCt) {
				gapValue = standardMaxCt;
			}else {
				gapValue = standardMaxCt - tempMinCt;
			}
			for(Object[] objArr : toolChangeTimeDetailList) {
				if(streamToolName.equals(objArr[1].toString())) {
					Integer changedTime = Integer.parseInt(objArr[2].toString());
					if(gapValue == standardMaxCt) {
						if(changedTime >= 0 && changedTime < (gapValue/9)*1) {
							arrSection[0]++;
						}else if(changedTime >= ((gapValue/9)*1) && changedTime < ((gapValue/9)*2)) {
							arrSection[1]++;
						}else if(changedTime >= ((gapValue/9)*2) && changedTime < ((gapValue/9)*3)) {
							arrSection[2]++;
						}else if(changedTime >= ((gapValue/9)*3) && changedTime < ((gapValue/9)*4)) {
							arrSection[3]++;
						}else if(changedTime >= ((gapValue/9)*4) && changedTime < ((gapValue/9)*5)) {
							arrSection[4]++;
						}else if(changedTime >= ((gapValue/9)*5) && changedTime < ((gapValue/9)*6)) {
							arrSection[5]++;
						}else if(changedTime >= ((gapValue/9)*6) && changedTime < ((gapValue/9)*7)) {
							arrSection[6]++;
						}else if(changedTime >= ((gapValue/9)*7) && changedTime < ((gapValue/9)*8)) {
							arrSection[7]++;
						}else if(changedTime >= ((gapValue/9)*8) && changedTime < gapValue) {
							arrSection[8]++;
						}else if(changedTime >= gapValue) {
							arrSection[9]++;
						}
						arrSectionStr[0] = "0~" + (Integer.toString((int)((gapValue/9)*1))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
						arrSectionStr[1] = Integer.toString((int)((gapValue/9)*1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*2)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
						arrSectionStr[2] = Integer.toString((int)((gapValue/9)*2)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*3)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[3] = Integer.toString((int)((gapValue/9)*3)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*4)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[4] = Integer.toString((int)((gapValue/9)*4)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*5)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[5] = Integer.toString((int)((gapValue/9)*5)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*6)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[6] = Integer.toString((int)((gapValue/9)*6)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*7)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[7] = Integer.toString((int)((gapValue/9)*7)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*8)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[8] = Integer.toString((int)((gapValue/9)*8)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)((gapValue/9)*9)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[9] = Integer.toString((int)((gapValue/9)*9)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + "(s)";
					}else {
						if(changedTime >= Math.floor(tempMinCt) && changedTime < (tempMinCt + ((gapValue/9)*1))) {
							arrSection[0]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*1)) && changedTime < (tempMinCt + ((gapValue/9)*2))) {
							arrSection[1]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*2)) && changedTime < (tempMinCt + ((gapValue/9)*3))) {
							arrSection[2]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*3)) && changedTime < (tempMinCt + ((gapValue/9)*4))) {
							arrSection[3]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*4)) && changedTime < (tempMinCt + ((gapValue/9)*5))) {
							arrSection[4]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*5)) && changedTime < (tempMinCt + ((gapValue/9)*6))) {
							arrSection[5]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*6)) && changedTime < (tempMinCt + ((gapValue/9)*7))) {
							arrSection[6]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*7)) && changedTime < (tempMinCt + ((gapValue/9)*8))) {
							arrSection[7]++;
						}else if(changedTime >= (tempMinCt + ((gapValue/9)*8)) && changedTime < (tempMinCt + gapValue)) {
							arrSection[8]++;
						}else if(changedTime >= (tempMinCt + gapValue)) {
							arrSection[9]++;
						}
						arrSectionStr[0] = (Integer.toString((int)tempMinCt)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + (Integer.toString((int)(tempMinCt + ((gapValue/9)*1)))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
						arrSectionStr[1] = Integer.toString((int)(tempMinCt + ((gapValue/9)*1))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*2))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
						arrSectionStr[2] = Integer.toString((int)(tempMinCt + ((gapValue/9)*2))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*3))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[3] = Integer.toString((int)(tempMinCt + ((gapValue/9)*3))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*4))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[4] = Integer.toString((int)(tempMinCt + ((gapValue/9)*4))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*5))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[5] = Integer.toString((int)(tempMinCt + ((gapValue/9)*5))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*6))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[6] = Integer.toString((int)(tempMinCt + ((gapValue/9)*6))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*7))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[7] = Integer.toString((int)(tempMinCt + ((gapValue/9)*7))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*8))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[8] = Integer.toString((int)(tempMinCt + ((gapValue/9)*8))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(tempMinCt + ((gapValue/9)*9))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
						arrSectionStr[9] = Integer.toString((int)(tempMinCt + ((gapValue/9)*9))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + "(s)";
					}
					
//					Integer gapMaxMin = tempMaxCt - tempMinCt;
//					if(changedTime > 0 && changedTime < (gapMaxMin*0.1)) {
//						arrSection[0]++;
//					}else if(changedTime >= (gapMaxMin*0.1) && changedTime < (gapMaxMin*0.2)) {
//						arrSection[1]++;
//					}else if(changedTime >= (gapMaxMin*0.2) && changedTime < (gapMaxMin*0.3)) {
//						arrSection[2]++;
//					}else if(changedTime >= (gapMaxMin*0.3) && changedTime < (gapMaxMin*0.4)) {
//						arrSection[3]++;
//					}else if(changedTime >= (gapMaxMin*0.4) && changedTime < (gapMaxMin*0.5)) {
//						arrSection[4]++;
//					}else if(changedTime >= (gapMaxMin*0.5) && changedTime < (gapMaxMin*0.6)) {
//						arrSection[5]++;
//					}else if(changedTime >= (gapMaxMin*0.6) && changedTime < (gapMaxMin*0.7)) {
//						arrSection[6]++;
//					}else if(changedTime >= (gapMaxMin*0.7) && changedTime < (gapMaxMin*0.8)) {
//						arrSection[7]++;
//					}else if(changedTime >= (gapMaxMin*0.8) && changedTime < (gapMaxMin*0.9)) {
//						arrSection[8]++;
//					}else if(changedTime >= (gapMaxMin*0.9)) {
//						arrSection[9]++;
//					}
//					
//					arrSectionStr[0] = "0~" + Integer.toString((int)(Math.floor(gapMaxMin*0.1))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[1] = Integer.toString((int)(Math.floor(gapMaxMin*0.1)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.2)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[2] = Integer.toString((int)(Math.floor(gapMaxMin*0.2)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.3)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[3] = Integer.toString((int)(Math.floor(gapMaxMin*0.3)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.4)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[4] = Integer.toString((int)(Math.floor(gapMaxMin*0.4)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.5)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[5] = Integer.toString((int)(Math.floor(gapMaxMin*0.5)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.6)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[6] = Integer.toString((int)(Math.floor(gapMaxMin*0.6)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.7)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[7] = Integer.toString((int)(Math.floor(gapMaxMin*0.7)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.8)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[8] = Integer.toString((int)(Math.floor(gapMaxMin*0.8)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)Math.floor(gapMaxMin*0.9)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[9] = Integer.toString((int)(Math.floor(gapMaxMin*0.9)+1)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + " (s)";
					
					changeToolCnt++;
				}
			}
			
			for(int i=0; i<arrSection.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				if(arrSectionStr[i] == null) {
					arrSectionStr[i] = "-";
				}
				map.put("name", arrSectionStr[i]);
				map.put("y", arrSection[i]);
				mapList.add(map);
			}
			
			if(cnt != 0) {
				tempAvgCt = tempTotalCt / cnt;
			}
			qgToolChangeTimeDetailView.setMachineToolId(machineToolId);
			qgToolChangeTimeDetailView.setToolName(streamToolName);
			qgToolChangeTimeDetailView.setMinCycleTimeDetail(tempMinCt);
			qgToolChangeTimeDetailView.setAvgCycleTimeDetail(tempAvgCt);
			qgToolChangeTimeDetailView.setMaxCycleTimeDetail(tempMaxCt);
			qgToolChangeTimeDetailView.setCtDistributionDetail(arrSection);
			qgToolChangeTimeDetailView.setDistributionMapList(mapList);
			qgToolChangeTimeDetailView.setChangeToolCnt(changeToolCnt);
			toolChangeTimeDetailViewList.add(qgToolChangeTimeDetailView);
		}
		return toolChangeTimeDetailViewList;
	}
}
