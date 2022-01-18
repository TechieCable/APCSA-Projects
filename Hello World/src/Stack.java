import java.util.ArrayList;

public class Stack<T> { // <T> allows storing any data type

	// data
	private ArrayList<T> data;

	// # of elements, not storage space size
	private int size;

	// should init array with 10 spaces
	public Stack() {
		data = new ArrayList<T>();
		size = 0;
	}

	// getter for size

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
		size--;
		return data.remove(size);
	}

	public static void main(String[] args) {
		Stack test = new Stack();
		System.out.println(test.size());
		test.push(1);
		System.out.println(test.size());
		test.push(2);
		System.out.println(test.size());
		System.out.println("pop " + test.pop());
		System.out.println(test.size());
	}

}
