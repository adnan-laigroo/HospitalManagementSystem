package com.magic.project.services;

import java.util.List;

import javax.validation.Valid;

import com.magic.project.models.Receptionist;

public interface ReceptionistService {

	void saveReceptionist(@Valid Receptionist receptionist);

	Receptionist deleteReceptionist(@Valid String email);

	Receptionist updateReceptionist(Receptionist updatedReceptionist, @Valid String email);

	List<Receptionist> getReceptionistList();

}