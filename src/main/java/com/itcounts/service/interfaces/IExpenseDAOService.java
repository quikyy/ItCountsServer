package com.itcounts.service.interfaces;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.expense.ExpenseDTO;
import com.itcounts.model.dto.expense.ExpenseDTOBucket;
import com.itcounts.requestbody.ExpenseBodyDTO;
import com.itcounts.requestbody.ExpenseEditBodyDTO;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

public interface IExpenseDAOService {

	ExpenseDTO addExpense(UserDAO userDao, ExpenseBodyDTO expenseBodyDto);
	ExpenseDTO getExpense(UserDAO userDao, BigInteger expenseId);
	ExpenseDTO editExpense(UserDAO userDao, BigInteger expenseId, ExpenseEditBodyDTO expenseEditBodyDTO);
	boolean deleteExpense(UserDAO userDao, BigInteger expenseId);
	ExpenseDTOBucket getExpenses(UserDAO userDao, Date startDate, Date endDate, BigInteger categoryId);
	List<ExpenseDTO> getExpensesBetweenDates(UserDAO userDao, Date startDate, Date endDate);
}
