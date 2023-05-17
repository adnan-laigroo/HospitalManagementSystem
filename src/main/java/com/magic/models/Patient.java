package com.magic.models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Patient {
	@Id
	@GeneratedValue(generator = "patient_sequence_generator")
	@GenericGenerator(name = "patient_sequence_generator", strategy = "com.magic.models.PatientSequenceGenerator")
	private String patId;

	@NotBlank(message = "First name is required")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets are allowed for first name")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets are allowed for last name")
	private String lastName;
	
	@NotNull(message = "Address is required")
	@Embedded
	private Address address;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number should contain exactly 10 digits")
	private String phoneNo;

	@NotNull(message = "Symptom is required")
	@Pattern(regexp = "^(Arthritis|Backpain|Tissue injuries|Dysmenorrhea|Skin infection|Skin burn|Ear pain)$", message = "Invalid symptom")
	private String symptom;

	public String getPatId() {
		return patId;
	}

	public void setPatId(String patId) {
		this.patId = patId;
	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

}
