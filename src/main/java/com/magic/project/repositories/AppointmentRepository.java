package com.magic.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.project.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

}