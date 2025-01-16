package com.example.assignment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.model.Venue;
import com.example.assignment.services.VenueServices;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/venue")
public class VenueController {

   @Autowired
   VenueServices venueServices;
   
   @GetMapping("/")
   public String getMethodName() {
       return "new String()";
   }
   
   @PostMapping("/add/new")
   public String postMethodName(@RequestBody Venue venue,Authentication authentication) {
       
      return venueServices.addVenue(venue,authentication);
   }

   @GetMapping("/{id}")
   public Optional<Venue> getVenue(@PathVariable("id") int id){
      return venueServices.getVenue(id);
   }

   @PutMapping("/update/{id}")
   public String updateVenue(@PathVariable("id") int id,@RequestBody Venue venue){
      return venueServices.updateVenue(id, venue);
   }
   
   @DeleteMapping("/delete/{id}")
   public String deleteVenue(@PathVariable("id") int id){
      return venueServices.deleteVenue(id);
   }
   
}
