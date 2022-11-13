package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.manager.domain.jpa.Factory;
import kr.co.manager.domain.jpa.SchedulePolicy;

public interface SchedulePolicyRepository extends JpaRepository<SchedulePolicy, Integer>{

	List<SchedulePolicy> findByFactory(Factory factory);

    /**
     * <pre>
     * 1. 개요 : 사용중인 스케쥴 조회
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : findByScheduleUseYn
     * @date : 2019. 11. 18.
     * @author : tachyon
     * @history : 
     *  -----------------------------------------------------------------------
     *	변경일                작성자	                         변경내용  
     *  ----------- ------------------- ---------------------------------------
     *	2019. 11. 18.      tachyon             최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param string
     * @return
     */
    List<SchedulePolicy> findByScheduleUseYn(String string);

    /**
     * <pre>
     * 1. 개요 : 활성화중인 스케쥴 조회
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : findByFactoryAndScheduleUseYn
     * @date : 2019. 11. 19.
     * @author : tachyon
     * @history : 
     *  -----------------------------------------------------------------------
     *	변경일                작성자	                         변경내용  
     *  ----------- ------------------- ---------------------------------------
     *	2019. 11. 19.      tachyon             최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param factory
     * @param string "y" 만 사용 가능 
     * @return
     */
    SchedulePolicy findByFactoryAndScheduleUseYn(Factory factory, String scheduleUseYn);
    
    SchedulePolicy findByPolicyId(Integer scheduleId);
    
    @Query(nativeQuery = true, value = "select \r\n"
    		+ "    *\r\n"
    		+ "from\r\n"
    		+ "    schedule_policy\r\n"
    		+ "where\r\n"
    		+ "    factory_id = :factoryId\r\n"
    		+ "    and\r\n"
    		+ "    schedule_use_yn = 'y'\r\n"
    		+ ";")
    List<Object[]> userFindScheduleByFactoryId(@Param("factoryId") Integer factoryId);
}
