package com.example.assignment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.model.Organizer;
import com.example.assignment.services.OrganizerServices;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
   
   @Autowired
   OrganizerServices organizerServices;

   @GetMapping("/")
   public String check(){
      return "org";
   }


   @PostMapping("/create")
   public String createOrganizer(@RequestBody Organizer organizer,Authentication authentication){
      return organizerServices.newOrganizer(organizer, authentication);
   }

   @GetMapping("/{id}")
   public Optional<Organizer> getOrganizer(@PathVariable("id") int id){

      return organizerServices.getOrganizer(id);
   }

   @PutMapping("/update/{id}")
   public String updateOrg(@PathVariable("id") int id, @RequestBody Organizer organizer){
      return organizerServices.UpdateOrganizer(id, organizer);
   }

   @DeleteMapping("/delete/{id}")
   public String deleteOrg(@PathVariable("id") int id){
      return organizerServices.deleteOrganizer(id);
   }
}
