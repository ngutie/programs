package chivos;

class Manager extends Employee {
    private ArrayList<DayOffRequest> dayOffRequests; // constructors
    private WeeklySchedule weeklySchedule;

    public Manager(String firstName, String lastName, int employeeID, double wage) {//creates manager with parameters
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
