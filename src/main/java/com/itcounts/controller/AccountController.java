package com.itcounts.controller;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.repository.IUserDAORepository;
import com.itcounts.service.implementation.AccountDAOService;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@PostMapping(path = "/account/create")
	public ResponseEntity<String> createAccount(Principal principal) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		boolean isAccountCreated = accountDaoService.createNewAccount(optionalUserDao.get());
		if (!isAccountCreated) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}