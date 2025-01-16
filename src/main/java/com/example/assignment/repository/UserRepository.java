package com.example.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assignment.model.Users;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Users,Integer>{

   Optional<Users> findByEmail(String email);
}
