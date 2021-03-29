package com.bootproject.mybootpro.service;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bootproject.mybootpro.model.Role;
import com.bootproject.mybootpro.model.User;
import com.bootproject.mybootpro.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	public User save(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setEnabled(true);
		Role role = new Role();
		role.setId(1l);
		user.getRoles().add(role);
		return userRepository.save(user);
	}

	
}
