package kr.co.manager.exception.rdb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.manager.domain.jpa.Machine;
import kr.co.manager.domain.jpa.QFactory;
import kr.co.manager.domain.jpa.QInspectionPolicy;
import kr.co.manager.domain.jpa.QInspectionPolicyGroup;
import kr.co.manager.domain.jpa.QMachine;
import kr.co.manager.domain.jpa.QMachiningSection;
import kr.co.manager.domain.jpa.QProduct;
import kr.co.manager.domain.jpa.QQgClient;

@Repository
public class MachineRepositorySupport extends QuerydslRepositorySupport{
	
	private final JPAQueryFactory jpaQueryFactory;
	
	public MachineRepositorySupport(final JPAQueryFactory jpaQueryFactory) {
		super(Machine.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	QMachine m = QMachine.machine;
	QQgClient qc = QQgClient.qgClient;
	QFactory f = QFactory.factory;
	QProduct p = QProduct.product;
	
	QMachiningSection ms = QMachiningSection.machiningSection;
	QInspectionPolicy i = QInspectionPolicy.inspectionPolicy;
	QInspectionPolicyGroup ig = QInspectionPolicyGroup.inspectionPolicyGroup;
	
	public List<Tuple> userfindByMonitoringMachineList(Integer factoryId, Integer areaId, String machineStatusType, String warnStatus, String machineName, String viewYn){
		List<Tuple> resultList = new ArrayList<>();
		
//		jpaQueryFactory.select(
//		
//		)
//		.from(m)
//		.join(qc).on(m.qgClient.qgClientId.eq(qc.qgClientId))
//		.join(f).on(qc.factory.factoryId.eq(f.factoryId))
//		.leftJoin(p).on(m.currentProductId.eq(p.productId)) // left outer join 되는지 확인 필요
//		.leftJoin(	
//				
//				
//				JPAExpressions.select(
//						ig.productId,
//						i.inspectionPolicyId.count().as("insCnt")
//				)
//				.from(ig)
//				.leftJoin(ms).on(ig.machiningSectionId.eq(ms.machiningSectionId))
//				.leftJoin(i).on(ig.inspectionGroupId.eq(i.inspectionGroup.inspectionGroupId))
//				.where(
//						i.activeYn.eq("y"),
//						ms.activeYn.eq("y")
//				)
//				.groupBy(ig.productId)
//				
//		)
//		.where()
//		.fetch();
		
		
		return resultList;
	}
	
	public List<Tuple> findFactoryIdAreaId(Integer factoryId, Integer areaId){
		List<Tuple> resultList = new ArrayList<>();
		
		
		
		return resultList;
	}
	
	
}
