package com.ibm.fullstack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(UserRoleMapPK.class)
public class UserRoleMap {

	@Id
	@Column(name = "role")
	private String role;

	@Id
	@Column(name = "user_name")
	private String userName;

}