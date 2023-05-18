package com.magic.project.services.implementation;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.magic.project.models.Password;
import com.magic.project.models.User;
import com.magic.project.repositories.UserRepository;
import com.magic.project.services.UserService;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepo;
	
	private BCryptPasswordEncoder userPasswordEncoder = new BCryptPasswordEncoder();

	
	@Override
	public User updateUserPassword(@Valid Password updatedPassword, String username) {
		User user = userRepo.findById(username).orElse(null);
		user.setPassword(userPasswordEncoder.encode(updatedPassword.getPassword()));
		userRepo.save(user);
		return user;
	}

	@Override
	public void saveUser(@Valid User user) {
		user.setPassword(userPasswordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}
	

	@Override
	public List<User> getUserList() {
		List<User> users = userRepo.findAll();
		return users;
	}

	@Override
	public org.springframework.security.core.userdetails.User loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepo.findById(username).orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
				getAuthorities(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
	}



}
