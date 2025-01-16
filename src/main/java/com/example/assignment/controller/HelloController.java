package com.example.assignment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.services.UserServices;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

   @Autowired
   UserServices userServices;

   
   @GetMapping("/")
   public String hello(Authentication authentication) {
      return "Hello, " + authentication.getName() + "!";
   }

   @GetMapping("/csrf-token")
   public CsrfToken getToken(HttpServletRequest request){
      return (CsrfToken) request.getAttribute("_csrf");
   }
   

}
