package kr.co.manager.util;

import java.util.HashMap;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import kr.co.manager.domain.jpa.Machine;
import kr.co.manager.domain.view.MachineView;

public class ProcessUtil {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ProcessUtil.class);


	public static HashMap<String, Integer> getTotalStatus(List<Machine> mList, Integer totalCnt){
		HashMap<String, Integer> tStatus = new HashMap<String, Integer>();
		int runCnt = 0;
		int holdCnt = 0;
		int disconnectCnt = 0;
		int stopCnt = 0;
		int settingCnt = 0;		
		int offCnt=0;
		for (Machine m : mList) {
			if (m.getMachineStatusType() != null) {
				switch (m.getMachineStatusType()) {
				case run:
					runCnt++;
					break;
				case hold:
					holdCnt++;
					break;
				case dis:
					disconnectCnt++;
					break;
				case stop:
					stopCnt++;
					break;
				case setting:
					settingCnt++;
					break;
				case off:
					offCnt++;
					break;
				case tc:
					settingCnt++;
					break;
				case cl:
					settingCnt++;
						break;
				case gc:
					settingCnt++;
					break;
				case mt:
					settingCnt++;
					break;
				default:
					break;
				}
			} else {
				logger.info(m.getMachineName() + "'status is null.");
			}			
		}
		tStatus.put("runCnt", runCnt);
		tStatus.put("holdCnt", holdCnt);
		tStatus.put("disconnectCnt", disconnectCnt);
		tStatus.put("stopCnt", stopCnt);
		tStatus.put("offCnt", offCnt);
		tStatus.put("settingCnt", settingCnt);
		tStatus.put("totalCnt", totalCnt);
		return tStatus;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 각 machine상태를 조회하여 카운팅
	 * 2. 처리내용 : machine들의 상태를 비교하여 각 상태값을 증가 시킴
	 * </pre>
	 * @Method Name : getTotalStatusMachineView
	 * @date : 2017. 8. 17.
	 * @author : Jinsung
	 * @history : 
	 *  -----------------------------------------------------------------------
	 *  Date                Author                        Note
	 *  ----------- ------------------- ---------------------------------------
	 *  2017. 8. 17.     Jinsung             최초 작성 
	 *  -----------------------------------------------------------------------
	 * 
	 * @param  mList                       machine_view List
	 * @param  totalCnt                    machine 총 갯수
	 * @return HashMap<String, Integer>    각 상태에 대한 카운팅 정보
	 */
	public static HashMap<String, Integer> getTotalStatusMachineView(List<MachineView> mList, Integer totalCnt){
		HashMap<String, Integer> tStatus = new HashMap<String, Integer>();
		int runCnt = 0;
		int holdCnt = 0;
		int disconnectCnt = 0;
		int stopCnt = 0;
		int settingCnt = 0;		
		int offCnt=0;
		for (MachineView m : mList) {
			if (m.getMachineStatusType() != null) {
				switch (m.getMachineStatusType()) {
				case run:
					runCnt++;
					break;
				case hold:
					holdCnt++;
					break;
				case dis:
					disconnectCnt++;
					break;
				case stop:
					stopCnt++;
					break;
				case setting:
					settingCnt++;
					break;
				case off:
					offCnt++;
					break;
				case tc:
					settingCnt++;
					break;
				case cl:
					settingCnt++;
						break;
				case gc:
					settingCnt++;
					break;
				case mt:
					settingCnt++;
					break;
				default:
					break;
				}
			} else {
				logger.info(m.getMachineName() + "'status is null.");
			}			
		}
		tStatus.put("runCnt", runCnt);
		tStatus.put("holdCnt", holdCnt);
		tStatus.put("disconnectCnt", disconnectCnt);
		tStatus.put("stopCnt", stopCnt);
		tStatus.put("offCnt", offCnt);
		tStatus.put("settingCnt", settingCnt);
		tStatus.put("totalCnt", totalCnt);
		return tStatus;
	}

	public static double[] copyFromIntArray(Integer[] source) {
		double[] dest = new double[source.length];
		for (int i = 0; i < source.length; i++) {
			dest[i] = source[i];
		}
		return dest;
	}

//	public static List<Integer> getInspectionSection(Integer[] loadMeterArray) {
//
//		double[] doubles = copyFromIntArray(loadMeterArray);
//		int mean = (int) StatUtils.mean(doubles, 20, doubles.length - 40);
//		int std = (int) Math.sqrt(StatUtils.variance(doubles, 20, doubles.length - 40));
//		System.out.println("mean: " + mean);
//		System.out.println("std: " + std);
//		System.out.println("Size:: " + loadMeterArray.length);
//		Map<Integer, String> pointStatus = new HashMap<>();
//		int inspectionStartPoint = 0;
//		int inspectionEndPoint = 0;
//		boolean isFirstDeceleration = false;
//		int lastDeceleration = 0;
//		int lastNoMovement = 0;
//		List<Integer> lastDecelerationList = new ArrayList<>();
//		List<Integer> statablePointList = new ArrayList<>();
//		String lastStatus = "";
//		//변화율 array 뒷밸류 - 앞밸류
//		Integer[] accValueArray = new Integer[loadMeterArray.length - 1];
//		for (int i = 0; i < loadMeterArray.length - 1; i++) {
//			accValueArray[i] = loadMeterArray[i + 1] - loadMeterArray[i];
//		}
//		//제로 구간 찾기
//		int zeroCount = 0;
//
//		//급가속 구강 찾기
//		for (int i = 0; i < accValueArray.length; i++) {
//			if (accValueArray[i] > 1500) {
//				System.out.println("급상승 구간: " + i);
//				lastStatus = "급상승";
//				pointStatus.put(i, "급상승");
//			}
//			if (accValueArray[i] < -1500) {
//				System.out.println("급하강 구간: " + i);
//				lastStatus = "급하강";
//				pointStatus.put(i, "급하강");
//				if (inspectionStartPoint == 0) {
//					isFirstDeceleration = true;
//				}
//				if (i - lastDeceleration > 2) {
//					lastDeceleration = i;
//				}
//			}
//			if (i > 0 && i + 2 < accValueArray.length && accValueArray[i - 1] < 10 && accValueArray[i] + accValueArray[i + 1] + accValueArray[i + 2] > std / 40) {
//				System.out.println("변곡점: " + i);
//				lastStatus = "변곡점";
//				pointStatus.put(i, "변곡점");
//				if (isFirstDeceleration)
//					inspectionStartPoint = i;
//				isFirstDeceleration = false;
//				lastDecelerationList.add(0, i);
//			}
//			if (i > 0 && i + 2 < accValueArray.length && Math.abs(accValueArray[i]) + Math.abs(accValueArray[i + 1]) + Math.abs(accValueArray[i + 2]) < std / 20) {
//				statablePointList.add(0, i);
//				if(!pointStatus.containsKey(i)){
//					System.out.println("안정구간: " + i);
//					pointStatus.put(i, "안정구간");
//				}
//			}
//			if (accValueArray.length*0.25 < i && Math.abs(accValueArray[i]) + Math.abs(accValueArray[i  -1]) + Math.abs(accValueArray[i  -2]) < 4) {
//
//				if(!pointStatus.containsKey(i)){
//					System.out.println("무변화구간: " + i);
//					pointStatus.put(i, "무변화구간");
//				}
//
//				if (i - lastNoMovement > 2) {
//
//					lastNoMovement = i;
//				}
//			}
//		}

		//앞검색
//		int lastDeaccPoint = 0;
//		int lastChangePoint = 0;
//		for (int i = 0; i < 60; i++) {
//			if (pointStatus.get(i) != null) {
//				if (pointStatus.get(i).equals("급하강"))
//					lastDeaccPoint = i;
//			}
//		}
//		if (lastDeaccPoint == 0) {
//			System.out.println("앞구간 급하강 구간 없음");
//			for (int j = 0; j < 60; j++) {
//				if(pointStatus.get(j) != null && pointStatus.get(j).equals("변곡점")){
//					inspectionStartPoint = j;
//					break;
//				}
//			}
//			inspectionStartPoint = lastChangePoint;
//		} else {
//			System.out.println("앞구간 급하강 구간 있음: "+ lastDeaccPoint);
//			for (int j = lastDeaccPoint; j < accValueArray.length; j++) {
//				if (pointStatus.get(j) != null && pointStatus.get(j).equals("변곡점")){
//					inspectionStartPoint = j;
//					break;
//				}
//			}
////			inspectionStartPoint = lastDeaccPoint;
//		}
//		if (lastChangePoint == 0 && lastDeaccPoint == 0)
//			inspectionStartPoint = 5;
//
//		//뒷검색
//		lastDeaccPoint = 0;
//		lastChangePoint = 0;
//		if (lastNoMovement != 0) {
//			System.out.println("무변화 구간 있음: "+ lastNoMovement);
//			for (int i = lastNoMovement; i > inspectionStartPoint; i--) {
//				if (pointStatus.get(i) != null) {
//					if (pointStatus.get(i).equals("급상승")){
//						lastDeaccPoint = i;
//						break;
//					}
//
////                    if (pointStatus.get(i).equals("변곡점"))
////                        lastChangePoint = i;
//				}
//			}
//			if (lastDeaccPoint == 0) {
//				System.out.println("무변화있음 / 급상승 없음");
//				inspectionEndPoint = lastChangePoint;
//			} else {
//				System.out.println("무변화있음 / 급상승 있음: "+lastDeaccPoint);
//				for (int j = lastDeaccPoint; j > lastDeaccPoint - 30; j--) {
//					if (pointStatus.get(j) != null && pointStatus.get(j).equals("변곡점")){
//						inspectionEndPoint = j;
//						break;
//					}
//				}
//
//
//			}
//
//		} else {
//			System.out.println("무변화 구간 없");
//			lastDeaccPoint = 0;
//			lastChangePoint = 0;
//			for (int i = accValueArray.length; i > inspectionStartPoint; i--) {
//				if (pointStatus.get(i) != null) {
//					if (pointStatus.get(i).equals("급상승")){
//						lastDeaccPoint = i;
//						break;
//					}
////                    if (pointStatus.get(i).equals("변곡점"))
////                        lastChangePoint = i;
//				}
//			}
//			if (lastDeaccPoint == 0) {
//				System.out.println("뒷구간 급상승구간 없음");
//				inspectionEndPoint = lastChangePoint;
//
//			} else {
//				System.out.println("뒷구간 급상승구간 있음:" +lastDeaccPoint);
//				for (int j = lastDeaccPoint; j > lastDeaccPoint - 30; j--) {
//					if (pointStatus.get(j) != null && pointStatus.get(j).equals("변곡점")){
//						inspectionEndPoint = j;
//						break;
//					}
//				}
////				inspectionEndPoint = lastDeaccPoint;
//			}
//		}
//
////		List<Integer> loadMeterList = Arrays.asList(loadMeterArray);
////		List<Integer> subLoadMeterList = loadMeterList.subList(inspectionEndPoint-30,inspectionEndPoint);
//		if(inspectionEndPoint - inspectionStartPoint > 40){
//			double subMean = StatUtils.mean(doubles,inspectionEndPoint-30, 30);
//			double subStd = Math.sqrt(StatUtils.variance(doubles,inspectionEndPoint-30, 30));
//			if(subMean < mean/2 && subStd < std/4){
//				inspectionEndPoint = inspectionEndPoint-30;
//			}
//		}
//		System.out.println("inspectionStartPoint:" + inspectionStartPoint);
//		System.out.println("inspectionEndPoint:" + inspectionEndPoint);
//
//
//
////		int[] result = {inspectionStartPoint,inspectionEndPoint};
//		List<Integer> result = new ArrayList<>();
//		result.add(inspectionStartPoint);
//		result.add(inspectionEndPoint);
//		return result;
//	}

}
