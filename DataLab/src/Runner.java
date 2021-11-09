import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File text = new File("Cereal.csv");

		ArrayList<Cereal> cereals = new ArrayList<Cereal>();

		Scanner scnr;
		try {
			scnr = new Scanner(text);

			// skip header line
			scnr.nextLine();

			// loop through every line
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine();

				String[] lineArr = line.split(",");
				// Name,Type,Calories,Protein,Fat,Sodium,Fiber,Carbohydrates,Sugar,Potassium,Vitamins,Shelf,Weight,Cups,Rating

				// System.out.println(line);
				Cereal temp = new Cereal(lineArr[0], lineArr[1], Integer.valueOf(lineArr[2]),
						Integer.valueOf(lineArr[3]), Integer.valueOf(lineArr[4]), Integer.valueOf(lineArr[5]),
						Double.valueOf(lineArr[6]), Double.valueOf(lineArr[7]), Integer.valueOf(lineArr[8]),
						Integer.valueOf(lineArr[9]), Integer.valueOf(lineArr[10]), Integer.valueOf(lineArr[11]),
						Double.valueOf(lineArr[12]), Double.valueOf(lineArr[12]), Double.valueOf(lineArr[13]));

				// String name, String type, int calories,
				// int protein, int fat, int sodium,
				// Double fiber, Double carbs, int sugar,
				// int potassium, int vitamins, int shelf,
				// Double weight, Double cups, Double ratings
				cereals.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Cereal k = cereals.get(0);

		for (int i = 1; i < cereals.size(); i++) {
			if (cereals.get(i).potassium < k.potassium) {
				k = cereals.get(i);
			}
		}

		System.out.println(k);
	}

}

class Cereal {

	String name;
	String type;

	int calories, protein, fat, sodium;
	Double fiber, carbs;
	int sugar, potassium, vitamins, shelf;
	Double weight, cups, rating;
	boolean glutenFree;

	public Cereal(String name, String type, int calories, int protein, int fat, int sodium, Double fiber, Double carbs,
			int sugar, int potassium, int vitamins, int shelf, Double weight, Double cups, Double rating) {
		this.name = name;
		this.type = type;
		this.calories = calories;
		this.protein = protein;
		this.fat = fat;
		this.sodium = sodium;
		this.fiber = fiber;
		this.carbs = carbs;
		this.sugar = sugar;
		this.potassium = potassium;
		this.vitamins = vitamins;
		this.shelf = shelf;
		this.weight = weight;
		this.cups = cups;
		this.rating = rating;
	}

	public String toString() {
		return "Cereal [name=" + name + ", type=" + type + ", calories=" + calories + ", protein=" + protein + ", fat="
				+ fat + ", sodium=" + sodium + ", fiber=" + fiber + ", carbs=" + carbs + ", sugar=" + sugar
				+ ", " + "\n\t" + "potassium=" + potassium + ", vitamins=" + vitamins + ", shelf=" + shelf + ", weight=" + weight
				+ ", cups=" + cups + ", rating=" + rating + "]";
	}

}
