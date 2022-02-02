public class QStack<T> {
	Queue<T> q1 = new Queue<T>();
	Queue<T> q2 = new Queue<T>();
	int curr_size;

	public void push(T t) {
		q1.add(t);
		curr_size++;
	}

	public T pop() {
		while (q1.size() > 1) {
			q2.add(q1.remove());
		}
		T res = q1.remove();
		while (q2.size() > 0) {
			q1.add(q2.remove());
		}
		curr_size--;
		return res;
	}

	// return top of the list - does not modify list
	public T peek() {
		while (q1.size() > 1) {
			q2.add(q1.remove());
		}
		T res = q1.peek();
		q2.add(q1.remove());
		while (q2.size() > 0) {
			q1.add(q2.remove());
		}
		return res;
	}

	public int size() {
		return curr_size;
	}

	public static void main(String[] args) {
		QStack test = new QStack();
		test.push(1);
		test.push(2);
		System.out.println(test.pop());
		System.out.println(test.peek());
		System.out.println(test.pop());
		System.out.println(test.size());
	}

}