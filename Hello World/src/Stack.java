import java.util.ArrayList;

public class Stack<T> {
	private ArrayList<T> data;
	private int size; // # elements

	public Stack() {
		data = new ArrayList<T>();
		size = 0;
	}

	public int size() {
		return size;
	}

	// push - add element to top of list
	public void push(T elem) {
		data.add(elem);
		size++;
	}

	// pop - remove and return element from top of list
	public T pop() {
		if (size < 1) {
			return null;
		}

		size--;
		return data.remove(size);
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
