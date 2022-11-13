package kr.co.manager.exception.rdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.Factory;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Integer>{
	
	@Query
	Factory findByFactoryId(Integer factoryId);
}
