package com.ibm.fullstack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Store role information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user_role", schema = "fullstack")
public class UserRoleMap {

	@Id
	@Column(name = "role")
	private String role;

	@Column(name = "user_id")
	private Long userId;

}