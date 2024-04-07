package exercise13-9;

import java.util.Objects;

public class Circle extends GeometricObject implements Comparable<Circle> {
    private double radius;

    @Override
    public boolean equals(Object o) {
        Circle circle = (Circle) o;
        return this.radius == circle.radius;
    }

    @Override
    public int compareTo(Circle c1) {
        if (this.radius > c1.radius) {
            return 1;
        } else if (this.radius < c1.radius) {
            return -1;
        } else {
            return 0;
        }
    }

   
    public Circle() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled) {
        this.radius = radius;
        setColor(color);
        setFilled(filled);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return radius * radius * Math.PI;
    }

    public double getDiameter() {
        return 2 * radius;
    }


    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    public void printCircle() {
        System.out.println("The circle is created " + getDateCreated() +
                " and the radius is " + radius);
    }
  }
