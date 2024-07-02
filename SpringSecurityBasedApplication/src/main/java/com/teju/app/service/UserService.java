package com.teju.app.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.teju.app.dto.UserRegitrationDto;
import com.teju.app.model.User;

public interface UserService extends UserDetailsService{
	
	User save(UserRegitrationDto userRegitrationDto);

}
