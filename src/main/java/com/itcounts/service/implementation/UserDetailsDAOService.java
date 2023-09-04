package com.itcounts.service.implementation;

import com.itcounts.model.dao.GenderDAO;
import com.itcounts.model.dao.user.UserDetailsDAO;
import com.itcounts.model.dto.auth.request.RegisterRequestDTO;
import com.itcounts.repository.IGenderDAORepository;
import com.itcounts.service.interfaces.IUserDetailsDAOService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsDAOService implements IUserDetailsDAOService {

	@Autowired
	private IGenderDAORepository genderDaoRepository;


	@Override
	public UserDetailsDAO buildUserDetails(RegisterRequestDTO registerRequestDTO) {
		UserDetailsDAO userDetailsDao = new UserDetailsDAO();
		userDetailsDao.setFirstName(registerRequestDTO.getFirstName() == null ? null : registerRequestDTO.getFirstName());
		userDetailsDao.setLastName(registerRequestDTO.getLastName() == null ? null : registerRequestDTO.getLastName());
		userDetailsDao.setDateOfBirth(registerRequestDTO.getDate() == null ? null : registerRequestDTO.getDate());
		if (registerRequestDTO.getGender() != null) {
			Optional<GenderDAO> genderDaoOptional = genderDaoRepository.getGenderByName(registerRequestDTO.getGender());
			genderDaoOptional.ifPresent(userDetailsDao::setGenderDao);
		}
		return userDetailsDao;
	}
}
