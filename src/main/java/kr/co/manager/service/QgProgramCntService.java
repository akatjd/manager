package kr.co.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.view.QgProgramCntView;
import kr.co.manager.exception.rdb.ItemCtRepository;
import kr.co.manager.exception.rdb.MachineEventRepository;
import kr.co.manager.exception.rdb.MachineRepository;

@Service
public class QgProgramCntService {

	private static final Logger logger = LoggerFactory.getLogger(QgProgramCntService.class);
	
	@Autowired
	MachineRepository machineRepository;
	
	@Autowired
	MachineEventRepository machineEventRepository;
	
	@Autowired
	ItemCtRepository itemCtRepository;
	
	// 프로그램 수정 횟수 그리드에 뿌릴 programList 뽑기 (설비명 기준)
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<QgProgramCntView> getProgramCntList(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, Integer standard) {
		List<QgProgramCntView> programCntViewList = new ArrayList<>();
		
		List<Object[]> machineList = machineRepository.userFindProgramCntMachineListByFactoryIdAndAreaId(factoryId, areaId, machineId);
		for(Object[] objArr : machineList) {
			QgProgramCntView qpcView = new QgProgramCntView();
			Integer machineIdTemp = Integer.parseInt(objArr[0].toString());
			Integer totalItemCnt = itemCtRepository.userFindItemCntByFactoryIdAndMachineIdAndStartDateAndEndDate(factoryId, machineIdTemp, startDate, endDate);
			String machineName = objArr[1].toString();
			String areaName = objArr[2].toString();
			Integer programEditCnt = machineEventRepository.userFindProgramEditCntByMachineId(machineIdTemp, startDate, endDate);
			qpcView.setMachineId(machineIdTemp);
			qpcView.setMachineName(machineName);
			qpcView.setProgramEditCnt(programEditCnt);
			qpcView.setTotalItemCnt(totalItemCnt);
			qpcView.setAreaName(areaName);
			totalItemCnt = (totalItemCnt == 0) ? 1 : totalItemCnt;
			double calCnt = (double)(programEditCnt * standard) / totalItemCnt;
			qpcView.setCalCnt(calCnt);
			programCntViewList.add(qpcView);
		}
		
//		List<Object[]> programCntList = itemCtRepository.userFindProgramCntListByFactoryIdAndStartDateAndEndDate(factoryId, startDate, endDate);
//		for(Object[] objArr : programCntList) {
//			Integer totalItemCnt = (Integer.parseInt(objArr[1].toString()) != 0) ? Integer.parseInt(objArr[1].toString()) : 1;
//			Integer programEditCnt = (Integer.parseInt(objArr[6].toString()) != 0) ? Integer.parseInt(objArr[6].toString()) : 0;
//			double calCnt = (double)(programEditCnt * standard) / totalItemCnt;
//			qpcView.set
//		}
		
		return programCntViewList;
	}
	
	// 프로그램 수정 횟수에서 설비명 클릭시 프로그램별 수정 횟수 리스트 뽑기
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<QgProgramCntView> getProgramCntDetailList(Integer factoryId, Integer machineId, String startDate, String endDate, Integer standard) {
		List<QgProgramCntView> programCntDetailViewList = new ArrayList<>();
		List<Object[]> programCntDetailList = machineEventRepository.userFindProgramCntDetailListByMachineId(machineId, startDate, endDate);
		Integer totalItemCnt = itemCtRepository.userFindItemCntByFactoryIdAndMachineIdAndStartDateAndEndDate(factoryId, machineId, startDate, endDate);
		for(Object[] objArr : programCntDetailList) {
			QgProgramCntView qpcView = new QgProgramCntView();
			Integer programEditDetailCnt = Integer.parseInt(objArr[3].toString());
			double calCnt = (double)(programEditDetailCnt * standard) / totalItemCnt;
			qpcView.setProgramName(objArr[1].toString());
			qpcView.setProgramEditDetailCnt(programEditDetailCnt);
			qpcView.setTotalItemCnt(totalItemCnt);
			qpcView.setCalCnt(calCnt);
			programCntDetailViewList.add(qpcView);
		}
		return programCntDetailViewList;
	}
}
