package com.blogApp.controller;

import com.blogApp.entity.User;
import com.blogApp.payload.LoginDto;
import com.blogApp.payload.SingUp;

import com.blogApp.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Data
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    //http://localhost:8097/api/auth/sign-up
@PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@RequestBody SingUp singUp){
       if(userRepository.existsByEmail(singUp.getEmail())){
           return new ResponseEntity<>("email id is already exist", HttpStatus.INTERNAL_SERVER_ERROR);

       }
       if(userRepository.existsByUsername(singUp.getUsername())){
           return new ResponseEntity<>("email id is already exist", HttpStatus.INTERNAL_SERVER_ERROR);
       }
       User user=new User();
       user.setName(singUp.getName());
       user.setUsername(singUp.getUsername());
       user.setEmail(singUp.getEmail());
       user.setPassword(passwordEncoder.encode(singUp.getPassword()));
       userRepository.save(user);
        return new ResponseEntity<>("user created", HttpStatus.CREATED);
    }
    @PostMapping
    public ResponseEntity<String> signin(LoginDto loginDto){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return  new ResponseEntity<>("logIN ",HttpStatus.OK);

    }
}
