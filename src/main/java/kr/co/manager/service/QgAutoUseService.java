package kr.co.manager.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.manager.domain.view.QgAutoUseView;
import kr.co.manager.exception.rdb.DownTimeScheduleRepository;
import kr.co.manager.exception.rdb.ItemCtRepository;
import kr.co.manager.exception.rdb.MachineRepository;
import kr.co.manager.exception.rdb.ProductRepository;
import kr.co.manager.exception.rdb.QgClientRepository;
import kr.co.manager.exception.rdb.SchedulePolicyRepository;

@Service
public class QgAutoUseService {

    private final static Logger logger = LoggerFactory.getLogger(QgAutoUseService.class);

    @Autowired
    SchedulePolicyRepository schedulePolicyRepository;

    @Autowired
    DownTimeScheduleRepository downTimeScheduleRepository;

    @Autowired
    ItemCtRepository itemCtRepository;
    
    @Autowired
    QgClientRepository qgClientRepository;
    
    @Autowired
    MachineRepository machineRepository; 
    
    @Autowired
    ProductRepository productRepository;
    
    
    public List<HashMap<String, Object>> getAutoUseViewList(Integer factoryId, Integer areaId, Integer machineId,
            String startDate, String endDate) throws ParseException {
        // gapDays 구하기
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date firstDate = format.parse(startDate);
        Date secondDate = format.parse(endDate);
        long calDate = firstDate.getTime() - secondDate.getTime(); 
        long gapDays = calDate / (24*60*60*1000); 
        gapDays = Math.abs(gapDays) + 1;
        List<Object[]> downtimeScheduleList = downTimeScheduleRepository.userFindAutoScheduleListByFactoryId(factoryId);
        List<HashMap<String, Object>> dtScheduleMapList = new ArrayList<>();
        for(Object[] dtScheduleObjArr : downtimeScheduleList) {
            HashMap<String, Object> dtScheduleMap = new HashMap<>();
            dtScheduleMap.put("dtSeq", Integer.parseInt(dtScheduleObjArr[0].toString()));
            dtScheduleMap.put("dtScheduleName", dtScheduleObjArr[2].toString());
            dtScheduleMap.put("dtStartTime", dtScheduleObjArr[3].toString());
            dtScheduleMap.put("dtEndTime", dtScheduleObjArr[4].toString());
            dtScheduleMap.put("dayOrNight", dtScheduleObjArr[7].toString());
            dtScheduleMapList.add(dtScheduleMap);
        }
        for(HashMap<String, Object> dtMap : dtScheduleMapList) {
            String dtStartTime = dtMap.get("dtStartTime").toString();
            dtStartTime = dtStartTime.replace(":", "");
            String dtEndTime = dtMap.get("dtEndTime").toString();
            dtEndTime = dtEndTime.replace(":", "");
            String dtStartDate = startDate.replace("-", "");
            String dtEndDate = endDate.replace("-", "");
            dtStartDate = dtStartDate + dtStartTime + "00";
            dtEndDate = dtEndDate + dtEndTime + "00";
            // dtStartTime, dtEndTime ex) 1200, 1300 쿼리로 직접돌릴때는 120000, 130000 초까지 넣어줘야 값 나옴
            List<Object[]> dtCntList = itemCtRepository.userFindDtList(factoryId, areaId, machineId, dtStartDate, dtEndDate, dtStartTime, dtEndTime);
            List<Integer> dtMachineIdList = new ArrayList<>();
            // machineId list 추출
            for(Object[] dtCntObjArr : dtCntList) {
                Integer dtMachineId = Integer.parseInt(dtCntObjArr[0].toString());
                dtMachineIdList.add(dtMachineId);
            }
            dtMachineIdList = dtMachineIdList.stream().distinct().collect(Collectors.toList());
            List<HashMap<String, Object>> machineDataMapList = new ArrayList<>();
            // machineId 별 totalAutoCnt, rate 계산
            for(Integer dtMachineId : dtMachineIdList) {
                HashMap<String, Object> machineDataMap = new HashMap<>();
                Integer totalAutoCnt = 0;
                double totalRate = 0;
                double resultRate = 0;
                for(Object[] dtCntObjArr : dtCntList) {
                    Integer dtCntMachineId = Integer.parseInt(dtCntObjArr[0].toString());
                    if(dtCntMachineId.equals(dtMachineId)) {
                        Integer dtCntAutoCnt = Integer.parseInt(dtCntObjArr[2].toString());
                        totalAutoCnt += dtCntAutoCnt;
                        logger.info("dtCntAutoCnt :: {}", dtCntAutoCnt);
                        logger.info("totalAutoCnt :: {}", totalAutoCnt);
                        Integer dtStaticCt = 100;
                        if(dtCntObjArr[5] != null) {
                            dtStaticCt = (int) Float.parseFloat(dtCntObjArr[5].toString());
                            if(dtStaticCt == 0) {
                                dtStaticCt = 100;
                            }
                        }
                        // date format
                        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
                        //요청시간을 Date로 parsing 후 time가져오기
                        Date reqStartDate = dateFormat.parse(dtStartDate);
                        long reqStartDateTime = reqStartDate.getTime();
                        Date reqEndDate = dateFormat.parse(dtEndDate);
                        long reqEndDateTime = reqEndDate.getTime();
                        //분으로 표현
                        long gapMinute = (reqEndDateTime - reqStartDateTime) / 60000;
                        double dtCntRate = 0;
                        if(dtCntAutoCnt != 0) {
                            dtCntRate = dtCntAutoCnt / ((3600/dtStaticCt) * ((double)gapMinute/60));
                        }
                        totalRate += dtCntRate;
                        
                        machineDataMap.put("machineId", dtCntMachineId);
                        machineDataMap.put("machineName", dtCntObjArr[1].toString());
                        machineDataMap.put("areaName", dtCntObjArr[6].toString());
                    }
                }
                resultRate = (totalRate / gapDays) * 100;
                DecimalFormat df = new DecimalFormat("0.00");
                resultRate = Double.parseDouble(df.format(resultRate));
                machineDataMap.put("totalAutoCnt", totalAutoCnt);
                machineDataMap.put("resultRate", resultRate);
                machineDataMapList.add(machineDataMap);
            }
            dtMap.put("dtDatas", machineDataMapList);
        }
        return dtScheduleMapList;
    }

    // 무인가동 라벨 데이터
    public List<QgAutoUseView> getAutoUseLabelList(Integer factoryId) {
        List<QgAutoUseView> labelList = new ArrayList<>();
        List<Object[]> scheduleObjectList = schedulePolicyRepository.userFindScheduleByFactoryId(factoryId);
        Integer dayBeginHour = 0;
        Integer dayBeginMin = 0;
        Integer dayEndHour = 0;
        Integer dayEndMin = 0;
        String day = "주간";
        String night = "야간";
        for (Object[] scheduleObject : scheduleObjectList) {
            String dayBeginTime = scheduleObject[9].toString();
            String dayEndTime = scheduleObject[10].toString();

            dayBeginHour = Integer.parseInt(dayBeginTime.substring(0, 2));
            dayBeginMin = Integer.parseInt(dayBeginTime.substring(3, 5));
            dayEndHour = Integer.parseInt(dayEndTime.substring(0, 2));
            dayEndMin = Integer.parseInt(dayEndTime.substring(3, 5));
        }
        List<Object[]> downtimeObjectList = downTimeScheduleRepository.userFindAutoScheduleListByFactoryId(factoryId);
        List<String> scheduleNameArr = new ArrayList<>();
        List<String> startTimeArr = new ArrayList<>();
        List<String> endTimeArr = new ArrayList<>();
        for (Object[] downtimeObject : downtimeObjectList) {
            QgAutoUseView autoUseView = new QgAutoUseView();
            String scheduleName = downtimeObject[2].toString();
            String startTime = downtimeObject[3].toString();
            String endTime = downtimeObject[4].toString();
            Integer startHour = Integer.parseInt(startTime.substring(0, 2));
            Integer startMin = Integer.parseInt(startTime.substring(3, 5));
            Integer endHour = Integer.parseInt(endTime.substring(0, 2));
            Integer endMin = Integer.parseInt(endTime.substring(3, 5));
            if (startHour > dayBeginHour && startHour < dayEndHour) {
                autoUseView.setDayOrNight(day);
            } else if (startHour == dayBeginHour) {
                if (startMin > dayBeginMin) {
                    autoUseView.setDayOrNight(day);
                } else {
                    autoUseView.setDayOrNight(night);
                }
            } else if (startHour == dayEndHour) {
                if (startMin < dayEndMin) {
                    autoUseView.setDayOrNight(day);
                } else {
                    autoUseView.setDayOrNight(night);
                }
            } else if (endHour > dayBeginHour && endHour < dayEndHour) {
                autoUseView.setDayOrNight(day);
            } else if (endHour == dayBeginHour) {
                if (endMin > dayBeginMin) {
                    autoUseView.setDayOrNight(day);
                } else {
                    autoUseView.setDayOrNight(night);
                }
            } else if (endHour == dayEndHour) {
                if (endMin < dayEndMin) {
                    autoUseView.setDayOrNight(day);
                } else {
                    autoUseView.setDayOrNight(night);
                }
            } else {
                autoUseView.setDayOrNight(night);
            }
            scheduleNameArr.add(scheduleName);
            startTimeArr.add(startTime);
            endTimeArr.add(endTime);
            autoUseView.setScheduleName(scheduleName);
            autoUseView.setScheduleStartTime(startTime);
            autoUseView.setScheduleEndTime(endTime);
            labelList.add(autoUseView);
        }
        return labelList;
    }
}
