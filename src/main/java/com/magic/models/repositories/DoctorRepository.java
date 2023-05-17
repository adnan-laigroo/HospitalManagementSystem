package com.magic.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

}
