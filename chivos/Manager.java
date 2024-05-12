package chivos;

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