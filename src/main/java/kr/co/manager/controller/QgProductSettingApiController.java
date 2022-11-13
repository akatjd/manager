package kr.co.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.manager.domain.jpa.ProductGcode;
import kr.co.manager.domain.view.QgProductSettingView;
import kr.co.manager.exception.QgException;
import kr.co.manager.service.QgProductSettingService;

@RestController
@RequestMapping("/qgProductSettingApi")
public class QgProductSettingApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(QgProductSettingApiController.class);
	
	@Autowired
	QgProductSettingService qgProductSettingService;
	
	@RequestMapping(value = "/getProductSettingList", method = RequestMethod.POST)
	public HashMap<String, Object> getProductSettingList(
			@RequestParam(value = "factoryId") Integer factoryId,
			@RequestParam(value = "areaId", required = false) Integer areaId,
			@RequestParam(value = "machineId", required = false) Integer machineId){
		HashMap<String, Object> viewObj = new HashMap<>();
		List<QgProductSettingView> productSettingViewList = qgProductSettingService.getProductSettingViewList(factoryId, areaId, machineId);
		viewObj.put("productSettingViewList", productSettingViewList);
		return viewObj;
	}
	
	@RequestMapping(value = "/updateProductSetting", method = RequestMethod.POST)
	public String updateProductSetting(
			@RequestParam() HashMap<String, Object> param) throws QgException {
		return qgProductSettingService.updateProductSetting(param);
	}
	
	@RequestMapping(value = "/getProductGcode")
    public Map<String, Object> getProductGcode(
            @RequestParam(value = "productGcodeId") Integer productGcodeId) {
        return qgProductSettingService.getProductGcode(productGcodeId);
    }
	
	@RequestMapping(value = "/getGcodeList", method = RequestMethod.POST)
    public List<ProductGcode> getGcodeList( @RequestParam(value = "machineId") Integer machineId,
                                            @RequestParam(value = "productId") Integer productId,
                                            @RequestParam(value = "path") Integer path) throws QgException {
        
        List<ProductGcode> pGcodeList= new ArrayList<>();
        pGcodeList = qgProductSettingService.getGcodeList(machineId, productId, path);
        return pGcodeList;
    }
	
	@RequestMapping(value = "/getProductPath")
    public List<HashMap<String, Object>> getProductPath(
            @RequestParam(value = "productId") Integer productId) throws QgException {
        return qgProductSettingService.getProductPath(productId);
    }
	
}
