package exercise13-9;
public class Exercise13-9 {
    public static void main(String[] args) {
        Circle c1 = new Circle(12.23);
        Circle c2 = new Circle(67.89);
        Circle c3 = new Circle(65.43);
        Circle c4 = new Circle(12.23);

        System.out.println("c1.equals(c2): " + c1.equals(c2));
        System.out.println("c1.equals(c4)" + c1.equals(c4));
        System.out.println("c2.compareTo(c3)" + c2.compareTo(c3));
    }


}
