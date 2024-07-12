package com.example.demo.service;

import com.example.demo.model.Image;
import com.example.demo.model.Publicacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionService {
    private final String url = "jdbc:postgresql://localhost:5432/demo";
    private final String user = "postgres";
    private final String password = "root";

    @Autowired
    private ImageService imageService;

    public Publicacion obtenerPublicacionUltimoId() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Publicacion publicacion = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM publicacion WHERE id = (SELECT MAX(id) FROM publicacion)";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                publicacion = mapResultSetToPublicacion(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de excepciones en la práctica real
        } finally {
            close(conn, stmt, rs);
        }

        return publicacion;
    }

    private Publicacion mapResultSetToPublicacion(ResultSet rs) throws SQLException {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(rs.getLong("id"));
        publicacion.setTitulo(rs.getString("titulo"));
        publicacion.setResumen(rs.getString("resumen"));
        publicacion.setDescripcion(rs.getString("descripcion"));
        LocalDate fecha = rs.getDate("fecha").toLocalDate(); // Convertir java.sql.Date a LocalDate
        publicacion.setFecha(fecha);
        // Obtener la imagen por su id y asignarla a la publicacion
        Long imagenId = rs.getLong("imagen_id");
        if (imagenId != null) {
            byte[] imageData = imageService.getImageDataById(imagenId);
            if (imageData != null) {
                Image imagen = new Image(); // Suponiendo que tienes una clase Imagen
                imagen.setId(imagenId);
                imagen.setData(imageData);
                publicacion.setImagen(imagen);
            }
        }

        return publicacion;
    }

    private void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de excepciones en la práctica real
        }
    }

    public List<Publicacion> getPenultimasPublicaciones() {
        List<Publicacion> publicaciones = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM publicacion ORDER BY id DESC LIMIT 3 OFFSET 1")) {

            while (resultSet.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion = mapResultSetToPublicacion(resultSet);
                publicaciones.add(publicacion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return publicaciones;
    }
}

