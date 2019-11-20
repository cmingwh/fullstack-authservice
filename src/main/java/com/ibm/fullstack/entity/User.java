package com.ibm.fullstack.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user", schema = "fullstack")
public class User {
	
	@Id
	@Column(name="user_name")
	private String userName;
	
	@GeneratedValue
	@Column(name="user_id")
	private Long userId;

	@Column(name="password")
	private String password;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="contact_number")
	private String contactNumber;

	@Column(name="reg_code")
	private String regCode;

	@Column(name="linedin_url")
	private String linedinUrl;

	@Column(name="years_of_experience")
	private Float yearsOfExperience;

	@Column(name="active")
	private Boolean active = false;

	@Column(name="confirmed_signup")
	private Boolean confirmedSignUp = false;

	@Column(name="reset_password_date")
	private Date resetPasswordDate;

	@Column(name="reset_password")
	private Boolean resetPassword = false;

	@ManyToMany
	@JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "user_name", 
		referencedColumnName = "user_name", updatable = false, insertable = false), 
		inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "role", 
		updatable = false, insertable = false))
	private List<Role> roles;
}