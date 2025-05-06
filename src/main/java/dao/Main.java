package main.java.dao;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        SellerDAO sellerDAO = new SellerDAO();
        OrderDAO orderDAO = new OrderDAO();
        ReviewDAO reviewDAO = new ReviewDAO();

        // Add a review for a product
        boolean reviewAdded = reviewDAO.addReview(1, 1, 5, "Great product, highly recommend it!", "Alice");
        System.out.println("Review added: " + reviewAdded);

// Get reviews for a product
        List<String> reviews = reviewDAO.getReviewsForProduct(1);
        System.out.println("Reviews for product 1:");
        for (String review : reviews) {
            System.out.println(review);
        }
    }
}