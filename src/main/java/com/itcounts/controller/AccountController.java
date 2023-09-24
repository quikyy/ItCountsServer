package com.itcounts.controller;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.account.AccountDTO;
import com.itcounts.repository.IUserDAORepository;
import com.itcounts.service.implementation.AccountDAOService;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class AccountController {

	@Autowired
	private IUserDAORepository userDaoRepository;

	@Autowired
	private AccountDAOService accountDaoService;

	@GetMapping(path = "/account")
	public ResponseEntity<AccountDTO> getAccount(Principal principal) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		AccountDTO response = accountDaoService.getAccount(optionalUserDao.get());
		if (response == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
