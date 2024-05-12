class Employee {
    private String firstName;
    private String lastName;
    private int employeeID;
    private double wage;

    public Employee(String firstName, String lastName, int employeeID, double wage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.wage = wage;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public double getWage() {
        return wage;
    }
}

class Manager extends Employee {
    private ArrayList<DayOffRequest> dayOffRequests;
    private WeeklySchedule weeklySchedule;

    public Manager(String firstName, String lastName, int employeeID, double wage) {
        super(firstName, lastName, employeeID, wage);
        this.dayOffRequests = new ArrayList<>();
        this.weeklySchedule = new WeeklySchedule();
    }

    public ArrayList<DayOffRequest> getDayOffRequests() {
        return dayOffRequests;
    }

    public WeeklySchedule getWeeklySchedule() {
        return weeklySchedule;
    }
}

class DayOffRequest {
    private int employeeID;
    private String date;

    public DayOffRequest(int employeeID, String date) {
        this.employeeID = employeeID;
        this.date = date;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getDate() {
        return date;
    }
}

class WeeklySchedule {
    private String[][] schedule = new String[7][24];

    public WeeklySchedule() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                schedule[i][j] = "Empty";
            }
        }
    }

    public void setSchedule(int day, int hour, String employee) {
        schedule[day][hour] = employee;
    }

    public String getSchedule(int day, int hour) {
        return schedule[day][hour];
    }
}

public class Main {
    private static Employee currentEmployee;
    private static Manager currentManager;
    private static WeeklySchedule weeklySchedule;

    public static void main(String[] args) {
        Employee employee = new Employee("Tony", "Pedraza", 1, 25.5);
        Manager manager = new Manager("Noah", "Gutierrez", 2, 20.75);

        weeklySchedule = new WeeklySchedule();
        weeklySchedule.setSchedule(0, 8, "Noah Gutierrez");
        weeklySchedule.setSchedule(1, 8, "John Doe");
        weeklySchedule.setSchedule(2, 8, "Jane Smith");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your employee ID:");
        int employeeID = scanner.nextInt();

        if (employeeID == manager.getEmployeeID()) {
            currentManager = manager;
            menuManager();
        } else if (employeeID == employee.getEmployeeID()) {
            currentEmployee = employee;
            menuEmployee();
        } else {
            System.out.println("Invalid employee ID.");
        }

        scanner.close();
    }

    private static void menuEmployee() {
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

            switch (choice) {
                case 1:
                    System.out.println("First Name: " + currentEmployee.getFirstName());
                    System.out.println("Last Name: " + currentEmployee.getLastName());
                    System.out.println("Employee ID: " + currentEmployee.getEmployeeID());
                    System.out.println("Wage: " + currentEmployee.getWage());
                    break;
                case 2:
                    // Implement day off request logic
                    break;
                case 3:
                    System.out.println("Weekly Schedule:");
                    for (int day = 0; day < 7; day++) {
                        System.out.println("Day " + day + ":");
                        for (int hour = 0; hour < 24; hour++) {
                            System.out.println(hour + ":00 - " + hour + ":30: " + weeklySchedule.getSchedule(day, hour));
                            System.out.println(hour + ":30 - " + (hour + 1) + ":00: " + weeklySchedule.getSchedule(day, hour));
                        }
                    }
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
  private static void menuManager() {
    Scanner scanner = new Scanner(System.in);
    int choice;

    while (true) {
        System.out.println("\nPlease choose an option:");
        System.out.println("1. See all employee info");
        System.out.println("2. View all requests to have a day off");
        System.out.println("3. Edit weekly schedule");
        System.out.println("4. Logout");
        System.out.print("> ");
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("First Name\tLast Name\tEmployee ID\tWage");
                System.out.println(currentManager.getFirstName() + "\t" + currentManager.getLastName() + "\t" + currentManager.getEmployeeID() + "\t" + currentManager.getWage());
                // Implement employee info logic
                break;
            case 2:
                // Implement day off request view logic
                System.out.println("Requests to have a day off:");
                for (DayOffRequest request : currentManager.getDayOffRequests()) {
                    System.out.println("Employee ID: " + request.getEmployeeID());
                    System.out.println("Date: " + request.getDate());
                }
                break;
            case 3:
                System.out.println("Weekly Schedule:");
                for (int day = 0; day < 7; day++) {
                    System.out.println("Day " + day + ":");
                    for (int hour = 0; hour < 24; hour++) {
                        System.out.println(hour + ":00 - " + hour + ":30: " + weeklySchedule.getSchedule(day, hour));
                        System.out.println(hour + ":30 - " + (hour + 1) + ":00: " + weeklySchedule.getSchedule(day, hour));
                    }
                }
                System.out.println("Please enter the day and hour you would like to edit (e.g. 0 8 for Monday 8:00):");
                int day = scanner.nextInt();
                int hour = scanner.nextInt();
                System.out.println("Please enter the new employee name:");
                String newEmployee = scanner.next();
                weeklySchedule.setSchedule(day, hour, newEmployee);
                break;
            case 4:
                System.out.println("Logging out...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
}
    
