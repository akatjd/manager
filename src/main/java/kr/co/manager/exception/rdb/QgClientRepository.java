package kr.co.manager.exception.rdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.QgClient;

@Repository
public interface QgClientRepository extends JpaRepository<QgClient, Integer>{
	
	@Query
	QgClient findByQgClientId(Integer qgClientId);
}
