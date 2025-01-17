package com.example.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.assignment.model.Users;
import com.example.assignment.repository.UserRepository;

@Service
public class UserServices {

   @Autowired
   UserRepository userRepository;
   
   public ResponseEntity<String> createUser(Users user){

      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String password = encoder.encode(user.getPassword());

        user.setPassword(password);

        userRepository.save(user);
      return ResponseEntity.ok("user created successfully");
   }
}
