import java.util.Scanner;

public class Map {
	Position[][][] data;
	int rooms;
	int rows;
	int cols;

	// rows, columns, rooms

	// data[0] = {{".","."},{".","."}}

//  ########  ########   #######   ######  ########  ######   ######  #### ##    ##  ######   
//  ##     ## ##     ## ##     ## ##    ## ##       ##    ## ##    ##  ##  ###   ## ##    ##  
//  ##     ## ##     ## ##     ## ##       ##       ##       ##        ##  ####  ## ##        
//  ########  ########  ##     ## ##       ######    ######   ######   ##  ## ## ## ##   #### 
//  ##        ##   ##   ##     ## ##       ##             ##       ##  ##  ##  #### ##    ##  
//  ##        ##    ##  ##     ## ##    ## ##       ##    ## ##    ##  ##  ##   ### ##    ##  
//  ##        ##     ##  #######   ######  ########  ######   ######  #### ##    ##  ######   

	public Map(int rows, int cols, int rooms) {
		this.rooms = rooms;
		this.rows = rows;
		this.cols = cols;

		data = new Position[rooms][rows][cols];

		for (int d = 0; d < data.length; d++) {
			for (int r = 0; r < data[d].length; r++) {
				for (int c = 0; c < data[d][r].length; c++) {
					// set each position with blank space by default
					data[d][r][c] = new Position(d, r, c, ".");
				}
			}
		}
	}

	/*
	 * takes a scan with a map file and processes it
	 */
	public Map(Scanner scan, boolean inType) {
		this(scan.nextInt(), scan.nextInt(), scan.nextInt());
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

				data[d][row][col].value = value;
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
					data[d][r][i].value = line.charAt(i);
				}

				r++;
				if (r == rows) { // when r is number of rows in a room, reset and increment
					d++;
					r = 0;
				}

			}
		}
	}

//  ########  ########  #### ##    ## ########     #######  ##     ## ########  ######  
//  ##     ## ##     ##  ##  ###   ##    ##       ##     ## ##     ##    ##    ##    ## 
//  ##     ## ##     ##  ##  ####  ##    ##       ##     ## ##     ##    ##    ##       
//  ########  ########   ##  ## ## ##    ##       ##     ## ##     ##    ##     ######  
//  ##        ##   ##    ##  ##  ####    ##       ##     ## ##     ##    ##          ## 
//  ##        ##    ##   ##  ##   ###    ##       ##     ## ##     ##    ##    ##    ## 
//  ##        ##     ## #### ##    ##    ##        #######   #######     ##     ######  

	public String toString() {
		String m = "";
		m += "Tower with " + rooms + " " + rows + "x" + cols + " room" + (rooms > 1 ? "s" : "");
		m += " and " + numCakes() + " cake" + (numCakes() > 1 ? "s" : "") + "\n";
		for (int d = 0; d < data.length; d++) {
			m += printRoom(d);
		}
		return m;
	}

	public String printRoom(int roomNum) {
		String m = "";
		m += "Room#" + roomNum + "\n";
		for (int r = 0; r < data[roomNum].length; r++) {
			for (int c = 0; c < data[roomNum][r].length; c++) {
				m += data[roomNum][r][c].value + "";
			}
			m += "\n";
		}
		return m;
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
					m += data[d][r][c].value;
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
					m += data[d][r][c].value + " " + r + " " + c + "\n";
				}
			}
		}
		return m;
	}

//  ##        #######   ######     ###    ######## ####  #######  ##    ##  ######  
//  ##       ##     ## ##    ##   ## ##      ##     ##  ##     ## ###   ## ##    ## 
//  ##       ##     ## ##        ##   ##     ##     ##  ##     ## ####  ## ##       
//  ##       ##     ## ##       ##     ##    ##     ##  ##     ## ## ## ##  ######  
//  ##       ##     ## ##       #########    ##     ##  ##     ## ##  ####       ## 
//  ##       ##     ## ##    ## ##     ##    ##     ##  ##     ## ##   ### ##    ## 
//  ########  #######   ######  ##     ##    ##    ####  #######  ##    ##  ######  

	public int numCakes() {
		int count = 0;
		for (int d = 0; d < data.length; d++) {
			for (int r = 0; r < data[d].length; r++) {
				for (int c = 0; c < data[d][r].length; c++) {
					if (data[d][r][c].equals("C")) {
						count++;
					}
				}
			}
		}
		return count;
	}

	public Position locateKurby(int roomNum) {
		for (int r = 0; r < data[roomNum].length; r++) {
			for (int c = 0; c < data[roomNum][r].length; c++) {
				if (data[roomNum][r][c].equals("K")) {
					return data[roomNum][r][c];
				}
			}
		}

		return null;
	}

	public Position get(int room, int row, int col) {
		try {
			return data[room][row][col];
		} catch (Exception e) {
			return null;
		}
	}

}

class Position {
	int room, row, col;
	char value;
	boolean visited;

	public Position() {
		this.room = -1;
		this.row = -1;
		this.col = -1;
		this.value = " ".charAt(0);
		this.visited = false;
	}

	public Position(int room, int row, int col, char value) {
		this.room = room;
		this.row = row;
		this.col = col;
		this.value = value;
		this.visited = false;
	}

	public Position(int room, int row, int col, String value) {
		this(room, row, col, value.charAt(0));
	}

	public boolean equals(String check) {
		return (value + "").equals(check);
	}

	public String toString() {
		return "Space at room#" + room + " " + row + "," + col + " is " + value + " and " + (visited ? "" : "not ")
				+ "visited";
	}
}
