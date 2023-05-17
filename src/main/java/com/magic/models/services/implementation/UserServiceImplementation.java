package com.magic.models.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.models.Receptionist;
import com.magic.models.User;
import com.magic.models.repositories.UserRepository;
import com.magic.models.services.UserService;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepository userRepo;

	@Override
	public User updateUserPassword(User updatedPassword, @Valid String username) {
		User user = userRepo.findById(username).orElse(null);
		user.setPassword(updatedPassword.getPassword());
		userRepo.save(user);
		return user;
	}

	@Override
	public void saveUser(User user) {
		userRepo.save(user);
	}

	@Override
	public List<User> getUserList() {
		List<User> users = userRepo.findAll();
		return users;
	}

}
