
public class Queue<T> {
	private Stack<T> in, out;
	private int size;

	public Queue() {
		in = new Stack();
		out = new Stack();
		size = 0;
	}

	public void add(T elem) {
		in.push(elem);
		size++;
	}

	public T remove() {
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

}
