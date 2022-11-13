package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.manager.domain.jpa.Area;
import kr.co.manager.domain.jpa.Factory;

public interface AreaRepository extends JpaRepository<Area, Integer>{

	List<Area> findByFactory(Factory factory, Sort sort);
	
	@Query
	Area findByAreaId(Integer areaId);
}
