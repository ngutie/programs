import java.sql.*;

public class StaffDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/yourDBName";
    private static final String DB_USER = "yourUsername";
    private static final String DB_PASSWORD = "yourPassword";

    public static void main(String[] args) {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create statement
            Statement stmt = conn.createStatement();

            // View staff
            viewStaff(stmt, "123456789");

            // Insert a new staff
            insertStaff(stmt, "Doe", "John", "A", "123 Main St", "Anytown", "CA", "1234567890", "john.doe@example.com");

            // Update the staff
            updateStaff(stmt, "123456789", "Smith", "Jane", "B", "456 Elm St", "Othertown", "NY", "0987654321", "jane.smith@example.com");

            // Close the connection
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewStaff(Statement stmt, String id) throws SQLException {
        String sql = "SELECT * FROM Staff WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println("ID: " + rs.getString("id"));
            System.out.println("Last Name: " + rs.getString("lastName"));
            System.out.println("First Name: " + rs.getString("firstName"));
            System.out.println("MI: " + rs.getString("mi"));
            System.out.println("Address: " + rs.getString("address"));
            System.out.println("City: " + rs.getString("city"));
            System.out.println("State: " + rs.getString("state"));
            System.out.println("Telephone: " + rs.getString("telephone"));
            System.out.println("Email: " + rs.getString("email"));
        }

        rs.close();
    }

    private static void insertStaff(Statement stmt, String lastName, String firstName, String mi, String address, String city, String state, String telephone, String email) throws SQLException {
        String sql = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES ('123456789', '" + lastName + "', '" + firstName + "', '" + mi + "', '" + address + "', '" + city + "', '" + state + "', '" + telephone + "', '" + email + "')";
        stmt.executeUpdate(sql);
    }

    private static void updateStaff(Statement stmt, String id, String lastName, String firstName, String mi, String address, String city, String state, String telephone, String email) throws SQLException {
        String sql = "UPDATE Staff SET lastName='" + lastName + "', firstName='" + firstName + "', mi='" + mi + "', address='" + address + "', city='" + city + "', state='" + state + "', telephone='" + telephone + "', email='" + email + "' WHERE id='" + id + "'";
        stmt.executeUpdate(sql);
    }
}
