package com.itcounts.repository;

import com.itcounts.model.dao.user.UserDetailsDAO;
import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDetailsDAORepository extends CrudRepository<UserDetailsDAO, BigInteger> {

}
