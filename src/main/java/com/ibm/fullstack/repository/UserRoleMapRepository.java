package com.ibm.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.fullstack.entity.UserRoleMap;

@Repository
public interface UserRoleMapRepository extends JpaRepository<UserRoleMap, String> {

}