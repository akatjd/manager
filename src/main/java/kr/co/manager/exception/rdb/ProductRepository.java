package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query
	Product findByProductId(Integer productId);

	@Query(nativeQuery=true, value="SELECT P.is_n1111_detect FROM product P WHERE P.product_id = :productId")
    Integer findIsN1111DetectByProductId(@Param("productId") Integer productId);
    
    @Query(nativeQuery=true, value="SELECT P.is_n9999_detect FROM product P WHERE P.product_id = :productId")
    Integer findIsN9999DetectByProductId(@Param("productId") Integer productId);
    
    @Query(nativeQuery=true, value="SELECT P.product_id FROM product P WHERE P.machine_id = :machineId")
    List<Integer> userFindProductIdBymachineId(@Param("machineId") Integer machineId);
    
    @Query(nativeQuery=true, value="SELECT P.product_name FROM product P WHERE P.product_id = :productId AND P.product_name IS NOT NULL AND P.product_name <> ''")
    String userFindProductNameByProductId(@Param("productId") Integer productId);
    
    // product_id, name 가져오기
    @Query(nativeQuery=true, value="SELECT \r\n"
    		+ "	P.product_id,\r\n"
    		+ "	P.product_name\r\n"
    		+ "FROM\r\n"
    		+ "	product P\r\n"
    		+ "	JOIN\r\n"
    		+ "	machine M ON P.machine_id = M.machine_id\r\n"
    		+ "	JOIN\r\n"
    		+ "	qg_client QC ON M.qg_client_id = QC.qg_client_id\r\n"
    		+ "	JOIN\r\n"
    		+ "	factory F ON QC.factory_id = F.factory_id\r\n"
    		+ "WHERE (QC.factory_id = :factoryId OR :factoryId = 1 OR :factoryId IS NULL)\r\n"
    		+ "AND (M.area_id = 1 or :areaId = 1 or :areaId IS NULL)\r\n"
    		+ "AND (P.machine_id = :machineId or :machineId = 1 or :machineId IS NULL)\r\n"
    		+ "AND M.view_yn = 'y'\r\n"
    		+ ";")
    List<Object[]> userFindProductListByFactoryIdAndAreaIdAndMachineId(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId);
    
    // static cycle 가져오기
    @Query(nativeQuery=true, value="SELECT static_cycle_time FROM product WHERE machine_id = :machineId AND product_id = :productId")
    Float userFindProductStaticCycleByMachineIdAndProductId(@Param("machineId") Integer machineId, @Param("productId") Integer productId);
    
    @Query(nativeQuery=true, value=
    		" SELECT                                                                                                                     \r\n" +
    		"     a.area_id, a.area_name, m.machine_id, m.machine_name, p.product_id, p.product_oid, p.product_name, p.static_cycle_time, p.active_yn \r\n" +
    		" FROM                                                                                                                       \r\n" +
    		"     factory f                                                                                                              \r\n" +
    		"     JOIN                                                                                                                   \r\n" +
    		"     qg_client qc ON f.factory_id = qc.factory_id                                                                           \r\n" +
    		"     JOIN                                                                                                                   \r\n" +
    		"     machine m ON qc.qg_client_id = m.qg_client_id                                                                          \r\n" +
    		"     JOIN                                                                                                                   \r\n" +
    		"     area a ON m.area_id = a.area_id                                                                                        \r\n" +
    		"     JOIN                                                                                                                   \r\n" +
    		"     product p ON m.machine_id = p.machine_id                                                                               \r\n" +
    		" WHERE                                                                                                                      \r\n" +
    		" 	f.factory_id = :factoryId                                                                                                \r\n" +
    		"     AND                                                                                                                    \r\n" +
    		"     (m.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)                                                                \r\n" +
    		"     AND                                                                                                                    \r\n" +
    		"     (m.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)                                                    \r\n" +
    		" ORDER BY                                                                                                                   \r\n" +
    		"     a.area_id, m.machine_id                                                                                                \r\n" +
    		" ;                                                                                                                          ")
    List<Object[]> userFindProductSettingListByFactoryIdAndAreaIdAndMachineId(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId);
    
    @Query(nativeQuery=true, value="SELECT \n"
    		+ "    *\n"
    		+ "FROM\n"
    		+ "    (SELECT \n"
    		+ "        ms.pallets_no,\n"
    		+ "        ip.inspection_policy_id,\n"
    		+ "		ip.active_yn,\n"
    		+ "		sp.section_piece_id\n"
    		+ "    FROM\n"
    		+ "        product p\n"
    		+ "    JOIN machining_section ms ON p.product_id = ms.product_id\n"
    		+ "    JOIN section_piece sp ON ms.machining_section_id = sp.machining_section_id\n"
    		+ "    LEFT JOIN inspection_policy_group ipg ON sp.machining_section_id = ipg.machining_section_id\n"
    		+ "        AND sp.section_piece_id = ipg.section_piece_id\n"
    		+ "    LEFT JOIN inspection_policy ip ON ipg.inspection_group_id = ip.inspection_group_id\n"
    		+ "        AND ip.active_yn = 'y'\n"
    		+ "    WHERE\n"
    		+ "        p.product_id = :productId\n"
    		+ "            AND ms.active_yn = 'y'\n"
    		+ "    ORDER BY ip.active_yn DESC) rs\n"
    		+ "GROUP BY rs.section_piece_id\n"
    		+ ";")
    List<Object[]> userFindInspectionSectionListByProductId(@Param("productId") Integer productId);
}
