package com.magic.models.services;

import javax.validation.Valid;

import com.magic.models.User;

public interface UserService {

	User updateUserPassword(User updatedPassword, @Valid String username);

	void saveUser(User user);

}