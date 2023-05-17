package com.magic.models.services.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magic.models.Appointment;
import com.magic.models.services.AppointmentService;

@RestController
@RequestMapping("hospital/appointment")
public class AppointmentController {
	@Autowired
	AppointmentService appServ;

	// Add a Appointment
	@PostMapping("/add")
	public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody Appointment appointment) {
		appServ.saveAppointment(appointment);
		return ResponseEntity.status(HttpStatus.OK).body(appointment);
	}

	// delete a Appointment
	@DeleteMapping("/delete/{appId}")
	public ResponseEntity<Appointment> deleteAppointment(@Valid @PathVariable String appId) {
		Appointment appointment = appServ.deleteAppointment(appId);
		return ResponseEntity.status(HttpStatus.OK).body(appointment);
	}

	// update a Appointment by ID and Put request
	@PutMapping("/update/{appId}")
	public ResponseEntity<Appointment> updateAppointment(@Valid @PathVariable String appId,
			@RequestBody Appointment updatedAppointment) {
		Appointment appointment = appServ.updateAppointment(updatedAppointment, appId);
		return ResponseEntity.status(HttpStatus.OK).body(appointment);
	}

	// get list of all Appointments
	@PutMapping("/list")
	public ResponseEntity<List<Appointment>> getAllAppointment() {
		List<Appointment> appointments = appServ.getAppointmentList();
		return ResponseEntity.status(HttpStatus.OK).body(appointments);
	}

	// update a Appointment Status by ID and Patch request
	@PatchMapping("/update/status/{appId}")
	public ResponseEntity<Appointment> updateAppointmentStaus(@Valid @PathVariable String appId,
			@RequestBody Appointment updatedAppointment) {
		Appointment appointment = appServ.updateAppointmentStatus(updatedAppointment, appId);
		return ResponseEntity.status(HttpStatus.OK).body(appointment);
	}

}
