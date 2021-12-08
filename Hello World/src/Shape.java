
public class Shape {
	private static int nID;
	protected int id;

	public static void main(String[] args) {
		System.out.println(new Triangle(3, 9));
		System.out.println(new Circle(2));
	}

	public Shape() {
		id = nID;
		nID++;
	}

	public double getArea() {
		return 0.0;
	}

	public String toString() {
		return "I am a " + this.getClass().getName() + " with an area of " + getArea() + ". I am shape #" + id;
	}
}

class Rectangle extends Shape {
	protected double length, width;

	public Rectangle(double length, double width) {
		super();
		this.length = length;
		this.width = width;
	}

	public double getArea() {
		return length * width;
	}
}

class Square extends Rectangle {
	public Square(int length) {
		super(length, length);
	}
}

class Triangle extends Shape {
	protected double width, height;

	public Triangle(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}

	public double getArea() {
		return (width * height) / 2;
	}
}

class Ellipse extends Shape {
	protected double radiusA;
	protected double radiusB;

	public Ellipse(double radiusA, double radiusB) {
		super();
		this.radiusA = radiusA;
		this.radiusB = radiusB;
	}

	public double getArea() {
		return Math.PI * radiusA * radiusB;
	}
}

class Circle extends Ellipse {

	public Circle(double radius) {
		super(radius, radius);
	}
}