package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class ImageService {


    private final String url = "jdbc:postgresql://localhost:5432/demo";
    private final String user = "postgres";
    private final String password = "root";

    public byte[] getImageDataById(Long id) {
        String sql = "SELECT data FROM images WHERE id = ?";
        byte[] imageData = null;

        try (java.sql.Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    imageData = rs.getBytes("data");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageData;
    }
}
