public class ReversedLinkedList {

	/*
	 * Requires: Properly formatted SinglyLinkedList Modifies: Reverses the list
	 * Effects: Takes a SinglyLinkedList and reverses it. This means the last
	 * elements becomes the head walking through the list should lead to what was
	 * formerly the head node. The respective order of the nodes must be maintained.
	 * 
	 * example: Original: 1->3->4->2 After revers: 2->4->3->1
	 */
	public static void reverse(SinglyLinkedList list) {
		Node next = list.head;
		Node current = list.head;
		Node prev = null;

		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		list.head = prev;
	}

	public static void main(String[] args) {
		SinglyLinkedList test = new SinglyLinkedList();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test);
		reverse(test);
		System.out.println(test);
	}

}