package com.example.assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment.model.Event;
import com.example.assignment.model.Organizer;
import com.example.assignment.model.Venue;
import com.example.assignment.repository.EventRepository;

@Service
public class EventServices {

   @Autowired
   EventRepository eventRepository;

   @Autowired
   VenueServices venueServices;

   @Autowired
   OrganizerServices organizerServices;

   public String createEvents(Event event){

      Event e = new Event();
      e.setEventName(event.getEventName());
      e.setDescription(event.getDescription());
      e.setEventDate(event.getEventDate());

      eventRepository.save(e);

      return "Event Created Successfully";
   }

   public List<Event> getAll(){
      return eventRepository.findAll();
   }

   public Optional<Event> getEvent(int id){
      return eventRepository.findById(id);
   }

   public String updateEvents(Event event){
      return "Event Updated successfully";
   }

   public String deleteEvents(int id){
      eventRepository.deleteById(id);
      return "Event Deleted Successfully";
   }

   public Optional<Event> getVenue(int venueId){

      Optional<Venue> venue = venueServices.getVenue(venueId);
      Venue venueObj = venue.get();
      return eventRepository.findByVenue(venueObj);
   }

   public List<Event> getOrgenizer(int organizer_id){

      Optional<Organizer> org = organizerServices.getOrganizer(organizer_id);
      Organizer orgObj = org.get();
      return eventRepository.findByOrganizer(orgObj);
   }

   public String assingVenue(int id, int venueId){
      

      Optional<Venue> venue = venueServices.getVenue(venueId);
      Venue venueObj = venue.get();
      Optional<Event> event = eventRepository.findByVenue(venueObj);
      if (event.isPresent()) {
         return " Venue is already assign to other event";
      } else {
         Optional<Event>  s = eventRepository.findById(id);
         Event e = s.get();
         e.setVenue(venueObj);
         eventRepository.save(e);
         return "assign venue successfully";
      }
      
   }

   public String assignOrganizer(int id,int orgId){
      Optional<Event>  s = eventRepository.findById(id);
      Event e = s.get();
      Optional<Organizer> org = organizerServices.getOrganizer(orgId);
      Organizer orgObj = org.get();

      e.setOrganizer(orgObj);
      eventRepository.save(e);

      return "Organizer assign successfully";
   }

}
