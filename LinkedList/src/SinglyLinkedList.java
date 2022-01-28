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
		String res = "";
		Node curr = head;

		while (curr.next != null) {
			res += (Integer) curr.getData() + ",";
			curr = curr.next;
		}
		res += (Integer) curr.getData();
		return res;
	}

	public Node get(int index) {
		Node curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr;
	}

	public Node remove(int n) {
		size--;
		Node curr = head;
		Node res = null;
		for (int i = 0; i < n; i++) {
			res = curr;
			curr = curr.next; // contains Node at n
		}
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
		test.add(new Node(10));
		test.add(new Node(11));
		test.add(new Node(12));
		test.add(new Node(13));
		System.out.println(test.get(0).getData());
		System.out.println(test.get(1).getData());
		System.out.println(test.get(2).getData());
		System.out.println(test.get(3).getData());
		System.out.println(test);
		System.out.println(test.remove(0).getData());
		System.out.println(test);

	}

}
