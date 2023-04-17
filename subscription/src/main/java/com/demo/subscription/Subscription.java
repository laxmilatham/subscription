package com.demo.subscription;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Subscription {
	@NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address")
    private String email;

    @NotBlank(message = "User type is required")
    private String userType;

   	@NotBlank(message = "Company is required")
    @Size(min = 2, max = 50, message = "Company name should be between 2 and 50 characters")
    private String company;

    @NotBlank(message = "Application type is required")
    private String applicationType;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	@Override
	public String toString() {
		return "Subscription [name=" + name + ", email=" + email + ", userType=" + userType + ", company=" + company
				+ ", applicationType=" + applicationType + "]";
	}

}
