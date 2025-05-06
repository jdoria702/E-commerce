import java.sql.*;
import java.util.*;

public class ReviewDAO {

    // Add a review for a product
    public boolean addReview(int productId, int customerId, int rating, String review, String reviewerName) {
        String query = "INSERT INTO Review (product_id, customer_id, rating, review, reviewer_name) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);
            stmt.setInt(2, customerId);
            stmt.setInt(3, rating);
            stmt.setString(4, review);
            stmt.setString(5, reviewerName);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get reviews for a product
    public List<String> getReviewsForProduct(int productId) {
        List<String> reviews = new ArrayList<>();
        String query = "SELECT * FROM Review WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String reviewerName = rs.getString("reviewer_name");
                int rating = rs.getInt("rating");
                String review = rs.getString("review");
                String reviewDetails = "Reviewer: " + reviewerName + ", Rating: " + rating + "/5, Review: " + review;
                reviews.add(reviewDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
