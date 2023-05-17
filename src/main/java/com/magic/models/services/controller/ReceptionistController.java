package com.magic.models.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magic.models.services.ReceptionistService;

@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {
	@Autowired
	ReceptionistService recepServ;
}
