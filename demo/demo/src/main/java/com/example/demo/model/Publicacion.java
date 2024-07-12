package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String resumen;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "imagen_id")
    private Image image;

    private LocalDate fecha;

    public Publicacion() {
    }

    public Publicacion(String titulo, String resumen, String descripcion, Image image) {
        this.titulo = titulo;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Image getImagen() {
        return image;
    }

    public void setImagen(Image image) {
        this.image = image;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    // toString para imprimir la entidad
    @Override
    public String toString() {
        return "Publicacion{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", resumen='" + resumen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen=" + image +
                ", fecha=" + fecha +
                '}';
    }
}
