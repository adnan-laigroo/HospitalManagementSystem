package com.magic.models.services.implementation;

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
import com.magic.models.Appointment;
import com.magic.models.Doctor;
import com.magic.models.Patient;
import com.magic.models.repositories.AppointmentRepository;
import com.magic.models.repositories.DoctorRepository;
import com.magic.models.repositories.PatientRepository;
import com.magic.models.services.AppointmentService;
import com.magic.project.handler.DoctorNotFoundException;
import com.magic.project.handler.PatientNotFoundException;

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
		Patient patient = patRepo.findById(appointment.getPatId()).orElse(null);
		if (patient == null) {
			throw new PatientNotFoundException("No patient found of patient Id: " + appointment.getPatId());
		}
		List<Doctor> doctorList = docRepo.findBySpeciality(symptomSpecialityMap.get(patient.getSymptom()));
		if (doctorList.isEmpty()) {
			throw new DoctorNotFoundException("No doctor found for symptom " + patient.getSymptom());
		}
		appointment.setDocId(getRandomDoctor(doctorList).getEmail());
		appRepo.save(appointment);
	}

	@Override
	public Appointment deleteAppointment(@Valid String apId) {
		Appointment appointment = appRepo.findById(apId).orElse(null);
//		if (Appointment==null){
//			throw  new Exception("No Appointment with ID "+appRepo);
//		}
		appRepo.deleteById(apId);
		return appointment;
	}

	@Override
	public Appointment updateAppointment(Appointment updatedAppointment, @Valid String apId) {
		updatedAppointment.setApId(apId);
		appRepo.save(updatedAppointment);
		return updatedAppointment;
	}

	@Override
	public List<Appointment> getAppointmentList() {
		List<Appointment> Appointments = appRepo.findAll();
		return Appointments;
	}

	@Override
	public Appointment updateAppointmentStatus(Appointment updatedAppointment, @Valid String apId) {
		Appointment appointment = appRepo.findById(apId).orElse(null);
		appointment.setAppointmentStatus(updatedAppointment.getAppointmentStatus());
		appRepo.save(appointment);
		return updatedAppointment;
	}
}