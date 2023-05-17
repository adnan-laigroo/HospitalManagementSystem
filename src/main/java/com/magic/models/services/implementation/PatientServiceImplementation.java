package com.magic.models.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.models.Patient;
import com.magic.models.repositories.PatientRepository;
import com.magic.models.services.PatientService;

@Service
public class PatientServiceImplementation implements PatientService {
	@Autowired
	PatientRepository patRepo;

	@Override
	public void savePatient(@Valid Patient Patient) {
		patRepo.save(Patient);

	}

	@Override
	public Patient deletePatient(@Valid String patId) {
		Patient Patient = patRepo.findById(patId).orElse(null);
//		if (Patient==null){
//			throw  new Exception("No Patient with ID "+patId);
//		}
		patRepo.deleteById(patId);
		return Patient;
	}

	@Override
	public Patient updatePatient(Patient updatedPatient, @Valid String patId) {
		updatedPatient.setPatId(patId);
		patRepo.save(updatedPatient);
		return updatedPatient;
	}

	@Override
	public List<Patient> getPatientList() {
		List<Patient> Patients = patRepo.findAll();
		return Patients;
	}
}