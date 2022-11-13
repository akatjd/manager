package kr.co.manager.exception.rdb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.manager.domain.jpa.AccountBcrypt;

public interface AccountBcryptRepository extends JpaRepository<AccountBcrypt, Integer> {
	
	@Query
	AccountBcrypt findByUserName(String userName);
	
	@Query
	AccountBcrypt findByAccountId(String userId);
	
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM account_bcrypt WHERE account_id = :accountId")
	int userDeleteByAccountId(@Param("accountId") String accountId);
	
	List<AccountBcrypt> findByAllowFactory(String allowFactory);
}
