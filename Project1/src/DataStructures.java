import java.util.ArrayList;

public class DataStructures {
}

class Stack {
	private ArrayList<Position> data;
	private int size; // # elements

	public Stack() {
		data = new ArrayList<Position>();
		size = 0;
	}

	public int size() {
		return size;
	}

	// push - add element to top of list
	public void push(Position elem) {
		data.add(elem);
		size++;
	}

	// pop - remove and return element from top of list
	public Position pop() {
		if (size < 1) {
			return null;
		}

		size--;
		return data.remove(size);
	}

	public Position peek() {
		if (size < 1) {
			return null;
		}

		return data.get(size - 1);
	}

	public String toString() {
		if (size < 1) {
			return "";
		}

		String res = "[";
		for (int i = 0; i < size; i++) {
			res += data.get(i) + "";
			if (i != size - 1) {
				res += ", ";
			}
		}
		return res + "]";
	}

}

class Queue {
	private Stack in, out;
	private int size;

	public Queue() {
		in = new Stack();
		out = new Stack();
		size = 0;
	}

	public void add(Position elem) {
		in.push(elem);
		size++;
	}

	public Position remove() {
		if (in.size() == 0 && out.size() == 0) {
			return null;
		}
		// when out stack is empty
		if (out.size() == 0) {
			// pop in stack into out stack, reverses order
			while (in.size() > 0) {
				out.push(in.pop());
			}
		}
		// increment size and pop element
		size--;
		return out.pop();
	}

	public int size() {
		return size;
	}

	public String toString() {
		if (in.size() > 0 && out.size() > 0) {
			return "in: " + in + ", out: " + out;
		} else if (in.size() > 0) {
			return "in: " + in;
		} else if (out.size() > 0) {
			return "out: " + out;
		}
		return "no data";
	}

	public Position peek() {
		if (in.size() == 0 && out.size() == 0) {
			return null;
		}
		// when out stack is empty
		if (out.size() == 0) {
			// pop in stack into out stack, reverses order
			while (in.size() > 0) {
				out.push(in.pop());
			}
		}
		Position res = out.pop();
		out.push(res);
		return res;
	}

}
