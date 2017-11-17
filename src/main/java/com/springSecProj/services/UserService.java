package com.springSecProj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springSecProj.models.User;
import com.springSecProj.repositories.RoleRepository;
import com.springSecProj.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
    
	@Autowired
	private RoleRepository roleRepository;
    
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
         
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
        
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
}
