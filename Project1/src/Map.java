import java.util.Scanner;

public class Map {
	char[][][] data;
	int rooms;
	int rows;
	int cols;

	// rows, columns, rooms

	// data[0] = {{".","."},{".","."}}

	public Map(int rows, int cols, int rooms) {
		this.rooms = rooms;
		this.rows = rows;
		this.cols = cols;

		data = new char[rooms][rows][cols];
		for (int d = 0; d < data.length; d++) {
			for (int r = 0; r < data[d].length; r++) {
				for (int c = 0; c < data[d][r].length; c++) {
					set(d, r, c, ".".charAt(0));
				}
			}
		}
	}

	public Map(int rows, int cols, int rooms, Scanner scan, boolean inType) {
		this(rows, cols, rooms);
		scan.nextLine(); // move scanner to next line (nextInt does not move to next line)
		processMap(scan, inType);
	}

	public void processMap(Scanner scan, boolean inType) {
		int d = 0; // room number
		if (inType) {
			// coordinate method
			int prevR = 0;
			// loop through each line
			while (scan.hasNextLine()) {
				char value = scan.next().charAt(0);
				int row = scan.nextInt();
				int col = scan.nextInt();

				if (row < prevR) { // when row is number of rows in a room
					// increment room number
					d++;
				}

				set(d, row, col, value);
				prevR = row;
			}
		} else {
			// map method
			int r = 0; // row number
			// loop through each line
			while (scan.hasNextLine()) {
				String line = scan.nextLine();

				for (int i = 0; i < line.length(); i++) {
					// use charAt to grab each element of the map for a given row (r)
					set(d, r, i, line.charAt(i));
				}

				r++;
				if (r == rows) { // when r is number of rows in a room, reset and increment
					d++;
					r = 0;
				}

			}
		}
	}

	public void set(int room, int row, int col, char value) {
		data[room][row][col] = value;
	}

	public String toString() {
		String m = "";
		m += "Tower with " + rooms + " " + rows + "x" + cols + " room" + (rooms > 1 ? "s" : "");
		m += " and " + numCakes() + " cake" + (numCakes() > 1 ? "s" : "") + "\n";
		for (int d = 0; d < data.length; d++) {
			m += "Room#" + d + "\n";
			for (int r = 0; r < data[d].length; r++) {
				for (int c = 0; c < data[d][r].length; c++) {
					m += data[d][r][c] + "";
				}
				m += "\n";
			}
		}

		return m;
	}

	public int numCakes() {
		int count = 0;
		for (int d = 0; d < data.length; d++) {
			for (int r = 0; r < data[d].length; r++) {
				for (int c = 0; c < data[d][r].length; c++) {
					if ((data[d][r][c] + "").equals("C")) {
						count++;
					}
				}
			}
		}
		return count;
	}

	public String printMap(boolean method) {
		String m = "";
		m += rows + " " + cols + " " + rooms + "\n";

		if (method) {
			return cors(m);
		} else {
			return map(m);
		}
	}

	private String map(String m) {
		for (int d = 0; d < data.length; d++) {
			for (int r = 0; r < data[d].length; r++) {
				for (int c = 0; c < data[d][r].length; c++) {
					m += data[d][r][c];
				}
				m += "\n";
			}
		}
		return m;
	}

	private String cors(String m) {
		for (int d = 0; d < data.length; d++) {
			for (int r = 0; r < data[d].length; r++) {
				for (int c = 0; c < data[d][r].length; c++) {
					m += data[d][r][c] + " " + r + " " + c + "\n";
				}
			}
		}
		return m;
	}
}
