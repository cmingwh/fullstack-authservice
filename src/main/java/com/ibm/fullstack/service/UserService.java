package com.ibm.fullstack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.fullstack.entity.User;
import com.ibm.fullstack.repository.UserRepository;
import com.ibm.fullstack.view.UserView;

@Service("userService")
public class UserService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
	
	@Override
//	@Transactional
    public UserDetails loadUserByUsername(String username) {
		User user = this.userRepository.findByUserName(username);

        if(user == null){
            //throw exception inform front end not this user
            throw new UsernameNotFoundException("user + " + username + "not found.");
        }

        List<String> roleList = this.userRepository.queryUserOwnedRoleCodes(username);
        List<GrantedAuthority> authorities = roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails
                .User(username,user.getPassword(),authorities);
    }
	
    @Transactional
    public UserView getUserByUserName(String userName){
        UserView userView = new UserView();
        User user = userRepository.findByUserName(userName);
        userView.setUserName(user.getUserName());
        userView.setUserDesc(user.getUserDescription());
        List<String> roleCodes = new ArrayList<>();
        user.getRoles().stream().forEach(role -> roleCodes.add(role.getRoleCode()));
        userView.setRoleCodes(roleCodes);
        return userView;
    }
}
