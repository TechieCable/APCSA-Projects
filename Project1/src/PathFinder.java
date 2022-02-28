public class PathFinder {

	public void find(Map m, int method) {
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
				stackFind(m, i);
			} else if (method == 2) {
				queueFind(m, i);
			} else if (method == 3) {
				// optimal
			}
		}
	}

	public void stackFind(Map m, int roomNum) {
		DataStructures s1 = new Stack();
		DataStructures s2 = new Stack();

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

		while (s2.size() > 0) {
			Position temp = s2.pop();
			if (temp.equals(".")) {
				temp.value = "+".charAt(0);
			}
		}

	}

	public void queueFind(Map m, int roomNum) {
		// enqueue
		DataStructures q1 = new Queue();
		// dequeue
		DataStructures q2 = new Queue();

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

		while (q2.size() > 0) {
			Position temp = q2.pop();
			if (temp.equals(".")) {
				temp.value = "+".charAt(0);
			}
		}
	}

}
