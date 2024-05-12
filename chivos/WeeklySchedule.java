package chivos;

public class WeeklySchedule {
    private String[][] schedule = new String[7][24]; //constructors

    public WeeklySchedule() {//uses loop to create array to store schedule
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
