package chivos;
class Employee {
    private String firstName; //constructors
    private String lastName;
    private int employeeID;
    private double wage;

    public Employee(String firstName, String lastName, int employeeID, double wage) {//creates employee with parameters
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
