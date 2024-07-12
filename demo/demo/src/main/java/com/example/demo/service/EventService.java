package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private ImageService imageService;

    private final String url = "jdbc:postgresql://localhost:5432/demo";
    private final String user = "postgres";
    private final String password = "root";

    public List<Event> obtainNearEvents() {
        List<Event> events = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM events WHERE fecha >= ? ORDER BY fecha ASC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, fechaActual);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event();
                event.setTitulo(resultSet.getString("titulo"));
                event.setFecha(resultSet.getDate("fecha").toLocalDate());
                Long imagenId = resultSet.getLong("imagen_id");
                if (imagenId != null) {
                    byte[] imageData = imageService.getImageDataById(imagenId);
                    if (imageData != null) {
                        Image imagen = new Image();
                        imagen.setId(imagenId);
                        imagen.setData(imageData);
                        event.setImagen(imagen);
                    }
                }
                event.setLocation(resultSet.getString("location"));
                event.setArchivopdf(resultSet.getString("archivopdf"));
                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }
}
