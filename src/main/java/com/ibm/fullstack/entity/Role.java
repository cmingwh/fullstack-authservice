package com.ibm.fullstack.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Store role information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_role", schema = "fullstack")
public class Role {

	@Column(name = "role")
	private String role;

	@Column(name = "parent")
	private String parent;

	@Column(name = "description")
	private String description;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

}