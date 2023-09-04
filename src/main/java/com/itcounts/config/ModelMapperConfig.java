package com.itcounts.config;

import com.itcounts.model.dao.account.ExpenseDAO;
import com.itcounts.model.dto.account.ExpenseDTO;
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

	}
	PropertyMap<ExpenseDAO, ExpenseDTO> expenseDaoToDto = new PropertyMap<>() {
		@Override
		protected void configure() {
			map().setId(source.getId());
			map().setAuthorId(source.getAuthor().getId());
			map().setExpenseCategoryId(source.getExpenseCategoryDao().getId());
			map().setAmount(source.getAmount());
			map().setInsertedDate(source.getInsertedDate());
			map().setSpendDate(source.getSpendDate());

		}
	};


}
