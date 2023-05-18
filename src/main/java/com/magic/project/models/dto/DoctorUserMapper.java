package com.magic.project.models.dto;

import org.modelmapper.ModelMapper;

import com.magic.project.models.Doctor;
import com.magic.project.models.User;

public class DoctorUserMapper {

	private DoctorUserMapper() {
		// Private constructor to prevent instantiation
	}

	public static Doctor mapToDoctor(DoctorDto doctorDto) {
		ModelMapper modelMapper = new ModelMapper();
		Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
		return doctor;
	}

	public static User mapToUser(DoctorDto doctorDto) {
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(doctorDto, User.class);
		return user;
	}
}
