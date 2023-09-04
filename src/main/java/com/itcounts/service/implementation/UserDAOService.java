package com.itcounts.service.implementation;

import com.itcounts.model.dao.user.UserDAO;
import com.itcounts.model.dao.user.UserDetailsDAO;
import com.itcounts.model.dto.user.UserDTO;
import com.itcounts.service.interfaces.IUserDAOService;
import org.springframework.stereotype.Service;

@Service
public class UserDAOService implements IUserDAOService {

	@Override
	public UserDTO getUserDto(UserDAO userDao) {
		UserDetailsDAO userDetailsDao = userDao.getUserDetailsDao();
		return UserDTO.builder()
				.id(userDao.getId())
				.email(userDao.getEmail())
				.firstName(userDetailsDao.getFirstName())
				.lastName(userDetailsDao.getLastName())
				.registerDate(userDao.getRegisterDate())
				.build();
	}
}
