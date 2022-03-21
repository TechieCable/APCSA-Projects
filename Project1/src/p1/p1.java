public class p1 {
	// help
	// method
	// time
	// incoordinate
	// outcoordinate

	public p1() {

	}

	public static void main(String[] args) {
		PathFinder p = new PathFinder(args);

		System.out.println(p.find().printMap(p.outType));
	}
}

class Print {
	static final String ps = "                                      ";
	static boolean silence = true;

	public static void er(String... lines) {
		if (silence)
			return;
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