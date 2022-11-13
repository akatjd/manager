package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.InspectionPolicyGroup;

@Repository
public interface InspectionPolicyGroupRepository extends JpaRepository<InspectionPolicyGroup, Integer> {

	@Query(nativeQuery = true, value = "SELECT section_piece_id FROM inspection_policy_group")
	List<Integer> userFindAll();
}
