package kr.co.manager.exception.rdb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.manager.domain.jpa.ProductGcode;
import kr.co.manager.domain.jpa.QProductGcode;

@Repository
public class ProductGcodeRepositorySupport extends QuerydslRepositorySupport {
	
	private final JPAQueryFactory jpaQueryFactory;
	
	public ProductGcodeRepositorySupport(final JPAQueryFactory jpaQueryFactory) {
		super(ProductGcode.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	QProductGcode pg = QProductGcode.productGcode;

	public List<ProductGcode> userFindByMachineIdAndProductIdAndPath(Integer machineId, Integer productId, Integer path){
		List<ProductGcode> resultList = new ArrayList<>();
		
//		jpaQueryFactory.select(
//				
//		)
//		.from(
//			ExpressionUtils.as(
//					JPAExpressions.select(
//							pg.machineId,
//							pg.product.productId,
//							pg.path,
//							pg.changedDate.max(),
//							pg.oid
//					)
//					.from(pg)
//					.where(pg.machineId.eq(machineId).and(pg.product.productId.eq(productId)).and(pg.path.eq(path)))
//					.groupBy(pg.machineId, pg.product.productId, pg.path, pg.oid)
//					, null
//			)
//			
//		)
		
		return resultList;
	}
}
