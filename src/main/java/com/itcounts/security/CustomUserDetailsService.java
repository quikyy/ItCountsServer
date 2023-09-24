package com.itcounts.security;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.repository.IUserDAORepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserDAORepository userDaoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserDAO> optionalUserDao = userDaoRepository.findUserByEmail(username);
		if (optionalUserDao.isEmpty()) {
			throw new UsernameNotFoundException("User with login: " + username + " not found.");
		}
		UserDAO userDao = optionalUserDao.get();
		List<GrantedAuthority> roles = new ArrayList<>();
		return new User(userDao.getEmail(), userDao.getPassword(), roles);
	}
}
