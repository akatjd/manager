package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.ItemCt;

@Repository
public interface ItemCtRepository extends JpaRepository<ItemCt, Integer> {
        // ctView 리스트 가져오기 (product name으로 가져옴)
        @Query(nativeQuery = true, value = "SELECT \r\n"
                        + "    f.factory_id, f.factory_name, m.area_id, a.area_name, ic.machine_id, m.machine_name, min(ic.cycle_time), avg(ic.cycle_time), max(ic.cycle_time), p.product_id, p.product_name\r\n"
                        + "FROM\r\n"
                        + "    item_ct ic\r\n"
                        + "    JOIN\r\n"
                        + "    product p ON ic.product_id = p.product_id\r\n"
                        + "    JOIN\r\n"
                        + "    machine m ON ic.machine_id = m.machine_id\r\n"
                        + "    JOIN\r\n"
                        + "    qg_client qc ON m.qg_client_id = qc.qg_client_id\r\n"
                        + "    JOIN\r\n"
                        + "    factory f ON qc.factory_id = f.factory_id\r\n"
                        + "    JOIN\r\n"
                        + "    area a ON m.area_id = a.area_id\r\n"
                        + "WHERE\r\n"
                        + "    ic.reg_date > :startDate\r\n"
                        + "    AND\r\n"
                        + "    ic.reg_date < :endDate\r\n"
                        + "    AND\r\n"
                        + "    f.factory_id = :factoryId\r\n"
                        + "    AND\r\n"
                        + "    (m.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)\r\n"
                        + "    AND\r\n"
                        + "    (m.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)\r\n"
                        + "    AND\r\n"
                        + "    (p.product_id = :productId OR :productId = 1 OR :productId IS NULL)\r\n"
                        + "    AND\r\n"
                        + "    ic.item_type_id IN (1, 2)\r\n"
//                        + "        AND\r\n"
//                        + "    ic.cycle_time > 0\r\n"
                        + "GROUP BY ic.product_id\r\n"
                        + "ORDER BY ic.machine_id, ic.reg_date DESC\r\n"
                        + ";")
        List<Object[]> userFindCtViewListByFactoryIdAndAreaIdAndMachineIdAndProductIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("productId") Integer productId, @Param("startDate") String startDate, @Param("endDate") String endDate);
        
        @Query(nativeQuery = true, value = "SELECT \r\n"
                        + "    ic.machine_id, ic.cycle_time, ic.reg_date, ic.product_id\r\n"
                        + "FROM\r\n"
                        + "    item_ct ic\r\n"
                        + "    JOIN\r\n"
                        + "    machine m ON ic.machine_id = m.machine_id\r\n"
                        + "    JOIN\r\n"
                        + "    qg_client qc ON m.qg_client_id = qc.qg_client_id\r\n"
                        + "    JOIN\r\n"
                        + "    factory f ON qc.factory_id = f.factory_id\r\n"
                        + "WHERE\r\n"
                        + "           ic.reg_date > :startDate\r\n"
                        + "    AND\r\n"
                        + "    ic.reg_date < :endDate\r\n"
                        + "    AND\r\n"
                        + "    f.factory_id = :factoryId\r\n"
                        + "    AND\r\n"
                        + "    (m.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)\r\n"
                        + "    AND\r\n"
                        + "    (m.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)\r\n"
                        + "    AND\r\n"
                        + "    (ic.product_id = :productId OR :productId = 1 OR :productId IS NULL)\r\n"
                        + "    AND\r\n"
                        + "    ic.item_type_id IN (1, 2)\r\n"
//                        + "        AND\r\n"
//                        + "    ic.cycle_time > 0\r\n"
                        + "ORDER BY ic.machine_id, ic.product_id, ic.reg_date DESC\r\n"
                        + ";")
        List<Object[]> userFindCtListByFactoryIdAndAreaIdAndMachineIdAndProductIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("productId") Integer productId, @Param("startDate") String startDate, @Param("endDate") String endDate);
        
        @Query(nativeQuery = true, value = "select\r\n"
                        + "    qc.qg_client_id, qc.client_name, m.machine_id, m.machine_name, p.product_id, p.product_name, count(*) as cnt\r\n"
                        + "from\r\n"
                        + "    factory f\r\n"
                        + "    join\r\n"
                        + "    qg_client qc on f.factory_id = qc.factory_id\r\n"
                        + "    join\r\n"
                        + "    machine m on qc.qg_client_id = m.qg_client_id and m.view_yn = 'y'\r\n"
                        + "    join\r\n"
                        + "    item_ct ic on m.machine_id = ic.machine_id\r\n"
                        + "    join\r\n"
                        + "    product p on ic.product_id = p.product_id\r\n"
                        + "where\r\n"
                        + "        f.factory_id = :factoryId\r\n"
                        + "    and\r\n"
                        + "    (m.area_id = :areaId or :areaId = 1 or :areaId is null)\r\n"
                        + "    and\r\n"
                        + "    (m.machine_id = :machineId or :machineId = 1 or :machineId is null)\r\n"
                        + "    and\r\n"
                        + "    (p.product_id = :productId or :productId = 1 or :productId is null)\r\n"
                        + "    and\r\n"
                        + "    ic.item_type_id = 1\r\n"
                        + "    and\r\n"
                        + "    ic.reg_date between :startDate and :endDate\r\n"
                        + "group by\r\n"
                        + "        ic.product_id\r\n"
                        + ";")
        List<Object[]> userFindAutoUseListByFactoryIdAndAreaIdAndMachineIdAndProductIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("productId") Integer productId, @Param("startDate") String startDate, @Param("endDate") String endDate);
        
        @Query(nativeQuery = true, value = "SELECT \r\n"
                        + "    *\r\n"
                        + "FROM\r\n"
                        + "    item_ct\r\n"
                        + "WHERE\r\n"
                        + "    factory_id = :factoryId\r\n"
                        + "    and\r\n"
                        + "    (qg_client_id = :areaId or :areaId = 1 or :areaId is null)\r\n"
                        + "    and\r\n"
                        + "    (machine_id = :machineId or :machineId = 1 or :machineId is null)\r\n"
                        + "    and\r\n"
                        + "    reg_date between :startDateCast and :endDateCast\r\n"
                        + "group by machine_id, product_id\r\n"
                        + ";\r\n"
                        + "")
        List<Object[]> userFindDowntimeProductIdListByFactoryIdAndAreaIdAndMachineIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("startDateCast") String startDateCast, @Param("endDateCast") String endDateCast);
        
        @Query(nativeQuery = true, value = "select \r\n"
                        + "    COUNT(*)\r\n"
                        + "from\r\n"
                        + "    item_ct\r\n"
                        + "where\r\n"
                        + "        factory_id = :factoryId\r\n"
                        + "    and\r\n"
                        + "    qg_client_id = :areaId\r\n"
                        + "    and\r\n"
                        + "    machine_id = :machineId\r\n"
                        + "    and\r\n"
                        + "    product_id = :productId\r\n"
                        + "        and\r\n"
                        + "    reg_date between :startDateCast and :endDateCast\r\n"
                        + ";")
        Integer userFindItemCntByFactoryIdAndAreaIdAndMachineIdAndProductIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("productId") Integer productId, @Param("startDateCast") String startDateCast, @Param("endDateCast") String endDateCast);
        
        @Query(nativeQuery = true, value = "SELECT \r\n"
                        + "    COUNT(*)\r\n"
                        + "FROM\r\n"
                        + "    item_ct\r\n"
                        + "WHERE\r\n"
                        + "    factory_id = :factoryId AND machine_id = :machineId\r\n"
                        + "        AND item_type_id IN (1, 2)\r\n"
                        + "        AND reg_date > :startDate\r\n"
                        + "        AND reg_date < :endDate\r\n"
                        + ";")
        Integer userFindItemCntByFactoryIdAndMachineIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate);
        
        @Query(nativeQuery = true, value = "SELECT \r\n"
                        + "    m.machine_id,\r\n"
                        + "    m.machine_name,\r\n"
                        + "    COUNT(ic.reg_date),\r\n"
                        + "    DATE_FORMAT(ic.reg_date, '%Y%m%d') AS date_sd,\r\n"
                        + "    ic.product_id,\r\n"
                        + "    p.static_cycle_time,\r\n"
                        + "    a.area_name\r\n"
                        + "FROM\r\n"
                        + "    factory f\r\n"
                        + "        JOIN\r\n"
                        + "    qg_client qc ON f.factory_id = qc.factory_id\r\n"
                        + "        JOIN\r\n"
                        + "    machine m ON qc.qg_client_id = m.qg_client_id\r\n"
                        + "        LEFT JOIN\r\n"
                        + "    item_ct ic ON m.machine_id = ic.machine_id\r\n"
                        + "        AND ic.reg_date BETWEEN :startDate AND :endDate\r\n"
                        + "        AND DATE_FORMAT(ic.reg_date, '%H%i%s') BETWEEN :startTime AND :endTime\r\n"
                        + "        LEFT JOIN\r\n"
                        + "    product p ON ic.product_id = p.product_id\r\n"
                        + "        JOIN\r\n"
                        + "    area a ON m.area_id = a.area_id\r\n"
                        + "WHERE\r\n"
                        + "    f.factory_id = :factoryId AND m.view_yn = 'y'\r\n"
                        + "    AND\r\n"
                        + "    (ic.machine_id = :machineId OR :machineId = 1 OR :machineId IS NULL)\r\n"
                        + "    AND\r\n"
                        + "    (m.area_id = :areaId OR :areaId = 1 OR :areaId IS NULL)\r\n"
                        + "GROUP BY m.machine_id , DATE_FORMAT(ic.reg_date, '%Y%m%d')\r\n"
                        + "ORDER BY date_sd , m.sort_seq;")
        List<Object[]> userFindDtList(@Param("factoryId") Integer factoryId, @Param("areaId") Integer areaId, @Param("machineId") Integer machineId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("startTime") String startTime, @Param("endTime") String endTime);
        
        // 그룹 a, b에 조건 다는게 더 느렸음
        @Query(nativeQuery = true, value = "   SELECT                                                    \r\n" + 
                        "       *                                                                                \r\n" +
                        "   FROM                                                                                 \r\n" +
                        "       (SELECT                                                                          \r\n" +
                        "           m.machine_id,                                                                \r\n" +
                        "                   IF(ic.item_ct_seq IS NULL, 0, COUNT(*)) AS productCnt                        \r\n" +
                        "       FROM                                                                             \r\n" +
                        "           item_ct ic                                                                   \r\n" +
                        "       RIGHT JOIN machine m ON ic.machine_id = m.machine_id                             \r\n" +
                        "           AND ic.item_type_id IN (1 , 2)                                               \r\n" +
                        "           AND ic.reg_date > :startDate                                                 \r\n" +
                        "           AND ic.reg_date < :endDate                                                   \r\n" +
                        "       WHERE                                                                            \r\n" +
                        "           m.machine_id IN (SELECT                                                      \r\n" +
                        "                   m.machine_id                                                         \r\n" +
                        "               FROM                                                                     \r\n" +
                        "                   factory f                                                            \r\n" +
                        "               JOIN qg_client qc ON f.factory_id = qc.factory_id                        \r\n" +
                        "               JOIN machine m ON qc.qg_client_id = m.qg_client_id                       \r\n" +
                        "               JOIN area a ON m.area_id = a.area_id                                     \r\n" +
                        "               WHERE                                                                    \r\n" +
                        "                   f.factory_id = :factoryId AND m.view_yn = 'y')                       \r\n" +
                        "       GROUP BY m.machine_id) a                                                         \r\n" +
                        "           JOIN                                                                         \r\n" +
                        "       (SELECT                                                                          \r\n" +
                        "           a.area_id,                                                                   \r\n" +
                        "                   a.area_name,                                                                 \r\n" +
                        "                   m.machine_id,                                                                \r\n" +
                        "                   m.machine_name,                                                              \r\n" +
                        "                   IF(me.machine_event_id IS NULL, 0, COUNT(*)) AS editCnt                      \r\n" +
                        "       FROM                                                                             \r\n" +
                        "           factory f                                                                    \r\n" +
                        "       JOIN qg_client qc ON f.factory_id = qc.factory_id                                \r\n" +
                        "       JOIN machine m ON qc.qg_client_id = m.qg_client_id                               \r\n" +
                        "       JOIN area a ON m.area_id = a.area_id                                             \r\n" +
                        "       LEFT JOIN machine_event me ON m.machine_id = me.machine_id                       \r\n" +
                        "           AND me.reg_date > :startDate                                                 \r\n" +
                        "           AND me.reg_date < :endDate                                                   \r\n" +
                        "           AND me.machine_event_type = 'gcode_change'                                   \r\n" +
                        "       WHERE                                                                            \r\n" +
                        "           f.factory_id = :factoryId                                                    \r\n" +
                        "       GROUP BY a.area_id , m.machine_id) b ON a.machine_id = b.machine_id              \r\n" +
                        "   ;                                                                                    \r\n")
        List<Object[]> userFindProgramCntListByFactoryIdAndStartDateAndEndDate(@Param("factoryId") Integer factoryId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}

