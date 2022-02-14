
public class PathFinder {

	public Map find(Map m) {
		if (m.locateKurby(0) == null) {
			return null;
		}

		Queue q1 = new Queue();
		Queue q2 = new Queue();

		q1.add(m.locateKurby(0));

		Position prev, n, s, e, w;

		boolean foundCake;

		while (true) {
			prev = q1.remove();
			q2.add(prev);

			n = m.get(prev.room, prev.row - 1, prev.col);
			s = m.get(prev.room, prev.row + 1, prev.col);
			e = m.get(prev.room, prev.row, prev.col + 1);
			w = m.get(prev.room, prev.row, prev.col - 1);

			if (n != null && !n.visited) {
				if (n.equals("."))
					q1.add(n);
				if (n.equals("C"))
					q1.add(n);
				n.visited = true;
			}

			// N S E W
		}

		return m;

	}

}
