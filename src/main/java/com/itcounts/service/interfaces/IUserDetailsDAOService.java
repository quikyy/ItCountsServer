package com.itcounts.service.interfaces;

import com.itcounts.model.dao.user.UserDetailsDAO;
import com.itcounts.model.dto.auth.request.RegisterRequestDTO;

public interface IUserDetailsDAOService {

	UserDetailsDAO buildUserDetails(RegisterRequestDTO registerRequestDTO);

}
