package com.teju.app.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teju.app.dto.UserRegitrationDto;
import com.teju.app.model.Role;
import com.teju.app.model.User;
import com.teju.app.repositorey.UserRepositorey;

@Service
public class UserServiceImpl implements UserService {

	
	private UserRepositorey userRepositorey;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepositorey userRepositorey) {
		super();
		this.userRepositorey = userRepositorey;
	}


	@Override
	//here usrregistration data will save in User object and through this it will store in user table.
	public User save(UserRegitrationDto userRegitrationDto) {
		// TODO Auto-generated method stub
		
		User user = new User(userRegitrationDto.getFirstName(),
							 userRegitrationDto.getLastName(),
							 userRegitrationDto.getEmail(),
							 passwordEncoder.encode(userRegitrationDto.getPassword()),
							 Arrays.asList(new Role("ROLE_USER")));
		return userRepositorey.save(user);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepositorey.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
