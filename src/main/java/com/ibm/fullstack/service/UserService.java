package com.ibm.fullstack.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.entity.UserRoleMap;
import com.ibm.fullstack.repository.UserRepository;
import com.ibm.fullstack.repository.UserRoleMapRepository;

@Service("userService")
public class UserService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
	
//	@Autowired
//    private RoleRepository roleRepository;
	
	@Autowired
    private UserRoleMapRepository userRoleRepository;
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public boolean existsUserName(String userName) {
		User user = this.findByUserName(userName);
		return user != null;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);

        if(user == null){
            //throw exception inform front end not this user
            throw new UsernameNotFoundException("user + " + userName + "not found.");
        }
        
        List<UserRoleMap> roleList = userRoleRepository.findByUserName(user.getUserName());
        List<GrantedAuthority> authorities = roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails
                .User(userName, user.getPassword(), authorities);
	}

	public User register(User user) {
		enCodePassword(user);
		return userRepository.save(user);
	}
	
	public void enCodePassword(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
		user.setPassword(encodedPassword);
	}
	
}
