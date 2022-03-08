import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// help
// method
// time
// incoordinate
// outcoordinate

public class p1 {

	public static void main(String[] args) {
		// stores random things that need to be printed sometimes
		String x = "";

		int commandM = 0;
		boolean printTime = true;

		// true, coordinate mode
		// false, map mode
		boolean inType = false;
		boolean outType = false;

		System.out.println("|----------------------------------------|");
		System.out.println("|   Project 1: Kirby's Quest for Cake    |");
		System.out.println("|----------------------------------------|");
		System.out.println("|                                        |");

		for (String arg : args) {
			if (arg.equals("--Help")) {
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
				System.out.println("|                                        |");
				System.exit(0);
			} else if (arg.equals("--Stack")) {
				commandM += 1;
			} else if (arg.equals("--Queue")) {
				commandM += 2;
			} else if (arg.equals("--Opt")) {
				commandM += 4;
			} else if (arg.equals("--Time")) {
				printTime = true;
			} else if (arg.equals("--Incoordinate")) {
				inType = true;
			} else if (arg.equals("--OutCoordinate")) {
				outType = true;
			}
		}

		int method = 0;
		if (commandM == 1) { // stack
			method = 1;
		} else if (commandM == 2) { // queue
			method = 2;
		} else if (commandM == 4) { // optimal
			method = 3;
		}

		if (method == 0) {
			System.out.println("|                 Error                  |");
			System.out.println("|                                        |");
			System.out.println("| Please specify one of the available    |");
			System.out.println("| methods for finding a route            |");
			System.out.println("|                                        |");
			System.out.println("| Pass --Help to see options             |");
			System.out.println("|                                        |");
			System.exit(-1);
		}

		int mapNum = 2;

		try {
			// parameter variables available
			// method = 1:stack, 2:queue, 3:opt
			// printTime
			// inType = true:coordinate, false:map
			// outType = true:coordinate, false:map

			Map m = new Map(new Scanner(new File("cable-map" + mapNum + (inType ? "-cor" : "") + ".txt")), inType);
			PathFinder f = new PathFinder();

			// print map before finding path
			System.out.println(m.printMapFormatted());

			// print the path-finding method being used
			x = ((method == 1) ? "stack" : ((method == 2) ? "queue" : "optimal"));
			System.out.println("|----------------------------------------|");
			System.out.println("| finding path with " + x + "          ".substring(x.length()) + "           |");
			System.out.println("|----------------------------------------|");
			System.out.println("|                                        |");

			// start timer
			long start = System.currentTimeMillis();

			Map pathFound = f.find(m, method);

			// print path finding runtime
			x = "" + ((System.currentTimeMillis() - start) / 1000);
			if (printTime) {
				System.out.println("|----------------------------------------|");
				System.out.println("| path found                             |");
				System.out.println("| elapsed time: " + x + "                        ".substring(x.length()) + " |");
				System.out.println("|----------------------------------------|");
				System.out.println("|                                        |");
			}

			System.out.println(pathFound.printMapFormatted());

		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}
}
