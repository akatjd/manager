package kr.co.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.view.QgToolLifeView;
import kr.co.manager.exception.rdb.MachineRepository;
import kr.co.manager.exception.rdb.MachineToolHistoryRepository;

@Service
public class QgToolLifeService {

	private static final Logger logger = LoggerFactory.getLogger(QgToolLifeService.class);

	@Autowired
	MachineToolHistoryRepository machineToolHistoryRepository;

	@Autowired
	MachineRepository machineRepository;
	
	// 공구 수명 리스트
	public List<QgToolLifeView> getQgToolLifeViewList(Integer factoryId, Integer areaId, Integer machineId, Integer minSection, Integer maxSection, String startDate,
			String endDate) {
		List<QgToolLifeView> qgToolLifeViewList = new ArrayList<>();
		List<Integer> machineIdList = machineRepository.userFindMachineIdListByFactoryIdAndAreaIdAndMachineId(factoryId, areaId, machineId);
		for(Integer id : machineIdList) {
			Integer machineIdTemp = id;
			List<Object[]> qgToolLifeViewObjList = machineToolHistoryRepository.userFindToolLifeListByFactoryIdAndAreaIdAndMachineIdAndStartDateAndEndDate(factoryId, areaId, machineIdTemp, startDate, endDate);
			List<String> toolNameList = machineToolHistoryRepository.userFindToolNameListByMachineIdAndStartDateAndEndDate(machineIdTemp, startDate, endDate);
			for(int i = 0; i<toolNameList.size(); i++) {
				QgToolLifeView qlView = new QgToolLifeView();
				String[] toolNameArr = toolNameList.get(i).split(",");
				String toolName = toolNameArr[0]; // ex) T01, T02, T03
				Integer toolId = Integer.parseInt(toolNameArr[1]);
				Integer nowPresetCnt = 0;
				Integer minUseCntTotal = 0;
				Integer avgUseCntTotal = 0;
				Integer maxUseCntTotal = 0;
				Integer minSectionTotal = 0;
				Integer maxSectionTotal = 0;
				Integer avgSectionTotal = 0;
				Integer sectionTotal = 0;
				int cnt = 0;
				Integer avgLife = 0;
				Integer minLife = 0;
				Integer maxLife = 0;
				Integer objFactoryId = 0;
				String objFactoryName = null;
				Integer objAreaId = 0;
				String objAreaName = null;
				Integer objClientId = 0;
				String objClientName = null;
				Integer objMachineId = 0;
				String objMachineName = null;
				String objToolName = null;
				Integer objCurrentProductId = 0;
				Integer objNowPresetCnt = 0;
				Integer objToolId = 0;
				Integer[] arrDistributionCnt = new Integer[10];
				Integer[] arrDistributionSection = new Integer[10];
				String[] arrSectionStr = new String[10];
				for(int temp=0; temp<arrDistributionCnt.length; temp++) {
					arrDistributionCnt[temp] = 0;
					arrDistributionSection[temp] = 0;
				}
				List<Object> presetArr = new ArrayList<>();
				List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
				Date changedDate = null;
				Integer sumUseCnt = 0;
				
				for(Object[] objArr : qgToolLifeViewObjList) {
					
					objFactoryId = objArr[0] != null ? Integer.parseInt(objArr[0].toString()) : null;
					objFactoryName = objArr[1] != null ? objArr[1].toString() : null;
					objAreaId = objArr[2] != null ? Integer.parseInt(objArr[2].toString()) : null;
					objAreaName = objArr[3] != null ? objArr[3].toString() : null;
					objClientId = objArr[4] != null ? Integer.parseInt(objArr[4].toString()) : null;
					objClientName = objArr[5] != null ? objArr[5].toString() : null;
					objMachineId = objArr[6] != null ? Integer.parseInt(objArr[6].toString()) : null;
					objMachineName = objArr[7] != null ? objArr[7].toString() : null;
					objToolName = objArr[8] != null ? objArr[8].toString() : null;
					objCurrentProductId = objArr[12] != null ? Integer.parseInt(objArr[12].toString()) : null;
					objNowPresetCnt = objArr[13] != null ? Integer.parseInt(objArr[13].toString()) : null;
					changedDate = objArr[11] != null ? (Date) objArr[11] : null;
					objToolId = objArr[14] != null ? Integer.parseInt(objArr[14].toString()) : null;
					
					if(objToolId.equals(toolId)) {
						if(cnt == 0) nowPresetCnt = objNowPresetCnt;
						
						Integer minUseStandard = (int) (nowPresetCnt * (minSection * 0.01)); // 공구 수명 페이지에서 최소구간(minSection) 설정 
						Integer maxUseStandard = (int) (nowPresetCnt * (100 - maxSection) * 0.01); // 공구 수명 페이지에서 최대구간(maxSection) 설정
						Integer useCnt = Integer.parseInt(objArr[9].toString());
						
						// arrDistributionSection 값 구해서 넣기
						for(int temp=0; temp<arrDistributionSection.length; temp++) {
							arrDistributionSection[temp] = (int) (nowPresetCnt * (0.1 * (temp+1)));
						}
						
						if(useCnt > 0 && useCnt <= arrDistributionSection[0]) {
							arrDistributionCnt[0]++;
						}else if(useCnt > arrDistributionSection[0] && useCnt <= arrDistributionSection[1]) {
							arrDistributionCnt[1]++;
						}else if(useCnt > arrDistributionSection[1] && useCnt <= arrDistributionSection[2]) {
							arrDistributionCnt[2]++;
						}else if(useCnt > arrDistributionSection[2] && useCnt <= arrDistributionSection[3]) {
							arrDistributionCnt[3]++;
						}else if(useCnt > arrDistributionSection[3] && useCnt <= arrDistributionSection[4]) {
							arrDistributionCnt[4]++;
						}else if(useCnt > arrDistributionSection[4] && useCnt <= arrDistributionSection[5]) {
							arrDistributionCnt[5]++;
						}else if(useCnt > arrDistributionSection[5] && useCnt <= arrDistributionSection[6]) {
							arrDistributionCnt[6]++;
						}else if(useCnt > arrDistributionSection[6] && useCnt <= arrDistributionSection[7]) {
							arrDistributionCnt[7]++;
						}else if(useCnt > arrDistributionSection[7] && useCnt <= arrDistributionSection[8]) {
							arrDistributionCnt[8]++;
						}else{
							arrDistributionCnt[9]++;
						}
						
						arrSectionStr[0] = "0~" + Integer.toString(arrDistributionSection[0]).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
						arrSectionStr[1] = Integer.toString(arrDistributionSection[0]+1).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString(arrDistributionSection[1]).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
						arrSectionStr[2] = Integer.toString(arrDistributionSection[1]+1).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString(arrDistributionSection[2]).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
						arrSectionStr[3] = Integer.toString(arrDistributionSection[2]+1).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString(arrDistributionSection[3]).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
						arrSectionStr[4] = Integer.toString(arrDistributionSection[3]+1).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString(arrDistributionSection[4]).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
						arrSectionStr[5] = Integer.toString(arrDistributionSection[4]+1).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString(arrDistributionSection[5]).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
						arrSectionStr[6] = Integer.toString(arrDistributionSection[5]+1).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString(arrDistributionSection[6]).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
						arrSectionStr[7] = Integer.toString(arrDistributionSection[6]+1).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString(arrDistributionSection[7]).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
						arrSectionStr[8] = Integer.toString(arrDistributionSection[7]+1).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString(arrDistributionSection[8]).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
						arrSectionStr[9] = Integer.toString(arrDistributionSection[8]+1).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~";
						
						if(useCnt < minUseStandard){
							minUseCntTotal++;
							minSectionTotal += useCnt;
						}else if(useCnt > maxUseStandard) {
							maxUseCntTotal++;
							maxSectionTotal += useCnt;
						}else {
							avgUseCntTotal++;
							avgSectionTotal += useCnt;
						}
						sectionTotal += useCnt;
						
						List<Object> hcDataArr = new ArrayList<>();
						hcDataArr.add(changedDate.getTime()/1000);
						hcDataArr.add(useCnt);
						presetArr.add(hcDataArr);
						sumUseCnt += useCnt;
						cnt++;
					}
				}
				
				// sparkline data
				for(int j=0; j<arrSectionStr.length; j++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					if(arrSectionStr[j] == null) {
						arrSectionStr[j] = "-";
					}
					map.put("name", arrSectionStr[j]);
					map.put("y", arrDistributionCnt[j]);
					mapList.add(map);
				}
				
				if((minSection + maxSection) == 100) {
					avgLife = 0;
				}else {
					if(cnt == 0) {
						avgLife = 0;
						
					}else {
						avgLife = sectionTotal / cnt;
					}
				}
					
				if(minUseCntTotal == 0) {
					minLife = 0;
				}else {
					minLife = minSectionTotal / minUseCntTotal;
				}
				if(maxUseCntTotal == 0) {
					maxLife = 0;
				}else {
					maxLife = maxSectionTotal / maxUseCntTotal;
				}
				
				qlView.setMachineId(objMachineId);
				qlView.setMachineName(objMachineName);
				qlView.setToolName(toolName);
				qlView.setMinLife(minLife);
				qlView.setAvgLife(avgLife);
				if(nowPresetCnt == 0) {
					avgUseCntTotal = maxUseCntTotal;
					qlView.setMaxLife(0);
					qlView.setMaxSectionCnt(0);
				}else {
					qlView.setMaxLife(maxLife);
					qlView.setMaxSectionCnt(maxUseCntTotal);
				}
				qlView.setMinSectionCnt(minUseCntTotal);
				qlView.setAvgSectionCnt(avgUseCntTotal);
				qlView.setAreaName(objAreaName);
				qlView.setNowPresetCnt(nowPresetCnt);
				qlView.setFactoryId(objFactoryId);
				qlView.setFactoryName(objFactoryName);
				qlView.setArrDistributionCnt(arrDistributionCnt);
				qlView.setArrDistributionSection(arrDistributionSection);
				qlView.setPresetArr(presetArr);
				qlView.setDistributionMapList(mapList);
				qlView.setGapStandardAvg(nowPresetCnt - avgLife);
				qlView.setSumUseCnt(sumUseCnt);
				if(objAreaName != null) {
					qgToolLifeViewList.add(qlView);
				}
			}
		}
		return qgToolLifeViewList;
	}
}
