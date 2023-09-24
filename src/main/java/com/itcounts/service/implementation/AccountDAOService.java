package com.itcounts.service.implementation;

import com.itcounts.model.dao.account.AccountDAO;
import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.account.AccountDTO;
import com.itcounts.repository.IAccountDAORepository;
import com.itcounts.repository.IExpenseDAORepository;
import com.itcounts.service.interfaces.IAccountDAOService;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDAOService implements IAccountDAOService {

	@Autowired
	private IAccountDAORepository accountDaoRepository;

	@Autowired
	private IExpenseDAORepository expenseDaoRepository;

	@Autowired
	private ModelMapper modelMapper;


	@Override
	public boolean createNewAccount(UserDAO userDAO) {
		Optional<AccountDAO> accountDaoOptional = accountDaoRepository.getAccountByOwnerId(userDAO.getId());
		if (accountDaoOptional.isPresent()) {
			return false;
		}
		AccountDAO accountDao = AccountDAO.builder()
				.owner(userDAO)
				.build();
		accountDaoRepository.save(accountDao);
		return true;
	}

	@Override
	public AccountDTO getAccount(UserDAO userDAO) {
		Optional<AccountDAO> optionalAccountDao = accountDaoRepository.getAccountByOwnerId(userDAO.getId());
		if (optionalAccountDao.isEmpty()) {
			return null;
		}
		AccountDAO accountDao = optionalAccountDao.get();
		return modelMapper.map(accountDao, AccountDTO.class);
	}
}
