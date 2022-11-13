package kr.co.manager.exception.rdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.AlarmInfo;

@Repository
public interface AlarmInfoRepository extends JpaRepository<AlarmInfo, Integer>{
	
}
