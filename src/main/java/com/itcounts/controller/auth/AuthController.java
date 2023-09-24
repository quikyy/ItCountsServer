package com.itcounts.controller.auth;


import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.auth.request.LoginRequestDTO;
import com.itcounts.model.dto.auth.request.RegisterRequestDTO;
import com.itcounts.model.dto.auth.response.LoginResponseDTO;
import com.itcounts.model.dto.auth.response.RegisterResponseDTO;
import com.itcounts.repository.IUserDAORepository;
import com.itcounts.security.jwt.JWTUtility;
import com.itcounts.service.implementation.UserDAOService;
import com.itcounts.service.auth.AuthService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private IUserDAORepository userDaoRepository;

	@Autowired
	private UserDAOService userDaoService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthService authService;


	@PostMapping(path = "/register")
	public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDto) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(registerRequestDto.getEmail());
		if (optionalUserDao.isPresent()) {
			return new ResponseEntity<>(new RegisterResponseDTO(false, "Email already taken"), HttpStatus.BAD_REQUEST);
		}

		RegisterResponseDTO response = authService.registerUser(registerRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(loginRequestDTO.getEmail());
		if (optionalUserDao.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
		if (!authentication.isAuthenticated()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtUtility.generateJsonWebToken(authentication);

		LoginResponseDTO response = authService.loginUser(token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
