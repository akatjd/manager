package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.MachineEvent;

@Repository
public interface MachineEventRepository extends JpaRepository<MachineEvent, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "    COUNT(*)\r\n"
			+ "FROM\r\n"
			+ "    machine_event ME\r\n"
			+ "WHERE\r\n"
			+ "    machine_id = :machineId\r\n"
			+ "        AND reg_date > :startDate\r\n"
			+ "        AND reg_date < :endDate\r\n"
			+ "        AND machine_event_type = 'gcode_change';")
	Integer userFindProgramEditCntByMachineId(@Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	// 프로그램별 수정 횟수 가져오기 (full index 두 번 돌고 있어서 느린 현상 나타남)
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "	pg1.product_id, SUBSTRING(SUBSTRING_INDEX(pg1.gcode_content, '\"', 2), 3) AS gcode_name, total.changed_date, cnt\r\n"
			+ "FROM\r\n"
			+ "    product_gcode pg1\r\n"
			+ "    JOIN\r\n"
			+ "    (SELECT \r\n"
			+ "		p.product_id, p.machine_id, pg.gcode_type, pg.changed_date, pg.gcode_content, me.reg_date, COUNT(*) AS cnt\r\n"
			+ "	FROM\r\n"
			+ "		product p\r\n"
			+ "		JOIN\r\n"
			+ "		product_gcode pg ON p.product_id = pg.product_id\r\n"
			+ "		JOIN \r\n"
			+ "		(SELECT *, SUBSTRING_INDEX(SUBSTRING(event_msg, 29 + 16), ',', 1) AS product_gcode_id FROM machine_event WHERE machine_event_type = 'gcode_change' AND machine_id = :machineId AND reg_date BETWEEN :startDate AND :endDate) me ON pg.product_gcode_id = me.product_gcode_id\r\n"
			+ "	GROUP BY\r\n"
			+ "		p.product_id) total ON pg1.product_id = total.product_id\r\n"
			+ "WHERE\r\n"
			+ "	pg1.gcode_type = 'main'\r\n"
			+ "GROUP BY\r\n"
			+ "	pg1.product_id\r\n"
			+ ";")
	List<Object[]> userFindProgramCntDetailListByMachineId(@Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
}
