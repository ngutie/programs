import java.util.Date;
import java.util.Scanner;

public class GeometricObject {
    private String color = "white";
    private boolean filled;
    private Date dateCreated;


    public GeometricObject() {

        dateCreated = new Date();

    }

    public GeometricObject(String color, boolean filled) {

        dateCreated = new Date();
        this.color = color;
        this.filled = filled;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "created on: " + dateCreated + "\ncolor: " + color + " and filled: "
                + filled;
    }
    public class Triangle{
        private double side1;
        private double side2;
        private double side3;


        public Triangle() {

             this.side1 = 1.0;
            this.side2 = 1.0;
            this.side3 = 1.0;

        }

        public Triangle(double newSide1, double newSide2, double newSide3) {

            this.side1 = newSide1;
            this.side2 = newSide2;
            this.side3 = newSide3;

        }

        public double getArea() {
             double s = (this.side1 + this.side2 + this.side3) / 2;
            return Math.sqrt(s * (s - this.side1) * (s - this.side2) * (s - this.side3));
        }

        public double getPerimeter() {

            double p = this.side1 + this.side2 + this.side3;

            return p;

        }

        public double getSide1() {
            return side1;
        }

        public double getSide2() {
            return side2;
        }

        public double getSide3() {
            return side3;
        }

        @Override
        public String toString() {
            return "Triangle: side1 = " + this.side1 + " side2 = " + this.side2 +
                " side3 = " + this.side3;
        }
    }
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the three sides of the triangle:");

        double one = input.nextDouble();
        double two = input.nextDouble();
        double three = input.nextDouble();

        Triangle triangle = new Triangle(one, two, three);

        System.out.print("What color is the triangle?");

        String color = input.next();

        System.out.print("Is the triangle filled? (Enter Yes or No):");

        String fill = input.next();

        triangle.setColor(color);

        boolean filled;

        filled = !"No".matches(fill);

        triangle.setFilled(filled);

        System.out.println("For " + triangle.toString() + " the area is " + triangle.getArea()
                + " square units, " + "\nThe perimeter is " + triangle.getPerimeter());

        System.out.println("The current color is " + triangle.getColor() + " \nAnd "
                + "is the triangle filled is: " + triangle.isFilled());

           input.close();
    }

}
