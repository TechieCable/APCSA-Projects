public class Node {
	private int data;
	Node next;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
		next = null;
		left = null;
		right = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public String toString() {
		return data + "";
	}

	public boolean equals(Node n) {
		return n.getData() == getData();
	}
}