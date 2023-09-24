package com.itcounts.controller;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.summary.SummaryDTO;
import com.itcounts.repository.IUserDAORepository;
import com.itcounts.service.implementation.SummaryService;
import java.security.Principal;
import java.sql.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class SummaryController {

	@Autowired
	private IUserDAORepository userDaoRepository;

	@Autowired
	private SummaryService summaryService;

	@GetMapping(path = "/summary")
	public ResponseEntity<?> getSummary(Principal principal,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		SummaryDTO response = summaryService.getSummary(optionalUserDao.get(), startDate, endDate);
		if (response == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
