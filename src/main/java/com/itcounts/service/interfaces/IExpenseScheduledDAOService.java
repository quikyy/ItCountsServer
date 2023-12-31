package com.itcounts.service.interfaces;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.expense.ExpenseScheduledDTO;
import com.itcounts.requestbody.ExpenseScheduledBodyDTO;
import java.math.BigInteger;

public interface IExpenseScheduledDAOService {

	ExpenseScheduledDTO createExpenseScheduled(UserDAO userDao, ExpenseScheduledBodyDTO expenseScheduledBodyDto);
	boolean deleteExpenseScheduled(UserDAO userDao, BigInteger expenseScheduledId);
}
