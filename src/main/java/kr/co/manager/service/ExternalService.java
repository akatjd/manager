package kr.co.manager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;

import kr.co.manager.exception.rdb.AlarmEventStatusRepositorySupport;

@Service
public class ExternalService {
	
	@Autowired
	AlarmEventStatusRepositorySupport alarmEventStatusRepositorySupport;
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<Map<String, Object>> getAlarmHistory(Integer factoryId, String startDate, String endDate) {
		List<Map<String, Object>> alarmHistoryMapList = new ArrayList<>();
		if(factoryId == 3 || factoryId == 4 || factoryId == 7 || factoryId == 8) {
			
			if(startDate.length() == 10) {
				startDate = startDate + "0000";
			}else if(startDate.length() == 12){
				startDate = startDate + "00";
			}else if(startDate.length() == 8) {
				startDate = startDate + "000000";
			}
			
			if(endDate.length() == 10) {
				endDate = endDate + "0000";
			}else if(endDate.length() == 12){
				endDate = endDate + "00";
			}else if(endDate.length() == 8) {
				endDate = endDate + "000000";
			}
			
			List<Tuple> alarmHistoryList = new ArrayList<>();
			alarmHistoryList = alarmEventStatusRepositorySupport.getAlarmHistoryList(factoryId, startDate, endDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date sDate = null;
			Date eDate = null;
			for(Tuple t : alarmHistoryList) {
				Map<String, Object> alarmMap = new HashMap<>();
				Object[] objArr = t.toArray();
				alarmMap.put("factoryId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0);
				alarmMap.put("factoryName", objArr[1] != null ? objArr[1].toString() : "");
				alarmMap.put("lineId", objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : 0);
				alarmMap.put("lineName", objArr[3] != null ? objArr[3].toString() : "");
				alarmMap.put("machineId", objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : 0);
				alarmMap.put("machineName", objArr[5] != null ? objArr[5].toString() : "");
				
				String sDateStr = objArr[6] != null ? objArr[6].toString() : "";
				String eDateStr = objArr[7] != null ? objArr[7].toString() : "";
				try {
					sDate = sdf.parse(sDateStr);
					eDate = sdf.parse(eDateStr);
				}catch(ParseException e){
					e.printStackTrace();
				}
				long diff = eDate.getTime() - sDate.getTime();
				long diffSec = diff / 1000;
				
				alarmMap.put("gapSec", diffSec);
				alarmMap.put("startDate", sDateStr.substring(0, sDateStr.length()-2));
				alarmMap.put("endDate", eDateStr.substring(0, eDateStr.length()-2));
				alarmMap.put("alarmTypeId", objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
				alarmMap.put("alarmType", objArr[9] != null ? objArr[9].toString() : "");
				alarmMap.put("alarmMsg", objArr[10] != null ? objArr[10].toString() : "");
				alarmMap.put("alarmId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : 0);
				alarmMap.put("alarmStatusId", objArr[12] != null ? Integer.parseInt(objArr[12].toString()) : 0);
				alarmHistoryMapList.add(alarmMap);
			}
		}
		return alarmHistoryMapList;
	}
	
	// KMS 2022-03-03 명화공업 알람이력 factoryId 포함
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<Map<String, Object>> getAlarmHistory(String startDate, String endDate) {
		List<Map<String, Object>> alarmHistoryMapList = new ArrayList<>();
			
		if(startDate.length() == 10) {
			startDate = startDate + "0000";
		}else if(startDate.length() == 12){
			startDate = startDate + "00";
		}else if(startDate.length() == 8) {
			startDate = startDate + "000000";
		}
		
		if(endDate.length() == 10) {
			endDate = endDate + "0000";
		}else if(endDate.length() == 12){
			endDate = endDate + "00";
		}else if(endDate.length() == 8) {
			endDate = endDate + "000000";
		}
		
		List<Tuple> alarmHistoryList = new ArrayList<>();
		alarmHistoryList = alarmEventStatusRepositorySupport.getAlarmHistoryList(startDate, endDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sDate = null;
		Date eDate = null;
		
		if(alarmHistoryList.size() > 0) {
			for(Tuple t : alarmHistoryList) {
				
				Map<String, Object> alarmMap = new HashMap<>();
				Object[] objArr = t.toArray();
				alarmMap.put("factoryId", objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : 0);
				alarmMap.put("factoryName", objArr[1] != null ? objArr[1].toString() : "");
				alarmMap.put("lineId", objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : 0);
				alarmMap.put("lineName", objArr[3] != null ? objArr[3].toString() : "");
				alarmMap.put("machineId", objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : 0);
				alarmMap.put("machineName", objArr[5] != null ? objArr[5].toString() : "");
				
				String sDateStr = objArr[6] != null ? objArr[6].toString() : "";
				String eDateStr = objArr[7] != null ? objArr[7].toString() : "";
				
				long diffSec = 0L;
				
				if(sDateStr != "" && eDateStr != "") {
					try {
						sDate = sdf.parse(sDateStr);
						eDate = sdf.parse(eDateStr);
					}catch(ParseException e){
						e.printStackTrace();
					}
					long diff = eDate.getTime() - sDate.getTime();
					diffSec = diff / 1000;
				}
				
				alarmMap.put("gapSec", diffSec);
				alarmMap.put("startDate", eDateStr != "" ? sDateStr.substring(0, sDateStr.length()-2) : "");
				alarmMap.put("endDate", eDateStr != "" ? eDateStr.substring(0, sDateStr.length()-2) : "");
				alarmMap.put("alarmTypeId", objArr[8] != null ? Integer.parseInt(objArr[8].toString()) : 0);
				alarmMap.put("alarmType", objArr[9] != null ? objArr[9].toString() : "");
				alarmMap.put("alarmMsg", objArr[10] != null ? objArr[10].toString() : "");
				alarmMap.put("alarmId", objArr[11] != null ? Integer.parseInt(objArr[11].toString()) : 0);
				alarmMap.put("alarmStatusId", objArr[12] != null ? Integer.parseInt(objArr[12].toString()) : 0);
				if(sDateStr != "" && eDateStr != "") {
					alarmHistoryMapList.add(alarmMap);
				}
			}
		}
		
		return alarmHistoryMapList;
	}
	
}
