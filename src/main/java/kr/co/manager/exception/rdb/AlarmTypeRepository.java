package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.manager.domain.jpa.AlarmType;

public interface AlarmTypeRepository extends JpaRepository<AlarmType, Integer> {

	List<AlarmType> findByFactoryId(Integer factoryId);
}
