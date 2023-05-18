package com.magic.project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Receptionist {

	@NotBlank(message = "First name cannot be blank")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets are allowed in the first name")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabets are allowed in the last name")
	private String lastName;

	@Id
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Phone number cannot be blank")
	@Pattern(regexp = "^[0-9]{10,}$", message = "Phone number should contain at least 10 digits")
	private String phoneNo;

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

}
