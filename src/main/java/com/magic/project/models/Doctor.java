package com.magic.project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
public class Doctor {

	@Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets are allowed for first name")
	private String firstName;

	@Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets are allowed for last name")
	private String lastName;

	@Id
	@Email(message = "Invalid email format")
	private String email;

	@Pattern(regexp = "^[0-9]{10,}$", message = "Phone number should contain at least 10 digits")
	private String phoneNo;

	@Pattern(regexp = "^(Orthopedic|Gynecology|Dermatology|ENT)$", message = "Speciality must be Orthopedic, Gynecology, Dermatology or ENT specialist")
	private String speciality;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

}
