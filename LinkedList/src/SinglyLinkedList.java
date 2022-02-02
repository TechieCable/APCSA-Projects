public class SinglyLinkedList {
	Node head;
	private int size;

	public SinglyLinkedList() {
		head = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public String toString() {
		String res = "[";
		Node curr = head;

		while (curr.next != null) {
			res += (Integer) curr.getData() + ",";
			curr = curr.next;
		}
		res += (Integer) curr.getData();
		return res + "]";
	}

	public Node get(int index) {
		Node curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr;
	}

	public Node remove(int n) {
		if (head == null) {
			return null;
		}

		Node curr = head;
		Node res = null;
		size--;

		if (n == 0) { // remove first one
			res = head;
			head = curr.next;
			return res;
		}

		// get node before one that needs to be removed
		for (int i = 0; i < n - 1; i++) {
			curr = curr.next;
		}
		res = curr.next;
		// set one that needs to be removed to the one after it
		curr.next = curr.next.next;
		return res;
	}

	public void add(Node n) {
		size++;
		if (head == null) {
			head = n;
			return;
		}

		Node curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = n;
	}

	public static void main(String[] arg0) {
		SinglyLinkedList test = new SinglyLinkedList();
		test.add(new Node(7));
		System.out.println(test);
		System.out.println(test.remove(1).getData());
		System.out.println(test);
	}

}
