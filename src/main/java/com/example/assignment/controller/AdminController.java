package com.example.assignment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.model.Event;
import com.example.assignment.services.EventServices;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventServices eventServices;

    @PostMapping("/create/events")
    public Event createEvent(@RequestBody Event event) {
        return eventServices.createEvents(event);
    }

    @GetMapping("/get/all/events")
    public List<Event> getAllEvents() {
        return eventServices.getAll();
    }

    @GetMapping("/get/event/{id}")
    public Event getEvent(@PathVariable("id") int id) {
        return eventServices.getEvent(id);
    }

    @GetMapping("/get/by-venue/{venueId}")
    public Event getVenue(@PathVariable("venueId") int venue_id) {
        return eventServices.getVenue(venue_id);
    }

    @GetMapping("/get/by-org/{orgId}")
    public List<Event> getOrg(@PathVariable("orgId") int orgId) {
        return eventServices.getOrgenizer(orgId);
    }

    @PutMapping("/assign/venue/{id}")
    public ResponseEntity<String> assignVenue(@PathVariable("id") int id, @RequestBody Map<String, Integer> request) {
        int venueId = request.get("venueId");
        return eventServices.assingVenue(id, venueId);
    }

    @PutMapping("/assign/org/{id}")
    public ResponseEntity<String> assignOrg(@PathVariable("id") int id, @RequestBody Map<String, Integer> request) {
        int orgId = request.get("orgid");
        return eventServices.assignOrganizer(id, orgId);
    }
}
