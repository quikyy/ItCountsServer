package com.itcounts.service.interfaces;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.account.AccountDTO;

public interface IAccountDAOService {

	boolean createNewAccount(UserDAO userDAO);
	AccountDTO getAccount(UserDAO userDAO);
}
