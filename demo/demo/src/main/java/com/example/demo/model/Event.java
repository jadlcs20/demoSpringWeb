package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private LocalDate fecha;
    
    @ManyToOne
    @JoinColumn(name = "imagen_id")
    private Image image;
    
    private String location;
    private String archivopdf;

    // Constructor vacío requerido por Spring para trabajar con entidades
    public Event() {
    }

    public Event(String titulo, LocalDate fecha, Image image, String location, String archivopdf) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.image = image;
        this.location = location;
        this.archivopdf = archivopdf;
    }

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Image getImagen() {
        return image;
    }

    public void setImagen(Image image) {
        this.image = image;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArchivopdf() {
        return archivopdf;
    }

    public void setArchivopdf(String archivopdf) {
        this.archivopdf = archivopdf;
    }
}
