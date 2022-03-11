import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
	private boolean inType, outType;
	Map m;

	private long start;

	public PathFinder() {
		inType = false;
		outType = false;
		method = 0;
		m = null;
	}

	public PathFinder(String[] params) {
		this();

		System.out.println("|----------------------------------------|");
		System.out.println("|   Project 1: Kirby's Quest for Cake    |");
		System.out.println("|----------------------------------------|");
		System.out.println("|                                        |");

		int commandM = 0;

		for (String arg : params) {
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
				doPrintTime = true;
			} else if (arg.equals("--Incoordinate")) {
				inType = true;
			} else if (arg.equals("--OutCoordinate")) {
				outType = true;
			}
		}

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
	}

	public void setMap(int mapNum) {
		try {
			m = new Map(new Scanner(new File("cable-map" + mapNum + (inType ? "-cor" : "") + ".txt")), inType);
		} catch (FileNotFoundException e) {
			System.out.println("|                 Error                  |");
			System.out.println("|                                        |");
			System.out.println("| The specified map file was not found   |");
			System.out.println("|                                        |");
			// TODO: add regex removal
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}

	public void printMethod() {
		String x = ((method == 1) ? "stack" : ((method == 2) ? "queue" : "optimal")) + " method";
		System.out.println("|----------------------------------------|");
		System.out.println("| finding path with " + x + "                 ".substring(x.length()) + "    |");
		System.out.println("|----------------------------------------|");
		System.out.println("|                                        |");
	}

	public void startTimer() {
		start = System.currentTimeMillis();
	}

	public void printTime() {
		String x = "" + ((System.currentTimeMillis() - start) / 1000);
		if (doPrintTime) {
			System.out.println("|----------------------------------------|");
			System.out.println("| path found                             |");
			System.out.println("| elapsed time: " + x + "                        ".substring(x.length()) + " |");
			System.out.println("|----------------------------------------|");
			System.out.println("|                                        |");
		}
	}

//	######     #    ####### #     #    ####### ### #     # ######  ### #     #  #####  
//	#     #   # #      #    #     #    #        #  ##    # #     #  #  ##    # #     # 
//	#     #  #   #     #    #     #    #        #  # #   # #     #  #  # #   # #       
//	######  #     #    #    #######    #####    #  #  #  # #     #  #  #  #  # #  #### 
//	#       #######    #    #     #    #        #  #   # # #     #  #  #   # # #     # 
//	#       #     #    #    #     #    #        #  #    ## #     #  #  #    ## #     # 
//	#       #     #    #    #     #    #       ### #     # ######  ### #     #  #####  

	public Map find() {
		return find(this.method);
	}

	public Map find(int method) {
		if (method == 3) {
			Map tempS = find(1);
			Map tempQ = find(2);
			if (tempS.pathLength() < tempQ.pathLength()) {
				System.out.println("| Stack method is optimal                |");
				System.out.println("|                                        |");
				return tempS;
			} else {
				System.out.println("| Queue method is optimal                |");
				System.out.println("|                                        |");
				return tempQ;
			}
		}

		Map temp = new Map(m.data);

		if (m.numCakes() == 0) {
			System.out.println("|                 Error                  |");
			System.out.println("|                                        |");
			System.out.println("| No cakes found on the map              |");
			System.out.println("|                                        |");
			System.exit(-1);
		}

		for (int i = 0; i < m.rooms; i++) {
			if (m.locateKurby(i) == null) {
				System.out.println("|                 Error                  |");
				System.out.println("|                                        |");
				System.out.println(
						"| Kurby could not be found on room#" + i + "    ".substring((i + "").length()) + " |");
				System.out.println("| Check the map file                     |");
				System.out.println("|                                        |");
				System.exit(-1);
			}

			if (method == 1) {
				stackFind(temp, i);
			} else if (method == 2) {
				queueFind(temp, i);
			}
		}

		return temp;
	}

	public void stackFind(Map m, int roomNum) {
		DataStructures s1 = new Stack();
		DataStructures s2 = new Stack();

		{ // stack loading
			s1.push(m.locateKurby(roomNum));
			s1.peek().visited = true;

			Position prev, current;

			boolean end = false;

			while (!end) {
				prev = s1.pop();
				s2.push(prev);

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
							s1.push(current);
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
		} // end stack loading

		{// path tracing
			Position prev = s2.pop(), curr;
			prev.setPath();

			while (s2.size() > 0) {
				curr = s2.pop();
				// if not a valid move
				if (!curr.adjacent(prev))
					continue;

				curr.setPath();
				prev = curr;
			}
		}

	}

	public void queueFind(Map m, int roomNum) {
		// enqueue
		DataStructures q1 = new Queue();
		// dequeue
		DataStructures q2 = new Queue();

		{ // queue loading
			q1.push(m.locateKurby(roomNum));
			q1.peek().visited = true;

			Position prev, current;

			boolean end = false;

			while (!end) {
				prev = q1.pop();
				q2.push(prev);

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
							q1.push(current);
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
		} // end queue loading

		{ // path tracing
			Stack s = new Stack();
			while (q2.size() > 0) {
				s.push(q2.pop());
			}

			Position prev = s.pop(), curr;
			prev.setPath();

			while (s.size() > 0) {
				curr = s.pop();
				// if not a valid move
				if (!curr.adjacent(prev))
					continue;

				curr.setPath();
				prev = curr;
			}
		} // end path tracing
	}

//	#     # ####### ######  #######   ####### #######  #####  ####### ### #     #  #####  
//	##    # #     # #     # #            #    #       #     #    #     #  ##    # #     # 
//	# #   # #     # #     # #            #    #       #          #     #  # #   # #       
//	#  #  # #     # #     # #####        #    #####    #####     #     #  #  #  # #  #### 
//	#   # # #     # #     # #            #    #             #    #     #  #   # # #     # 
//	#    ## #     # #     # #            #    #       #     #    #     #  #    ## #     # 
//	#     # ####### ######  #######      #    #######  #####     #    ### #     #  #####  

	class PNode extends Position {
		ArrayList<PNode> next = new ArrayList<PNode>();
	}

	public void nodeFind(Map m, int roomNum) {
		DataStructures s1 = new Stack();
		DataStructures s2 = new Stack();

		s1.push(m.locateKurby(roomNum));
		s1.peek().visited = true;

		PNode node = (PNode) s1.peek();
		PNode curr = node;
		Position prev, current;

		boolean end = false;

		while (!end) {
			prev = s1.pop();
			s2.push(prev);

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
						s1.push(current);
					}
					if (current.equals("C")) {
						end = true;
						curr.next.add((PNode) current);
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

		PNode x = node;
		while (x != null) {
			Position temp = s2.pop();
			if (temp.equals(".")) {
				temp.value = "+".charAt(0);
			}
		}

	}

}
