import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<String> getAllProducts() {
        List<String> products = new ArrayList<>();
        String sql = "SELECT name, price, category, description FROM Product";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String productInfo = rs.getString("name") + " | $" + rs.getDouble("price") +
                        " | " + rs.getString("category") + " | " + rs.getString("description");
                products.add(productInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public boolean addProduct(int sellerId, String name, String category, String description, double price, int quantity) {
        String query = "INSERT INTO Product (seller_id, name, category, description, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, sellerId);
            stmt.setString(2, name);
            stmt.setString(3, category);
            stmt.setString(4, description);
            stmt.setDouble(5, price);
            stmt.setInt(6, quantity);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
