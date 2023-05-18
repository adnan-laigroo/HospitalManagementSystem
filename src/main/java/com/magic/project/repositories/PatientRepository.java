package com.magic.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.project.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
