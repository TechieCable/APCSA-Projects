public class CircularlyLinkedList {
	public static boolean isCircular(SinglyLinkedList list) {
		Node curr = list.head;
		for (int i = 0; i < list.size(); i++) {
			curr = curr.next;
		}
		if (curr == null) {
			return false;
		}
		return true;
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
