import java.sql.*;
import java.util.*;

public class OrderDAO {

    // Place a new order
    public boolean placeOrder(int customerId, int productId, int quantity) {
        String query = "INSERT INTO Orders (customer_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);

            int rowsAffected = stmt.executeUpdate();

            // After inserting the order, update product quantity
            if (rowsAffected > 0) {
                updateProductStock(productId, quantity);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update product stock after an order is placed
    private void updateProductStock(int productId, int quantity) {
        String query = "UPDATE Product SET quantity = quantity - ? WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all orders placed by a customer
    public List<String> getOrderHistory(int customerId) {
        List<String> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                String orderDetails = "Order ID: " + orderId + ", Product ID: " + productId + ", Quantity: " + quantity;
                orders.add(orderDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
