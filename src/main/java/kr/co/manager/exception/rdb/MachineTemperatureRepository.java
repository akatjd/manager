package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.manager.domain.jpa.MachineTemperature;

public interface MachineTemperatureRepository extends JpaRepository<MachineTemperature, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "	MIN(MT.temp),\r\n"
			+ "	AVG(MT.temp),\r\n"
			+ "	MAX(MT.temp),\r\n"
			+ "	MT.machine_id,\r\n"
			+ "	M.machine_name,\r\n"
			+ "	A.area_name\r\n"
			+ "FROM\r\n"
			+ "	machine_temperature MT\r\n"
			+ "	JOIN\r\n"
			+ "	machine M ON MT.machine_id = M.machine_id\r\n"
			+ "	JOIN\r\n"
			+ "	area A ON M.area_id = A.area_id\r\n"
			+ "	JOIN\r\n"
			+ "	qg_client QC ON M.qg_client_id = QC.qg_client_id\r\n"
			+ "	JOIN\r\n"
			+ "	factory F ON QC.factory_id = F.factory_id\r\n"
			+ "WHERE\r\n"
			+ "	F.factory_id = :factoryId\r\n"
			+ "	AND\r\n"
			+ "	(M.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)\r\n"
			+ "	AND\r\n"
			+ "	(M.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)\r\n"
			+ "	AND\r\n"
			+ "	MT.reg_date > :startDate\r\n"
			+ "	AND\r\n"
			+ "	MT.reg_date < :endDate\r\n"
			+ "GROUP BY MT.machine_id\r\n"
			+ "ORDER BY M.sort_seq\r\n"
			+ ";")
	List<Object[]> userFindTemperatureViewList(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "	m.machine_id,\r\n"
			+ "	m.machine_name,\r\n"
			+ "	mt.temp,\r\n"
			+ "	mt.reg_date\r\n"
			+ "FROM\r\n"
			+ "	factory f\r\n"
			+ "	JOIN\r\n"
			+ "	qg_client qc ON f.factory_id = qc.factory_id\r\n"
			+ "	JOIN\r\n"
			+ "	machine m ON qc.qg_client_id = m.qg_client_id\r\n"
			+ "	LEFT JOIN\r\n"
			+ "    machine_temperature mt ON m.machine_id = mt.machine_id\r\n"
			+ "WHERE f.factory_id = :factoryId\r\n"
			+ "AND (m.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)\r\n"
			+ "AND (m.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)\r\n"
			+ "AND mt.reg_date > :startDate\r\n"
			+ "AND mt.reg_date < :endDate\r\n"
			+ ";")
	List<Object[]> userFindSparklineDataList(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
