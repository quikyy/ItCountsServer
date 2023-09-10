package com.itcounts.config;

import com.itcounts.model.dao.account.AccountDAO;
import com.itcounts.model.dao.expense.ExpenseDAO;
import com.itcounts.model.dto.account.AccountDTO;
import com.itcounts.model.dto.expense.ExpenseDTO;
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
	}

	PropertyMap<AccountDAO, AccountDTO> accountDaoToAccountDto = new PropertyMap<>() {
		@Override
		protected void configure() {
			map().setId(source.getId());
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

		}
	};


}
