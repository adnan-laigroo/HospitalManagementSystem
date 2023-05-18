package com.magic.project.services;

import java.util.List;

import javax.validation.Valid;

import com.magic.project.models.Doctor;

public interface DoctorService {

	void saveDoctor(@Valid Doctor doctor);

	Doctor deleteDoctor(@Valid String email);

	Doctor updateDoctor(Doctor updatedDoctor, @Valid String email);

	List<Doctor> getDoctorList();

}