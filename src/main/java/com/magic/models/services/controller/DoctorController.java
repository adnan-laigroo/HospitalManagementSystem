package com.magic.models.services.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magic.models.Doctor;
import com.magic.models.services.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	DoctorService docServ;

	// Add an doctor
	@PostMapping("/add")
	public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {
		docServ.saveDoctor(doctor);
		return ResponseEntity.status(HttpStatus.OK).body(doctor);
	}

	// delete a doctor
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<Doctor> deleteDoctor(@Valid @PathVariable String email) {
		Doctor doctor = docServ.deleteDoctor(email);
		return ResponseEntity.status(HttpStatus.OK).body(doctor);
	}

	// update a doctor by ID and Put request
	@PutMapping("/update/{email}")
	public ResponseEntity<Doctor> updateDoctor(@Valid @PathVariable String email, @RequestBody Doctor updatedDoctor) {
		Doctor doctor = docServ.updateDoctor(updatedDoctor, email);
		return ResponseEntity.status(HttpStatus.OK).body(doctor);
	}

	// get list of all doctors
	@PutMapping("/list")
	public ResponseEntity<List<Doctor>> getAllDoctor() {
		List<Doctor> doctors = docServ.getDoctorList();
		return ResponseEntity.status(HttpStatus.OK).body(doctors);
	}

}
