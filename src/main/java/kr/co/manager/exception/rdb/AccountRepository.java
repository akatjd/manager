package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	@Query
	Account findByUserName(String userName);
	
	@Query
	Account findByAccountId(String userId);
	
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM account WHERE account_id = :accountId")
	int userDeleteByAccountId(@Param("accountId") String accountId);
	
	List<Account> findByAllowFactory(String allowFactory);
}
