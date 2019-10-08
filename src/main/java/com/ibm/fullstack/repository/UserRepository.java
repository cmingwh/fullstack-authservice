package com.ibm.fullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.fullstack.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select r.role from User u inner join u.roles as r where u.userId = :userId")
    List<String> queryUserOwnedRoles(@Param(value = "userId") Long userId);

	User findByUserName(String userName);
}