package com.magic.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magic.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
