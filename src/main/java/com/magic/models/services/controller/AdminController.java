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
import com.magic.models.Doctor;
import com.magic.models.Patient;
import com.magic.models.Receptionist;
import com.magic.models.User;
import com.magic.models.dto.DoctorDto;
import com.magic.models.dto.DoctorUserMapper;
import com.magic.models.dto.ReceptionistDto;
import com.magic.models.dto.ReceptionistUserMapper;
import com.magic.models.services.AppointmentService;
import com.magic.models.services.DoctorService;
import com.magic.models.services.PatientService;
import com.magic.models.services.ReceptionistService;
import com.magic.models.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	DoctorService docServ;
	@Autowired
	ReceptionistService recepServ;
	@Autowired
	PatientService patServ;
	@Autowired
	AppointmentService appServ;
	@Autowired
	UserService userServ;

	@RequestMapping("/doctor")
	public class DoctorController {

		// Add an doctor
		@PostMapping("/add")
		public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody DoctorDto doctorDto) {
			Doctor doctor = DoctorUserMapper.mapToDoctor(doctorDto);
			User user = DoctorUserMapper.mapToUser(doctorDto);
			userServ.saveUser(user);
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
		public ResponseEntity<Doctor> updateDoctor(@Valid @PathVariable String email,
				@RequestBody Doctor updatedDoctor) {
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

	@RequestMapping("/receptionist")
	public class ReceptionistController {
		// Add a receptionist
		@PostMapping("/add")
		public ResponseEntity<Receptionist> addReceptionist(@Valid @RequestBody ReceptionistDto receptionistDto) {
			Receptionist receptionist = ReceptionistUserMapper.mapToReceptionist(receptionistDto);
			User user = ReceptionistUserMapper.mapToUser(receptionistDto);
			userServ.saveUser(user);
			recepServ.saveReceptionist(receptionist);
			return ResponseEntity.status(HttpStatus.OK).body(receptionist);
		}

		// delete a receptionist
		@DeleteMapping("/delete/{email}")
		public ResponseEntity<Receptionist> deleteReceptionist(@Valid @PathVariable String email) {
			Receptionist receptionist = recepServ.deleteReceptionist(email);
			return ResponseEntity.status(HttpStatus.OK).body(receptionist);
		}

		// update a receptionist by ID and Put request
		@PutMapping("/update/{email}")
		public ResponseEntity<Receptionist> updateReceptionist(@Valid @PathVariable String email,
				@RequestBody Receptionist updatedReceptionist) {
			Receptionist receptionist = recepServ.updateReceptionist(updatedReceptionist, email);
			return ResponseEntity.status(HttpStatus.OK).body(receptionist);
		}

		// get list of all receptionists
		@PutMapping("/list")
		public ResponseEntity<List<Receptionist>> getAllReceptionist() {
			List<Receptionist> receptionists = recepServ.getReceptionistList();
			return ResponseEntity.status(HttpStatus.OK).body(receptionists);
		}

	}

	@RequestMapping("/patient")
	public class PatientController {

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

	@RequestMapping("/appointment")
	public class AppointmentController {

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

	@RequestMapping("/user")
	public class UserController {

		// update a user password by ID and Patch request
		@PatchMapping("/update/password/{username}")
		public ResponseEntity<User> updateAppointmentStaus(@Valid @PathVariable String username,
				@RequestBody User updatedPassword) {
			User user = userServ.updateUserPassword(updatedPassword, username);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
	}
}
