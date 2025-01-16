package com.example.assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assignment.model.Event;
import com.example.assignment.model.Organizer;
import com.example.assignment.model.Venue;

public interface EventRepository extends JpaRepository<Event,Integer>{

   Optional<Event>findByVenue(Venue venue);
   List<Event>findByOrganizer(Organizer organizer);

}
