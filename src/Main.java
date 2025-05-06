public class Main {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        boolean success = customerDAO.loginCustomer("bob@example.com", "mySecret123");
        System.out.println("Login result: " + success);
    }
}