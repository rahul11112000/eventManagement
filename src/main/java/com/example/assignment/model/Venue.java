package com.example.assignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Venue {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private int id;
   private String name;
   private String location;
   private String capacity;
   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
   private Users user;
}
