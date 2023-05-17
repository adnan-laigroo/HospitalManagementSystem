package com.magic.models.services.implementation;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.magic.models.Doctor;
import com.magic.models.repositories.DoctorRepository;
import com.magic.models.services.DoctorService;

@Service
public class DoctorServiceImplementation implements DoctorService {
	@Autowired
	DoctorRepository docRepo;

	@Override
	public void saveDoctor(@Valid Doctor doctor) {
		docRepo.save(doctor);
		
	}

	@Override
	public Doctor deleteDoctor(@Valid String email) {
		Doctor doctor = docRepo.findById(email).orElse(null);
//		if (doctor==null){
//			throw  new Exception("No doctor with ID "+email);
//		}
		docRepo.deleteById(email);
		return doctor;
	}

	@Override
	public Doctor updateDoctor(Doctor updatedDoctor, @Valid String email) {
		updatedDoctor.setEmail(email);
		docRepo.save(updatedDoctor);
		return updatedDoctor;
	}

	@Override
	public List<Doctor> getDoctorList() {
		List<Doctor> doctors = docRepo.findAll();
		return doctors;
	}
}