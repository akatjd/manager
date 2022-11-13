package kr.co.manager.exception.rdb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.manager.domain.jpa.AlarmEventStatus;
import kr.co.manager.domain.jpa.QAlarmDictionary;
import kr.co.manager.domain.jpa.QAlarmEventStatus;
import kr.co.manager.domain.jpa.QAlarmType;
import kr.co.manager.domain.jpa.QArea;
import kr.co.manager.domain.jpa.QFactory;
import kr.co.manager.domain.jpa.QMachine;
import kr.co.manager.domain.jpa.QQgClient;

@Repository
public class AlarmEventStatusRepositorySupport extends QuerydslRepositorySupport {
	
	private static final Logger logger = LoggerFactory.getLogger(AlarmEventStatusRepositorySupport.class);
	
	private final JPAQueryFactory jpaQueryFactory;
	
	public AlarmEventStatusRepositorySupport(final JPAQueryFactory jpaQueryFactory) {
		super(AlarmEventStatus.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	QFactory f = QFactory.factory;
	QQgClient qc = QQgClient.qgClient;
	QMachine m = QMachine.machine;
	QArea a = QArea.area;
	QAlarmEventStatus aes = QAlarmEventStatus.alarmEventStatus;
	QAlarmDictionary ad = QAlarmDictionary.alarmDictionary;
	// 테이블에 alarmType 칼럼이 존재
	QAlarmType atp = QAlarmType.alarmType1;
	
	public List<Tuple> userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, String filter, Integer alarmType, String groupBy){
		List<Tuple> resultList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		resultList = jpaQueryFactory.select(
								a.areaId,
								a.areaName,
								m.machineId,
								m.machineName,
								aes.holdingTime.sum().as("total_holding_time"),
								new CaseBuilder() .when(aes.holdingTime.sum().eq(0)) .then(0L) .otherwise(aes.count()).as("cnt"),
								aes.alarmId,
								aes.alarmMsg
		)
		.from(f)
		.join(qc).on(f.factoryId.eq(qc.factory.factoryId))
		.join(m).on(qc.qgClientId.eq(m.qgClient.qgClientId))
		.join(a).on(m.areaId.eq(a.areaId))
		.join(aes).on(m.machineId.eq(aes.machineId).and(aes.regDate.between(sDate, eDate)).and(aes.updateDate.isNotNull()).and(eqAlarmType(alarmType)))
		.join(ad).on(aes.alarmId.eq(ad.alarmId).and(ad.useYn.eq("y")))
		.join(atp).on(ad.alarmTypeId.eq(atp.alarmTypeId).and(atp.useYn.eq("y")))
		.where(
				qc.factory.factoryId.eq(factoryId),
				eqAreaId(areaId),
				eqMachineId(machineId),
				m.viewYn.eq("y")
		)
		.groupBy(selGroupBy(groupBy))
		.orderBy(filter.equals("time") ? selOrderByTime(filter) : selOrderByCnt(filter))
		.fetch();
		
		return resultList;
	}
	
	public List<Tuple> userFindAlarmDetailListByFactoryIdAndAreaIdAndMachineIdAndRegDate(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, String filter, Integer alarmId){
		List<Tuple> resultList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		StringTemplate formattedDate = Expressions.stringTemplate(
		        "DATE_FORMAT({0}, {1})"
		        , aes.regDate
		        , ConstantImpl.create("%Y-%m-%d"));
		
		resultList = jpaQueryFactory.select(
								aes.machineId,
								aes.regDate,
								aes.updateDate,
								aes.alarmId,
								aes.machineAlarmType,
								aes.machineAlarmNo,
								aes.machineAlarmAxis,
								aes.alarmMsg,
								aes.holdingTime.sum().as("total_holding_time"),
								new CaseBuilder() .when(aes.holdingTime.sum().eq(0)) .then(0L) .otherwise(aes.count()).as("cnt"),
								formattedDate.as("date"),
								aes.alarmTypeId,
								atp.alarmType,
								m.machineName
		)
		.from(f)
		.join(qc).on(f.factoryId.eq(qc.factory.factoryId))
		.join(m).on(qc.qgClientId.eq(m.qgClient.qgClientId))
		.join(a).on(m.areaId.eq(a.areaId))
		.join(aes).on(m.machineId.eq(aes.machineId).and(aes.regDate.between(sDate, eDate)).and(aes.updateDate.isNotNull()))
		.join(ad).on(aes.alarmId.eq(ad.alarmId).and(eqAlarmId(alarmId)))
		.join(atp).on(ad.alarmTypeId.eq(atp.alarmTypeId))
		.where(
				f.factoryId.eq(factoryId),
				eqAreaId(areaId),
				eqMachineId(machineId),
				m.viewYn.eq("y"),
				atp.useYn.eq("y"),
				ad.useYn.eq("y")
		)
		.groupBy(m.machineId, aes.alarmId, formattedDate)
		.orderBy(filter.equals("total_holding_time") ? selOrderByTime(filter) : selOrderByCnt(filter), formattedDate.asc())
		.fetch();
		
		return resultList;
	}
	
	public List<Tuple> userFindAlarmMsgListByFactoryIdAndAreaIdAndMachineIdAndRegDate(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, String filter, String groupBy){
		List<Tuple> resultList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		StringTemplate formattedDate = Expressions.stringTemplate(
		        "DATE_FORMAT({0}, {1})"
		        , aes.regDate
		        , ConstantImpl.create("%Y-%m-%d"));
		
		resultList = jpaQueryFactory.select(
								aes.machineId,
								aes.regDate,
								aes.updateDate,
								aes.alarmId,
								aes.machineAlarmType,
								aes.machineAlarmNo,
								aes.machineAlarmAxis,
								aes.alarmMsg,
								aes.holdingTime.sum().as("total_holding_time"),
								aes.count().as("cnt"),
								formattedDate.as("date"),
								m.machineName
		)
		.from(f)
		.join(qc).on(f.factoryId.eq(qc.factory.factoryId))
		.join(m).on(qc.qgClientId.eq(m.qgClient.qgClientId))
		.join(a).on(m.areaId.eq(a.areaId))
		.join(aes).on(m.machineId.eq(aes.machineId).and(aes.regDate.between(sDate, eDate)).and(aes.updateDate.isNotNull()))
		.join(ad).on(aes.alarmId.eq(ad.alarmId).and(ad.useYn.eq("y")))
		.join(atp).on(ad.alarmTypeId.eq(atp.alarmTypeId).and(atp.useYn.eq("y")))
		.where(
				f.factoryId.eq(factoryId),
				eqAreaId(areaId),
				eqMachineId(machineId),
				m.viewYn.eq("y")
		)
		.groupBy(selGroupBy(groupBy), reverseSelGroupBy(groupBy))
		.orderBy(filter.equals("total_holding_time") ? selOrderByTime(filter) : selOrderByCnt(filter), aes.alarmId.asc(), formattedDate.asc())
		.fetch();
		
		return resultList;
	}
	
	public List<Tuple> userFindAlarmDetailCntListByFactoryIdAndAreaIdAndMachineIdAndRegDate(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate){
		List<Tuple> resultList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		StringTemplate formattedDate = Expressions.stringTemplate(
		        "DATE_FORMAT({0}, {1})"
		        , aes.regDate
		        , ConstantImpl.create("%Y-%m-%d"));
		
		resultList = jpaQueryFactory.select(
				aes.machineId,
				aes.regDate,
				aes.updateDate,
				aes.alarmId,
				aes.machineAlarmType,
				aes.machineAlarmNo,
				aes.machineAlarmAxis,
				ad.alarmMsgKr,
				aes.holdingTime.sum().as("total_holding_time"),
				aes.count().as("cnt"),
				formattedDate.as("date")
		)
		.from(f)
		.join(qc).on(f.factoryId.eq(qc.factory.factoryId))
		.join(m).on(qc.qgClientId.eq(m.qgClient.qgClientId))
		.join(a).on(m.areaId.eq(a.areaId))
		.join(aes).on(m.machineId.eq(aes.machineId).and(aes.regDate.between(sDate, eDate)).and(aes.updateDate.isNotNull()))
		.join(ad).on(aes.alarmId.eq(ad.alarmId).and(ad.useYn.eq("y")))
		.join(atp).on(ad.alarmTypeId.eq(atp.alarmTypeId).and(atp.useYn.eq("y")))
		.where(
				f.factoryId.eq(factoryId),
				eqAreaId(areaId),
				eqMachineId(machineId),
				m.viewYn.eq("y")
		)
		.groupBy(
				m.machineId,
				aes.alarmId,
				formattedDate
		)
		.orderBy(
				aes.count().desc(),
				formattedDate.asc()
		)
		.fetch();
		
		return resultList;
	}
	
	public List<Tuple> userFindAlarmHistoryByFactoryIdAndAreaIdAndMachineIdAndRegDate(Integer factoryId, Integer areaId, Integer machineId, String startDate, String endDate, Integer alarmTypeId){
		List<Tuple> resultList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		resultList = jpaQueryFactory.select(
				a.areaId,
				a.areaName,
				m.machineId,
				m.machineName,
				aes.regDate,
				aes.updateDate,
				aes.alarmId,
				aes.machineAlarmType,
				aes.machineAlarmNo,
				aes.machineAlarmAxis,
				aes.alarmMsg,
				atp.alarmType
		)
		.from(f)
		.join(qc).on(f.factoryId.eq(qc.factory.factoryId))
		.join(m).on(qc.qgClientId.eq(m.qgClient.qgClientId))
		.join(a).on(m.areaId.eq(a.areaId))
		.join(aes).on(m.machineId.eq(aes.machineId).and(aes.regDate.between(sDate, eDate)))
		.join(ad).on(aes.alarmId.eq(ad.alarmId).and(ad.useYn.eq("y")))
		.join(atp).on(ad.alarmTypeId.eq(atp.alarmTypeId).and(atp.useYn.eq("y")).and(eqAlarmTypeId(alarmTypeId)))
		.where(
				f.factoryId.eq(factoryId),
				eqAreaId(areaId),
				eqMachineId(machineId),
				m.viewYn.eq("y")
		)
		.orderBy(m.machineId.asc(), aes.regDate.asc(), aes.updateDate.asc())
		.fetch();
		
		return resultList;
	}
	
	// KMS 2022-02-16 명화 알람이력 데이터 api 송신용
	public List<Tuple> getAlarmHistoryList(Integer factoryId, String startDate, String endDate){
		List<Tuple> resultList = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		resultList = jpaQueryFactory.select(
				f.factoryId,
				f.factoryName,
				a.areaId,
				a.areaName,
				m.machineId,
				m.machineName,
				aes.regDate,
				aes.updateDate,
				atp.alarmTypeId,
				atp.alarmType,
				aes.alarmMsg,
				aes.alarmId,
				aes.alarmStatusId
		)
		.from(f)
		.join(qc).on(f.factoryId.eq(qc.factory.factoryId))
		.join(m).on(qc.qgClientId.eq(m.qgClient.qgClientId))
		.join(a).on(m.areaId.eq(a.areaId))
		.join(aes).on(m.machineId.eq(aes.machineId).and(aes.regDate.between(sDate, eDate)))
		.join(ad).on(aes.alarmId.eq(ad.alarmId).and(ad.useYn.eq("y")))
		.join(atp).on(ad.alarmTypeId.eq(atp.alarmTypeId).and(atp.useYn.eq("y")))
		.where(
				f.factoryId.eq(factoryId),
//				eqAreaId(areaId),
//				eqMachineId(machineId),
				m.viewYn.eq("y")
		)
//		.orderBy(m.machineId.asc(), aes.regDate.asc(), aes.updateDate.asc())
		// KMS 2022-03-16 명화 요청 머신 정렬 빼고 시작일, 종료일로만 소팅
		.orderBy(aes.regDate.asc(), aes.updateDate.asc())
		.fetch();
		
		return resultList;
	}
	
	// KMS 2022-03-03 명화 알람이력 데이터 api 송신용 (명화공업 factoryId 3, 4, 7, 8)
	public List<Tuple> getAlarmHistoryList(String startDate, String endDate){
		List<Tuple> resultList = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date sDate = null;
		Date eDate = null;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		resultList = jpaQueryFactory.select(
				f.factoryId,
				f.factoryName,
				a.areaId,
				a.areaName,
				m.machineId,
				m.machineName,
				aes.regDate,
				aes.updateDate,
				atp.alarmTypeId,
				atp.alarmType,
				aes.alarmMsg,
				aes.alarmId,
				aes.alarmStatusId
		)
		.from(f)
		.join(qc).on(f.factoryId.eq(qc.factory.factoryId))
		.join(m).on(qc.qgClientId.eq(m.qgClient.qgClientId))
		.join(a).on(m.areaId.eq(a.areaId))
		.join(aes).on(m.machineId.eq(aes.machineId).and(aes.regDate.between(sDate, eDate)))
		.join(ad).on(aes.alarmId.eq(ad.alarmId).and(ad.useYn.eq("y")))
		.join(atp).on(ad.alarmTypeId.eq(atp.alarmTypeId).and(atp.useYn.eq("y")))
		.where(
				f.factoryId.in(3, 4, 7, 8),
				m.viewYn.eq("y")
		)
//		.orderBy(m.machineId.asc(), aes.regDate.asc(), aes.updateDate.asc())
		// KMS 2022-03-16 명화 요청 머신 정렬 빼고 시작일, 종료일로만 소팅
		.orderBy(aes.regDate.asc(), aes.updateDate.asc())
		.fetch();
		
		return resultList;
	}
	
	private BooleanExpression eqAlarmType(Integer alarmType) {
		if(alarmType == null) {
			return null;
		}
		return aes.alarmTypeId.eq(alarmType);
	}
	
	private BooleanExpression eqAlarmId(Integer alarmId) {
		if(alarmId == null) {
			return null;
		}
		return ad.alarmId.eq(alarmId);
	}
	
	private BooleanExpression eqAreaId(Integer areaId) {
		if(areaId == null) {
			return null;
		}
		return a.areaId.eq(areaId);
	}
	
	private BooleanExpression eqMachineId(Integer machineId) {
		if(machineId == null) {
			return null;
		}
		return m.machineId.eq(machineId);
	}
	
	private BooleanExpression eqAlarmTypeId(Integer alarmTypeId) {
		if(alarmTypeId == null) {
			return null;
		}
		return atp.alarmTypeId.eq(alarmTypeId);
	}
	
	private OrderSpecifier<Integer> selOrderByTime(String filter) {
		return aes.holdingTime.sum().desc();
	}
	
	private OrderSpecifier<Long> selOrderByCnt(String filter) {
		return aes.holdingTime.count().desc();
	}
	
	private NumberPath<Integer> selGroupBy(String groupBy) {
		if(groupBy.equals("machineId")) {
			return m.machineId;
		}else {
			return aes.alarmId;
		}
	}
	
	private NumberPath<Integer> reverseSelGroupBy(String groupBy) {
		if(groupBy.equals("machineId")) {
			return aes.alarmId;
		}else {
			return m.machineId;
		}
	}
	
}
