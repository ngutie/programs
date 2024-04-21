import java.util.Scanner;
import java.util.ArrayList;

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

public class Main {//man class that brings up menus
    static Employee currentEmployee;//constructors
    static Manager currentManager;
    static WeeklySchedule weeklySchedule;

    public static void main(String[] args) {
        Employee employee = new Employee("Tony", "Pedraza", 1, 25.5); //these are made to have the program not think there is none employed and allow us to make new employees its a starting point
        Manager manager = new Manager("Noah", "Gutierrez", 2, 20.75);

        weeklySchedule = new WeeklySchedule();
        weeklySchedule.setSchedule(0, 8, "Noah Gutierrez");
        weeklySchedule.setSchedule(1, 8, "John Doe");
        weeklySchedule.setSchedule(2, 8, "Jane Smith");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your employee ID:");
        int employeeID = scanner.nextInt();//ask for id number and begins to valid a id number and chek if manager

       
        if (employeeID == manager.employeeID) {//decide which menu to print need to decide if i want to have one class for both menus or the two i have listed here
            currentManager = manager;
            menuManager();
        } else if (employeeID == employee.employeeID || employeeID == manager.employeeID) {
            currentEmployee = employee;
            menuEmployee();
        } else {
            System.out.println("Invalid employee ID.");
        }

        scanner.close();
    }

    public static void menu() {// shows menu and ask what the user wants to do next
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. See employee info");
            System.out.println("2. Make a request to have a day off");
            System.out.println("3. See weekly schedule");
            System.out.println("4. Logout");
            System.out.print("> ");
            choice = scanner.nextInt();
        }
    }
}
