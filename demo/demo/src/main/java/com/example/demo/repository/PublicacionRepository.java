package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
   
}
