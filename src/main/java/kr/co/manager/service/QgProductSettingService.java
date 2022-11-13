package kr.co.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.jpa.MachiningSection;
import kr.co.manager.domain.jpa.Product;
import kr.co.manager.domain.jpa.ProductGcode;
import kr.co.manager.domain.type.GcodeType;
import kr.co.manager.domain.view.QgProductSettingView;
import kr.co.manager.exception.QgException;
import kr.co.manager.exception.rdb.MachineRepository;
import kr.co.manager.exception.rdb.MachiningSectionRepository;
import kr.co.manager.exception.rdb.ProductGcodeRepository;
import kr.co.manager.exception.rdb.ProductRepository;
import kr.co.manager.exception.rdb.SectionPieceRepository;
import kr.co.manager.util.JsonConverter;

@Service
public class QgProductSettingService {
	
	private static final Logger logger = LoggerFactory.getLogger(QgProductSettingService.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductGcodeRepository productGcodeRepository;
	
	@Autowired
	MachiningSectionRepository machiningSectionRepository;
	
	@Autowired
	SectionPieceRepository sectionPieceRepository;
	
	@Autowired
	MachineRepository machineRepository;
	
	public List<QgProductSettingView> getProductSettingViewList(Integer factoryId, Integer areaId, Integer machineId){
		List<QgProductSettingView> productSettingViewList = new ArrayList<>();
		List<Object[]> productSettingList = productRepository.userFindProductSettingListByFactoryIdAndAreaIdAndMachineId(factoryId, areaId, machineId);
		for(Object[] objArr : productSettingList) {
			QgProductSettingView psView = new QgProductSettingView();
			psView.setAreaId((objArr[0] != null) ? Integer.parseInt(objArr[0].toString()) : null);
			psView.setAreaName((objArr[1] != null) ? objArr[1].toString() : null);
			psView.setMachineId((objArr[2] != null) ? Integer.parseInt(objArr[2].toString()) : null);
			psView.setMachineName((objArr[3] != null) ? objArr[3].toString() : null);
			psView.setProductId((objArr[4] != null) ? Integer.parseInt(objArr[4].toString()) : null);
			psView.setProductOid((objArr[5] != null) ? objArr[5].toString() : null);
			psView.setProductName((objArr[6] != null) ? objArr[6].toString() : null);
			psView.setStaticCycleTime((objArr[7] != null) ? (Float)objArr[7] : null);
			psView.setActiveYn((objArr[8] != null) ? objArr[8].toString().charAt(0) : null);
			productSettingViewList.add(psView);
		}
		return productSettingViewList;
	}
	
	public String updateProductSetting(HashMap<String, Object> param) throws QgException {
		String msg = "fail";
		List<Object> pList = JsonConverter.convertToList((String) param.get("productJson"));
		for(Object obj : pList){
            String jsonStr = JsonConverter.convertToJson(obj);
            Map<String, Object> pMap= JsonConverter.convert(jsonStr); 
            Product product = productRepository.findByProductId(Integer.parseInt(pMap.get("productId").toString()));
            product.setProductName((String) pMap.get("productName"));
            if(pMap.get("staticCycleTime") != null && pMap.get("staticCycleTime") != ""){
                product.setStaticCycleTime(Float.parseFloat(pMap.get("staticCycleTime").toString()));
            }else{
                product.setStaticCycleTime(null);
            }
            product.setActiveYn((String) pMap.get("activeYn"));
            productRepository.save(product);
        }
		msg = "success";
		return msg;
	}
	
	public HashMap<String, Object> getProductGcode(Integer productGcodeId) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        Optional<ProductGcode> productGcode =  productGcodeRepository.findById(productGcodeId);
        ProductGcode pg = productGcode.get();
        pg.setProduct(null);
        resultMap.put("productGcode", pg);
        
        return resultMap;
    }
	
	public List<ProductGcode> getGcodeList(Integer machineId, Integer productId, Integer path) throws QgException {
        List<ProductGcode> resultList = new ArrayList<>();
        
        List<Object[]> objList = productGcodeRepository.userFindByMachineIdAndProductIdAndPath(machineId, productId, path);
        
        for(Object[] objArr : objList) {
        	ProductGcode pg = new ProductGcode();
        	pg.setProductGcodeId(Integer.parseInt(objArr[0].toString()));
        	pg.setMachineId(Integer.parseInt(objArr[1].toString()));
//        	pg.setProduct(productRepository.findByProductId(Integer.parseInt(objArr[2].toString())));
        	if(objArr[3].toString().equals("main")) {
        		pg.setGcodeType(GcodeType.main);
        	}else if(objArr[3].toString().equals("sub")) {
        		pg.setGcodeType(GcodeType.sub);
        	}else {
        		pg.setGcodeType(GcodeType.macro);
        	}
        	pg.setChangedDate((Date)objArr[4]);
        	pg.setPath(Integer.parseInt(objArr[5].toString()));
        	pg.setExProductGcodeId(Integer.parseInt(objArr[6].toString()));
        	pg.setGcodeContent(objArr[7].toString());
        	pg.setOid(objArr[8].toString());
        	pg.setHash(objArr[9].toString());
        	pg.setRegDate((Date)objArr[10]);
        	resultList.add(pg);
        	
//        	logger.info("objArr[]" + objArr[0].toString());
//        	logger.info("objArr[]" + objArr[1].toString());
//        	logger.info("objArr[]" + objArr[2].toString());
//        	logger.info("objArr[]" + objArr[3].toString());
//        	logger.info("objArr[]" + objArr[4].toString());
//        	logger.info("objArr[]" + objArr[5].toString());
//        	logger.info("objArr[]" + objArr[6].toString());
//        	logger.info("objArr[]" + objArr[7].toString());
//        	logger.info("objArr[]" + objArr[8].toString());
//        	logger.info("objArr[]" + objArr[9].toString());
//        	logger.info("objArr[]" + objArr[10].toString());
        }
        return resultList;
    }
	
	public List<HashMap<String, Object>> getProductPath(Integer productId) throws QgException {
        List<HashMap<String, Object>> resultList = new ArrayList<>(); 
        Optional<Product> product = productRepository.findById(productId);
        MachiningSection ms = machiningSectionRepository.findOneByProductAndActiveYn(product.get(), "y");
//        if(ms == null) {
//            throw new QgException("not found machiningSection.");
//        }
        if(ms != null) {
            List<Object[]> spList = sectionPieceRepository.userFindSectionPathByMachiningSection(ms.getMachiningSectionId());
            for(Object[] spArr : spList){
                HashMap<String, Object> channelMap = new HashMap<String, Object>();
                channelMap.put("channelName", spArr[0]);
                channelMap.put("path", spArr[1]);
                resultList.add(channelMap);
            }
        }
        
        return resultList;
    }
}
