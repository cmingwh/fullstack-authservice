package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.fullstack.Application;
import com.ibm.fullstack.entity.Role;
import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.entity.UserRoleMap;
import com.ibm.fullstack.repository.RoleRepository;
import com.ibm.fullstack.repository.UserRepository;
import com.ibm.fullstack.repository.UserRoleMapRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private UserRoleMapRepository userRoleMapRepository;
	
	@Test
	public void contextLoads() {
		Role user = roleRepository.findByRole("user");
		if(null == user) {
			user = new Role();
			user.setDescription("A Normal User");
			user.setRole("user");
			roleRepository.save(user);
		}
		
		Role admin = roleRepository.findByRole("admin");
		if(null == admin) {
			admin = new Role();
			admin.setDescription("A Admin User");
			admin.setRole("admin");
			roleRepository.save(admin);
		}
	}

	@Test
	public void test89() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User my = userRepository.findByUserName("cmingwh@cn.ibm.com");
		if(null == my) {
			User user = new User();
			user.setUserName("cmingwh@cn.ibm.com");
			user.setFirstName("Cheng");
			user.setLastName("Ming");
			String encodedPassword = passwordEncoder.encode("123456");
			user.setPassword(encodedPassword);
			userRepository.save(user);
			
			user = userRepository.findByUserName("cmingwh@cn.ibm.com");
			UserRoleMap userMap = new UserRoleMap();
			userMap.setRole("user");
			userMap.setUserName(user.getUserName());
			userRoleMapRepository.save(userMap);
		}
		
		User adm = userRepository.findByUserName("admin");
		if(null == adm) {
			User admin = new User();
			admin.setUserName("admin");
			admin.setFirstName("admin");
			admin.setLastName("001");
			String encodedPassword = passwordEncoder.encode("123456");
			admin.setPassword(encodedPassword);
			userRepository.save(admin);
			
			admin = userRepository.findByUserName("admin");
			UserRoleMap userMap = new UserRoleMap();
			userMap.setRole("admin");
			userMap.setUserName(admin.getUserName());
			userRoleMapRepository.save(userMap);
		}
	}
}
