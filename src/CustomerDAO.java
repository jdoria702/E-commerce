import java.sql.*;

public class CustomerDAO {
    public void registerCustomer(String email, String password, String address) {
        String sql = "INSERT INTO Customer (email, password, address) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, address);
            stmt.executeUpdate();

            System.out.println("Customer registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean loginCustomer(String email, String password) {
        String sql = "SELECT * FROM Customer WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful for: " + email);
                return true;
            } else {
                System.out.println("Login failed: Incorrect email or password.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
