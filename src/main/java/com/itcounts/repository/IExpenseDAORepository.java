package com.itcounts.repository;

import com.itcounts.model.dao.expense.ExpenseDAO;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpenseDAORepository extends JpaRepository<ExpenseDAO, BigInteger> {

	@Query(value = "SELECT * FROM accounts_expenses WHERE id = :id", nativeQuery = true)
	Optional<ExpenseDAO> getExpenseById(@Param("id") BigInteger id);

	@Query(value = "SELECT * FROM accounts_expenses WHERE account_id = :accountId", nativeQuery = true)
	List<ExpenseDAO> getExpensesByAccountId(@Param("accountId") BigInteger accountId);


	@Query(value = "SELECT * FROM accounts_expenses "
			+ "WHERE account_id = :accountId AND "
			+ "CAST(spend_date as DATE) >= :startDate AND "
			+ "CAST(spend_date as DATE) <= :endDate AND "
			+ "category_id = :categoryId", nativeQuery = true)
	List<ExpenseDAO> getExpensesByAccountIdStartEndDateCategoryId(@Param("accountId") BigInteger accountId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("categoryId") BigInteger categoryId);


	@Query(value = "SELECT * FROM accounts_expenses "
			+ "WHERE account_id = :accountId AND "
			+ "CAST(spend_date as DATE) >= :startDate AND "
			+ "CAST(spend_date as DATE) <= :endDate", nativeQuery = true)
	List<ExpenseDAO> getExpensesByAccountIdStartEndDate(@Param("accountId") BigInteger accountId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
