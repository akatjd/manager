package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.DowntimeSchedule;

@Repository
public interface DownTimeScheduleRepository extends JpaRepository<DowntimeSchedule, Integer>{
	
	List<DowntimeSchedule> findByFactoryId(Integer factoryId);
	
	@Query(nativeQuery = true, value = 
		"SELECT                                                                                     \r\n" +
		"			     f.factory_name, ds.*                                                       \r\n" +
		"			 FROM                                                                           \r\n" +
		"			     factory f                                                                  \r\n" +
		"			     JOIN                                                                       \r\n" +
		"			     downtime_schedule ds ON f.factory_id = ds.factory_id                       \r\n" +
		"			 WHERE                                                                          \r\n" +
		"			 	(f.factory_id = :factoryId OR :factoryId = 1 OR :factoryId IS NULL)         \r\n" +
		"			 ;                                                                              \r\n")
	List<Object[]> userFindDownTimeScheduleListByFactoryId(@Param("factoryId") Integer factoryId);
	
	@Query(nativeQuery = true, value = "select \r\n"
			+ "    *\r\n"
			+ "from\r\n"
			+ "    downtime_schedule\r\n"
			+ "where\r\n"
			+ "	factory_id = :factoryId\r\n"
			+ "    and\r\n"
			+ "    schedule_use_yn = 'y'\r\n"
			+ "    and\r\n"
			+ "    auto_use_yn = 'y'\r\n"
			+ "    order by day_or_night, etc1\r\n"
			+ ";")
	List<Object[]> userFindAutoScheduleListByFactoryId(@Param("factoryId") Integer factoryId);
	
	DowntimeSchedule findByDtSeq(Integer dtSeq);
}
