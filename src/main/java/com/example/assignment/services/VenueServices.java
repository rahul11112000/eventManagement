package com.example.assignment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

   
   public String addVenue(Venue venue,Authentication authentication){

      Optional<Users> user = userRepository.findByEmail(authentication.getName());
      Users userObj = user.get();

      Venue v = new Venue();
      v.setName(venue.getName());
      v.setCapacity(venue.getCapacity());
      v.setLocation(venue.getLocation());
      v.setUser(userObj);

      venueRepository.save(v);

      return "venue created successfully";
   }

   public Optional<Venue> getVenue(int id){
      return venueRepository.findById(id);
   }

   public String updateVenue(int id,Venue V){
      Optional<Venue> venue = Optional.ofNullable(venueRepository.findById(id)
                              .orElseThrow(() -> new UsernameNotFoundException("Venue not found: " + id)));
      Venue v = venue.get();
      v.setName(V.getName());
      v.setCapacity(V.getCapacity());
      v.setLocation(V.getLocation());
      venueRepository.save(v);

      return "update successfully";
   }

   public String deleteVenue(int id){
      venueRepository.deleteById(id);
      return "Venue Deleted Successfully";
   }
}
