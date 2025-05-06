import java.sql.*;

public class SellerDAO {

    public boolean addSeller(String name, String address) {
        String query = "INSERT INTO Seller (name, address) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, address);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getSellerIdByName(String name) {
        String query = "SELECT seller_id FROM Seller WHERE name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("seller_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Not found
    }
}
