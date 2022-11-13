package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.manager.domain.jpa.DowntimeSchedule;
import kr.co.manager.domain.jpa.QDowntimeSchedule;

@Repository
public class DownTimeScheduleRepositorySupport extends QuerydslRepositorySupport{
	
	private final JPAQueryFactory jpaQueryFactory;
	
	public DownTimeScheduleRepositorySupport(final JPAQueryFactory jpaQueryFactory) {
		super(DowntimeSchedule.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	public List<DowntimeSchedule> findByDtSeq(final Integer dtSeq){
		
		QDowntimeSchedule downtimeSchedule = QDowntimeSchedule.downtimeSchedule;
		
		return jpaQueryFactory.selectFrom(downtimeSchedule).where(downtimeSchedule.dtSeq.eq(1)).fetch();
	}
}
