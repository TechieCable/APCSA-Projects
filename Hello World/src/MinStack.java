public class MinStack {
	Stack data, mins;

	public MinStack() {
		data = new Stack();
		mins = new Stack();
	}

	public void push(int el) {
		data.push(el);
		if (mins.size() == 0) {
			mins.push(el);
		} else if (el < (int) mins.peek()) {
			mins.push(el);
		}
	}

	public int pop() {
		if ((int) data.peek() == (int) mins.peek()) {
			mins.pop();
		}

		return (int) data.pop();
	}

	public int size() {
		return data.size();
	}

	public int min() {
		return (int) mins.peek();
	}

	public static void main(String[] args) {
		MinStack test = new MinStack();
		test.push(3);
		test.push(2);
		test.push(1);
		System.out.println(test.min());
		test.pop();
		System.out.println(test.min());
	}

}
