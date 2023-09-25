package com.itcounts.controller;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.expense.ExpenseDTO;
import com.itcounts.model.dto.expense.ExpenseDTOBucket;
import com.itcounts.repository.IUserDAORepository;
import com.itcounts.requestbody.ExpenseBodyDTO;
import com.itcounts.requestbody.ExpenseEditBodyDTO;
import com.itcounts.service.implementation.ExpenseDAOService;
import java.math.BigInteger;
import java.security.Principal;
import java.sql.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping(path = "/expenses")
	public ResponseEntity<ExpenseDTOBucket> getExpenses(Principal principal,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate,
			@RequestParam(value = "categoryId", required = false) BigInteger categoryId) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		ExpenseDTOBucket response = expenseDaoService.getExpenses(optionalUserDao.get(), startDate, endDate, categoryId);
		if (response == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

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

	@GetMapping(path = "/expense/{id}")
	public ResponseEntity<ExpenseDTO> getExpense(Principal principal, @PathVariable("id") BigInteger expenseId) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		ExpenseDTO response = expenseDaoService.getExpense(optionalUserDao.get(), expenseId);
		if (response == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/expense/{id}")
	public ResponseEntity<ExpenseDTO> editExpense(Principal principal, @PathVariable("id") BigInteger expenseId, @RequestBody ExpenseEditBodyDTO expenseEditBodyDto) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		ExpenseDTO response = expenseDaoService.editExpense(optionalUserDao.get(), expenseId, expenseEditBodyDto);
		if (response == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/expense/{id}")
	public ResponseEntity<String> deleteExpense(Principal principal, @PathVariable("id") BigInteger expenseId) {
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
