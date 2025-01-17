package com.example.assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.assignment.model.Event;
import com.example.assignment.model.Organizer;
import com.example.assignment.model.Venue;
import com.example.assignment.repository.EventRepository;

@Service
public class EventServices {

   @Autowired
   private EventRepository eventRepository;

   @Autowired
   private VenueServices venueServices;

   @Autowired
   private OrganizerServices organizerServices;

   public Event createEvents(Event event) {

      eventRepository.save(event);

      return event;
   }

   public List<Event> getAll() {
      return eventRepository.findAll();
   }

   public Event getEvent(int id) {
      return eventRepository.findById(id).get();
   }

   public ResponseEntity<String> deleteEvents(int id) {
      eventRepository.deleteById(id);
      return ResponseEntity.ok("Event Deleted Successfully");
   }

   public Event getVenue(int venueId) {
      Venue venue = venueServices.getVenue(venueId);

      return eventRepository.findByVenue(venue).get();
   }

   public List<Event> getOrgenizer(int organizer_id) {

      Organizer org = organizerServices.getOrganizer(organizer_id);
      return eventRepository.findByOrganizer(org);
   }

   public ResponseEntity<String> assingVenue(int id, int venueId) {

      Venue venue = venueServices.getVenue(venueId);
      Optional<Event> event = eventRepository.findByVenue(venue);
      if (event.isPresent()) {
         throw new IllegalStateException("Venue is already assigned to another event");
      } else {
         Event s = eventRepository.findById(id).get();
         s.setVenue(venue);
         eventRepository.save(s);
         return ResponseEntity.ok("Venue assigned successfully");
      }

   }

   public ResponseEntity<String> assignOrganizer(int id, int orgId) {
      Event s = eventRepository.findById(id).get();
      Organizer org = organizerServices.getOrganizer(orgId);

      s.setOrganizer(org);
      eventRepository.save(s);

      return ResponseEntity.ok("Organizer assign successfully");
   }

}
