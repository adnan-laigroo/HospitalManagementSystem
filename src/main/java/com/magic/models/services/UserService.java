package com.magic.models.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.magic.models.Password;
import com.magic.models.User;

public interface UserService extends UserDetailsService {

	User updateUserPassword(@Valid Password updatedPassword, @Valid String username);

	void saveUser( User user);

	List<User> getUserList();

	org.springframework.security.core.userdetails.User loadUserByUsername(String username)
			throws UsernameNotFoundException;

}