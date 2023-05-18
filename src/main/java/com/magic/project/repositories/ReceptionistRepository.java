package com.magic.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.project.models.Receptionist;

public interface ReceptionistRepository extends JpaRepository<Receptionist, String> {

}
