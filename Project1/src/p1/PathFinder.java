import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PathFinder {
	// method = 1:stack, 2:queue, 3:opt
	// printTime
	// inType = true:coordinate, false:map
	// outType = true:coordinate, false:map

	private int method;
	private boolean doPrintTime = false;

	// true, coordinate mode
	// false, map mode
	private boolean inType;
	boolean outType;
	Map m;

	private long start;

	/**
	 * sets all values to default values
	 */
	public PathFinder() {
		inType = false;
		outType = false;
		method = 0;
		m = null;
	}

	/**
	 * handles passed arguments and saves them as path finding settings
	 * 
	 * checks for validity of the path finder's elements
	 * 
	 * @param params
	 */
	public PathFinder(String[] params) {
		this();

		Print.er("01", "00Project 1: Kirby's Quest for Cake", "01");

		int commandM = 0;

		for (String arg : params) {
			if (arg.equals("--Help")) {
				Print.er("00Help", "02", "--Stack", "   Use a stack-based approach", "--Queue",
						"   Use a queue-based approach", "--Opt", "   Find the best path to the cake", "--Incoordinate",
						"   File is in coordinate system", "--Outcoordinate", "   Print in coordinate system", "--Help",
						"   View this help dialog");
				System.exit(0);
			} else if (arg.equals("--Stack")) {
				commandM += 1;
			} else if (arg.equals("--Queue")) {
				commandM += 2;
			} else if (arg.equals("--Opt")) {
				commandM += 4;
			} else if (arg.equals("--Time")) {
				doPrintTime = true;
			} else if (arg.equals("--Incoordinate")) {
				inType = true;
			} else if (arg.equals("--Outcoordinate")) {
				outType = true;
			}
		}

		setMap(params[params.length - 1]);

		if (commandM == 1) { // stack
			method = 1;
		} else if (commandM == 2) { // queue
			method = 2;
		} else if (commandM == 4) { // optimal
			method = 3;
		}

		if (!valid()) {
			System.exit(-1);
		}
	}

	/**
	 * grabs the map from a file, given a file name
	 * 
	 * @param mapNum
	 */
	public void setMap(String fileName) {
		try {
			m = new Map(new Scanner(new File(fileName)), inType);
		} catch (FileNotFoundException e) {
			Print.er("03", "The specified map file was not found");
			// TODO: add regex removal
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}

	/**
	 * checks if the path finder's elements are valid
	 * 
	 * @return isValid
	 */
	private boolean valid() {
		if (method == 0) {
			Print.er("03", "Please specify one of the available", "methods for finding a route", "02",
					"Pass --Help to see options");
			return false;
		}
		if (m != null) {
			if (m.numCakes() == 0) {
				Print.er("03", "No cakes found on the map");
				return false;
			}
			for (int i = 0; i < m.rooms; i++) {
				if (m.locateKurby(i) == null) {
					Print.er("03", "Kurby could not be found on room#" + i, "Check the map file");
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * print the method used to find the path
	 */
	public void printMethod() {
		Print.er("01",
				"finding path with " + ((method == 1) ? "stack" : ((method == 2) ? "queue" : "optimal")) + " method",
				"01");
	}

	public String printMap() {
		return m.printMap(outType);
	}

	/**
	 * starts the path finder's timer
	 */
	public void startTimer() {
		start = System.currentTimeMillis();
	}

	/**
	 * prints the time since the path finder started
	 */
	public void printTime() {
		if (doPrintTime) {
			Print.er("01", "path found", "elapsed time: " + ((System.currentTimeMillis() - start) / 1000), "01");
		}
	}

//	######     #    ####### #     #    ####### ### #     # ######  ### #     #  #####  
//	#     #   # #      #    #     #    #        #  ##    # #     #  #  ##    # #     # 
//	#     #  #   #     #    #     #    #        #  # #   # #     #  #  # #   # #       
//	######  #     #    #    #######    #####    #  #  #  # #     #  #  #  #  # #  #### 
//	#       #######    #    #     #    #        #  #   # # #     #  #  #   # # #     # 
//	#       #     #    #    #     #    #        #  #    ## #     #  #  #    ## #     # 
//	#       #     #    #    #     #    #       ### #     # ######  ### #     #  #####  

	/**
	 * find the path given the path finding method
	 * 
	 * Precondition: method must be between 1 and 3, inclusive
	 * 
	 * @param method - 1: stack, 2: queue, 3: optimal
	 * @return map with path found
	 */
	public Map find(int method) {
		if (!(method == 1 || method == 2 || method == 3)) {
			Print.er("03", "Invalid pathfinding index method");
			System.exit(-1);
		}

		Map temp = new Map(m.data);

		if (method == 3) {
			Map tempS = find(1);
			Map tempQ = find(2);
			if (tempS.pathLength() < tempQ.pathLength()) {
				return nodeFind(temp, 1);
			} else {
				return nodeFind(temp, 2);
			}
		}

		for (int i = 0; i < m.rooms; i++) {
			// generate a stack of potential path positions using the indicated method
			DataStructure partialPath = doFind(temp, i, method);

			{
				Position prev = partialPath.pop(), curr;
				prev.setPath();

				while (partialPath.size() > 0) {
					curr = partialPath.pop();
					// if not a valid move
					if (!curr.adjacent(prev))
						continue;

					curr.setPath();
					prev = curr;
				}
			}
		}

		return temp;
	}

	/**
	 * uses the find method with the path finder's method setting to find a path
	 * 
	 * @return map with path found
	 */
	public Map find() {
		return find(this.method);
	}

	/**
	 * path finding method for each room, given a map and the path finding method
	 * 
	 * @param m
	 * @param roomNum
	 * @param type    - 1: stack, 2: queue
	 * @return data structure with partial path
	 */
	private DataStructure doFind(Map m, int roomNum, int type) {
		DataStructure visiter;
		DataStructure pather;

		if (type == 1) {
			visiter = new Stack();
			pather = new Stack();
		} else { // type == 2;
			visiter = new Queue();
			pather = new Queue();
		}

		{ // start data loading
			visiter.push(m.locateKurby(roomNum));
			visiter.peek().visited = true;

			Position prev, current;

			boolean end = false;

			while (!end) {
				prev = visiter.pop();
				pather.push(prev);

				for (int i = 0; i < 4; i++) {
					// N S E W
					if (i == 0) {
						current = m.get(prev.room, prev.row - 1, prev.col);
					} else if (i == 1) {
						current = m.get(prev.room, prev.row + 1, prev.col);
					} else if (i == 2) {
						current = m.get(prev.room, prev.row, prev.col + 1);
					} else { // i == 3
						current = m.get(prev.room, prev.row, prev.col - 1);
					}

					if (current != null && !current.visited) {
						if (current.equals(".")) {
							visiter.push(current);
						}
						if (current.equals("C")) {
							end = true;
							break;
						}
						if (current.equals("|")) {
							end = true;
							break;
						}

						current.visited = true;
					}
				}
			}
		} // end data loading

		// if using queue method, push data into a stack
		if (type == 2) {
			DataStructure res = new Stack();
			while (pather.size() > 0) {
				res.push(pather.pop());
			}
			return res;
		}

		return pather;
	}

//	#     # ####### ######  #######   ####### #######  #####  ####### ### #     #  #####  
//	##    # #     # #     # #            #    #       #     #    #     #  ##    # #     # 
//	# #   # #     # #     # #            #    #       #          #     #  # #   # #       
//	#  #  # #     # #     # #####        #    #####    #####     #     #  #  #  # #  #### 
//	#   # # #     # #     # #            #    #             #    #     #  #   # # #     # 
//	#    ## #     # #     # #            #    #       #     #    #     #  #    ## #     # 
//	#     # ####### ######  #######      #    #######  #####     #    ### #     #  #####  

	/**
	 * uses nodes to find a more optimal path
	 * 
	 * @param m
	 * @param method
	 * @return map with path found
	 */
	private Map nodeFind(Map m, int method) {
		for (int i = 0; i < m.rooms; i++) {
			DataStructure path = doFind(m, i, method);
			Node curr;
			Node head = new Node(path.pop());
			Node it;

			while (path.size() > 0) {
				curr = new Node(path.pop());
				it = head;

				while (it != null) {
					if (it.adjacent(curr)) {
						it.n = curr;
					}
					it = it.n;
				}
			}

			it = head;
			while (it != null) {
				it.p.setPath();
				it = it.n;
			}
		}

		return m;
	}

}

class Node {
	Position p;
	Node n;

	/**
	 * sets all values to null
	 */
	public Node() {
		p = null;
		n = null;
	}

	/**
	 * sets position value to passed position and next node to null
	 * 
	 * @param p
	 */
	public Node(Position p) {
		this.p = p;
		n = null;
	}

	/**
	 * determines if the passed node's position is adjacent to this position
	 * 
	 * @param x
	 * @return isAdjacent
	 */
	public boolean adjacent(Node x) {
		return this.p.adjacent(x.p);
	}

	/**
	 * returns this position's information and specifies that it is contained in a
	 * node
	 * 
	 * @return node as string
	 */
	public String toString() {
		return "Node " + p.toString();
	}
}
