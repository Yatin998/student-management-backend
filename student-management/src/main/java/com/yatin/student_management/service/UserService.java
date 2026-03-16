package com.yatin.student_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yatin.student_management.model.User;
import com.yatin.student_management.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user){

        // hash password
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    public User loginUser(String username, String password){

    User user = userRepository.findByUsername(username);

    if(user == null){
        throw new RuntimeException("User not found");
    }

    boolean isMatch = passwordEncoder.matches(password, user.getPassword());

    if(!isMatch){
        throw new RuntimeException("Invalid password");
    }

    return user;
}
public User saveUser(User user){
    return userRepository.save(user); 
}
}