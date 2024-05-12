package chivos;
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