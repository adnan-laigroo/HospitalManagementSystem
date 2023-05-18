package com.magic.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.project.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

	List<Doctor> findBySpeciality(String speciality);

}
