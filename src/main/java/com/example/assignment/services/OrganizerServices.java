package com.example.assignment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.assignment.model.Organizer;
import com.example.assignment.model.Users;
import com.example.assignment.repository.OrganizerRepository;
import com.example.assignment.repository.UserRepository;

@Service
public class OrganizerServices {
   
   @Autowired
   UserRepository userRepository;

   @Autowired
   OrganizerRepository organizerRepository;

   public ResponseEntity<String> newOrganizer(Organizer organizer,Authentication authentication){

      Users userObj = userRepository.findByEmail(authentication.getName()).get();

      Optional<Organizer> existingOrganizer = organizerRepository.findByUser(userObj);
      if (existingOrganizer.isPresent()) {
         throw new IllegalStateException("Organizer already exists for this user");
      }

      organizer.setUser(userObj);

      organizerRepository.save(organizer);

      return ResponseEntity.ok("Organizer Created Successfully");
   }

   public Organizer getOrganizer(int id){
      return organizerRepository.findById(id).get();
   }

   public ResponseEntity<String> deleteOrganizer(int id){
      organizerRepository.deleteById(id);
      return ResponseEntity.ok("Organizer Deleted Successfully");
   }

   public ResponseEntity<String> UpdateOrganizer(int id, Organizer organizer){
      Optional<Organizer> org = Optional.ofNullable(organizerRepository.findById(id)
                              .orElseThrow(() -> new UsernameNotFoundException("Organizer not found: " + id)));
      Organizer o = org.get();
      o.setContactInfo(organizer.getContactInfo());
      o.setName(organizer.getName());
      organizerRepository.save(o);
      return ResponseEntity.ok("Organizer Updated Successfully");
   }

}
