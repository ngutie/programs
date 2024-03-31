import java.util.GregorianCalendar;
public class mydate {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    private int year;
    private int month;
    private int day;

    public mydate() {
        this(System.currentTimeMillis());
    }

    public mydate(long elapsedTime) {
        setDate(elapsedTime);
    }

    public mydate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }


    public void setDate(long elapsedTime) {
        gregorianCalendar.setTimeInMillis(elapsedTime);
        this.year = gregorianCalendar.get(GregorianCalendar.YEAR);
        this.month = gregorianCalendar.get(GregorianCalendar.MONTH);
        this.day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public int getYear() {
        return year;
    }

    public mydate setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public mydate setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getDay() {
        return day;
    }

    public mydate setDay(int day) {
        this.day = day;
        return this;
    }

    public static void main(String[] args){
        mydate myDate1 = new mydate();
        mydate myDate2 = new mydate(34355555133101L);


        System.out.println("MyDate1:\n year=" + myDate1.getYear() + "\n month=" + myDate1.getMonth() + "\n day=" + myDate1.getDay());
        System.out.println("MyDate2:\n year=" + myDate2.getYear() + "\n month=" + myDate2.getMonth() + "\n day=" + myDate2.getDay());
    }
}