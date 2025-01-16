package com.example.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assignment.model.Venue;

public interface VenueRepository extends JpaRepository<Venue,Integer>{
   
}
