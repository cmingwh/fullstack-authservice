package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.fullstack.Application;
import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {
	@Autowired
    private UserRepository userRepository;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void test89() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		User user = userRepository.findByUserName("user");
		// 加密
		String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		
		User admin = userRepository.findByUserName("admin");
		// 加密
		encodedPassword = passwordEncoder.encode(admin.getPassword().trim());
		admin.setPassword(encodedPassword);
		userRepository.save(admin);
	}
}
