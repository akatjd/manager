package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query(nativeQuery = true, value = "SELECT I.item_id, I.item_type, I.product_id FROM item I WHERE I.reg_date > :searchTime")
	List<Object[]> userFindItemListByRegDate(@Param("searchTime") String searchTime);
	
	@Query(nativeQuery = true, value = "SELECT I.reg_date FROM item I WHERE I.item_id = :itemId")
    String userFindRegDateByItemId(@Param("itemId") Integer itemId);

	@Query(nativeQuery = true, value = "SELECT \r\n"
			+ "    ic.machine_id, m.machine_name, count(*)\r\n"
			+ "FROM\r\n"
			+ "    item_ct ic\r\n"
			+ "        JOIN\r\n"
			+ "    product p ON ic.product_id = p.product_id\r\n"
			+ "        JOIN\r\n"
			+ "    machine m ON p.machine_id = m.machine_id\r\n"
			+ "        JOIN\r\n"
			+ "    qg_client qc ON m.qg_client_id = qc.qg_client_id\r\n"
			+ "        JOIN\r\n"
			+ "    factory f ON qc.factory_id = f.factory_id\r\n"
			+ "        JOIN\r\n"
			+ "    area a ON f.factory_id = a.factory_id AND (a.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)\r\n"
			+ "WHERE\r\n"
			+ "    ic.item_type_id = 5\r\n"
			+ "        AND ic.reg_date > :startDate\r\n"
			+ "        AND ic.reg_date < :endDate\r\n"
			+ "        AND f.factory_id = :factoryId\r\n"
			+ "        AND (m.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)\r\n"
			+ "GROUP BY ic.machine_id\r\n"
			+ "ORDER BY m.sort_seq\r\n"
			+ ";")
	List<Object[]> userFindStopCntByFactoryIdAndAreaId(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
