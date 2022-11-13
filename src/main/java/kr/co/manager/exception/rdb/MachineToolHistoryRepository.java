package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.manager.domain.jpa.MachineToolHistory;

public interface MachineToolHistoryRepository extends JpaRepository<MachineToolHistory, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "    MTH.tool_name,\r\n"
			+ "    MIN(MTH.use_cnt),\r\n"
			+ "    AVG(MTH.use_cnt),\r\n"
			+ "    MAX(MTH.use_cnt)\r\n"
			+ "FROM\r\n"
			+ "    machine_tool_history MTH\r\n"
			+ "WHERE\r\n"
			+ "	MTH.machine_id = :machineId\r\n"
			+ "	AND MTH.changed_date > :startDate\r\n"
			+ "	AND MTH.changed_date < :endDate\r\n"
			+ "GROUP BY MTH.machine_id , MTH.tool_name\r\n"
			+ ";")
	List<Object[]> userFindToolLifeDetailListByMachineIdAndStartDateAndEndDate(@Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "    f.factory_id,\r\n"
			+ "    f.factory_name,\r\n"
			+ "    a.area_id,\r\n"
			+ "    a.area_name,\r\n"
			+ "    qc.qg_client_id,\r\n"
			+ "    qc.client_name,\r\n"
			+ "    mt.machine_id,\r\n"
			+ "    m.machine_name,\r\n"
			+ "    mt.tool_name,\r\n"
			+ "    mt.use_cnt,\r\n"
			+ "    mt.preset_cnt,\r\n"
			+ "    mt.changed_date,\r\n"
			+ "    mt.current_product_id,\r\n"
			+ "    GETMACHINETOOLPRESETCNT(mt.machine_id,mt.tool_name) AS tool_preset,\r\n"
			+ "    mt.machine_tool_id\r\n"
//			+ "    GETMACHINEPRODUCTTOOLPRESETCNT(mt.machine_id,mt.tool_name,mt.current_product_id) AS producT_tool_preset\r\n"
			+ "FROM\r\n"
			+ "    factory f\r\n"
			+ "        JOIN\r\n"
			+ "    area a ON f.factory_id = a.factory_id\r\n"
			+ "        JOIN\r\n"
			+ "    qg_client qc ON f.factory_id = qc.factory_id\r\n"
			+ "        JOIN\r\n"
			+ "    machine m ON qc.qg_client_id = m.qg_client_id\r\n"
			+ "        AND a.area_id = m.area_id\r\n"
			+ "        JOIN\r\n"
			+ "    machine_tool_history mt ON m.machine_id = mt.machine_id\r\n"
			+ "WHERE\r\n"
			+ "    f.factory_id = :factoryId AND (a.area_id = :areaId OR :areaId is NULL)\r\n"
			+ "        AND m.machine_id = :machineId\r\n"
			+ "        AND mt.changed_date BETWEEN :startDate AND :endDate\r\n"
			+ ";")
	List<Object[]> userFindToolLifeListByFactoryIdAndAreaIdAndMachineIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "    distinct tool_name, machine_tool_id\r\n"
			+ "FROM\r\n"
			+ "    machine_tool_history\r\n"
			+ "WHERE\r\n"
			+ "	machine_id = :machineId\r\n"
//			+ "    AND\r\n"
//			+ "    preset_cnt > 0\r\n"
			+ "	AND\r\n"
			+ "    changed_date > :startDate\r\n"
			+ "    AND\r\n"
			+ "    changed_date < :endDate\r\n"
			+ "ORDER BY tool_name\r\n"
			+ ";")
	List<String> userFindToolNameListByMachineIdAndStartDateAndEndDate(@Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "    f.factory_id, f.factory_name, m.area_id, a.area_name, mth.machine_id, m.machine_name, COUNT(mth.machine_id)\r\n"
			+ "FROM\r\n"
			+ "    machine_tool_history mth\r\n"
			+ "    JOIN\r\n"
			+ "    machine m ON mth.machine_id = m.machine_id\r\n"
			+ "    JOIN\r\n"
			+ "    qg_client qc ON m.qg_client_id = qc.qg_client_id\r\n"
			+ "    JOIN\r\n"
			+ "    factory f ON qc.factory_id = f.factory_id\r\n"
			+ "    LEFT JOIN\r\n"
			+ "    area a ON m.area_id = a.area_id\r\n"
			+ "WHERE\r\n"
			+ "	f.factory_id = :factoryId\r\n"
			+ "    AND\r\n"
			+ "    (m.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)\r\n"
			+ "    AND\r\n"
			+ "    (mth.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)\r\n"
			+ "    AND\r\n"
			+ "    mth.changed_date > :startDate\r\n"
			+ "    AND\r\n"
			+ "    mth.changed_date < :endDate\r\n"
			+ "GROUP BY mth.machine_id\r\n"
			+ ";")
	List<Object[]> userFindToolChangeCntListByFactoryIdAndAreaIdAndMachineIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "    mth.machine_tool_id, mth.tool_name, COUNT(mth.tool_name)\r\n"
			+ "FROM\r\n"
			+ "    machine_tool_history mth\r\n"
			+ "WHERE\r\n"
			+ "    mth.machine_id = :machineId\r\n"
			+ "    AND\r\n"
			+ "    mth.changed_date > :startDate\r\n"
			+ "    AND\r\n"
			+ "    mth.changed_date < :endDate\r\n"
			+ "GROUP BY\r\n"
			+ "    mth.tool_name;")
	List<Object[]> userFindToolChangeCntDetailListByMachineIdAndStartDateAndEndDate(@Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "    f.factory_id, f.factory_name, m.area_id, a.area_name, mth.machine_id, m.machine_name, mth.changed_time, mth.changed_date\r\n"
			+ "FROM\r\n"
			+ "    machine_tool_history mth\r\n"
			+ "    JOIN\r\n"
			+ "    machine m ON mth.machine_id = m.machine_id\r\n"
			+ "    JOIN\r\n"
			+ "    qg_client qc ON m.qg_client_id = qc.qg_client_id\r\n"
			+ "    JOIN\r\n"
			+ "    factory f ON qc.factory_id = f.factory_id\r\n"
			+ "    JOIN\r\n"
			+ "    area a ON m.area_id = a.area_id\r\n"
			+ "WHERE\r\n"
			+ "    f.factory_id = :factoryId\r\n"
			+ "    AND\r\n"
			+ "    (m.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)\r\n"
			+ "    AND\r\n"
			+ "    (m.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)\r\n"
			+ "    AND\r\n"
			+ "	mth.changed_date > :startDate\r\n"
			+ "    AND\r\n"
			+ "    mth.changed_date < :endDate\r\n"
			+ "    AND\r\n"
			+ "    mth.changed_time IS NOT NULL\r\n"
			+ "    AND\r\n"
			+ "    m.view_yn = 'y'\r\n"
			+ ";")
	List<Object[]> userFindToolChangeTimeListByFactoryIdAndAreaIdAndMachineIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "    machine_tool_id, tool_name, changed_time\r\n"
			+ "FROM\r\n"
			+ "    machine_tool_history mth\r\n"
			+ "WHERE\r\n"
			+ "	   machine_id = :machineId\r\n"
			+ "    AND\r\n"
			+ "	   changed_date > :startDate\r\n"
			+ "    AND\r\n"
			+ "    changed_date < :endDate\r\n"
			+ "    AND\r\n"
			+ "    changed_time IS NOT NULL\r\n"
			+ ";")
	List<Object[]> userFindToolChangeTimeDetailListByMachineIdAndStartDateAndEndDate(@Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
