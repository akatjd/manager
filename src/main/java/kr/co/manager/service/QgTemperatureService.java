package kr.co.manager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.view.QgTemperatureView;
import kr.co.manager.exception.rdb.MachineTemperatureRepository;

@Service
public class QgTemperatureService {

	private static final Logger logger = LoggerFactory.getLogger(QgTemperatureService.class);

	@Autowired
	MachineTemperatureRepository machineTemperatureRepository;

	// 설비 온도 페이지 그리드에 뿌릴 temperatureList 뽑기
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<QgTemperatureView> getTemperatureList(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate) throws ParseException{

		// 전체 데이터 기준으로 추후 라인 및 머신 검색 조건 넣어서 뽑아줘야 할 수 있음
		List<Object[]> temperatureList = machineTemperatureRepository.userFindTemperatureViewList(factoryId, areaId, machineId, startDate, endDate);
		List<Object[]> sparkLineDataList = machineTemperatureRepository.userFindSparklineDataList(factoryId, areaId, machineId, startDate, endDate);
		List<QgTemperatureView> qgTemperatureViewList = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Object[] objArr : temperatureList) {
			QgTemperatureView qv = new QgTemperatureView();
			String machineIdTemp = objArr[3].toString();
			List<Integer> sparkLineData = new ArrayList<>();
			List<HashMap<String, Object>> sparkLineDataMapList = new ArrayList<>();
			if(Integer.parseInt(objArr[0].toString()) < 0) {
				qv.setMinTemp("0");
			}else {
				qv.setMinTemp(objArr[0].toString());
			}
			qv.setAvgTemp(objArr[1].toString().substring(0, 5));
			qv.setMaxTemp(objArr[2].toString());
			qv.setMachineId(machineIdTemp);
			qv.setMachineName(objArr[4].toString());
			qv.setAreaName(objArr[5].toString());
			for(Object[] objArr2 : sparkLineDataList) {
				HashMap<String, Object> sparkLineDataMap = new HashMap<>();
				if(objArr[3].equals(objArr2[0])) {
					if(Integer.parseInt(objArr2[2].toString()) <= 0) {
//						sparkLineData.add(0);
//						sparkLineDataMap.put("x", simpleDateFormat.format((Date)objArr2[3]));
//						sparkLineDataMap.put("y", 0);
//						sparkLineDataMapList.add(sparkLineDataMap);
					}else {
						sparkLineData.add(Integer.parseInt(objArr2[2].toString()));
						sparkLineDataMap.put("x", simpleDateFormat.format((Date)objArr2[3]));
						sparkLineDataMap.put("y", objArr2[2]);
						sparkLineDataMapList.add(sparkLineDataMap);
					}
				}
			}
			qv.setSparkLineDataMapList(sparkLineDataMapList);
			qv.setSparkLineData(sparkLineData);
			qgTemperatureViewList.add(qv);
		}
		
		return qgTemperatureViewList;
	}

}
