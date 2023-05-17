package com.magic.models.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.magic.models.services.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	DoctorService docServ;

}
