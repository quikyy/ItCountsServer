package com.itcounts.repository;

import com.itcounts.model.dao.account.ExpenseDAO;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpenseDAORepository extends JpaRepository<ExpenseDAO, BigInteger> {

	@Query(value = "SELECT * FROM accounts_expenses WHERE id = :id", nativeQuery = true)
	Optional<ExpenseDAO> getExpenseById(@Param("id") BigInteger id);

}
