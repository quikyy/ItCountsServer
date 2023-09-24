package com.itcounts.repository;

import com.itcounts.model.dao.expense.ExpenseScheduledDAO;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpenseScheduledRepository extends JpaRepository<ExpenseScheduledDAO, BigInteger> {

	@Query(value = "SELECT * FROM expense_scheduled WHERE id = :id", nativeQuery = true)
	Optional<ExpenseScheduledDAO> getExpenseScheduledById(@Param("id") BigInteger id);

	@Query(value = "SELECT * FROM expense_scheduled WHERE day_of_month = :dayOfMonth AND is_active = 1", nativeQuery = true)
	List<ExpenseScheduledDAO> getExpenseScheduledDaoListByDayOfMonth(@Param("dayOfMonth") String dayOfMonth);

}
