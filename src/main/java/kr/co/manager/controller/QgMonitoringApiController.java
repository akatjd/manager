package kr.co.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.manager.domain.view.AreaView;
import kr.co.manager.domain.view.MachineView;
import kr.co.manager.domain.view.ProductView;
import kr.co.manager.domain.view.QgMonitoringView;
import kr.co.manager.service.MachineService;

@RestController
@RequestMapping("/qgMonitoringApi")
public class QgMonitoringApiController {

    private static final Logger logger = LoggerFactory.getLogger(QgMonitoringApiController.class);

    @Autowired
    MachineService machineService;

    // AreaList를 얻기 위해 ajax 호출
    @RequestMapping(value = "/getAreaList", method = RequestMethod.POST)
    public List<AreaView> getAreaList(@RequestParam(value = "factoryId", required = false) Integer factoryId) {
        List<AreaView> areaViewList = machineService.getAreaList(factoryId);
        return areaViewList;
    }

    // MachineList를 얻기 위해 ajax 호출
    @RequestMapping(value = "/getMachineList", method = RequestMethod.POST)
    public List<MachineView> getMachineList(@RequestParam(value = "factoryId", required = false) Integer factoryId,
            @RequestParam(value = "areaId", required = false) Integer areaId) {
        List<MachineView> machineViewList = machineService.getMachineList(factoryId, areaId);
        return machineViewList;
    }

    // ProductList를 얻기 위해 ajax 호출
    @RequestMapping(value = "/getProductList", method = RequestMethod.POST)
    public List<ProductView> getProductList(@RequestParam(value = "factoryId", required = false) Integer factoryId,
            @RequestParam(value = "areaId", required = false) Integer areaId,
            @RequestParam(value = "machineId", required = false) Integer machineId) {
        List<ProductView> productViewList = machineService.getProductList(factoryId, areaId, machineId);
        return productViewList;
    }

    // 설비 모니터링 머신리스트 호출
    @RequestMapping(value = "/getMonitoringMachineList", method = RequestMethod.POST)
    public HashMap<String, Object> getMonitoringMachineList(
            @RequestParam(value = "factoryId", required = false) Integer factoryId,
            @RequestParam(value = "areaId", required = false) Integer areaId,
            @RequestParam(value = "machineStatusType") String machineStatusType,
            @RequestParam(value = "warnStatus") String warnStatus,
            @RequestParam(value = "machineName") String machineName,
            @RequestParam(value = "accumulSearchTime") Integer accumulSearchTime) {
        HashMap<String, Object> viewObj = new HashMap<>();
        List<QgMonitoringView> machines = new ArrayList<>();
        String viewYn = "y";
        if ("".equals(machineStatusType))
            machineStatusType = null;
        if ("".equals(warnStatus))
            warnStatus = null;
        if ("".equals(machineName))
            machineName = null;
        machines = machineService.getMonitoringMachineList(factoryId, areaId, machineStatusType, warnStatus,
                machineName, viewYn, accumulSearchTime);
        viewObj.put("machines", machines);
        return viewObj;
    }
}
