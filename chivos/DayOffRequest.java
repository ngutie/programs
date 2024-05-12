package chivos;

public class DayOffRequest {
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
