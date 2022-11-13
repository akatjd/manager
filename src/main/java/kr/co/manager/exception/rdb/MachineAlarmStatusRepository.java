package kr.co.manager.exception.rdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.MachineAlarmStatus;

@Repository
public interface MachineAlarmStatusRepository extends JpaRepository<MachineAlarmStatus, Integer>{
	
	// alarm chart time data
//	@Query(nativeQuery = true, value = " SELECT                                                                                                        \r\n" +
//			"     a.area_id, a.area_name, m.machine_id, m.machine_name, SUM(mas.holding_time), COUNT(*)                                                \r\n" +
//			" FROM                                                                                                                                     \r\n" +
//			"     factory f                                                                                                                            \r\n" +
//			" JOIN                                                                                                                                     \r\n" +
//			" 	  qg_client qc ON f.factory_id = qc.factory_id                                                                                         \r\n" +
//			" JOIN                                                                                                                                     \r\n" +
//			" 	  machine m ON qc.qg_client_id = m.qg_client_id                                                                                        \r\n" +
//			" JOIN                                                                                                                                     \r\n" +
//			" 	  area a ON m.area_id = a.area_id                                                                                                      \r\n" +
//			" LEFT JOIN                                                                                                                                \r\n" +
//			" 	  machine_alarm_status mas ON m.machine_id = mas.machine_id AND mas.reg_date > :startDate AND mas.reg_date < :endDate                  \r\n" +
//			" WHERE                                                                                                                                    \r\n" +
//			" 	  f.factory_id = :factoryId                                                                                                            \r\n" +
//			"     AND                                                                                                                                  \r\n" +
//			"     (a.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)                                                                              \r\n" +
//			"     AND                                                                                                                                  \r\n" +
//			"     (m.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)                                                                  \r\n" +
//			"     AND                                                                                                                                  \r\n" +
//			"     m.view_yn = 'y'                                                                                                                      \r\n" +
//			" GROUP BY                                                                                                                                 \r\n" +
//			" 	  m.machine_id                                                                                                                         \r\n" +
//			" ORDER BY                                                                                                                                 \r\n" +
//			" 	  SUM(mas.holding_time) desc                                                                                                                         \r\n" +
//			" ;                                                                                                                                        \r\n")
//	List<Object[]> userFindAlarmMachineListByFactoryIdAndAreaIdAndMachineIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	// alarm chart cnt data
//	@Query(nativeQuery = true, value = " SELECT                                                                                                        \r\n" +
//			"     a.area_id, a.area_name, m.machine_id, m.machine_name, SUM(mas.holding_time), IF(SUM(mas.holding_time) IS NULL, 0, COUNT(*)) as cnt                                                \r\n" +
//			" FROM                                                                                                                                     \r\n" +
//			"     factory f                                                                                                                            \r\n" +
//			" JOIN                                                                                                                                     \r\n" +
//			" 	  qg_client qc ON f.factory_id = qc.factory_id                                                                                         \r\n" +
//			" JOIN                                                                                                                                     \r\n" +
//			" 	  machine m ON qc.qg_client_id = m.qg_client_id                                                                                        \r\n" +
//			" JOIN                                                                                                                                     \r\n" +
//			" 	  area a ON m.area_id = a.area_id                                                                                                      \r\n" +
//			" LEFT JOIN                                                                                                                                \r\n" +
//			" 	  machine_alarm_status mas ON m.machine_id = mas.machine_id AND mas.reg_date > :startDate AND mas.reg_date < :endDate                  \r\n" +
//			" WHERE                                                                                                                                    \r\n" +
//			" 	  f.factory_id = :factoryId                                                                                                            \r\n" +
//			"     AND                                                                                                                                  \r\n" +
//			"     (a.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)                                                                              \r\n" +
//			"     AND                                                                                                                                  \r\n" +
//			"     (m.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)                                                                  \r\n" +
//			"     AND                                                                                                                                  \r\n" +
//			"     m.view_yn = 'y'                                                                                                                      \r\n" +
//			" GROUP BY                                                                                                                                 \r\n" +
//			" 	  m.machine_id                                                                                                                         \r\n" +
//			" ORDER BY                                                                                                                                 \r\n" +
//			" 	  cnt desc                                                                                                                         \r\n" +
//			" ;                                                                                                                                        \r\n")
//	List<Object[]> userFindAlarmMachineCntListByFactoryIdAndAreaIdAndMachineIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
