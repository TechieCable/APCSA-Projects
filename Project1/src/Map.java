import java.util.Scanner;

public class Map {
	Position[][][] data;
	int rooms;
	int rows;
	int cols;

//  ########  ########   #######   ######  ########  ######   ######  #### ##    ##  ######   
//  ##     ## ##     ## ##     ## ##    ## ##       ##    ## ##    ##  ##  ###   ## ##    ##  
//  ##     ## ##     ## ##     ## ##       ##       ##       ##        ##  ####  ## ##        
//  ########  ########  ##     ## ##       ######    ######   ######   ##  ## ## ## ##   #### 
//  ##        ##   ##   ##     ## ##       ##             ##       ##  ##  ##  #### ##    ##  
//  ##        ##    ##  ##     ## ##    ## ##       ##    ## ##    ##  ##  ##   ### ##    ##  
//  ##        ##     ##  #######   ######  ########  ######   ######  #### ##    ##  ######   

	/**
	 * Map Constructor
	 * 
	 * fills a map with empty spaces given on the size of the map
	 * 
	 * @param rows
	 * @param cols
	 * @param rooms
	 */
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

	/**
	 * Map Constructor
	 * 
	 * takes a scan with a map file and processes it
	 * 
	 * @param scan
	 * @param inType - map or coordinate mode
	 */
	public Map(Scanner scan, boolean inType) {
		this(scan.nextInt(), scan.nextInt(), scan.nextInt());
		scan.nextLine(); // move scanner to next line (nextInt does not move to next line)
		processMap(scan, inType);
	}

	public Map(Position[][][] input) {
		this.rooms = input.length;
		this.rows = input[0].length;
		this.cols = input[0][0].length;

		this.data = new Position[this.rooms][this.rows][this.cols];

		for (int d = 0; d < input.length; d++) {
			for (int r = 0; r < input[d].length; r++) {
				for (int c = 0; c < input[d][r].length; c++) {
					// set each position with blank space by default
					this.data[d][r][c] = new Position(d, r, c, input[d][r][c].value);
				}
			}
		}
	}

	/**
	 * processMap
	 * 
	 * processes a map file scan given the mode
	 * 
	 * @param scan
	 * @param inType
	 */
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

	/**
	 * toString
	 * 
	 * returns map information and all rooms
	 * 
	 * @return map
	 */
	public String toString() {
		String m = "";
		m += "Tower with " + rooms + " " + rows + "x" + cols + " room" + (rooms > 1 ? "s" : "");
		m += " and " + numCakes() + " cake" + (numCakes() > 1 ? "s" : "") + "\n";
		for (int d = 0; d < data.length; d++) {
			m += printRoom(d);
		}
		return m;
	}

	public String printMapFormatted() {
		String res = "";
		for (int r = 0; r < data.length; r++) {
			String[] lines = printRoom(r).split("\n");
			for (int i = 0; i < lines.length; i++) {
				res += "|   " + lines[i] + "                                    ".substring(lines[i].length()) + " |\n";
			}
		}
		res += "|                                        |";

		return res;
	}

	/**
	 * printRoom
	 * 
	 * returns a given room on the map
	 * 
	 * @param roomNum
	 * @return room
	 */
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

	/**
	 * printMap
	 * 
	 * return a map given map or coordinate method
	 * 
	 * @param method
	 * @return map
	 */
	public String printMap(boolean method) {
		String m = "";
		m += rows + " " + cols + " " + rooms + "\n";

		if (method) {
			return cors(m);
		} else {
			return map(m);
		}
	}

	/**
	 * map
	 * 
	 * return a map in map mode given a starter string
	 * 
	 * @param m
	 * @return map
	 */
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

	/**
	 * cors
	 * 
	 * return a map in coordinate mode given a starter string
	 * 
	 * @param m
	 * @return map
	 */
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

	/**
	 * numCakes
	 * 
	 * get the number of cakes in a map
	 * 
	 * @return cakeCount
	 */
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

	/**
	 * get the length of the path to the cake
	 * 
	 * @return
	 */
	public int pathLength() {
		int count = 0;
		for (int d = 0; d < data.length; d++) {
			for (int r = 0; r < data[d].length; r++) {
				for (int c = 0; c < data[d][r].length; c++) {
					if (data[d][r][c].equals("+")) {
						count++;
					}
				}
			}
		}
		return count;
	}

	/**
	 * locateKurby
	 * 
	 * returns Kurby's position in a given room or null
	 * 
	 * @param roomNum
	 * @return kurbyPosition
	 */
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

	/**
	 * get
	 * 
	 * returns a given position
	 * 
	 * @param room
	 * @param row
	 * @param col
	 * @return position
	 */
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

	/**
	 * Position Constructor
	 * 
	 * sets all values to defaults
	 */
	public Position() {
		this.room = -1;
		this.row = -1;
		this.col = -1;
		this.value = " ".charAt(0);
		this.visited = false;
	}

	/**
	 * Position Constructor
	 * 
	 * sets values based on passed values
	 * 
	 * @param room
	 * @param row
	 * @param col
	 * @param value
	 */
	public Position(int room, int row, int col, char value) {
		this.room = room;
		this.row = row;
		this.col = col;
		this.value = value;
		this.visited = false;
	}

	/**
	 * Position Constructor
	 * 
	 * sets values based on passed values, accepts String value instead of char
	 * 
	 * @param room
	 * @param row
	 * @param col
	 * @param value
	 */
	public Position(int room, int row, int col, String value) {
		this(room, row, col, value.charAt(0));
	}

	public void setPath() {
		if (this.equals(".")) {
			this.value = "+".charAt(0);
		}
	}

	/**
	 * equals
	 * 
	 * checks if the position's value matches a passed value
	 * 
	 * @param check
	 * @return isEqual
	 */
	public boolean equals(String check) {
		return (value + "").equals(check);
	}

	/**
	 * check if a position is adjacent to this position
	 * 
	 * @param check
	 * @return
	 */
	public boolean adjacent(Position check) {
		if (this.room != check.room) {
			return true;
		}
		// must be in the same room from here on

		// check same row
		if (this.row == check.row) {
			// check if within one move left/right
			if (this.col == check.col - 1 || this.col == check.col + 1) {
				// same row, column left/right once
				return true;
			}
		}
		// check same column
		if (this.col == check.col) {
			// check if within one move up/down
			if (this.row == check.row - 1 || this.row == check.row + 1) {
				// same column, row up/down once
				return true;
			}
		}

		return false;
	}

	/**
	 * toString
	 * 
	 * returns information about the position
	 * 
	 * @return positionInfo
	 */
	public String toString() {
		return "Space at room#" + room + " " + row + "," + col + " is " + value + " and " + (visited ? "" : "not ")
				+ "visited";
	}
}
