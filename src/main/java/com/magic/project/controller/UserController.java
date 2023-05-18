package com.magic.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magic.project.models.Password;
import com.magic.project.models.User;
import com.magic.project.services.UserService;

@RestController
@RequestMapping("hospital/user")
public class UserController {
	@Autowired
	UserService userServ;

	// update a user password by ID and Patch request
	@PatchMapping("/update/password/{username}")
	public ResponseEntity<User> updateUserPassword(@Valid @PathVariable String username,
			@Valid @RequestBody Password updatedPassword) {
		User user = userServ.updateUserPassword(updatedPassword, username);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	// get list of all user
	@GetMapping("/list")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = userServ.getUserList();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

}
