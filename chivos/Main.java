package chivos;
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

public class Main{
        private static Employee currentEmployee;
        private static Manager currentManager;
        private static WeeklySchedule weeklySchedule;
    
        @Override
        public void start(Stage primaryStage) {
            Employee employee = new Employee("Tony", "Pedraza", 1, 25.5);//creates premade employee and manager to avoid error
            Manager manager = new Manager("Noah", "Gutierrez", 2, 20.75);
    
            weeklySchedule = new WeeklySchedule();//makes a schedule with some premade people scheduled
            weeklySchedule.setSchedule(0, 8, "Noah Gutierrez");
            weeklySchedule.setSchedule(1, 8, "John Doe");
            weeklySchedule.setSchedule(2, 8, "Jane Smith");
    
            showLoginScene(primaryStage);
        }
    
        private void showLoginScene(Stage primaryStage) {// makes login screen asking for employeeid
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
                if (employeeID == currentManager.getEmployeeID()) {
                    currentManager = manager;
                    showMenuManager(primaryStage);
                } else if (employeeID == currentEmployee.getEmployeeID()) {
                    currentEmployee = employee;
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
        private void showMenuEmployee(Stage primaryStage) {//shows menu for employee and makes a new screen with new buttons
                VBox vBox = new VBox(10);
                vBox.setAlignment(Pos.CENTER);
            
                Button button1 = new Button("See employee info");
                button1.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Employee Information");
                    alert.setContentText("First Name: " + currentEmployee.getFirstName() + "\n" +
                                            "Last Name: " + currentEmployee.getLastName() + "\n" +
                                            "Employee ID: " + currentEmployee.getEmployeeID() + "\n" +
                                            "Wage: " + currentEmployee.getWage());
                    alert.showAndWait();
                });
            
                Button button2 = new Button("Make a request to have a day off");
                button2.setOnAction(e -> {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Day Off Request");
                    dialog.setHeaderText("Enter the date for the day off request:");
                    dialog.showAndWait();
                    String date = dialog.getResult();
                    if (date != null && !date.trim().isEmpty()) {
                        DayOffRequest newday = new DayOffRequest(currentEmployee.getEmployeeID(), date);
                        DayOffRequest.add(newday);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Day Off Request");
                        alert.setContentText("Day off request made.");
                        alert.showAndWait();
                    }
                });
            
                Button button3 = new Button("See weekly schedule");
                button3.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Weekly Schedule");
                    String schedule = weeklySchedule.toString();
                    alert.setContentText(schedule);
                    alert.showAndWait();
                });
            
                Button button4 = new Button("Logout");
                button4.setOnAction(e -> {
                    showLoginScene(primaryStage);
                });
            
                vBox.getChildren().addAll(button1, button2, button3, button4);
            
                Scene scene = new Scene(vBox, 300, 200);
                primaryStage.setScene(scene);
                primaryStage.show();
        }
        private void showMenuManager(Stage primaryStage) {//shows manager menu with a new screen and buttons
                VBox vBox = new VBox(10);
                vBox.setAlignment(Pos.CENTER);
            
                Button button1 = new Button("See manager info");
                button1.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Manager Information");
                    alert.setContentText("First Name: " + currentManager.getFirstName() + "\n" +
                                            "Last Name: " + currentManager.getLastName() + "\n" +
                                            "Employee ID: " + currentManager.getEmployeeID() + "\n" +
                                            "Wage: " + currentManager.getWage());
                    alert.showAndWait();
                });
            
                Button button2 = new Button("See all day off requests");
                button2.setOnAction(e -> {
                    StringBuilder builder = new StringBuilder();
                    for (DayOffRequest request : DayOffRequest.getRequests()) {
                        builder.append("Employee ID: ").append(request.getEmployeeID()).append("\n");
                        builder.append("Date: ").append(request.getDate()).append("\n");
                        builder.append("Status: ").append(request.getStatus()).append("\n");
                        builder.append("-----------------------------------\n");
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("All Day Off Requests");
                    alert.setContentText(builder.toString());
                    alert.showAndWait();
                });
            
                Button button3 = new Button("Approve a day off request");
                button3.setOnAction(e -> {
                    DayOffRequestRequestApprovalDialog requestApprovalDialog = new DayOffRequestRequestApprovalDialog();
                    Optional<DayOffRequest> result = requestApprovalDialog.showAndWait();
                    if (result.isPresent()) {
                        DayOffRequest request = result.get();
                        request.setStatus(DayOffRequest.Status.APPROVED);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Day Off Request Approval");
                        alert.setContentText("Day off request approved.");
                        alert.showAndWait();
                    }
                });
            
                Button button4 = new Button("Logout");
                button4.setOnAction(e -> {
                    showLoginScene(primaryStage);
                });
            
                vBox.getChildren().addAll(button1, button2, button3, button4);
            
                Scene scene = new Scene(vBox, 300, 200);
                primaryStage.setScene(scene);
                primaryStage.show();
        }
}
