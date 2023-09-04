package com.itcounts.service.interfaces;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.account.ExpenseDTO;
import com.itcounts.requestbody.ExpenseBodyDTO;
import java.math.BigInteger;

public interface IExpenseDAOService {

	ExpenseDTO addExpense(UserDAO userDao, ExpenseBodyDTO expenseBodyDto);
	boolean deleteExpense(UserDAO userDao, BigInteger expenseId);
}
