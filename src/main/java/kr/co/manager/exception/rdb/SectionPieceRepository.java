package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.SectionPiece;

@Repository
public interface SectionPieceRepository extends JpaRepository<SectionPiece, Integer>{

	@Query(nativeQuery=true, value="SELECT section_piece_id FROM section_piece WHERE machining_section_id = :machiningSectionId")
    List<Integer> userFindSectionPieceIdByMachiningSectionId(@Param("machiningSectionId") Integer machiningSectionId);
	
	@Query(nativeQuery=true,value="SELECT channel_name, path, machining_section_id FROM section_piece WHERE machining_section_id = :machiningSectionId GROUP by path")
    List<Object[]> userFindSectionPathByMachiningSection(@Param("machiningSectionId") Integer machiningSectionId);
}
