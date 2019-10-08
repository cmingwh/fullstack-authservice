package com.ibm.fullstack.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtl {
	private Long userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Long contactNumber;
	private String regCode;
	private List<String> roles;
	private String linedinUrl;
	private Float yearsOfExperience;
	private Boolean active = false;
	private Boolean confirmedSignUp = false;
	private Date resetPasswordDate;
	private Boolean resetPassword = false;

}
