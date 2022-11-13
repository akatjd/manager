package kr.co.manager.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.view.QgCtView;
import kr.co.manager.exception.rdb.ItemCtRepository;
import kr.co.manager.exception.rdb.ItemToolCtRepository;
import kr.co.manager.exception.rdb.ProductRepository;

@Service
public class QgCtService {

	private final static Logger logger = LoggerFactory.getLogger(QgCtService.class);
	
	@Autowired
	ItemCtRepository itemCtRepository;
	
	@Autowired
	ItemToolCtRepository itemToolCtRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public List<QgCtView> getQgCtViewList(Integer factoryId, Integer areaId, Integer machineId, Integer productId, String startDate, String endDate, Integer standardMaxCt) {
		List<QgCtView> resultList = new ArrayList<>();
		List<Object[]> ctViewList = itemCtRepository.userFindCtViewListByFactoryIdAndAreaIdAndMachineIdAndProductIdAndStartDateAndEndDate(factoryId, areaId, machineId, productId, startDate, endDate);
		List<Object[]> ctList = itemCtRepository.userFindCtListByFactoryIdAndAreaIdAndMachineIdAndProductIdAndStartDateAndEndDate(factoryId, areaId, machineId, productId, startDate, endDate);
		
		for(Object[] objArr : ctViewList) {
			QgCtView ctView = new QgCtView();
			Integer objMachineId = Integer.parseInt(objArr[4].toString());
			Integer objProductId = Integer.parseInt(objArr[9].toString());
			String objProductName = "";
			Integer cnt = 0;
			double minCt = (Double)objArr[6];
			double avgCt = (Double)objArr[7];
			double maxCt = (Double)objArr[8];
			double standardCt = 1;
			double gapValue = 0;
			if(standardMaxCt < minCt) {
				gapValue = standardMaxCt;
			}else {
				gapValue = standardMaxCt - minCt;
			}
			Integer[] sectionCnt = new Integer[10];
			String[] arrSectionStr = new String[10]; 
			List<Object> ctArr = new ArrayList<>();
			List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
			objProductName = objArr[10].toString();
			for(int i=0; i<sectionCnt.length; i++) {
				sectionCnt[i] = 0;
			}
			for(Object[] ctObjArr : ctList) {
				if(objMachineId == Integer.parseInt(ctObjArr[0].toString())) {
					if(objProductId == Integer.parseInt(ctObjArr[3].toString())) {
						double ct = (Double) ctObjArr[1];
						Integer nowCt = 0;
						nowCt = (int) ct;
						if(cnt == 0) {
							Float tempStaticCt = productRepository.userFindProductStaticCycleByMachineIdAndProductId(Integer.parseInt(ctObjArr[0].toString()), Integer.parseInt(ctObjArr[3].toString()));
							if(tempStaticCt < 1 || tempStaticCt == null) {
								standardCt = nowCt;
							}else {
								standardCt = tempStaticCt;
							}
						}
						if(gapValue == standardMaxCt) {
							if(nowCt >= 0 && nowCt < (gapValue/9)*1) {
								sectionCnt[0]++;
							}else if(nowCt >= ((gapValue/9)*1) && nowCt < ((gapValue/9)*2)) {
								sectionCnt[1]++;
							}else if(nowCt >= ((gapValue/9)*2) && nowCt < ((gapValue/9)*3)) {
								sectionCnt[2]++;
							}else if(nowCt >= ((gapValue/9)*3) && nowCt < ((gapValue/9)*4)) {
								sectionCnt[3]++;
							}else if(nowCt >= ((gapValue/9)*4) && nowCt < ((gapValue/9)*5)) {
								sectionCnt[4]++;
							}else if(nowCt >= ((gapValue/9)*5) && nowCt < ((gapValue/9)*6)) {
								sectionCnt[5]++;
							}else if(nowCt >= ((gapValue/9)*6) && nowCt < ((gapValue/9)*7)) {
								sectionCnt[6]++;
							}else if(nowCt >= ((gapValue/9)*7) && nowCt < ((gapValue/9)*8)) {
								sectionCnt[7]++;
							}else if(nowCt >= ((gapValue/9)*8) && nowCt < gapValue) {
								sectionCnt[8]++;
							}else if(nowCt >= gapValue) {
								sectionCnt[9]++;
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
							if(nowCt >= Math.floor(minCt) && nowCt < (minCt + ((gapValue/9)*1))) {
								sectionCnt[0]++;
							}else if(nowCt >= (minCt + ((gapValue/9)*1)) && nowCt < (minCt + ((gapValue/9)*2))) {
								sectionCnt[1]++;
							}else if(nowCt >= (minCt + ((gapValue/9)*2)) && nowCt < (minCt + ((gapValue/9)*3))) {
								sectionCnt[2]++;
							}else if(nowCt >= (minCt + ((gapValue/9)*3)) && nowCt < (minCt + ((gapValue/9)*4))) {
								sectionCnt[3]++;
							}else if(nowCt >= (minCt + ((gapValue/9)*4)) && nowCt < (minCt + ((gapValue/9)*5))) {
								sectionCnt[4]++;
							}else if(nowCt >= (minCt + ((gapValue/9)*5)) && nowCt < (minCt + ((gapValue/9)*6))) {
								sectionCnt[5]++;
							}else if(nowCt >= (minCt + ((gapValue/9)*6)) && nowCt < (minCt + ((gapValue/9)*7))) {
								sectionCnt[6]++;
							}else if(nowCt >= (minCt + ((gapValue/9)*7)) && nowCt < (minCt + ((gapValue/9)*8))) {
								sectionCnt[7]++;
							}else if(nowCt >= (minCt + ((gapValue/9)*8)) && nowCt < (minCt + gapValue)) {
								sectionCnt[8]++;
							}else if(nowCt >= (minCt + gapValue)) {
								sectionCnt[9]++;
							}
							arrSectionStr[0] = (Integer.toString((int)minCt)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + (Integer.toString((int)(minCt + ((gapValue/9)*1)))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
							arrSectionStr[1] = Integer.toString((int)(minCt + ((gapValue/9)*1))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(minCt + ((gapValue/9)*2))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
							arrSectionStr[2] = Integer.toString((int)(minCt + ((gapValue/9)*2))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(minCt + ((gapValue/9)*3))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
							arrSectionStr[3] = Integer.toString((int)(minCt + ((gapValue/9)*3))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(minCt + ((gapValue/9)*4))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
							arrSectionStr[4] = Integer.toString((int)(minCt + ((gapValue/9)*4))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(minCt + ((gapValue/9)*5))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
							arrSectionStr[5] = Integer.toString((int)(minCt + ((gapValue/9)*5))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(minCt + ((gapValue/9)*6))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
							arrSectionStr[6] = Integer.toString((int)(minCt + ((gapValue/9)*6))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(minCt + ((gapValue/9)*7))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
							arrSectionStr[7] = Integer.toString((int)(minCt + ((gapValue/9)*7))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(minCt + ((gapValue/9)*8))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
							arrSectionStr[8] = Integer.toString((int)(minCt + ((gapValue/9)*8))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Integer.toString((int)(minCt + ((gapValue/9)*9))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)"; 
							arrSectionStr[9] = Integer.toString((int)(minCt + ((gapValue/9)*9))).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + "(s)";
						}
						Date temp = (Date)ctObjArr[2];
						List<Object> hcDataArr = new ArrayList<>();
						hcDataArr.add(temp.getTime()/1000);
						hcDataArr.add(nowCt);
						ctArr.add(hcDataArr);
						cnt++;
					}
				}
			}
			for(int i=0; i<arrSectionStr.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				if(arrSectionStr[i] == null) {
					arrSectionStr[i] = "-";
				}
				map.put("name", arrSectionStr[i]);
				map.put("y", sectionCnt[i]);
				mapList.add(map);
			}
			ctView.setDistributionMapList(mapList);
			ctView.setFactoryId(Integer.parseInt(objArr[0].toString()));
			ctView.setFactoryName(objArr[1].toString());
			ctView.setAreaId(Integer.parseInt(objArr[2].toString()));
			ctView.setAreaName(objArr[3].toString());
			ctView.setMachineId(objMachineId);
			ctView.setMachineName(objArr[5].toString());
			ctView.setProductId(objProductId);
			ctView.setProductName(objProductName);
			ctView.setMinCt((int)minCt);
			ctView.setAvgCt((int)avgCt);
			ctView.setMaxCt((int)maxCt);
//			ctView.setStandardCt(standardCt);
			ctView.setDoubleStandardCt(standardCt);
//			logger.info("objMachineId :: " + objMachineId);
//			logger.info("standardCt :: " + standardCt);
			ctView.setDistributionCt(sectionCnt);
			ctView.setDoubleGapStandardAvg(standardCt - avgCt);
			ctView.setCtArr(ctArr);
			ctView.setItemCnt(cnt);
			ctView.setDoubleMinCt(minCt);
			resultList.add(ctView);
		}
		return resultList;
	} 
	
	public List<QgCtView> getQgCtDetailViewList(Integer machineId, Integer productId, String startDate, String endDate) {
		List<QgCtView> resultList = new ArrayList<>();
		
//		Timestamp ts01 = new Timestamp(System.currentTimeMillis());
//		logger.info("ts01 :: " + ts01);
		
		List<Object[]> ctDetailViewList = itemToolCtRepository.userFindCtDetailViewListByMachineIdAndProductIdAndStartDateAndEndDate(machineId, productId, startDate, endDate);
		
//		Timestamp ts02 = new Timestamp(System.currentTimeMillis());
//		logger.info("ts02 :: " + ts02);
		
		List<Object[]> ctDetailList = itemToolCtRepository.userFindCtDetailByMachineIdAndProductIdAndStartDateAndEndDate(machineId, productId, startDate, endDate);
		
//		Timestamp ts03 = new Timestamp(System.currentTimeMillis());
//		logger.info("ts03 :: " + ts03);
		
		for(Object[] objArr : ctDetailViewList) {
			Integer tempMachineId = Integer.parseInt(objArr[0].toString());
			Integer tempProductId = Integer.parseInt(objArr[1].toString());
			Integer sectionNum = Integer.parseInt(objArr[2].toString());
			String toolName = objArr[3].toString();
			Integer minCtDetail = 0;
			if(objArr[5] != null) {
				minCtDetail = Integer.parseInt(objArr[5].toString());
			}
			BigDecimal avgCtDetail = new BigDecimal("0");
			if(objArr[6] != null) {
				avgCtDetail = (BigDecimal)objArr[6];
			}
			Integer maxCtDetail = 0;
			if(objArr[7] != null) {
				maxCtDetail = Integer.parseInt(objArr[7].toString());
			}
			QgCtView ctDetailView = new QgCtView();
			Integer gapValue = maxCtDetail - minCtDetail;
			Integer[] sectionCnt = new Integer[10];
			String[] arrSectionStr = new String[10];
			for(int i=0; i<sectionCnt.length; i++) {
				sectionCnt[i] = 0;
			}
			Integer cnt = 0;
			List<Object> ctArr = new ArrayList<>();
			List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
			
			for(Object[] detailObjArr : ctDetailList) {
				if((sectionNum == Integer.parseInt(detailObjArr[2].toString())) && toolName.equals(detailObjArr[3].toString())) {
					Integer nowCt = Integer.parseInt(detailObjArr[5].toString());
					if(cnt == 0) {
						Integer standardCt = nowCt;
					}
					
					// 최대-최소 값을 10등분 하여 최소값에서 더해나감
					if(nowCt >= minCtDetail && nowCt < (minCtDetail + ((double)gapValue/10)*1)) {
						sectionCnt[0]++;
					}else if(nowCt >= (minCtDetail + ((double)gapValue/10)*1) && nowCt < (minCtDetail + ((double)gapValue/10)*2)) {
						sectionCnt[1]++;
					}else if(nowCt >= (minCtDetail + ((double)gapValue/10)*2) && nowCt < (minCtDetail + ((double)gapValue/10)*3)) {
						sectionCnt[2]++;
					}else if(nowCt >= (minCtDetail + ((double)gapValue/10)*3) && nowCt < (minCtDetail + ((double)gapValue/10)*4)) {
						sectionCnt[3]++;
					}else if(nowCt >= (minCtDetail + ((double)gapValue/10)*4) && nowCt < (minCtDetail + ((double)gapValue/10)*5)) {
						sectionCnt[4]++;
					}else if(nowCt >= (minCtDetail + ((double)gapValue/10)*5) && nowCt < (minCtDetail + ((double)gapValue/10)*6)) {
						sectionCnt[5]++;
					}else if(nowCt >= (minCtDetail + ((double)gapValue/10)*6) && nowCt < (minCtDetail + ((double)gapValue/10)*7)) {
						sectionCnt[6]++;
					}else if(nowCt >= (minCtDetail + ((double)gapValue/10)*7) && nowCt < (minCtDetail + ((double)gapValue/10)*8)) {
						sectionCnt[7]++;
					}else if(nowCt >= (minCtDetail + ((double)gapValue/10)*8) && nowCt < (minCtDetail + ((double)gapValue/10)*9)) {
						sectionCnt[8]++;
					}else {
						sectionCnt[9]++;
					}
					
					// 최대값을 10구간으로 나눔
//					if(nowCt >= 0 && nowCt < (int)(maxCtDetail * 0.1)) {
//						sectionCnt[0]++;
//					}else if(nowCt >= (int)(maxCtDetail * 0.1) && nowCt < (int)(maxCtDetail * 0.2)) {
//						sectionCnt[1]++;
//					}else if(nowCt >= (int)(maxCtDetail * 0.2) && nowCt < (int)(maxCtDetail * 0.3)) {
//						sectionCnt[2]++;
//					}else if(nowCt >= (int)(maxCtDetail * 0.3) && nowCt < (int)(maxCtDetail * 0.4)) {
//						sectionCnt[3]++;
//					}else if(nowCt >= (int)(maxCtDetail * 0.4) && nowCt < (int)(maxCtDetail * 0.5)) {
//						sectionCnt[4]++;
//					}else if(nowCt >= (int)(maxCtDetail * 0.5) && nowCt < (int)(maxCtDetail * 0.6)) {
//						sectionCnt[5]++;
//					}else if(nowCt >= (int)(maxCtDetail * 0.6) && nowCt < (int)(maxCtDetail * 0.7)) {
//						sectionCnt[6]++;
//					}else if(nowCt >= (int)(maxCtDetail * 0.7) && nowCt < (int)(maxCtDetail * 0.8)) {
//						sectionCnt[7]++;
//					}else if(nowCt >= (int)(maxCtDetail * 0.8) && nowCt < (int)(maxCtDetail * 0.9)) {
//						sectionCnt[8]++;
//					}else if(nowCt >= (int)(maxCtDetail * 0.9)) {
//						sectionCnt[9]++;
//					}
					
					arrSectionStr[0] = (Math.round(minCtDetail * 0.05)) + "~" + (Math.round(((minCtDetail + ((double)gapValue/10)*1) * 0.05)*100)/100.0) + "(s)";
					arrSectionStr[1] = (Math.round(((minCtDetail + ((double)gapValue/10)*1) * 0.05)*100)/100.0) + "~" + (Math.round(((minCtDetail + ((double)gapValue/10)*2) * 0.05)*100)/100.0) + "(s)";
					arrSectionStr[2] = (Math.round(((minCtDetail + ((double)gapValue/10)*2) * 0.05)*100)/100.0) + "~" + (Math.round(((minCtDetail + ((double)gapValue/10)*3) * 0.05)*100)/100.0) + "(s)";
					arrSectionStr[3] = (Math.round(((minCtDetail + ((double)gapValue/10)*3) * 0.05)*100)/100.0) + "~" + (Math.round(((minCtDetail + ((double)gapValue/10)*4) * 0.05)*100)/100.0) + "(s)";
					arrSectionStr[4] = (Math.round(((minCtDetail + ((double)gapValue/10)*4) * 0.05)*100)/100.0) + "~" + (Math.round(((minCtDetail + ((double)gapValue/10)*5) * 0.05)*100)/100.0) + "(s)";
					arrSectionStr[5] = (Math.round(((minCtDetail + ((double)gapValue/10)*5) * 0.05)*100)/100.0) + "~" + (Math.round(((minCtDetail + ((double)gapValue/10)*6) * 0.05)*100)/100.0) + "(s)";
					arrSectionStr[6] = (Math.round(((minCtDetail + ((double)gapValue/10)*6) * 0.05)*100)/100.0) + "~" + (Math.round(((minCtDetail + ((double)gapValue/10)*7) * 0.05)*100)/100.0) + "(s)";
					arrSectionStr[7] = (Math.round(((minCtDetail + ((double)gapValue/10)*7) * 0.05)*100)/100.0) + "~" + (Math.round(((minCtDetail + ((double)gapValue/10)*8) * 0.05)*100)/100.0) + "(s)";
					arrSectionStr[8] = (Math.round(((minCtDetail + ((double)gapValue/10)*8) * 0.05)*100)/100.0) + "~" + (Math.round(((minCtDetail + ((double)gapValue/10)*9) * 0.05)*100)/100.0) + "(s)";
					arrSectionStr[9] = (Math.round(((minCtDetail + ((double)gapValue/10)*9) * 0.05)*100)/100.0) + "~" + "(s)";
					
//					if(Math.round(maxCtDetail*0.1*0.05) == 0) {
//						arrSectionStr[0] = "0(s)";
//					}else {
//						arrSectionStr[0] = "0~" + Double.toString(Math.round(maxCtDetail*0.1*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					}
//					arrSectionStr[1] = Double.toString(Math.round(maxCtDetail*0.1*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Double.toString(Math.round(maxCtDetail*0.2*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[2] = Double.toString(Math.round(maxCtDetail*0.2*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Double.toString(Math.round(maxCtDetail*0.3*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[3] = Double.toString(Math.round(maxCtDetail*0.3*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Double.toString(Math.round(maxCtDetail*0.4*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[4] = Double.toString(Math.round(maxCtDetail*0.4*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Double.toString(Math.round(maxCtDetail*0.5*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[5] = Double.toString(Math.round(maxCtDetail*0.5*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Double.toString(Math.round(maxCtDetail*0.6*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[6] = Double.toString(Math.round(maxCtDetail*0.6*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Double.toString(Math.round(maxCtDetail*0.7*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[7] = Double.toString(Math.round(maxCtDetail*0.7*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Double.toString(Math.round(maxCtDetail*0.8*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[8] = Double.toString(Math.round(maxCtDetail*0.8*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + Double.toString(Math.round(maxCtDetail*0.9*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "(s)";
//					arrSectionStr[9] = Double.toString(Math.round(maxCtDetail*0.9*0.05)).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",") + "~" + "(s)";
					
					ctArr.add(nowCt);
					cnt++;
				}
			}
			
			for(int i=0; i<arrSectionStr.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				if(arrSectionStr[i] == null) {
					arrSectionStr[i] = "-";
				}
				map.put("name", arrSectionStr[i]);
				map.put("y", sectionCnt[i]);
				mapList.add(map);
			}
			
			ctDetailView.setDistributionMapList(mapList);
			ctDetailView.setMachineId(tempMachineId);
			ctDetailView.setProductId(tempProductId);
			ctDetailView.setSectionNumber(sectionNum);
			ctDetailView.setToolName(toolName);
			ctDetailView.setMinCtDetail(minCtDetail);
			ctDetailView.setAvgCtDetail(avgCtDetail.intValue());
			ctDetailView.setMaxCtDetail(maxCtDetail);
			ctDetailView.setDistributionDetailCt(sectionCnt);
			ctDetailView.setCtArr(ctArr);
			resultList.add(ctDetailView);
		}
		
		return resultList;
	}
}
