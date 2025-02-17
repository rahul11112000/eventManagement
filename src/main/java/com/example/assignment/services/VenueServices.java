package com.example.assignment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.assignment.model.Users;
import com.example.assignment.model.Venue;
import com.example.assignment.repository.UserRepository;
import com.example.assignment.repository.VenueRepository;

@Service
public class VenueServices {

   @Autowired
   VenueRepository venueRepository;

   @Autowired
   UserRepository userRepository;

   
   public ResponseEntity<String> addVenue(Venue venue,Authentication authentication){

      Users user = userRepository.findByEmail(authentication.getName()).get();

      venue.setUser(user);

      venueRepository.save(venue);

      return ResponseEntity.ok("venue created successfully");
   }

   public Venue getVenue(int id){
      return venueRepository.findById(id).get();
   }

   public ResponseEntity<String> updateVenue(int id,Venue V){
      Optional<Venue> venue = Optional.ofNullable(venueRepository.findById(id)
                              .orElseThrow(() -> new UsernameNotFoundException("Venue not found: " + id)));
      Venue v = venue.get();
      v.setName(V.getName());
      v.setCapacity(V.getCapacity());
      v.setLocation(V.getLocation());
      venueRepository.save(v);

      return ResponseEntity.ok("Update Venue successfully");
   }

   public ResponseEntity<String> deleteVenue(int id){
      venueRepository.deleteById(id);
      return ResponseEntity.ok("Venue Deleted Successfully");
   }
}
