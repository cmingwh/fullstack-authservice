package com.ibm.fullstack.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleMapPK implements Serializable {
	private static final long serialVersionUID = -9138240497526791484L;

	private String role;

	private String userName;
}
