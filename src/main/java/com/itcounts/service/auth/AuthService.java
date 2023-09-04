package com.itcounts.service.auth;


import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dto.auth.request.RegisterRequestDTO;
import com.itcounts.model.dto.auth.response.LoginResponseDTO;
import com.itcounts.model.dto.auth.response.RegisterResponseDTO;
import com.itcounts.repository.IUserDAORepository;
import com.itcounts.security.jwt.JWTUtility;
import com.itcounts.service.implementation.AccountDAOService;
import com.itcounts.service.implementation.UserDAOService;
import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private IUserDAORepository userDaoRepository;

	@Autowired
	private UserDAOService userDaoService;
	@Autowired
	private AccountDAOService accountDaoService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private PasswordEncoder encoder;


	public RegisterResponseDTO registerUser(RegisterRequestDTO registerRequestDto) {
		UserDAO userDao = UserDAO.builder()
				.email(registerRequestDto.getEmail())
				.password(encoder.encode(registerRequestDto.getPassword()))
				.registerDate(Timestamp.from(Instant.now()))
				.firstName(registerRequestDto.getFirstName())
				.lastName(registerRequestDto.getLastName())
				.build();
		userDao = userDaoRepository.save(userDao);
		if (registerRequestDto.isCreateAccount()) {
			accountDaoService.createNewAccount(userDao);
		}
		return new RegisterResponseDTO(true, "User registered.");
	}

	public LoginResponseDTO loginUser(String token) {
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.setToken(token);
		loginResponseDTO.setExpiresAt(jwtUtility.getExpirationDateFromJsonWebToken(token));
		return loginResponseDTO;
	}
}
