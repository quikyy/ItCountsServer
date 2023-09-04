package com.itcounts.repository;

import com.itcounts.model.dao.account.AccountDAO;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountDAORepository extends JpaRepository<AccountDAO, BigInteger> {

	@Query(value = "SELECT * FROM accounts WHERE id = :accountId", nativeQuery = true)
	Optional<AccountDAO> getAccountById(@Param("accountId") BigInteger accountId);

	@Query(value = "SELECT * FROM accounts WHERE owner_id = :ownerId", nativeQuery = true)
	Optional<AccountDAO> getAccountByOwnerId(@Param("ownerId") BigInteger ownerId);

}
