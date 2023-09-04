package com.itcounts.controller;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.account.ExpenseDTO;
import com.itcounts.repository.IUserDAORepository;
import com.itcounts.requestbody.ExpenseBodyDTO;
import com.itcounts.service.implementation.ExpenseDAOService;
import java.math.BigInteger;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api")
@RestController
public class ExpenseController {

	@Autowired
	private IUserDAORepository userDaoRepository;

	@Autowired
	private ExpenseDAOService expenseDaoService;

	@PostMapping(path = "/expense")
	public ResponseEntity<ExpenseDTO> addExpense(Principal principal, @RequestBody ExpenseBodyDTO expenseBodyDto) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		ExpenseDTO response = expenseDaoService.addExpense(optionalUserDao.get(), expenseBodyDto);
		if (response == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/expense")
	public ResponseEntity<String> deleteExpense(Principal principal, @RequestParam("expenseId") BigInteger expenseId) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		boolean isDeleted = expenseDaoService.deleteExpense(optionalUserDao.get(), expenseId);
		if (!isDeleted) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
