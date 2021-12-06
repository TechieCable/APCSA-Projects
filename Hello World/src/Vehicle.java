
class Boat extends Vehicle {
	public Boat() {
		super();
		numWheels = 0;
	}

	public String toString() {
		return super.toString() + " I am a boat.";
	}
}

class Sailboat extends Boat {
	protected int numSails;

	public Sailboat() {
		super();
		numSails = 3;
	}

	public String toString() {
		return super.toString() + " I am a sailboat.";
	}
}

class Motorboat extends Boat {
	public Motorboat() {
		super();
	}

	public String toString() {
		return super.toString() + " I am a motorboat.";
	}
}

class Aircraft extends Vehicle {
	public Aircraft() {
		super();
		numWheels = 2;
	}

	public String toString() {
		return super.toString() + " I am an aircraft.";
	}
}

class Helicopter extends Aircraft {
	public Helicopter() {
		super();
		numWheels = 0;
	}

	public String toString() {
		return super.toString() + " I am a helicopter.";
	}
}

class Blimp extends Aircraft {
	public Blimp() {
		super();
		numWheels = 0;
	}

	public String toString() {
		return super.toString() + " I am a blimp.";
	}
}

class Airplane extends Aircraft {
	public Airplane() {
		super();
		numWheels = 0;
	}

	public String toString() {
		return super.toString() + " I am an airplane.";
	}
}

class Car extends Vehicle {

	public Car() {
		// call the parent constructor
		super();
	}

	public String toString() {
		return super.toString() + " I am a car with " + numWheels + " wheels.";
	}
}

class Truck extends Car {
	public Truck() {
		super();
	}

	public String toString() {
		return super.toString() + " I am a truck.";
	}
}

class Van extends Car {
	public Van() {
		super();
	}

	public String toString() {
		return super.toString() + " I am a van.";
	}
}

public class Vehicle {

	public static void main(String[] arg) {
		Vehicle v = new Vehicle();
		Car c = new Car();
		System.out.println(v);
		System.out.println(c);

		// mixing types and constructors
		Vehicle v2 = new Car();
		System.out.println(v2);
		// valid if a car is-a-kind-of vehicle

		// does not work, not a is-a-kind-of relationship
		// Car c2 = new Vehicle();

		System.out.println(new Boat());
		System.out.println(new Sailboat());

		System.out.println();
	}

	// "protected" changes visibility to all related classes
	protected int numWheels;
	protected String motor;
	protected String color;
	protected String model;
	protected String manufacturer;

	public Vehicle() {
		numWheels = 4;
		motor = "4-cylinder";
		color = "red";
		model = "generic";
		manufacturer = "generic";
	}

	@Override
	public String toString() {
		return "I am a vehicle.";
	}

	public int getNumWheels() {
		return numWheels;
	}

	public void setNumWheels(int numWheels) {
		this.numWheels = numWheels;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
}
