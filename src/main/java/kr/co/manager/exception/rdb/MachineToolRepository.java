package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.MachineTool;

@Repository
public interface MachineToolRepository extends JpaRepository<MachineTool, Integer>{

	@Query
	MachineTool findByMachineToolId(Integer machineToolId);
	
	@Query(nativeQuery = true, value =
            " SELECT                                                                                                     \r\n" +
            "     DISTINCT mt.*                                                                                          \r\n" +
            " FROM                                                                                                       \r\n" +
            "     machine_tool mt                                                                                        \r\n" +
            "         JOIN                                                                                               \r\n" +
            "     machine m ON mt.machine_id = m.machine_id                                                              \r\n" +
            "         JOIN                                                                                               \r\n" +
            "   product p ON m.current_product_id = p.product_id                                                         \r\n" +
            "       JOIN                                                                                                 \r\n" +
            "                                                                                                            \r\n" +
            "   machining_section ms ON p.product_id = ms.product_id AND ms.active_yn = 'y'                              \r\n" +
            "         JOIN                                                                                               \r\n" +
            "   section_piece sp ON ms.machining_section_id = sp.machining_section_id AND mt.tool_name = sp.tool_name    \r\n" +
            " WHERE                                                                                                      \r\n" +
            "     m.machine_id = :machineId order by sp.section_number                                                   \r\n" )
    List<MachineTool> userFindByMachineId(@Param("machineId") Integer machineId);
	
	@Query(nativeQuery = true, value = "SELECT preset_cnt FROM machine_tool where machine_tool_id = :machineToolId")
	Integer userFindPresetCntByMachineToolId(@Param("machineToolId") Integer machineToolId);
	
	@Query(nativeQuery = true, value = 
			" SELECT                                                                                                                                                        \r\n" +
			"     a.area_id, a.area_name, m.machine_id, m.machine_name, mt.machine_tool_id, mt.tool_name, mt.use_cnt, mt.preset_cnt, mt.tool_spec, mt.use_yn, mt.sort_seq, mt.view_name   \r\n" +
			" FROM                                                                                                                                                          \r\n" +
			"     factory f                                                                                                                                                 \r\n" +
			"     JOIN                                                                                                                                                      \r\n" +
			"     qg_client qc ON f.factory_id = qc.factory_id                                                                                                              \r\n" +
			"     JOIN                                                                                                                                                      \r\n" +
			"     machine m ON qc.qg_client_id = m.qg_client_id                                                                                                             \r\n" +
			"     JOIN                                                                                                                                                      \r\n" +
			"     area a ON m.area_id = a.area_id                                                                                                                           \r\n" +
			"     JOIN                                                                                                                                                      \r\n" +
			"     machine_tool mt ON m.machine_id = mt.machine_id                                                                                                           \r\n" +
			" WHERE                                                                                                                                                         \r\n" +
			" 	f.factory_id = :factoryId                                                                                                                                   \r\n" +
			"     AND                                                                                                                                                       \r\n" +
			"     (m.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)                                                                                                   \r\n" +
			"     AND                                                                                                                                                       \r\n" +
			"     (m.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)                                                                                       \r\n" +
			" ORDER BY m.area_id, m.machine_id, mt.sort_seq                                                                                                                 \r\n" +
			" ;                                                                                                                                                             \r\n")
	List<Object[]> userFindToolSettingListByFactoryIdAndAreaIdAndMachineId(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId);
}
