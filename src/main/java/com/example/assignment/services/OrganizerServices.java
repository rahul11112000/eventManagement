package com.example.assignment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

   public String newOrganizer(Organizer organizer,Authentication authentication){

      Optional<Users> user = userRepository.findByEmail(authentication.getName());
      Users userObj = user.get();

      Optional<Organizer> existingOrganizer = organizerRepository.findByUser(userObj);
      if (existingOrganizer.isPresent()) {
         return "Organizer already exists for this user";
      }

      Organizer o = new Organizer();
      o.setContactInfo(organizer.getContactInfo());
      o.setName(organizer.getName());
      o.setUser(userObj);

      organizerRepository.save(o);

      return "Organizer Created Successfully";
   }

   public Optional<Organizer> getOrganizer(int id){
      return organizerRepository.findById(id);
   }

   public String deleteOrganizer(int id){
      organizerRepository.deleteById(id);
      return "Organizer Deleted Successfully";
   }

   public String UpdateOrganizer(int id, Organizer organizer){
      Optional<Organizer> org = Optional.ofNullable(organizerRepository.findById(id)
                              .orElseThrow(() -> new UsernameNotFoundException("Organizer not found: " + id)));
      Organizer o = org.get();
      o.setContactInfo(organizer.getContactInfo());
      o.setName(organizer.getName());
      organizerRepository.save(o);
      return "Organizer Updated Successfully";
   }

}
