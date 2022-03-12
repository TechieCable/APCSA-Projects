import java.io.FileWriter;
import java.io.IOException;

public class p1 {
	// help
	// method
	// time
	// incoordinate
	// outcoordinate

	static final int mapNum = 8;

	public static void main(String[] args) {
		PathFinder p = new PathFinder(args);

		p.setMap(mapNum);

		// print map before finding path
		p.m.printMapFormatted();

		// print the path-finding method being used
		p.printMethod();

		// start timer
		p.startTimer();

		Map pathFound = p.find();

		// end timer and print time
		p.printTime();

		pathFound.printMapFormatted();

		try {
			FileWriter f = new FileWriter("lastmap.txt");
			f.write("" + pathFound.printMap(p.outType));
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Print {
	static final String ps = "                                      ";

	public static void er(String... lines) {
		for (int i = 0; i < lines.length; i++) {
			boolean center = false;

			// 00: centering command
			// 01: separation lines command
			// 02: blank line command
			// 03: error command
			if (lines[i].length() >= 2) {
				String cmd = lines[i].substring(0, 2);
				if (cmd.equals("00")) {
					center = true;
					lines[i] = lines[i].substring(2);
					if (lines[i].length() % 2 == 1) {
						lines[i] += " ";
					}
				} else if (cmd.equals("01")) {
					System.out.println("|----------------------------------------|");
					continue;
				} else if (cmd.equals("02")) {
					System.out.println("|                                        |");
					continue;
				} else if (cmd.equals("03")) {
					System.out.println("|                 Error                  |");
					System.out.println("|                                        |");
					continue;
				}

			}

			// handle line that is too long
			if (lines[i].length() > ps.length()) {
				System.out.println("| " + lines[i] + " |");
				continue;
			}

			// generate proper whitespace
			String pad = ps.substring(lines[i].length());

			// print message
			System.out.print("| ");
			if (center) {
				System.out.print(pad.substring(pad.length() / 2) + lines[i] + pad.substring(pad.length() / 2));
			} else {
				System.out.print(lines[i] + pad);
			}
			System.out.println(" |");

		}

		// print ending line
		System.out.println("| " + ps + " |");
	}
}