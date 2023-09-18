package com.itcounts.config;

import com.itcounts.model.dao.account.AccountDAO;
import com.itcounts.model.dao.expense.ExpenseDAO;
import com.itcounts.model.dao.expense.ExpenseScheduledDAO;
import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.repository.IAccountDAORepository;
import com.itcounts.repository.IExpenseCategoryDAORepository;
import com.itcounts.repository.IExpenseDAORepository;
import com.itcounts.repository.IExpenseScheduledRepository;
import com.itcounts.repository.IGenderDAORepository;
import com.itcounts.repository.IUserDAORepository;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConfig {

	@Autowired
	private IUserDAORepository userDaoRepository;

	@Autowired
	private IAccountDAORepository accountDaoRepository;

	@Autowired
	private IExpenseDAORepository expenseDaoRepository;

	@Autowired
	private IExpenseCategoryDAORepository expenseCategoryDaoRepository;

	@Autowired
	private IExpenseScheduledRepository expenseScheduledRepository;

	@Autowired
	private IGenderDAORepository genderDaoRepository;

	@Autowired
	private PasswordEncoder encoder;

	@EventListener(ApplicationReadyEvent.class)
	public void mockDatabaseApp() {
		mockUserAndAccount("marekgeret81@onet.pl", "marek81", "Marek", "Geret", "Male");
		mockUserAndAccount("spammerjoanna@gmail.com", "haslo12", "Joanna", "Kowalska", "Female");
	}


	private void mockUserAndAccount(String email, String password, String firstName, String lastName, String gender) {
		UserDAO userDao = UserDAO.builder()
				.email(email)
				.password(encoder.encode(password))
				.firstName(firstName)
				.lastName(lastName)
				.registerDate(getRandomRegisterTimestamp())
				.genderDAO(genderDaoRepository.getGenderByName(gender).get())
				.build();
		userDao = userDaoRepository.save(userDao);
		AccountDAO accountDao = AccountDAO.builder()
				.owner(userDao)
				.build();
		accountDaoRepository.save(accountDao);
		int expensesAmount = getRandomInt(5, 15);
		for (int i = 0; i < expensesAmount; i++){
			mockExpensesForUsers(userDao, accountDao);
		}
		int expensesScheduledAmount = getRandomInt(2, 6);
		for (int i = 0; i < expensesScheduledAmount; i++) {
			mockExpenseScheduledForUsers(userDao, accountDao);
		}
	}

	private void mockExpensesForUsers(UserDAO userDao, AccountDAO accountDao) {
		ExpenseDAO expenseDao = ExpenseDAO.builder()
				.accountDao(accountDao)
				.expenseCategoryDao(expenseCategoryDaoRepository.getExpenseCategoryById(getRandomExpenseCategoryId()))
				.amount(getRandomAmount())
				.insertedDate(Timestamp.from(Instant.now()))
				.spendDate(getRandomSpendDate())
				.isScheduled(false)
				.build();
		expenseDaoRepository.save(expenseDao);
	}

	private void mockExpenseScheduledForUsers(UserDAO userDao, AccountDAO accountDao) {
		ExpenseScheduledDAO expenseScheduledDAO = ExpenseScheduledDAO.builder()
				.amount(getRandomAmount())
				.dayOfMonth(getRandomInt(17, 19))
				.insertedDate(Timestamp.from(Instant.now()))
				.isActive(true)
				.userDao(userDao)
				.accountDao(accountDao)
				.expenseCategoryDao(expenseCategoryDaoRepository.getExpenseCategoryById(getRandomExpenseCategoryId()))
				.build();
		expenseScheduledRepository.save(expenseScheduledDAO);
	}

	private BigInteger getRandomExpenseCategoryId() {
		int randomInt = getRandomInt(1, 5);
		return new BigInteger(String.valueOf(randomInt));
	}

	private double getRandomAmount() {
		String amount = String.format("%s.%s", getRandomInt(1, 999), getRandomInt(9, 99));
		return Double.parseDouble(amount);
	}

	private Timestamp getRandomRegisterTimestamp() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, getRandomInt(1, 30));
		c.set(Calendar.MONTH, getRandomInt(0, 3));
		return new Timestamp(c.getTimeInMillis());
	}

	private Date getRandomSpendDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, getRandomInt(1, 30));
		c.set(Calendar.MONTH, getRandomInt(8, 9));
		Timestamp timestamp = new Timestamp(c.getTimeInMillis());
		return new Date(timestamp.getTime());
	}

	private int getRandomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

}
