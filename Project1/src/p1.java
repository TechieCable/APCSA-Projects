import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p1 {

	public static void main(String[] args) {
		boolean cor = false;
		try {
			Scanner scan = new Scanner(new File("cable-map3" + (cor ? "-cor" : "") + ".txt"));

			Map m = new Map(scan.nextInt(), scan.nextInt(), scan.nextInt());
			scan.nextLine(); // move scanner to next line (nextInt does not move to next line)

			if (cor) {
				mapCors(scan, m);
			} else {
				mapLines(scan, m);
			}

			System.out.println(m);
			System.out.println(m.cors());

		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}

	public static void mapCors(Scanner scan, Map m) {
		int d = 0; // room number
		int prevR = 0;
		// loop through each line
		while (scan.hasNextLine()) {
			char value = scan.next().charAt(0);
			int row = scan.nextInt();
			int col = scan.nextInt();

			if (row < prevR) {
				d++;
			}

			m.set(d, row, col, value);

			prevR = row;
		}
	}

	public static void mapLines(Scanner scan, Map m) {
		int d = 0; // room number
		int r = 0; // row number
		// loop through each line
		while (scan.hasNextLine()) {
			String line = scan.nextLine();

			System.out.println("row = " + r + ", " + line);
			for (int i = 0; i < line.length(); i++) {
				// use charAt to grab each element of the map for a given row (r)
				m.set(d, r, i, line.charAt(i));
			}
			r++;
			if (r == m.rows) { // when r is number of rows in a room, reset
				d++;
				r = 0;
			}

		}
	}
}
