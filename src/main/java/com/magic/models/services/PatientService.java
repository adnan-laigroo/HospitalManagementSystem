package com.magic.models.services;

import java.util.List;

import javax.validation.Valid;

import com.magic.models.Patient;

public interface PatientService {

	void savePatient(@Valid Patient patient);

	Patient deletePatient(@Valid String patId);

	Patient updatePatient(Patient updatedPatient, @Valid String patId);

	List<Patient> getPatientList();

}