package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.ProductGcode;

@Repository
public interface ProductGcodeRepository extends JpaRepository<ProductGcode, Integer>{
	@Query(nativeQuery=true,value=
            "SELECT                                                                            \n" +
            "    b.*                                                                           \n" +
            "FROM                                                                              \n" +
            "    (SELECT                                                                       \n" +
            "        machine_id,                                                               \n" +
            "            product_id,                                                           \n" +
            "            path,                                                                 \n" +
            "            MAX(changed_date) AS changed_date,                                    \n" +
            "            oid                                                                   \n" +
            "    FROM                                                                          \n" +
            "        product_gcode                                                             \n" +
            "    WHERE                                                                         \n" +
            "        machine_id = :machineId AND product_id = :productId                       \n" +
            "    AND path = :path                                                              \n" +
            "    GROUP BY machine_id , product_id , path , oid) a                              \n" +
            "        INNER JOIN                                                                \n" +
            "    product_gcode b USING (machine_id , product_id ,path , oid , changed_date)    \n" +
            "    WHERE hash IS NOT NULL                                                        \n")
    List<Object[]> userFindByMachineIdAndProductIdAndPath(@Param("machineId") Integer machineId, @Param("productId") Integer productId, @Param("path") Integer path);
}