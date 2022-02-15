
public class PathFinder {

	public Map find(Map m) {
		if (m.locateKurby(0) == null) {
			return null;
		}

		Queue q1 = new Queue();
		Queue q2 = new Queue();

		q1.add(m.locateKurby(0));

		Position prev, current;

		boolean end = false;

		int count = 0;

		while (!end) {
			count++;
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
						q1.add(current);
						end = true;
					}

					current.visited = true;
				}
			}

		}

		while (q2.size() > 0) {
			q2.remove().value = "+".charAt(0);
		}

		return m;
	}

}
