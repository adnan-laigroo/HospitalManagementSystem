package com.magic.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}