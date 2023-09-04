package com.itcounts.service.implementation;

import com.itcounts.model.dao.account.AccountDAO;
import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.repository.IAccountDAORepository;
import com.itcounts.service.interfaces.IAccountDAOService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDAOService implements IAccountDAOService {

	@Autowired
	private IAccountDAORepository accountDaoRepository;


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
}
