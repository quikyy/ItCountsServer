package com.itcounts.config;

import com.itcounts.model.dao.account.AccountDAO;
import com.itcounts.model.dao.expense.ExpenseDAO;
import com.itcounts.model.dao.expense.ExpenseScheduledDAO;
import com.itcounts.model.dto.account.AccountDTO;
import com.itcounts.model.dto.expense.ExpenseDTO;
import com.itcounts.model.dto.expense.ExpenseScheduledDTO;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Autowired
	private ModelMapper modelMapper;


	@PostConstruct
	public void init() {
		modelMapper.addMappings(accountDaoToAccountDto);
		modelMapper.addMappings(expenseDaoToExpenseDto);
		modelMapper.addMappings(expenseScheduledDaoToExpenseScheduledDto);
	}

	PropertyMap<AccountDAO, AccountDTO> accountDaoToAccountDto = new PropertyMap<>() {
		@Override
		protected void configure() {
			map().setAccountId(source.getId());
			map().setOwnerId(source.getOwner().getId());
		}
	};

	PropertyMap<ExpenseDAO, ExpenseDTO> expenseDaoToExpenseDto = new PropertyMap<>() {
		@Override
		protected void configure() {
			map().setId(source.getId());
			map().setExpenseCategoryId(source.getExpenseCategoryDao().getId());
			map().setAmount(source.getAmount());
			map().setInsertedDate(source.getInsertedDate());
			map().setSpendDate(source.getSpendDate());
			map().setScheduled(source.isScheduled());
		}
	};


	PropertyMap<ExpenseScheduledDAO, ExpenseScheduledDTO> expenseScheduledDaoToExpenseScheduledDto = new PropertyMap<>() {
		@Override
		protected void configure() {
			map().setId(source.getId());
			map().setAmount(source.getAmount());
			map().setCategoryId(source.getExpenseCategoryDao().getId());
			map().setUserId(source.getUserDao().getId());
			map().setActive(source.isActive());
		}
	};

}
