package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {

	@Query(nativeQuery = true, value = "SELECT                                                                                                              \n"
			+ "           m.machine_id,                                                                                                 \n"
			+ "           m.machine_name,                                                                                               \n"
			+ "           m.machine_status_type,                                                                                        \n"
			+ "           m.warn_status,                                                                                                \n"
			+ "           m.real_time_efficiency,                                                                                       \n"
			+ "           m.view_yn,                                                                                                    \n"
			+ "           m.warn_msg,                                                                                                   \n"
			+ "           m.sort_seq,                                                                                                   \n"
			+ "           m.inspection_use,                                                                                             \n"
			+ "           m.stop_use,                                                                                                   \n"
			+ "           m.last_product_date,                                                                                          \n"
			+ "           m.last_status_date,                                                                                            \n"
			+ "           p.product_name,                                                                                               \n"
			+ "           p.cycle_time,                                                                                                 \n"
			+ "           m.today_cnt,                                                                                                  \n"
			+ "           CASE                                                                                                          \n"
			+ "               WHEN inspection_use = 'y' && ip.insCnt > 0 THEN 'y'                                                       \n"
			+ "               WHEN inspection_use = 'n' && ip.insCnt > 0 THEN 'ny'                                                      \n"
			+ "               ELSE 'n'                                                                                                  \n"
			+ "           END AS inspectionActive,                                                                                      \n"
			+ "           m.area_id,                                                                                                    \n"
			+ "           m.current_product_id,                                                                                         \n"
			+ "           m.total_production_rate,                                                                                      \n"
			+ "           m.goal_cnt,                                                                                                   \n"
			+ "           m.status_delay,                                                                                               \n"
			+ "           m.product_change_stop_use,                                                                                    \n"
			+ "           m.product_change_inspection_use,                                                                              \n"
			+ "           m.basic_section_alarm                                                                                         \n"
			+ "       FROM                                                                                                              \n"
			+ "           machine m                                                                                                     \n"
			+ "               JOIN                                                                                                      \n"
			+ "           qg_client qc ON m.qg_client_id = qc.qg_client_id                                                              \n"
			+ "               JOIN                                                                                                      \n"
			+ "           factory f ON qc.factory_id = f.factory_id                                                                     \n"
			+ "               LEFT OUTER JOIN                                                                                           \n"
			+ "           product p ON m.current_product_id = p.product_id                                                              \n"
			+ "               LEFT OUTER JOIN                                                                          \n"
			+ "           (SELECT                                                                                      \n"
			+ "               ig.product_id,                                                                           \n"
			+ "               COUNT(i.inspection_policy_id) AS insCnt                                                  \n"
			+ "           FROM                                                                                         \n"
			+ "               inspection_policy_group ig                                                               \n"
			+ "           LEFT OUTER JOIN machining_section ms ON ig.machining_section_id = ms.machining_section_id    \n"
			+ "           LEFT OUTER JOIN inspection_policy i ON ig.inspection_group_id = i.inspection_group_id        \n"
			+ "           WHERE                                                                                        \n"
			+ "               i.active_yn = 'y'                                                                        \n"
			+ "               and ms.active_yn = 'y'                                                                   \n"
			+ "           GROUP BY ig.product_id                                                                       \n"
			+ "           ) ip ON ip.product_id = p.product_id                                                         \n"
			+ "       WHERE (m.area_id = :areaId or :areaId IS NULL)                                                                    \n"
			+ "       AND   (m.view_yn = :viewYn or :viewYn IS NULL)                                                                    \n"
			+ "       AND   (m.machine_status_type = :machineStatusType or :machineStatusType IS NULL)                                  \n"
			+ "       AND   (m.warn_status = :warnStatus or :warnStatus IS NULL)                                                        \n"
			+ "       AND   (lower(m.machine_name) LIKE lower(CONCAT('%',:machineName,'%')) or :machineName IS NULL)                    \n"
			+ "       AND   (qc.factory_id = :factoryId or :factoryId IS NULL)                                        \n"
			+ "       ORDER BY m.sort_seq ASC                                                                                           \n")
	List<Object[]> userfindByMonitoringMachineList(@Param("factoryId") Integer factoryId,
			@Param("areaId") Integer areaId, @Param("machineStatusType") String machineStatusType,
			@Param("warnStatus") String warnStatus, @Param("machineName") String machineName,
			@Param("viewYn") String viewYn);
	
	@Query(nativeQuery=true,value="SELECT                                                                                                              \n"
            +"           m.machine_id,                                                                                                 \n"
            +"           m.machine_name,                                                                                               \n"
            +"           m.machine_status_type,                                                                                        \n"
            +"           m.warn_status,                                                                                                \n"
            +"           m.real_time_efficiency,                                                                                       \n"
            +"           m.view_yn,                                                                                                    \n"
            +"           m.warn_msg,                                                                                                   \n"
            +"           m.sort_seq,                                                                                                   \n"
            +"           m.inspection_use,                                                                                             \n"
            +"           m.stop_use,                                                                                                   \n"
            +"           m.last_product_date,                                                                                          \n"
            +"           m.last_status_date,                                                                                            \n"
            +"           p.product_name,                                                                                               \n"
            +"           p.cycle_time,                                                                                                 \n"
            +"           m.today_cnt,                                                                                                  \n"
            +"           CASE                                                                                                          \n"
            +"               WHEN inspection_use = 'y' && ip.insCnt > 0 THEN 'y'                                                       \n"
            +"               ELSE 'n'                                                                                                  \n"
            +"           END AS inspectionActive,                                                                                      \n"
            +"           m.area_id,                                                                                                    \n"
            +"           m.current_product_id,                                                                                         \n"
            +"           m.total_production_rate,                                                                                      \n"
            +"           m.goal_cnt,                                                                                                   \n"
            +"           m.status_delay,                                                                                               \n"
            +"           m.product_change_stop_use,                                                                                    \n"
            +"           m.product_change_inspection_use,                                                                              \n"
            +"           m.basic_section_alarm                                                                                         \n"
            +"       FROM                                                                                                              \n"
            +"           machine m                                                                                                     \n"
            +"               JOIN                                                                                                      \n"
            +"           qg_client qc ON m.qg_client_id = qc.qg_client_id                                                              \n"
            +"               JOIN                                                                                                      \n"
            +"           factory f ON qc.factory_id = f.factory_id                                                                     \n"
            +"               LEFT OUTER JOIN                                                                                           \n"
            +"           product p ON m.current_product_id = p.product_id                                                              \n"
            +"               LEFT OUTER JOIN                                                                          \n"
            +"           (SELECT                                                                                      \n"
            +"               ig.product_id,                                                                           \n"
            +"                   COUNT(i.inspection_policy_id) AS insCnt                                              \n"
            +"           FROM                                                                                         \n"
            +"               inspection_policy_group ig                                                               \n"
            +"           LEFT OUTER JOIN machining_section ms ON ig.machining_section_id = ms.machining_section_id    \n"
            +"           LEFT OUTER JOIN inspection_policy i ON ig.inspection_group_id = i.inspection_group_id        \n"
            +"           WHERE                                                                                        \n"
            +"               i.active_yn = 'y'                                                                        \n"
            +"               and ms.active_yn = 'y'                                                                   \n"
            +"           GROUP BY ig.product_id                                                                       \n"
            +"           ) ip ON ip.product_id = p.product_id                                                         \n"
            +"       WHERE (m.area_id = :areaId or :areaId IS NULL)                                                                    \n"
            +"       AND   (m.view_yn = :viewYn or :viewYn IS NULL)                                                                    \n"
            +"       AND   (qc.factory_id = :factoryId)                                                              \n"
            +"       ORDER BY m.sort_seq ASC                                                                                           \n")
	List<Object[]> findMachineViewList(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("viewYn") String viewYn);
	
	@Query(nativeQuery=true, value="SELECT \r\n"
			+ "    M.machine_id,\r\n"
			+ "    M.machine_name\r\n"
			+ "FROM\r\n"
			+ "    machine M\r\n"
			+ "    JOIN\r\n"
			+ "    qg_client QC ON M.qg_client_id = QC.qg_client_id\r\n"
			+ "    JOIN\r\n"
			+ "    factory F ON QC.factory_id = F.factory_id\r\n"
			+ "WHERE (QC.factory_id = :factoryId or :factoryId = 1 or :factoryId IS NULL)\r\n"
			+ "AND (M.area_id = :areaId or :areaId IS NULL)\r\n"
			+ "AND M.view_yn = 'y'\r\n"
			+ ";")
	List<Object[]> userFindMachineListByFactoryIdAndAreaId(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId);
	
	@Query(nativeQuery=true, value="SELECT \r\n"
			+ "    M.machine_id,\r\n"
			+ "    M.machine_name,\r\n"
			+ "    A.area_name\r\n"
			+ "FROM\r\n"
			+ "    machine M\r\n"
			+ "    JOIN\r\n"
			+ "    qg_client QC ON M.qg_client_id = QC.qg_client_id\r\n"
			+ "    JOIN\r\n"
			+ "    factory F ON QC.factory_id = F.factory_id\r\n"
			+ "    JOIN\r\n"
			+ "    area A ON M.area_id = A.area_id\r\n"
			+ "WHERE QC.factory_id = :factoryId\r\n"
			+ "AND (M.area_id = :areaId or :areaId IS NULL)\r\n"
			+ "AND (M.machine_id = :machineId or :machineId IS NULL)\r\n"
			+ "AND M.view_yn = 'y'\r\n"
			+ "ORDER BY M.sort_seq\r\n"
			+ ";")
	List<Object[]> userFindProgramCntMachineListByFactoryIdAndAreaId(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId);
	
	@Query(nativeQuery=true, value="SELECT \r\n"
			+ "    M.machine_id\r\n"
			+ "FROM\r\n"
			+ "    machine M\r\n"
			+ "    JOIN\r\n"
			+ "    qg_client QC ON M.qg_client_id = QC.qg_client_id\r\n"
			+ "    JOIN\r\n"
			+ "    factory F ON QC.factory_id = F.factory_id\r\n"
			+ "WHERE QC.factory_id = :factoryId\r\n"
			+ "AND (M.area_id = :areaId or :areaId IS NULL)\r\n"
			+ "AND (M.machine_id = :machineId or :machineId IS NULL)\r\n"
			+ "AND M.view_yn = 'y'\r\n"
			+ "ORDER BY M.machine_id;")
	List<Integer> userFindMachineIdListByFactoryIdAndAreaIdAndMachineId(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId);
	
	@Query(nativeQuery=true, value="SELECT \r\n"
			+ "	M.machine_id, M.machine_name, F.factory_id, F.factory_name, M.area_id, A.area_name\r\n"
			+ "FROM\r\n"
			+ "	machine M\r\n"
			+ "	JOIN\r\n"
			+ "	qg_client QC ON M.qg_client_id = QC.qg_client_id\r\n"
			+ "	JOIN\r\n"
			+ "	factory F ON QC.factory_id = F.factory_id\r\n"
			+ "	JOIN\r\n"
			+ "	area A ON M.area_id = A.area_id\r\n"
			+ "WHERE QC.factory_id = :factoryId\r\n"
			+ "AND (M.area_id = :areaId or :areaId IS NULL)\r\n"
			+ "AND (M.machine_id = :machineId or :machineId IS NULL)\r\n"
			+ "AND M.view_yn = 'y'\r\n"
			+ "ORDER BY M.machine_id\r\n"
			+ ";")
	List<Object[]> userFindMachineObjListByFactoryIdAndAreaIdAndMachineId(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId);
	
//	@Query(nativeQuery=true,value="SELECT m.* "
//            + "                    FROM machine m "
//            + "                    WHERE m.view_yn = 'y'"
//            + "                    AND m.qg_client_id IN ("
//            + "                                             SELECT qc.qg_client_id "
//            + "                                             FROM qg_client qc "
//            + "                                             WHERE (qc.factory_id = :factoryId or :factoryId IS NULL)"
//            + "                                          )"
//            + "                    AND (m.area_id = :areaId or :areaId IS NULL)"
//            + "                    ORDER BY m.sort_seq, m.machine_name")
//    List<Machine> findFactoryIdAreaId(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId);
	
	@Query
	Machine findByMachineId(Integer machineId);
	
//	@Query(nativeQuery = true, value = "select \r\n"
//			+ "	a.area_name\r\n"
//			+ "from \r\n"
//			+ "	machine m\r\n"
//			+ "    join\r\n"
//			+ "	qg_client qc on m.qg_client_id = qc.qg_client_id\r\n"
//			+ "    join\r\n"
//			+ "    factory f on qc.factory_id = f.factory_id\r\n"
//			+ "    join\r\n"
//			+ "    area a on m.area_id = a.area_id\r\n"
//			+ "where\r\n"
//			+ "	f.factory_id = :factoryId\r\n"
//			+ "    and\r\n"
//			+ "    m.qg_client_id = :qgClientId\r\n"
//			+ "    and\r\n"
//			+ "    m.machine_id = :machineId\r\n"
//			+ ";")
//	String userFindAreaNameByFactoryIdAndQgClientIdAndMAchineId(@Param("factoryId") Integer factoryId, @Param("qgClientId") Integer qgClientId, @Param("machineId") Integer machineId);
	
	@Query(nativeQuery = true, value = "select db_date from time_dimension where db_date >= :startDate and db_date <= :endDate ;")
	List<String> userFindDayListByDbDate(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
