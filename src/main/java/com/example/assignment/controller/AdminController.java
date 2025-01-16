package com.example.assignment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.model.Event;
import com.example.assignment.services.EventServices;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    EventServices eventServices;
   @GetMapping("/")
   public String getMethodName() {
       return "new String()";
   }

   @PostMapping("/create/events")
   public String createEvent(@RequestBody Event event){
    return eventServices.createEvents(event);
   }

   @GetMapping("/get/all/events")
   public List<Event> getAllEvents(){
    return eventServices.getAll();
   }

   @GetMapping("/get/{id}")
   public Optional<Event> getEvent(@PathVariable("id") int id){

    return eventServices.getEvent(id);
   }
   
   @GetMapping("/getbyevenue/{venueid}")
   public Optional<Event> getVenue(@PathVariable("venueid") int venue_id){
    return eventServices.getVenue(venue_id);
   }

   @GetMapping("/getbyorg/{orgid}")
   public List<Event> getOrg(@PathVariable("orgid") int orgid){
    return eventServices.getOrgenizer(orgid);
   }

   @PutMapping("/assign/venue/{id}")
   public String assignVenue(@PathVariable("id") int id,@RequestBody Map<String, Integer> request){
    int venueId = request.get("venueId");
    return eventServices.assingVenue(id, venueId);
   }

   @PutMapping("/assign/org/{id}")
   public String assignOrg(@PathVariable("id") int id,@RequestBody Map<String, Integer> request){
    int orgId = request.get("orgid");
    return eventServices.assignOrganizer(id, orgId);
   }
}
