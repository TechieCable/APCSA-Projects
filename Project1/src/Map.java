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

	public void set(int room, int row, int col, char value) {
		data[room][row][col] = value;
	}

	public String toString() {
		String m = "";
		m += "Tower with " + rooms + " " + rows + "x" + cols + " room" + (rooms > 1 ? "s" : "") + "\n";
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

//	public String cors() {
//		
//	}

}
