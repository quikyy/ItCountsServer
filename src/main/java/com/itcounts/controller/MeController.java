package com.itcounts.controller;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.user.UserDTO;
import com.itcounts.repository.IUserDAORepository;
import com.itcounts.service.implementation.UserDAOService;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api")
@RestController
public class MeController {

	@Autowired
	private IUserDAORepository userDaoRepository;

	@Autowired
	private UserDAOService userDaoService;

	@GetMapping(path = "/me")
	public ResponseEntity<String> getMe(Principal principal) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(principal.getName());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
