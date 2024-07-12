package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Publicacion;
import com.example.demo.service.PublicacionService;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping("/last")
    public ResponseEntity<Publicacion> obtenerPublicacionPorId() {
        Publicacion publicacion = publicacionService.obtenerPublicacionUltimoId();
        if (publicacion != null) {
            return ResponseEntity.ok(publicacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/penultimas-publicaciones")
    public List<Publicacion> getPenultimasPublicaciones() {
        return publicacionService.getPenultimasPublicaciones();
    }
}
