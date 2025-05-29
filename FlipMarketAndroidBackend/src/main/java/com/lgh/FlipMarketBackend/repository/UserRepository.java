package com.lgh.FlipMarketBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.lgh.FlipMarketBackend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@NativeQuery("SELECT * FROM user WHERE email = ?1")
	Optional<User> findByEmail(String email);
	
}
