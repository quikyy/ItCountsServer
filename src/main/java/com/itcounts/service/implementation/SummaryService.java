package com.itcounts.service.implementation;

import com.itcounts.model.dao.expense.ExpenseCategoryDAO;
import com.itcounts.model.dao.expense.ExpenseDAO;
import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.expense.ExpenseDTO;
import com.itcounts.model.dto.summary.SummaryDTO;
import com.itcounts.repository.IExpenseCategoryDAORepository;
import com.itcounts.service.interfaces.ISummaryService;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryService implements ISummaryService {

	@Autowired
	private ExpenseDAOService expenseDaoService;

	@Autowired
	private IExpenseCategoryDAORepository expenseCategoryDaoRepository;

	@Override
	public SummaryDTO getSummary(UserDAO userDao, Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			startDate = new Date(c.getTimeInMillis());
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate = new Date(c.getTimeInMillis());
		}
		SummaryDTO summaryDto = SummaryDTO.builder()
				.userId(userDao.getId())
				.startDate(startDate)
				.endDate(endDate)
				.build();

		List<ExpenseDTO> expenseDtoList = expenseDaoService.getExpensesBetweenDates(userDao, startDate, endDate);
		if (expenseDtoList == null) {
			summaryDto.setTotalSpendAmount(0);
			summaryDto.setTotalExpensesAmount(0);
			summaryDto.setExpenses(null);
			return summaryDto;
		}
		summaryDto.setTotalSpendAmount(countExpensesTotalAmount(expenseDtoList));
		summaryDto.setTotalExpensesAmount(expenseDtoList.size());
		summaryDto.setExpenses(expenseDtoList);
		return summaryDto;
	}

	private double countExpensesTotalAmount(List<ExpenseDTO> expenseDtoList) {
		return expenseDtoList.stream().mapToDouble(ExpenseDTO::getAmount).sum();
	}
}
