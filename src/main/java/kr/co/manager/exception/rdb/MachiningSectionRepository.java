package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.MachiningSection;
import kr.co.manager.domain.jpa.Product;

@Repository
public interface MachiningSectionRepository extends JpaRepository<MachiningSection, Integer>{
	
	@Query(nativeQuery=true, value="SELECT MS.item_id FROM machining_section MS WHERE MS.product_id = :productId AND MS.active_yn = :activeYn")
	List<Integer> userFindItemIdByProductIdAndActiveYn(@Param("productId") Integer productId, @Param("activeYn") String activeYn);
	
	@Query(nativeQuery=true, value="SELECT MS.machining_section_id FROM machining_section MS WHERE MS.product_id = :productId AND MS.active_yn = 'y'")
	List<Integer> userFindMachiningSectionIdByProductId(@Param("productId") Integer productId);
	
	MachiningSection findOneByProductAndActiveYn(Product product, String activeYn);
	
}
