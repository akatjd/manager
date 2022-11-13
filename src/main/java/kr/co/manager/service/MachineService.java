package kr.co.manager.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.domain.jpa.Area;
import kr.co.manager.domain.jpa.Factory;
import kr.co.manager.domain.jpa.MachineTool;
import kr.co.manager.domain.view.AreaView;
import kr.co.manager.domain.view.MachineToolView;
import kr.co.manager.domain.view.MachineView;
import kr.co.manager.domain.view.ProductView;
import kr.co.manager.domain.view.QgMonitoringView;
import kr.co.manager.exception.QgException;
import kr.co.manager.exception.rdb.AccountBcryptRepository;
import kr.co.manager.exception.rdb.AccountRepository;
import kr.co.manager.exception.rdb.AreaRepository;
import kr.co.manager.exception.rdb.FactoryRepository;
import kr.co.manager.exception.rdb.InspectionPolicyGroupRepository;
import kr.co.manager.exception.rdb.ItemRepository;
import kr.co.manager.exception.rdb.MachineEventRepository;
import kr.co.manager.exception.rdb.MachineRepository;
import kr.co.manager.exception.rdb.MachineToolRepository;
import kr.co.manager.exception.rdb.MachiningSectionRepository;
import kr.co.manager.exception.rdb.ProductRepository;
import kr.co.manager.exception.rdb.SectionPieceRepository;

@Service
public class MachineService {
	
	private static final Logger logger = LoggerFactory.getLogger(MachineService.class);
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	InspectionPolicyGroupRepository inspectionPolicyGroupRepository;
	
	@Autowired
	MachineRepository machineRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	MachiningSectionRepository machiningSectionRepository;
	
	@Autowired
	SectionPieceRepository sectionPieceRepository;
	
	@Autowired
	FactoryRepository factoryRepository;
	
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
	MachineToolRepository machineToolRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountBcryptRepository accountBcryptRepository;
	
	@Autowired
	MachineEventRepository machineEventRepository;
	
	// 구역 리스트 가져오기
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<AreaView> getAreaList(Integer factoryId) {
        List<Area> aList = new ArrayList<>();
        List<AreaView> resultList = new ArrayList<>();
        if(factoryId != null){
            Factory factory = factoryRepository.findById(factoryId).get();
            aList = areaRepository.findByFactory(factory, Sort.by(Sort.Direction.ASC, "areaName"));
        }else {
            aList = areaRepository.findAll(Sort.by(Sort.Direction.ASC, "areaName"));
        }
        AreaView a = new AreaView();
        a.setAreaId(null);
        a.setAreaName("-All-");
        resultList.add(a);
        for(Area x: aList){
            AreaView av = new AreaView(x);
            resultList.add(av);
        }
        return resultList;
    }
	
	// 머신 리스트 가져오기(민성)
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<MachineView> getMachineList(Integer factoryId, Integer areaId) {
        List<MachineView> resultList = new ArrayList<>();
        List<Object[]> machineList = machineRepository.userFindMachineListByFactoryIdAndAreaId(factoryId, areaId);
        MachineView m = new MachineView();
        m.setMachineId(null);
        m.setMachineName("-All-");
        resultList.add(m);
        for(Object[] objArr : machineList){
        	MachineView mv = new MachineView();
        	mv.setMachineId(Integer.parseInt(objArr[0].toString()));
        	mv.setMachineName(objArr[1].toString());
            resultList.add(mv);
        }
        return resultList;
    }
	
	// 프로덕트 리스트 가져오기
	public List<ProductView> getProductList(Integer factoryId, Integer areaId, Integer machineId) {
		List<ProductView> resultList = new ArrayList<>();
		List<Object[]> productList = productRepository.userFindProductListByFactoryIdAndAreaIdAndMachineId(factoryId, areaId, machineId);
		ProductView p = new ProductView();
		p.setProductId(null);
		p.setProductName("-All-");
		resultList.add(p);
		for(Object[] objArr : productList) {
			ProductView pv = new ProductView();
			pv.setProductId(Integer.parseInt(objArr[0].toString()));
//			if(objArr[1] == null || "".equals(objArr[1].toString())) {
//				pv.setProductName(objArr[2].toString());
//			}else {
//				pv.setProductName(objArr[1].toString());
//			}
			if(objArr[1] == null) {
				pv.setProductName("제품명이 없습니다.");
			}else if(("").equals(objArr[1].toString())) {
				pv.setProductName("제품명이 없습니다.");
			}else {
				pv.setProductName(objArr[1].toString());	
			}
        	resultList.add(pv);
		}
		return resultList;
	}
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<QgMonitoringView> getMonitoringMachineList(Integer allowFactory, Integer areaId, String machineStatusType, String warnStatus, String machineName, String viewYn, Integer accumulSearchTime) {
    	// 민성 추가 2021-08-12 누적현황 조회시간을 위해 6 or 12 or 24
    	long currentTimeMillis = System.currentTimeMillis();
    	long accumulTimeSize = currentTimeMillis - Long.valueOf(accumulSearchTime * 60 * 60 * 1000);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String searchTime = format.format(accumulTimeSize);
    	List<Object[]> itemList = itemRepository.userFindItemListByRegDate(searchTime); // 누적현황 위한 item 리스트 가져오기
    	List<Integer> inspecitonPolicyGroupList = inspectionPolicyGroupRepository.userFindAll();
        List<Object[]> machineViewList = machineRepository.userfindByMonitoringMachineList(allowFactory, areaId, machineStatusType, warnStatus, machineName, viewYn);
        List<QgMonitoringView> resultList = new ArrayList<>();
        
        for(Object[] objArr : machineViewList){
        	QgMonitoringView mv = new QgMonitoringView(objArr);
            
            // 민성 추가 2021-08-12 변수 선언
            Integer machineId = mv.getMachineId();
            Integer currentProductId = mv.getCurrentProductId();
            Integer isN1111Detect = null;
            Integer isN9999Detect = null;
            String productName = null;
            String itemRegDate = "-";
//            String inspectionStr = "";
            int goodCnt = 0;
            int infoCnt = 0;
            int badsyncCnt = 0;
            int errorCnt = 0;
            int stopCnt = 0;
//            int inspectionCnt = 0;
//            int sectionCnt = 0;
            String isN1111DetectStr = null;
            String isN9999DetectStr = null;
            List<Integer> itemIdList = new ArrayList<>();
//            List<Integer> machiningSectionIdList = new ArrayList<>();
            List<Integer> productList = new ArrayList<>();
//            List<Integer> sectionPieceIdList = new ArrayList<>();
            
            // 민성 추가 2021-08-20 N코드
        	isN1111Detect = productRepository.findIsN1111DetectByProductId(currentProductId);
        	isN9999Detect = productRepository.findIsN9999DetectByProductId(currentProductId);
            if(isN1111Detect != null) {
            	if(isN1111Detect == 1)	isN1111DetectStr = "O";
            	else if(isN1111Detect == 0)	isN1111DetectStr = "X";
            }else {
            	isN1111DetectStr = "-";
            }
            if(isN9999Detect != null) {
            	if(isN9999Detect == 1) isN9999DetectStr = "O";
            	else if(isN9999Detect == 0)	isN9999DetectStr = "X";
            }else {
            	isN9999DetectStr = "-";
            }
            mv.setIsN1111Detect(isN1111DetectStr);
            mv.setIsN9999Detect(isN9999DetectStr);
            
            // 민성 추가 2021-08-18 누적현황
            productList = productRepository.userFindProductIdBymachineId(machineId);
            for(Integer productId : productList) {
            	for(int i=0; i<itemList.size(); i++) {
            		if(itemList.get(i)[2].equals(productId)) {
            			if(itemList.get(i)[1].equals("good")) goodCnt += 1;
            			else if(itemList.get(i)[1].equals("info")) infoCnt += 1;
            			else if(itemList.get(i)[1].equals("stop")) stopCnt += 1;
            			else if(itemList.get(i)[1].equals("error")) errorCnt += 1;
            			else badsyncCnt += 1;
            		}
            	}
            }
            
            mv.setGoodCnt(goodCnt);
            mv.setInfoCnt(infoCnt);
            mv.setBadsyncCnt(badsyncCnt);
            mv.setErrorCnt(errorCnt);
            mv.setStopCnt(stopCnt);
            
            // 민성 추가 2021-08-19 표준파형 관련 로직 추가
            productName = productRepository.userFindProductNameByProductId(currentProductId);
            itemIdList = machiningSectionRepository.userFindItemIdByProductIdAndActiveYn(currentProductId, "y");
            
            for(Integer itemId : itemIdList) {
            	if(itemIdList.size() == 1) {
            		itemRegDate = itemRepository.userFindRegDateByItemId(itemId);
            		if(itemRegDate != null) {
                		itemRegDate = itemRegDate.substring(0, 10);
                	}else {
                		itemRegDate = "-";
                	}
            	}else {
            		if(itemRepository.userFindRegDateByItemId(itemId) != null) {
            			itemRegDate += itemRepository.userFindRegDateByItemId(itemId).substring(0, 10) + ", ";
            		}else {
            			itemRegDate = "-" + ", ";
            		}
            	}
            }
            
            if(itemRegDate.startsWith("null") && itemIdList.size() > 1) {
            	itemRegDate = itemRegDate.substring(4, itemRegDate.length()-2);
            }else if(itemIdList.size() > 1){
            	itemRegDate = itemRegDate.substring(0, itemRegDate.length()-2);
            }
            
            mv.setStandardRegDate(itemRegDate);
            
            if(productName != null) {
            	mv.setProductName(productName);
            }else {
            	productName = "empty";
            	mv.setProductName(productName);
            }
            
//            // 민성 추가 2021-08-16 검사/섹션개수 로직 추가
//        	machiningSectionIdList = machiningSectionRepository.userFindMachiningSectionIdByProductId(currentProductId);
//            for(int i=0; i<machiningSectionIdList.size(); i++) {
//               sectionPieceIdList = sectionPieceRepository.userFindSectionPieceIdByMachiningSectionId(machiningSectionIdList.get(i));
//        	   for(int j=0; j<sectionPieceIdList.size(); j++) {
//        		   if(inspecitonPolicyGroupList.contains(sectionPieceIdList.get(j))) inspectionCnt++;
//               }
//               sectionCnt = sectionPieceIdList.size();
//               inspectionStr += Integer.toString(inspectionCnt) + "/" + Integer.toString(sectionCnt);
//               if(i == 0 && machiningSectionIdList.size()>1) {
//               	inspectionStr += ", ";
//               }
//               inspectionCnt = 0;
//            }
            
            // 민성 수정 2021-09-13 검사/섹션개수 로직 수정
            List<Object[]> inspectionSectionList = productRepository.userFindInspectionSectionListByProductId(currentProductId);
            Integer nowPalletNo = null;
            Integer sectionCnt = 0;
            Integer policyCnt = 0;
            Integer idx = 0;
            String inspectionStr = "";
            
            for(Object[] objArr2 : inspectionSectionList){
            	if(nowPalletNo == null) {
            		nowPalletNo = Integer.parseInt(objArr2[0].toString());
            	}
            	if(nowPalletNo != Integer.parseInt(objArr2[0].toString())) {
            		nowPalletNo = Integer.parseInt(objArr2[0].toString());
            		inspectionStr += policyCnt + "/" + sectionCnt + ", ";
            		policyCnt = 0;
            		sectionCnt = 0;
            	}
            	idx++;
            	sectionCnt++;
            	if(objArr2[1] != null) {
            		policyCnt++;
            	}
            	if(idx == inspectionSectionList.size()) {
            		inspectionStr += policyCnt + "/" + sectionCnt;
            	}
            }
            mv.setInspectionStr(inspectionStr);
            
            resultList.add(mv);
        }
        return resultList;
    }
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<MachineView> getDashboardMachineList(Integer allowFactory, Integer areaId, String viewYn) {
        List<Object[]> machineViewList = machineRepository.findMachineViewList(allowFactory, areaId, viewYn);
        List<MachineView> resultList = new ArrayList<>();
        for(Object[] objArr : machineViewList){
            MachineView mv = new MachineView(objArr);
            mv.setToolList(getMachineToolList(mv.getMachineId()));
            resultList.add(mv);
        }
        return resultList;
    }
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<MachineToolView> getMachineToolList(Integer machineId){
        List<MachineTool> tList = machineToolRepository.userFindByMachineId(machineId);
        List<MachineToolView> tvList = new ArrayList<>();
        for(MachineTool mt : tList){
            MachineToolView mv = new MachineToolView(mt);
            tvList.add(mv);
        }
        return tvList;
    }
	
	/**
     * <pre>
     * 1. 개요 : 사용자 정보를 저장
     * 2. 처리내용 : 사용자 정보를 저장
     * </pre>
     * @Method Name : saveUserInfo
     * @date : 2021. 08. 25.
     * @author : kms
     * @history : 
     *  -----------------------------------------------------------------------
     *  Date                Author                        Note
     *  ----------- ------------------- ---------------------------------------
     *  2021. 08. 25.     kms             비밀번호 BCrypt 인코딩해서 저장 
     *  -----------------------------------------------------------------------
     * 
     * @param param      accountId     사용자 아이디
     *                   userName      사용자 이름
     *                   password      비밀번호
     *                   department    부서
     * @return String                  성공여부 fail or success
     * @throws QgException
     */
    public String saveUserInfo(HashMap<String, Object> paramMap) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String msg = "fail";
        String accountId = (String) paramMap.get("accountId");
        String password = encoder.encode((String) paramMap.get("password"));
        AccountBcrypt account = accountBcryptRepository.findByAccountId(accountId);
        account.setUserName((String) paramMap.get("userName"));
        account.setPassword(password);
        account.setDepartment((String) paramMap.get("department"));
        account = accountBcryptRepository.save(account);
        if (account.getAccountId() != null)
            msg = "success";
        return msg;
    }
}
