public class PathFinder {

	public void find(Map m, int method) {
		for (int i = 0; i < m.rooms; i++) {
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

	}

	public void queueFind(Map m, int roomNum) {
		if (m.locateKurby(roomNum) == null) {
			System.out.println("|                 Error                  |");
			System.out.println("|                                        |");
			System.out.println(
					"| Kurby could not be found on level " + roomNum + "   ".substring((roomNum + "").length()) + " |");
			System.out.println("| Check the map file                     |");
			System.out.println("|                                        |");
			System.exit(-1);
		}

		// enqueue
		Queue q1 = new Queue();
		// dequeue
		Queue q2 = new Queue();

		q1.add(m.locateKurby(roomNum));
		q1.peek().visited = true;

		Position prev, current;

		boolean end = false;

		while (!end) {
			prev = q1.remove();
			q2.add(prev);

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
						q1.add(current);
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
			Position temp = q2.remove();
			if (temp.equals(".")) {
				temp.value = "+".charAt(0);
			}
		}
	}

}
