import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// method = true, coordinate mode
// method = false, map mode

// abcde
// a - help
// b - method
// c - time
// d - incoordinate
// e - outcoordinate

public class p1 {

	public static void main(String[] args) {
		int commands = 00000;
		System.out.println("|----------------------------------------|");
		System.out.println("|    Project 1: Kirby's Quest for Cake   |");
		System.out.println("|----------------------------------------|");
		for (String arg : args) {
			if (arg.equals("--Help")) {
				commands += 10000;
			} else if (arg.equals("--Stack")) {
				commands += 1000;
			} else if (arg.equals("--Queue")) {
				commands += 2000;
			} else if (arg.equals("--Opt")) {
				commands += 4000;
			} else if (arg.equals("--Time")) {
				commands += 100;
			} else if (arg.equals("--Incoordinate")) {
				commands += 10;
			} else if (arg.equals("--OutCoordinate")) {
				commands += 1;
			}
		}

		switch (commands) {
		case "--Help":
			System.out.println("|                                        |");
			System.out.println("|                  Help                  |");
			System.out.println("|                                        |");
			System.out.println("| --Stack                                |");
			System.out.println("|    Use a stack-based approach only     |");
			System.out.println("| --Queue                                |");
			System.out.println("|    Use a queue-based approach only     |");
			System.out.println("| --Opt                                  |");
			System.out.println("|    Find the best path to the cake      |");
			System.out.println("| --Incoordinate                         |");
			System.out.println("|    File is in coordinate system        |");
			System.out.println("| --Outcoordinate                        |");
			System.out.println("|    Print in coordinate system          |");
			System.out.println("| --Help                                 |");
			System.out.println("|    View this help dialog               |");
			System.exit(0);
			break;
		case "--Stack":
			break;
		case "--Queue":
			break;
		case "--Opt":
			break;
		case "--Incoordinate":
			break;
		case "--Outcoordinate":
			break;
		default:
			break;
		}

		boolean method = false;
		int mapNum = 2;
		try {
			Scanner scan = new Scanner(new File("cable-map" + mapNum + (method ? "-cor" : "") + ".txt"));

			Map m = new Map(scan.nextInt(), scan.nextInt(), scan.nextInt());
			scan.nextLine(); // move scanner to next line (nextInt does not move to next line)

			m.processMap(scan, method);
			System.out.println(m);

			long start = System.currentTimeMillis();

			//

			System.out.println((System.currentTimeMillis() - start) / 1000);
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
// .
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}
}
