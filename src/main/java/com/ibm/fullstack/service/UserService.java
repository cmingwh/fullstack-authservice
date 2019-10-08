package com.ibm.fullstack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.repository.UserRepository;
import com.ibm.fullstack.dto.UserDtl;

@Service("userService")
public class UserService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = this.userRepository.findByUserName(userName);

        if(user == null){
            //throw exception inform front end not this user
            throw new UsernameNotFoundException("user + " + userName + "not found.");
        }

        Long userId = user.getUserId();
        List<String> roleList = this.userRepository.queryUserOwnedRoles(userId);
        List<GrantedAuthority> authorities = roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails
                .User(userName, user.getPassword(), authorities);
	}
	
    public UserDtl getUserById(Long userId){
    	UserDtl userView = new UserDtl();
        Optional<User> userObj = userRepository.findById(userId);
        User user = userObj.get();
        if(user != null) {
        	userView.setUserName(user.getUserName());
            userView.setUserId(user.getUserId());
            List<String> roles = new ArrayList<>();
            user.getRoles().stream().forEach(role -> roles.add(role.getRole()));
            userView.setRoles(roles);
        }
        return userView;
    }
}
