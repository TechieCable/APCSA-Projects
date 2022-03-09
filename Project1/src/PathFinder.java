import java.util.ArrayList;

public class PathFinder {

	// walk backwards

	public Map find(Map m, int method) {
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
			} else if (method == 3) {
				stackFind(temp, i);
			}
		}
		
		if (method == 3) {
			
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

	// TESTING NODE PATH-FINDING

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
