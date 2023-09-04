package com.itcounts.repository;

import com.itcounts.model.dao.GenderDAO;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenderDAORepository extends CrudRepository<GenderDAO, BigInteger> {

	@Query(value = "SELECT * FROM genders WHERE name = 'Male'", nativeQuery = true)
	GenderDAO getMaleGenderDao();

	@Query(value = "SELECT * FROM genders WHERE name = 'Female'", nativeQuery = true)
	GenderDAO getFemaleGenderDao();

	@Query(value = "SELECT * FROM genders WHERE name = :name", nativeQuery = true)
	Optional<GenderDAO> getGenderByName(@Param("name") String name);


}
