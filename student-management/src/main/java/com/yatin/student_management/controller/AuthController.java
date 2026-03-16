package com.yatin.student_management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.yatin.student_management.model.User;
import com.yatin.student_management.security.JwtUtil;
import com.yatin.student_management.service.UserService;
import com.yatin.student_management.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

     @Autowired
    private UserRepository UserRepository;

    @Autowired
private JwtUtil jwtUtil;

@Autowired
    private PasswordEncoder passwordEncoder;


    // @PostMapping("/register")
    // public User register(@RequestBody User user){
    //     return userService.registerUser(user);
    // }

    @PostMapping("/register")
public User register(@RequestBody User user) {

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    user.setRole("USER");

    return userService.saveUser(user);  
}
@PostMapping("/login")
public Map<String,String> login(@RequestBody User user){

    User dbUser = UserRepository.findByUsername(user.getUsername());

    String token = jwtUtil.generateToken( 
            dbUser.getUsername(),
            dbUser.getRole() 
    );

    return Map.of("token", token);
}

}