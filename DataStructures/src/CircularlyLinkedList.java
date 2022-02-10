public class CircularlyLinkedList {
	public static boolean isCircular(SinglyLinkedList list) {
		if (list.head == null || list.size() < 2) {
			return false;
		}
		Node curr = list.head.next;
		while (curr != null && curr.next != null) {
			if (curr == list.head) {
				return true;
			}
			curr = curr.next;
		}
		return false;
	}

	public static void main(String[] args) {
		SinglyLinkedList test = new SinglyLinkedList();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test);
		System.out.println(isCircular(test));
	}
}
