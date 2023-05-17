package com.magic.models.services;

import java.util.List;

import javax.validation.Valid;

import com.magic.models.Appointment;

public interface AppointmentService {

	void saveAppointment(@Valid Appointment appointment);

	Appointment deleteAppointment(@Valid String appId);

	Appointment updateAppointment(Appointment updatedAppointment, @Valid String appId);
	
	List<Appointment> getAppointmentList();

	Appointment updateAppointmentStatus(Appointment updatedAppointment, @Valid String appId);

}