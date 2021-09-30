
public class GraphicsCard {

	private double price;
	private String brand;
	private String model;

	// 1) write the default constructor that sets the values $900, "NVidia", "3060"
	public GraphicsCard() {
		// the job of the constructor is to set the instance variables
		this.price = 700;
		this.brand = "NVidia";
		this.model = "3060";
	}

	// 2) write the non-default constructor that allows setting the 3 instance
	// variables

	public GraphicsCard(double price, String brand, String model) {
		this.price = price;
		this.brand = brand;
		this.model = model;
	}

	// getters and setters

	// GETTERS or ACCESSORS - allow access (read) to private instance variables
	// public returnType getNameOfAttribute() {return NameOfAttribute;}

	public double getPrice() {
		return this.price;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getModel() {
		return this.model;
	}

	/**
	 * override the toString method which will allow passing objects of this type to
	 * the print and println method
	 * 
	 * this method should return a string representation of the object
	 */

	public String toString() {
		return "This is a " + this.brand + " graphics card, model " + this.model + " which costs $" + this.price + ".";
	}

	// SETTERS or MUTATORS - allow access (write) to private instance variables
	// public void setNameOfAttribute(NameOfAttribute) {this.NameOfAttribute =
	// NameOfAttribute;}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
