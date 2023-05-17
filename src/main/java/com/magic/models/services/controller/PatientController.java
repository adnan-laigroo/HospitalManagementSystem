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

import com.magic.models.Patient;
import com.magic.models.services.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	PatientService patServ;

	// Add a Patient
	@PostMapping("/add")
	public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
		patServ.savePatient(patient);
		return ResponseEntity.status(HttpStatus.OK).body(patient);
	}

	// delete a Patient
	@DeleteMapping("/delete/{patId}")
	public ResponseEntity<Patient> deletePatient(@Valid @PathVariable String patId) {
		Patient patient = patServ.deletePatient(patId);
		return ResponseEntity.status(HttpStatus.OK).body(patient);
	}

	// update a Patient by ID and Put request
	@PutMapping("/update/{patId}")
	public ResponseEntity<Patient> updatePatient(@Valid @PathVariable String patId,
			@RequestBody Patient updatedPatient) {
		Patient patient = patServ.updatePatient(updatedPatient, patId);
		return ResponseEntity.status(HttpStatus.OK).body(patient);
	}

	// get list of all Patients
	@PutMapping("/list")
	public ResponseEntity<List<Patient>> getAllPatient() {
		List<Patient> patients = patServ.getPatientList();
		return ResponseEntity.status(HttpStatus.OK).body(patients);
	}
}
