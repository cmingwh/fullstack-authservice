package com.ibm.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.fullstack.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	Role findByRole(String role);

}