import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Exercise35_1 {

public class DatabaseConnection {
    private Connection connection;

    public void connect(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}


public class DBConnectionPanel extends GridPane {
    private TextField urlField;
    private TextField userField;
    private TextField passwordField;
    private DatabaseConnection databaseConnection;

    public DBConnectionPanel(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

        Label urlLabel = new Label("URL:");
        Label userLabel = new Label("User:");
        Label passwordLabel = new Label("Password:");

        urlField = new TextField();
        userField = new TextField();
        passwordField = new TextField();

        add(urlLabel, 0, 0);
        add(urlField, 1, 0);
        add(userLabel, 0, 1);
        add(userField, 1, 1);
        add(passwordLabel, 0, 2);
        add(passwordField, 1, 2);

        Button connectButton = new Button("Connect to Database");
        connectButton.setOnAction(e -> {
            try {
                databaseConnection.connect(urlField.getText(), userField.getText(), passwordField.getText());
            } catch (SQLException ex) {
                // Handle exception
            }
        });
        add(connectButton, 1, 3);
    }

    public String getUrl() {
        return urlField.getText();
    }

    public String getUser() {
        return userField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }
}



public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        DBConnectionPanel dbConnectionPanel = new DBConnectionPanel(databaseConnection);

        BorderPane root = new BorderPane();
        root.setCenter(dbConnectionPanel);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

public class BatchUpdatesTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL driver not found.");
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "username", "password")) {
            // Create Temp table
            String createTableSql = "CREATE TABLE IF NOT EXISTS Temp (num1 DOUBLE, num2 DOUBLE, num3 DOUBLE)";
            try (PreparedStatement statement = connection.prepareStatement(createTableSql)) {
                statement.execute();
            }

            // Insert 1000 records with batch updates
            String insertSql = "INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < 1000; i++) {
                    statement.setDouble(1, Math.random());
                    statement.setDouble(2, Math.random());
                    statement.setDouble(3, Math.random());
                    statement.addBatch();
                }
                statement.executeBatch();
                long endTime = System.currentTimeMillis();
                System.out.println("Time taken for batch updates: " + (endTime - startTime) + " ms");
            }

            // Insert 1000 records without batch updates
            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < 1000; i++) {
                    statement.setDouble(1, Math.random());
                    statement.setDouble(2, Math.random());
                    statement.setDouble(3, Math.random());
                    statement.executeUpdate();
                }
                long endTime = System.currentTimeMillis();
                System.out.println("Time taken for individual inserts: " + (endTime - startTime) + " ms");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
    }
}
}
