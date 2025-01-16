package com.example.assignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Organizer {
   
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private int id;
   private String name;
   private String contactInfo;
   @OneToOne
   @JoinColumn(name = "user_id", nullable = false)
   private Users user;

}
