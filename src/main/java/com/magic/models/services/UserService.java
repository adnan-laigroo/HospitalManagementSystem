package com.magic.models.services;

import java.util.List;

import javax.validation.Valid;

import com.magic.models.User;

public interface UserService {

	User updateUserPassword(User updatedPassword, @Valid String username);

	void saveUser(User user);

	List<User> getUserList();

}