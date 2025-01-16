package com.example.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.assignment.model.Users;
import com.example.assignment.repository.UserRepository;

@Service
public class UserServices {

   @Autowired
   UserRepository userRepository;
   
   public String createUser(Users user){

      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String password = encoder.encode(user.getPassword());

      Users u = new Users();
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        u.setPassword(password);

        userRepository.save(u);
      return "user created successfully";
   }
}
