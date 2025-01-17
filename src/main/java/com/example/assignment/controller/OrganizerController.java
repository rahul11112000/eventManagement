package com.example.assignment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
   private OrganizerServices organizerServices;

   @PostMapping("/create")
   public ResponseEntity<String> createOrganizer(@RequestBody Organizer organizer, Authentication authentication) {
      return organizerServices.newOrganizer(organizer, authentication);
   }

   @GetMapping("/{id}")
   public Organizer getOrganizer(@PathVariable("id") int id) {

      return organizerServices.getOrganizer(id);
   }

   @PutMapping("/update/{id}")
   public ResponseEntity<String> updateOrg(@PathVariable("id") int id, @RequestBody Organizer organizer) {
      return organizerServices.UpdateOrganizer(id, organizer);
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> deleteOrg(@PathVariable("id") int id) {
      return organizerServices.deleteOrganizer(id);
   }
}
