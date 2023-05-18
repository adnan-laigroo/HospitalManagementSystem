package com.magic.project.services.implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.project.handler.AppointmentNotConfirmedException;
import com.magic.project.handler.DoctorNotFoundException;
import com.magic.project.handler.PatientNotFoundException;
import com.magic.project.models.Appointment;
import com.magic.project.models.Doctor;
import com.magic.project.models.Patient;
import com.magic.project.repositories.AppointmentRepository;
import com.magic.project.repositories.DoctorRepository;
import com.magic.project.repositories.PatientRepository;
import com.magic.project.services.AppointmentService;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

	final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	private static Map<String, String> symptomSpecialityMap = new HashMap<>();
	static {
		symptomSpecialityMap.put("Arthritis", "Orthopedic");
		symptomSpecialityMap.put("Backpain", "Orthopedic");
		symptomSpecialityMap.put("Tissue injuries", "Orthopedic");
		symptomSpecialityMap.put("Dysmenorrhea", "Gynecology");
		symptomSpecialityMap.put("Skin infection", "Dermatology");
		symptomSpecialityMap.put("Skin burn", "Dermatology");
		symptomSpecialityMap.put("Ear pain", "ENT");
	}

	public static <T> T getRandomDoctor(List<T> list) {
		Random random = new Random();
		int index = random.nextInt(list.size());
		return list.get(index);
	}

	@Autowired
	DoctorRepository docRepo;

	@Autowired
	AppointmentRepository appRepo;

	@Autowired
	PatientRepository patRepo;

	@Override
	public void saveAppointment(@Valid Appointment appointment) {
		appointment.setAppointmentDate(LocalDate.now());
		appointment.setAppointmentTime(LocalTime.parse(appointment.getAppointmentTime(), formatter).format(formatter));
		appointment.setAppointmentStatus("Pending");
		if (LocalTime.parse(appointment.getAppointmentTime(), formatter).isBefore(LocalTime.now())) {
			throw new AppointmentNotConfirmedException("Appointment for time: " + appointment.getAppointmentTime()
					+ " can't be booked before: " + LocalTime.now());
		}
		Patient patient = patRepo.findById(appointment.getPatId()).orElse(null);
		if (patient == null) {
			throw new PatientNotFoundException("No patient found of patient Id: " + appointment.getPatId());
		}
		List<Doctor> doctorList = docRepo.findBySpeciality(symptomSpecialityMap.get(patient.getSymptom()));
		if (doctorList.isEmpty()) {
			throw new DoctorNotFoundException("No doctor found for symptom " + patient.getSymptom());
		}
		appointment.setDocId(getDoctorWithLeastPendingAppointments(doctorList));
		appRepo.save(appointment);
	}

	private String getDoctorWithLeastPendingAppointments(List<Doctor> doctorList) {
		String doctorWithLeastPendingAppointments = doctorList.get(0).getEmail();
		int minPendingAppointments = countPendingAppointments(doctorWithLeastPendingAppointments);
		for (Doctor doctor : doctorList) {
			int pendingAppointments = countPendingAppointments(doctor.getEmail());
			if (pendingAppointments < minPendingAppointments) {
				minPendingAppointments = pendingAppointments;
				doctorWithLeastPendingAppointments = doctor.getEmail();
			}
		}
		return doctorWithLeastPendingAppointments;
	}

	private int countPendingAppointments(String email) {
		int count = 0;
		List<Appointment> appointments = appRepo.findByDocId(email);
		for (Appointment appointment : appointments) {
			if (appointment.getAppointmentStatus().equals("Pending")) {
				count++;
			}
		}
		return count;
	}

	@Override
	public Appointment deleteAppointment(@Valid String apId) {
		Appointment appointment = appRepo.findById(apId).orElse(null);
		if (appointment == null) {
			throw new AppointmentNotConfirmedException("No Appointment with ID " + appRepo);
		}
		appRepo.deleteById(apId);
		return appointment;
	}

	@Override
	public Appointment updateAppointment(Appointment updatedAppointment, @Valid String apId) {
		Appointment appointment = appRepo.findById(apId).orElse(null);
		if (appointment == null) {
			throw new AppointmentNotConfirmedException("No Appointment with ID " + appRepo);
		}
		updatedAppointment.setApId(apId);
		appRepo.save(updatedAppointment);
		return updatedAppointment;
	}

	@Override
	public List<Appointment> getAppointmentList() {
		List<Appointment> appointments = appRepo.findAll();
		if (appointments.isEmpty()) {
			throw new AppointmentNotConfirmedException("No Appointments." + appRepo);
		}
		return appointments;
	}

	@Override
	public Appointment updateAppointmentStatus(Appointment updatedAppointment, @Valid String apId) {
		Appointment appointment = appRepo.findById(apId).orElse(null);
		if (appointment == null) {
			throw new AppointmentNotConfirmedException("No Appointment with ID " + appRepo);
		}
		appointment.setAppointmentStatus(updatedAppointment.getAppointmentStatus());
		appRepo.save(appointment);
		return appointment;
	}
}
