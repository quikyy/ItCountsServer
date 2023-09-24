package com.itcounts.scheduler;


import com.itcounts.model.dao.expense.ExpenseDAO;
import com.itcounts.model.dao.expense.ExpenseScheduledDAO;
import com.itcounts.repository.IExpenseDAORepository;
import com.itcounts.repository.IExpenseScheduledRepository;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ExpenseScheduler {


	@Autowired
	private IExpenseScheduledRepository expenseScheduledRepository;

	@Autowired
	private IExpenseDAORepository expenseDaoRepository;

	private final String EVERY_DAY_3AM = "0 0 3 * * *";

	@Scheduled(cron = EVERY_DAY_3AM)
	public void handleScheduledExpenses() {
		Calendar c = Calendar.getInstance();
		int currDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
		List<ExpenseScheduledDAO> expenseScheduledDaoList = expenseScheduledRepository.getExpenseScheduledDaoListByDayOfMonth(String.valueOf(currDayOfMonth));
		if (expenseScheduledDaoList.size() == 0) {
			return;
		}
		for (ExpenseScheduledDAO expenseScheduledDao : expenseScheduledDaoList) {
			expenseDaoRepository.save(ExpenseDAO.builder()
					.amount(expenseScheduledDao.getAmount())
					.insertedDate(Timestamp.from(Instant.now()))
					.spendDate(new Date(new Timestamp(c.getTimeInMillis()).getTime()))
					.accountDao(expenseScheduledDao.getAccountDao())
					.expenseCategoryDao(expenseScheduledDao.getExpenseCategoryDao())
					.isScheduled(true)
					.isDeleted(false)
					.build());
		}
	}

}
