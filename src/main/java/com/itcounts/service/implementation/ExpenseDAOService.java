package com.itcounts.service.implementation;

import com.itcounts.model.dao.account.AccountDAO;
import com.itcounts.model.dao.expense.ExpenseDAO;
import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.expense.ExpenseDTO;
import com.itcounts.model.dto.expense.ExpenseDTOBucket;
import com.itcounts.repository.IAccountDAORepository;
import com.itcounts.repository.IExpenseCategoryDAORepository;
import com.itcounts.repository.IExpenseDAORepository;
import com.itcounts.requestbody.ExpenseBodyDTO;
import com.itcounts.service.interfaces.IExpenseDAOService;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseDAOService implements IExpenseDAOService {


	@Autowired
	private IAccountDAORepository accountDaoRepository;

	@Autowired
	private IExpenseDAORepository expenseDaoRepository;

	@Autowired
	private IExpenseCategoryDAORepository expensesCategoryDaoRepository;

	@Autowired
	private ModelMapper modelMapper;

	private final Logger logger = LoggerFactory.getLogger(ExpenseDAOService.class);

	@Override
	public ExpenseDTO addExpense(UserDAO userDao, ExpenseBodyDTO expenseBodyDto) {
		if (expenseBodyDto.getAccountId() == null) {
			return null;
		}
		Optional<AccountDAO> accountDaoOptional = accountDaoRepository.getAccountById(expenseBodyDto.getAccountId());
		if (accountDaoOptional.isEmpty()) {
			return null;
		}
		AccountDAO accountDao = accountDaoOptional.get();
		boolean isUserLinkedWithAccount = accountDao.getOwner().getId().equals(userDao.getId());
		if (!isUserLinkedWithAccount) {
			return null;
		}

		ExpenseDAO expenseDao = ExpenseDAO.builder()
				.amount(expenseBodyDto.getAmount())
				.expenseCategoryDao(expensesCategoryDaoRepository.getExpenseCategoryById(expenseBodyDto.getExpenseCategoryId()))
				.insertedDate(Timestamp.from(Instant.now()))
				.build();
		if (expenseBodyDto.getSpendDate() == null) {
			expenseDao.setSpendDate(Date.valueOf(LocalDate.now()));
		} else {
			expenseDao.setSpendDate(expenseBodyDto.getSpendDate());
		}
		expenseDao = expenseDaoRepository.save(expenseDao);
		return modelMapper.map(expenseDao, ExpenseDTO.class);
	}

	@Override
	public boolean deleteExpense(UserDAO userDao, BigInteger expenseId) {
		Optional<ExpenseDAO> expenseDaoOptional = expenseDaoRepository.getExpenseById(expenseId);
		if (expenseDaoOptional.isEmpty()) {
			return false;
		}
		ExpenseDAO expenseDao = expenseDaoOptional.get();
		expenseDao.setDeleted(true);
		expenseDaoRepository.save(expenseDao);
		return true;
	}

	@Override
	public ExpenseDTOBucket getExpenses(BigInteger accountId, Date startDate, Date endDate, BigInteger categoryId) {
		Optional<AccountDAO> accountDaoOptional = accountDaoRepository.getAccountById(accountId);
		if (accountDaoOptional.isEmpty()) {
			return null;
		}
		List<ExpenseDAO> expenseDaoList = new ArrayList<>();
		List<ExpenseDTO> expenseDtoList = new ArrayList<>();

		/*
			Default view - current month, all categories
		 */
		if (startDate == null && endDate == null && categoryId == null) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			Date calendarStartDate = new Date(c.getTimeInMillis());
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date calendarEndDate = new Date(c.getTimeInMillis());
			expenseDaoList = expenseDaoRepository.getExpensesByAccountIdStartEndDate(accountId, calendarStartDate, calendarEndDate);
			for (ExpenseDAO expenseDao : expenseDaoList) {
				expenseDtoList.add(modelMapper.map(expenseDao, ExpenseDTO.class));
			}
			return ExpenseDTOBucket.builder()
					.startDate(calendarStartDate)
					.endDate(calendarEndDate)
					.categoryId(null)
					.expensesAmount(expenseDtoList.size())
					.expenses(expenseDtoList)
					.build();
		}
		expenseDaoList = expenseDaoRepository.getExpensesByAccountIdStartEndDateCategoryId(accountId, startDate, endDate, categoryId);
		logger.info("Daolist: " + expenseDaoList.size());
		for (ExpenseDAO expenseDao : expenseDaoList) {
			expenseDtoList.add(modelMapper.map(expenseDao, ExpenseDTO.class));
		}
		return ExpenseDTOBucket.builder()
				.startDate(startDate)
				.endDate(endDate)
				.categoryId(categoryId)
				.expensesAmount(expenseDtoList.size())
				.expenses(expenseDtoList)
				.build();
	}



}
