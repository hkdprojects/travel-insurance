package com.example.travelinsurance.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.travelinsurance.model.Role;
import com.example.travelinsurance.model.User;
import com.example.travelinsurance.repository.RoleRepository;
import com.example.travelinsurance.repository.UserRepository;

import java.util.Collections;

@Service
public class UserService {

    // These fields remain private, which is correct!
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role("ROLE_USER");
            roleRepository.save(userRole);
        }
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }
    
    // ==========================================================
    // ADD THIS NEW PUBLIC METHOD
    // ==========================================================
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}