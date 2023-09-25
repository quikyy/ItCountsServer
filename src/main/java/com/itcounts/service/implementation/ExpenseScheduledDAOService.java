package com.itcounts.service.implementation;

import com.itcounts.model.dao.account.AccountDAO;
import com.itcounts.model.dao.expense.ExpenseScheduledDAO;
import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.expense.ExpenseDTO;
import com.itcounts.model.dto.expense.ExpenseScheduledDTO;
import com.itcounts.repository.IAccountDAORepository;
import com.itcounts.repository.IExpenseCategoryDAORepository;
import com.itcounts.repository.IExpenseScheduledRepository;
import com.itcounts.requestbody.ExpenseScheduledBodyDTO;
import com.itcounts.service.interfaces.IExpenseScheduledDAOService;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseScheduledDAOService implements IExpenseScheduledDAOService {

	@Autowired
	private IExpenseScheduledRepository expenseScheduledRepository;

	@Autowired
	private IExpenseCategoryDAORepository expenseCategoryDaoRepository;

	@Autowired
	private IAccountDAORepository accountDaoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ExpenseScheduledDTO createExpenseScheduled(UserDAO userDao, ExpenseScheduledBodyDTO expenseScheduledBodyDto) {
		Optional<AccountDAO> accountDaoOptional = accountDaoRepository.getAccountByOwnerId(userDao.getId());
		if (accountDaoOptional.isEmpty()) {
			return null;
		}
		AccountDAO accountDao = accountDaoOptional.get();
		ExpenseScheduledDAO expenseScheduledDao = ExpenseScheduledDAO.builder()
				.amount(expenseScheduledBodyDto.getAmount())
				.dayOfMonth(expenseScheduledBodyDto.getDayOfMonth())
				.insertedDate(Timestamp.from(Instant.now()))
				.accountDao(accountDao)
				.userDao(userDao)
				.expenseCategoryDao(expenseCategoryDaoRepository.getExpenseCategoryById(expenseScheduledBodyDto.getExpenseCategoryId()))
				.build();
		expenseScheduledRepository.save(expenseScheduledDao);
		return modelMapper.map(expenseScheduledDao, ExpenseScheduledDTO.class);
	}

	@Override
	public boolean deleteExpenseScheduled(UserDAO userDao, BigInteger expenseScheduledId) {
		Optional<ExpenseScheduledDAO> optionalExpenseScheduledDao = expenseScheduledRepository.getExpenseScheduledById(expenseScheduledId);
		if (optionalExpenseScheduledDao.isEmpty()) {
			return false;
		}
		ExpenseScheduledDAO expenseScheduledDao = optionalExpenseScheduledDao.get();
		if (expenseScheduledDao.isActive()) {
			expenseScheduledDao.setActive(false);
			expenseScheduledRepository.save(expenseScheduledDao);
			return true;
		} else {
			return false;
		}
	}
}
