package com.example.assignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assignment.model.Organizer;
import com.example.assignment.model.Users;

public interface OrganizerRepository extends JpaRepository<Organizer,Integer>{

   Optional<Organizer> findByUser(Users user);
   
}