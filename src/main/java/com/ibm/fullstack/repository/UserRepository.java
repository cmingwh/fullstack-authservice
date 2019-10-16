package com.ibm.fullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.fullstack.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select r.role from User u inner join u.roles as r where u.userName = :name")
    List<String> queryUserOwnedRoleCodes(@Param("name")String name);

	User findByUserName(String userName);
}