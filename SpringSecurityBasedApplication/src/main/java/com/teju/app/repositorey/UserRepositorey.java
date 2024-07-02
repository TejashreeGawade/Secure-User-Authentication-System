package com.teju.app.repositorey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teju.app.model.User;

@Repository
public interface UserRepositorey extends JpaRepository<User, Long > {
	
	User findByEmail(String email);

}
