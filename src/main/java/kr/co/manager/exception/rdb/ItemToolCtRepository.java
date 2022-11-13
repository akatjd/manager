package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.manager.domain.jpa.ItemToolCt;

public interface ItemToolCtRepository extends JpaRepository<ItemToolCt, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "	ic.machine_id,\r\n"
			+ "    ms.product_id,\r\n"
			+ "    sp.section_number,\r\n"
			+ "    sp.tool_name,\r\n"
			+ "    sp.channel_name,\r\n"
			+ "    MIN(itc.section_data_size),\r\n"
			+ "    AVG(itc.section_data_size),\r\n"
			+ "    MAX(itc.section_data_size)\r\n"
			+ "FROM\r\n"
			+ "    machining_section ms\r\n"
			+ "        JOIN\r\n"
			+ "    section_piece sp ON ms.machining_section_id = sp.machining_section_id\r\n"
			+ "        LEFT JOIN\r\n"
			+ "    item_ct ic ON ms.product_id = ic.product_id\r\n"
			+ "      AND ic.machine_id = :machineId\r\n"
			+ "        AND ic.item_type_id IN (1, 2)\r\n"
			+ "        AND ic.reg_date > :startDate\r\n"
			+ "        AND ic.reg_date < :endDate\r\n"
			+ "        LEFT JOIN\r\n"
			+ "    item_tool_ct itc ON itc.item_ct_seq = ic.item_ct_seq\r\n"
			+ "        AND ms.product_id = itc.product_id\r\n"
			+ "        AND sp.channel_name = itc.channel_name\r\n"
			+ "        AND sp.section_number = itc.section_number\r\n"
			+ "        AND sp.tool_name = itc.tool_name\r\n"
			+ "WHERE\r\n"
			+ "    ms.pallets_no = 0\r\n"
			+ "        AND sp.channel_name = 'S1'\r\n"
			+ "        AND ms.active_yn = 'y'\r\n"
			+ "        AND ms.product_id = :productId\r\n"
			+ "GROUP BY product_id , channel_name , section_number , tool_name\r\n"
			+ ";")
	List<Object[]> userFindCtDetailViewListByMachineIdAndProductIdAndStartDateAndEndDate(@Param("machineId") Integer machineId, @Param("productId") Integer productId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "    b.machine_id, a.product_id, a.section_number, a.tool_name, a.channel_name, b.section_data_size, b.reg_date\r\n"
			+ "FROM\r\n"
			+ "    (SELECT \r\n"
			+ "		ms.product_id, sp.section_number, sp.tool_name, sp.channel_name, sp.path\r\n"
			+ "	FROM\r\n"
			+ "		machining_section ms\r\n"
			+ "		JOIN\r\n"
			+ "		section_piece sp ON ms.machining_section_id = sp.machining_section_id\r\n"
			+ "	WHERE\r\n"
			+ "		ms.product_id = :productId\r\n"
			+ "		AND\r\n"
			+ "		sp.channel_name = 'S1'\r\n"
			+ "		AND\r\n"
			+ "		ms.active_yn = 'y') a\r\n"
			+ "	LEFT JOIN\r\n"
			+ "    (SELECT \r\n"
			+ "		itc.channel_name,\r\n"
			+ "		itc.path,\r\n"
			+ "		itc.section_number,\r\n"
			+ "		itc.tool_name,\r\n"
			+ "		itc.section_data_size,\r\n"
			+ "		itc.machine_id,\r\n"
			+ "		ic.product_id,\r\n"
			+ "		itc.reg_date\r\n"
			+ "	FROM\r\n"
			+ "		item_tool_ct itc\r\n"
			+ "        RIGHT JOIN\r\n"
			+ "        item_ct ic ON itc.item_ct_seq = ic.item_ct_seq\r\n"
			+ "	WHERE\r\n"
			+ "		itc.machine_id = :machineId\r\n"
			+ "		AND \r\n"
			+ "		itc.product_id = :productId\r\n"
			+ "		AND\r\n"
			+ "		itc.item_type_id IN (1, 2)\r\n"
			+ "		AND\r\n"
			+ "		channel_name = 'S1') b ON a.product_id = b.product_id AND a.section_number = b.section_number\r\n"
			+ "WHERE\r\n"
			+ "	b.reg_date > :startDate\r\n"
			+ "    AND\r\n"
			+ "    b.reg_date < :endDate\r\n"
			+ "    AND\r\n"
			+ "    b.product_id = :productId\r\n"
			+ "ORDER BY product_id, section_number\r\n"
			+ ";")
	List<Object[]> userFindCtDetailByMachineIdAndProductIdAndStartDateAndEndDate(@Param("machineId") Integer machineId, @Param("productId") Integer productId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
