package com.magic.models.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.magic.models.repositories.PatientRepository;
import com.magic.models.services.PatientService;

@Service
public class PatientServiceImplementation implements PatientService {
	@Autowired
	PatientRepository patRepo;
}