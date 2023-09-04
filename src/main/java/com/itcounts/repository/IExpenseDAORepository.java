package com.itcounts.repository;

import com.itcounts.model.dao.account.ExpenseDAO;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpenseDAORepository extends JpaRepository<ExpenseDAO, BigInteger> {

}
