package com.magic.models.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.magic.models.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userServ;
}
