package com.magic.models.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.magic.models.repositories.DoctorRepository;
import com.magic.models.services.DoctorService;

@Service
public class DoctorServiceImplementation implements DoctorService {
	@Autowired
	DoctorRepository docRepo;
}