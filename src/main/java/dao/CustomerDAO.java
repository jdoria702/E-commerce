package main.java.dao;

import java.sql.*;

public class CustomerDAO {

    public boolean registerCustomer(String email, String password, String address) {
        String query = "INSERT INTO Customer (email, password, address) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, address);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginCustomer(String email, String password) {
        String query = "SELECT * FROM Customer WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // If a match is found

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
