package com.itcounts.repository;

import com.itcounts.model.dao.user.UserDAO;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDAORepository extends JpaRepository<UserDAO, BigInteger> {

	@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
	Optional<UserDAO> findUserByEmail(@Param("email") String email);

}
