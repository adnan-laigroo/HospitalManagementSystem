package com.magic.models.dto;

import org.modelmapper.ModelMapper;

import com.magic.models.Receptionist;
import com.magic.models.User;

public class ReceptionistUserMapper {

	private ReceptionistUserMapper() {
		// Private constructor to prevent instantiation
	}

	public static Receptionist mapToReceptionist(ReceptionistDto receptionistDto) {
		ModelMapper modelMapper = new ModelMapper();
		Receptionist receptionist = modelMapper.map(receptionistDto, Receptionist.class);
		return receptionist;
	}

	public static User mapToUser(ReceptionistDto receptionistDto) {
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(receptionistDto, User.class);
		return user;
	}
}
