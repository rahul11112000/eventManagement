package com.example.assignment.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Event {

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private int id;
   private String eventName;
   private LocalDateTime eventDate;
   private String description;

   @ManyToOne
   @JoinColumn(name = "organizer_id", nullable = true)
   private Organizer organizer;

   @OneToOne
   @JoinColumn(name = "venue_id", nullable = true)
   private Venue venue;
   
}
