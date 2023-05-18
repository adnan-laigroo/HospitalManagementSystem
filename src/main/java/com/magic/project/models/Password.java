package com.magic.project.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Password {
	
	@Size(min = 8, max = 15, message = "Password length must be between 8 and 15 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
