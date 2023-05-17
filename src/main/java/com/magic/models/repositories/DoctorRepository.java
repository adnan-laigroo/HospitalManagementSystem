package com.magic.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

	List<Doctor> findBySpeciality(String speciality);

}
