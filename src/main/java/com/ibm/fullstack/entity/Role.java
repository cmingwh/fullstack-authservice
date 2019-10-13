package com.ibm.fullstack.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "sys_role", schema = "fullstack")
public class Role {
	@Id
	@Column(name = "role")
	private String role;

	@Column(name = "parent")
	private String parent;

	@Column(name = "description")
	private String description;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

}