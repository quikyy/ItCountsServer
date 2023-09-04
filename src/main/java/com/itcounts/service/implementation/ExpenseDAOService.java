package com.itcounts.service.implementation;

import com.itcounts.model.dao.account.AccountDAO;
import com.itcounts.model.dao.account.ExpenseDAO;
import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.account.ExpenseDTO;
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
		if (accountDao.getGuest() != null) {
			if (accountDao.getGuest().getId().equals(userDao.getId())) {
				isUserLinkedWithAccount = true;
			}
		}
		if (!isUserLinkedWithAccount) {
			return null;
		}

		ExpenseDAO expenseDao = ExpenseDAO.builder()
				.author(userDao)
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


}
