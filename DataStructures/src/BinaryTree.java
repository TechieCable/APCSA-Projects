
public class BinaryTree {
	public static void main(String[] args) {
		BinaryTree test = new BinaryTree();
		test.add(new Node(1));
		test.add(new Node(2));
		test.add(new Node(3));
		test.add(new Node(4));
		test.add(new Node(5));

		System.out.println("hello");
		System.out.println(test);
	}

	// "top" of the tree
	Node head;
	int size;

	public BinaryTree() {
		head = null;
		size = 0;
	}

	/*
	 * add node to the tree.
	 * 
	 * keeps tree in sorted fashion.
	 * 
	 * every node to the right is > or = to that node.
	 * 
	 * every node the left is < that node.
	 */
	public void add(Node n) {
		size++;
		Node curr = head;
		Node prev = curr;
		if (head == null) {
			head = n;
			return;
		}

		while (curr != null) {
			prev = curr;
			if (n.getData() >= curr.getData()) {
				curr = curr.right;
			} else {
				curr = curr.left;
			}
		}

		if (n.getData() >= prev.getData()) {
			prev.right = n;
		} else {
			prev.left = n;
		}
	}

	public String toString() {
		return BFS(head);
	}

	// return a string representation of the nodes
	// in breadth-first-search order
	public String BFS(Node start) {
		String r = "";
		Node curr = start;
		if (curr != null) {
			r += curr.getData() + " ";
			if (curr.left != null) {
				r += BFS(curr.left) + " ";
			}
			if (curr.right != null) {
				r += BFS(curr.right) + " ";
			}
		}

		return r;
	}

	// depth first preorder traversal
	public String DFTPT() {
		String r = "";
		Node curr = head;

		return r;
	}

	/*
	 * public String printBrackets(Node start) { String r = "{ "; Node curr = start;
	 * if (curr != null) { r += " " + curr.getData() + " "; if (curr.left != null) {
	 * r += " " + printBrackets(curr.left) + " "; } if (curr.right != null) { r +=
	 * " " + printBrackets(curr.right) + " "; } }
	 * 
	 * return r + " }"; }
	 */
}
