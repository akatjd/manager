package kr.co.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.view.QgToolChangeCntView;
import kr.co.manager.exception.rdb.ItemCtRepository;
import kr.co.manager.exception.rdb.MachineToolHistoryRepository;
import kr.co.manager.exception.rdb.MachineToolRepository;

@Service
public class QgToolChangeCntService {
	
	private static final Logger logger = LoggerFactory.getLogger(QgToolChangeCntService.class);
	
	@Autowired
	MachineToolRepository machineToolRepository;
	
	@Autowired
	MachineToolHistoryRepository machineToolHistoryRepository;
	
	@Autowired
	ItemCtRepository itemCtRepository;
	
	// 공구 교환 횟수 리스트 (설비별)
	public List<QgToolChangeCntView> getToolChangeCntList(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, Integer standard) {
		List<QgToolChangeCntView> toolChangeCntViewList = new ArrayList<>();
		List<Object[]> toolChangeList = machineToolHistoryRepository.userFindToolChangeCntListByFactoryIdAndAreaIdAndMachineIdAndStartDateAndEndDate(factoryId, areaId, machineId, startDate, endDate);
		for(Object[] objArr : toolChangeList) {
			if(objArr != null) {
				Integer totalItemCnt = itemCtRepository.userFindItemCntByFactoryIdAndMachineIdAndStartDateAndEndDate(Integer.parseInt(objArr[0].toString()), Integer.parseInt(objArr[4].toString()), startDate, endDate);
				Integer totalToolChangeCnt = Integer.parseInt(objArr[6].toString());
				double calCnt = (double)(totalToolChangeCnt * standard) / totalItemCnt;
				QgToolChangeCntView qgToolChangeCntView = new QgToolChangeCntView();
				qgToolChangeCntView.setFactoryId(Integer.parseInt(objArr[0].toString()));
				qgToolChangeCntView.setFactoryName(objArr[1].toString());
				if(objArr[2] != null) {
					qgToolChangeCntView.setAreaId(Integer.parseInt(objArr[2].toString()));
				}
				if(objArr[3] != null) {
					qgToolChangeCntView.setAreaName(objArr[3].toString());
				}
				qgToolChangeCntView.setMachineId(Integer.parseInt(objArr[4].toString()));
				qgToolChangeCntView.setMachineName(objArr[5].toString());
				qgToolChangeCntView.setTotalToolChangeCnt(totalToolChangeCnt);
				qgToolChangeCntView.setTotalItemCnt(totalItemCnt);
				qgToolChangeCntView.setCalCnt(calCnt);
				toolChangeCntViewList.add(qgToolChangeCntView);
			}
		}
		return toolChangeCntViewList;
	}
	
	// 공구 교환 횟수 디테일 리스트 (tool 별)
	public List<QgToolChangeCntView> getToolChangeCntDetailList(Integer factoryId, Integer machineId, String startDate, String endDate, Integer standard) {
		List<QgToolChangeCntView> toolChangeCntDetailViewList = new ArrayList<>();
		List<Object[]> toolChangeDetailList = machineToolHistoryRepository.userFindToolChangeCntDetailListByMachineIdAndStartDateAndEndDate(machineId, startDate, endDate);
		Integer totalItemCnt = itemCtRepository.userFindItemCntByFactoryIdAndMachineIdAndStartDateAndEndDate(factoryId, machineId, startDate, endDate);
		for(Object[] objArr : toolChangeDetailList) {
			Integer totalToolChangeCnt = Integer.parseInt(objArr[2].toString());
			double calCnt = (double)(totalToolChangeCnt * standard) / totalItemCnt;
			Integer machineToolId = Integer.parseInt(objArr[0].toString());
			Integer presetCnt = machineToolRepository.userFindPresetCntByMachineToolId(machineToolId);
			QgToolChangeCntView qgToolChangeCntDetailView = new QgToolChangeCntView();
			qgToolChangeCntDetailView.setToolId(machineToolId);
			qgToolChangeCntDetailView.setToolName(objArr[1].toString());
			qgToolChangeCntDetailView.setToolChangeCnt(totalToolChangeCnt);
			qgToolChangeCntDetailView.setTotalItemCnt(totalItemCnt);
			qgToolChangeCntDetailView.setCalCnt(calCnt);
			qgToolChangeCntDetailView.setPresetCnt(presetCnt);
			toolChangeCntDetailViewList.add(qgToolChangeCntDetailView);
		}
		return toolChangeCntDetailViewList;
	}
}
