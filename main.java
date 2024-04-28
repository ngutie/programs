import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Scanner;
import java.util.ArrayList;

public class Main extends Application {

    static Employee currentEmployee;
    static Manager currentManager;
    static WeeklySchedule weeklySchedule;

    @Override
    public void start(Stage primaryStage) {
        Employee employee = new Employee("Tony", "Pedraza", 1, 25.5); //these are made to have the program not think there is none employed and allow us to make new employees its a starting point
        Manager manager = new Manager("Noah", "Gutierrez", 2, 20.75);

        weeklySchedule = new WeeklySchedule();
        weeklySchedule.setSchedule(0, 8, "Noah Gutierrez");
        weeklySchedule.setSchedule(1, 8, "John Doe");
        weeklySchedule.setSchedule(2, 8, "Jane Smith");
      
        // Create a scene for asking for Employee ID
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        Label label = new Label("Please enter your employee ID:");
        gridPane.add(label, 0, 0);

        TextField textField = new TextField();
        gridPane.add(textField, 1, 0);

        Button button = new Button("Login");
        gridPane.add(button, 1, 1);

        button.setOnAction(e -> {
            int employeeID = Integer.parseInt(textField.getText());
            if (employeeID == currentManager.employeeID) {
                showMenuManager(primaryStage);
            } else if (employeeID == currentEmployee.employeeID || employeeID == currentManager.employeeID) {
                showMenuEmployee(primaryStage);
            } else {
                Label errorLabel = new Label("Invalid employee ID.");
                gridPane.add(errorLabel, 1, 2);
            }
        });

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMenuEmployee(Stage primaryStage) {
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);

        Button button1 = new Button("See employee info");
        button1.setOnAction(e -> {
            
            System.out.println("Employee info: " + currentEmployee.firstName + " " + currentEmployee.lastName + " " + currentEmployee.wage);
        });

        Button button2 = new Button("Make a request to have a day off");
        button2.setOnAction(e -> {
            
            System.out.println("Enter EmployeeID and the date:");
            Scanner scanner = new Scanner(System.in);
            int EmployeeId = scanner.next();
            String date = scanner.next();
            DayOffRequest newday = new DayOffRequest(EmployeeId, date);
            DayOffRequest.add(newday);
            System.out.println("Day off request made.");
        });

        Button button3 = new Button("See weekly schedule");
        button3.setOnAction(e -> {
            
            System.out.println("Weekly schedule:");
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 24; j++) {
                    System.out.print(weeklySchedule.getSchedule(i, j) + " ");
                }
                System.out.println();
            }
        });

        Button button4 = new Button("Logout");
        button4.setOnAction(e -> {
            
            System.out.println("Logged out.");
            showLoginScene(primaryStage);
        });

        vBox.getChildren().addAll(button1, button2, button3, button4);

        Scene scene = new Scene(vBox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
  private void showMenuManager(Stage primaryStage) {
    VBox vBox = new VBox(10);
    vBox.setAlignment(Pos.CENTER);

    Button button1 = new Button("See all employee info");
    button1.setOnAction(e -> {
        
        System.out.println("All employee info: ");
       System.out.println(currentEmployee.firstName + " " + currentEmployee.lastName + " " + currentEmployee.wage);
        System.out.println(currentManager.firstName + " " + currentManager.lastName + " " + currentManager.wage);
        for (Employee employee : employees) {
            if (employee != currentEmployee && employee != currentManager) {
                System.out.println(employee.firstName + " " + employee.lastName + " " + employee.wage);
            }
        }
    });

    Button button2 = new Button("View all request to have a day off");
    button2.setOnAction(e -> {
        
        System.out.println("All day off requests: ");
        for (DayOffRequest request : dayOffRequests) {
            System.out.println(request.employeeID + " " + request.date);
        }
    });

    Button button3 = new Button("Edit weekly schedule");
    button3.setOnAction(e -> {
        
        System.out.println("Enter the day of the week (0-6) and the hour (0-23) to edit:");
        Scanner scanner = new Scanner(System.in);
        int day = scanner.nextInt();
        int hour = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter the name of the employee to assign:");
        String employeeName = scanner.nextLine();
        weeklySchedule.setSchedule(day, hour, employeeName);
        System.out.println("Weekly schedule edited.");
    });

    Button button4 = new Button("Create new employee");
    button4.setOnAction(e -> {
        
        System.out.println("Enter the first name, last name, and wage of the new employee:");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.next();
        String lastName = scanner.next();
        double wage = scanner.nextDouble();
        Employee newEmployee = new Employee(firstName, lastName, employees.size() + 2, wage);
        employees.add(newEmployee);
        System.out.println("New employee created: " + newEmployee.firstName + " " + newEmployee.lastName);
    });

    Button button5 = new Button("Create new manager");
    button5.setOnAction(e -> {
        
        System.out.println("Enter the first name, last name, and wage of the new manager:");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.next();
        String lastName = scanner.next();
        double wage = scanner.nextDouble();
        Manager newManager = new Manager(firstName, lastName, employees.size() + 1, wage);
        employees.add(newManager);
        System.out.println("New manager created: " + newManager.firstName + " " + newManager.lastName);
    });

    Button button6 = new Button("Logout");
    button6.setOnAction(e -> {
        
        System.out.println("Logged out.");
        showLoginScene(primaryStage);
    });

    vBox.getChildren().addAll(button1, button2, button3, button4, button5, button6);

    Scene scene = new Scene(vBox, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  class Employee { //creates and saves employee data
    String firstName; //constructors
    String lastName;
    int employeeID;
    double wage;

    public Employee(String firstName, String lastName, int employeeID, double wage) {//creates new employee
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.wage = wage;
    }
}

class Manager extends Employee { //class that creates and saves manager data
    ArrayList<DayOffRequest> dayOffRequests;
    WeeklySchedule weeklySchedule;

    public Manager(String firstName, String lastName, int employeeID, double wage) { //creates new manager
        super(firstName, lastName, employeeID, wage);
        this.dayOffRequests = new ArrayList<DayOffRequest>();
        this.weeklySchedule = new WeeklySchedule();
    }
}

class DayOffRequest { //class that creats and saves day off request using id number and a date
    int employeeID;//constructors
    String date;

    public DayOffRequest(int employeeID, String date) {//creates new request
        this.employeeID = employeeID;
        this.date = date;
    }
}

class WeeklySchedule {//class creates and saves weekly schedule in a array
    String schedule[][] = new String[7][24];//constructors

    public WeeklySchedule() {//loop that creates schedule with array and sets every to empty to start
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                schedule[i][j] = "Empty";
            }
        }
    }

    public void setSchedule(int day, int hour, String employee) {//allows someon to enter an employee to work at a certain time
        schedule[day][hour] = employee;
    }

    public String getSchedule(int day, int hour) {//allows someone to see who works at a certain time
        return schedule[day][hour];
    }
}

}
