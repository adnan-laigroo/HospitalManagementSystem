package com.magic.models.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.models.repositories.AppointmentRepository;
import com.magic.models.services.AppointmentService;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
	
	@Autowired
	AppointmentRepository appRepo;
}