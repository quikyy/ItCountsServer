package com.itcounts.repository;

import com.itcounts.model.dao.expense.ExpenseCategoryDAO;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpenseCategoryDAORepository extends JpaRepository<ExpenseCategoryDAO, BigInteger> {

	@Query(value = "SELECT * FROM expense_categories WHERE id = :id", nativeQuery = true)
	ExpenseCategoryDAO getExpenseCategoryById(@Param("id") BigInteger id);

	@Query(value = "SELECT * FROM expense_categories WHERE id = :id", nativeQuery = true)
	Optional<ExpenseCategoryDAO> getExpenseCategoryByIdOptional(@Param("id") BigInteger id);

}
