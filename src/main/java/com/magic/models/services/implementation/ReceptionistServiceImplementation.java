package com.magic.models.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.magic.models.repositories.ReceptionistRepository;
import com.magic.models.services.ReceptionistService;

@Service
public class ReceptionistServiceImplementation implements ReceptionistService {
	@Autowired
	ReceptionistRepository recepRepo;
}