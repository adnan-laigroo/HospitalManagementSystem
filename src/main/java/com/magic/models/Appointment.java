package com.magic.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(generator = "appointment_sequence_generator")
	@GenericGenerator(name = "appointment_sequence_generator", strategy = "com.magic.models.AppointmentSequenceGenerator")
	private String apId;

	@NotBlank(message = "Patient ID cannot be blank")
	private String patId;

	@NotBlank(message = "Blood group cannot be blank")
	@Pattern(regexp = "^(O|A|B|AB)[+-]$", message = "Invalid blood group")
	private String bloodGroup;

	@NotBlank(message = "Doctor ID cannot be blank")
	private String docId;

	private LocalDate appointmentDate;

	@NotBlank(message = "Appointment time cannot be blank")
	private String appointmentTime;

	@NotBlank(message = "Appointment status cannot be blank")
	private String appointmentStatus;

	public String getApId() {
		return apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}

	public String getPatId() {
		return patId;
	}

	public void setPatId(String patId) {
		this.patId = patId;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

}
