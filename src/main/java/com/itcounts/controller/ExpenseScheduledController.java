package com.itcounts.controller;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.account.AccountDTO;
import com.itcounts.model.dto.expense.ExpenseScheduledDTO;
import com.itcounts.repository.IUserDAORepository;
import com.itcounts.requestbody.ExpenseScheduledBodyDTO;
import com.itcounts.service.implementation.ExpenseScheduledDAOService;
import java.math.BigInteger;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/expense")
public class ExpenseScheduledController {


	@Autowired
	private IUserDAORepository userDaoRepository;

	@Autowired
	private ExpenseScheduledDAOService expenseScheduledDaoService;


	@PostMapping(path = "/schedule")
	public ResponseEntity<ExpenseScheduledDTO> createExpenseScheduled(Principal principal, @RequestBody ExpenseScheduledBodyDTO expenseScheduledBodyDto) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		ExpenseScheduledDTO response = expenseScheduledDaoService.createExpenseScheduled(optionalUserDao.get(), expenseScheduledBodyDto);
		if (response == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/schedule/{id}")
	public ResponseEntity<String> deleteExpenseScheduled(Principal principal, @PathVariable("id") BigInteger expenseId, @RequestParam("expenseScheduledId") BigInteger expenseScheduledId) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		boolean isDeleted = expenseScheduledDaoService.deleteExpenseScheduled(optionalUserDao.get(), expenseScheduledId);
		if (!isDeleted) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
