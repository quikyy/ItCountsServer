package com.itcounts.service.interfaces;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.expense.ExpenseDTO;
import com.itcounts.model.dto.expense.ExpenseDTOBucket;
import com.itcounts.requestbody.ExpenseBodyDTO;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

public interface IExpenseDAOService {

	ExpenseDTO addExpense(UserDAO userDao, ExpenseBodyDTO expenseBodyDto);
	boolean deleteExpense(UserDAO userDao, BigInteger expenseId);
	ExpenseDTOBucket getExpenses(BigInteger accountId, Date startDate, Date endDate, BigInteger categoryId);
}
