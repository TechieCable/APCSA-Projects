public class BinarySearchTree {
	// "Top" of the tree
	Node head;
	int size;

	public BinarySearchTree() {
		head = null;
		size = 0;
	}

	/**
	 * Add node to the tree! keeps the tree in a sorted fashion every node to the
	 * right of a node is great-than-or-equal-to that node every node to the left of
	 * a node is less-than that node
	 * 
	 * @param arg
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

	public boolean find(int s) {
		Node curr = head;

		while (curr != null) {
			if (s == curr.getData()) {
				return true;
			}
			if (s > curr.getData()) {
				curr = curr.right;
			} else {
				curr = curr.left;
			}
		}
		return false;
	}

	public void print() {
		// print all the elements - no specifics on how for the time being
		// for testing purposes
		System.out.println(print(head));
	}

	public String bfs() {
		// return a string representation of the nodes
		// in a breath-first-search order
		/* to be completed */
		return bfs(head);
	}

	// pre-order depth-first-search traversal
	public String dfs() {
		/* to be completed */
		return dfs(head);
	}

	public String print(Node start) {
		String r = "";
		Node curr = start;
		if (curr != null) {
			if (start != head) {
				r += curr.getData() + " ";
			}

			if (curr.left != null) {
				r += "left of " + curr.getData() + " is {" + print(curr.left) + "}";
			}
			if (curr.right != null) {
				r += "right of " + curr.getData() + " is {" + print(curr.right) + "}";
			}
		}

		return r;
	}

	// error here - should look at each "row"
	public String bfs(Node start) {
		if (start == null)
			return "";
		String r = "";
		r += start.getData() + " ";
		r += bfs(start.left);
		r += bfs(start.right);

		return r;
	}

	public String dfs(Node start) {
		// preorder traversal
		if (start == null)
			return "";
		String r = "";
		r += dfs(start.left);
		r += start.getData() + " ";
		r += dfs(start.right);

		return r;
	}

	public static void main(String[] args) {
		BinarySearchTree test = new BinarySearchTree();
//		test.add(new Node(5));
//		test.add(new Node(2));
//		test.add(new Node(1));
//		test.add(new Node(3));
//		test.add(new Node(12));
//		test.add(new Node(9));
//		test.add(new Node(21));
//		test.add(new Node(19));
//		test.add(new Node(25));

		test.add(new Node(10));
		test.add(new Node(7));
		test.add(new Node(11));
		test.add(new Node(5));

		System.out.println(test.find(25));

		System.out.println(test.bfs());
		System.out.println(test.dfs());
	}

}
