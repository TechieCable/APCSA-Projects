import java.util.ArrayList;

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

	/*
	 * Search for integer s in a binary search tree
	 * 
	 * @param int s - search value
	 * 
	 * @return boolean found
	 */
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

	private String print(Node start) {
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

	public String postOrder() {
		return postOrder(head);
	}

	private String postOrder(Node start) {
		if (start == null)
			return "";

		String r = "";
		r += postOrder(start.left);
		r += postOrder(start.right);
		r += start.getData() + " ";
		return r;
	}

	public String bfs() {
		// return a string representation of the nodes
		// in a breadth-first-search order
		/* to be completed */
		return bfs(head);
		// (head != null ? head.getData() : "") + " " +
	}

	private String bfs(Node start) {
		String r = "";
		if (start == head)
			r += head.getData() + " ";
		if (start == null)
			return "";
		if (start.left != null) {
			r += start.left.getData() + " ";
		}
		if (start.right != null) {
			r += start.right.getData() + " ";
		}
		r += bfs(start.left);
		r += bfs(start.right);

		return r;
	}

	public String bfsArray() {
		ArrayList<Integer> values = new ArrayList<Integer>();
		bfsArray(head, values);

		return values.toString();
	}

	private void bfsArray(Node start, ArrayList<Integer> values) {
		// same problem exists
		if (start == head)
			values.add(head.getData());
		if (start == null)
			return;
		if (start.left != null) {
			values.add(start.left.getData());
		}
		if (start.right != null) {
			values.add(start.right.getData());
		}
		bfsArray(start.left, values);
		bfsArray(start.right, values);
		return;
	}

	// inorder depth-first-search traversal
	public String dfs() {
		/* to be completed */
		return dfs(head);
	}

	private String dfs(Node start) {
		// inorder traversal
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

//		test.add(new Node(10));
//		test.add(new Node(7));
//		test.add(new Node(11));
//		test.add(new Node(5));
//		test.add(new Node(4));
//		test.add(new Node(8));

//		test.add(new Node(10));
//		test.add(new Node(1));
//		test.add(new Node(11));
//		test.add(new Node(0));

		test.add(new Node(20));
		test.add(new Node(10));
		test.add(new Node(30));
		test.add(new Node(5));
		test.add(new Node(15));
		test.add(new Node(25));
		test.add(new Node(35));
		test.add(new Node(0));

		System.out.println(test.find(25));

		System.out.println("bfs: " + test.bfs());
		System.out.println("bfsArray: " + test.bfsArray());
		System.out.println("dfs (inorder): " + test.dfs());
		System.out.println("dfs (postorder): " + test.postOrder());
	}

}

class BinarySearchTree2 extends BinarySearchTree {
	public void add(int x) {
		add(new Node(x));
	}

//	BinarySearchTree temp = new BinarySearchTree();
//	temp.add(top);
//	System.out.println(temp.bfs());
	public void rotateLeft(Node t) {
		if (t.equals(head)) {
			Node x = head;
			if (x.right == null) {
				return;
			}
			Node top = head.right;
			head.right = null;
			top.left = x;
			head = top;
			return;
		}
		Node rotate = null;
		Node parent = findParent(t, head);
		if (parent.left != null && parent.left.equals(t)) {
			rotate = parent.left;
		} else if (parent.right != null && parent.left.equals(t)) {
			rotate = parent.right;
		}
		if (rotate.right == null || rotate == null) {
			return;
		}
		Node top = rotate.right;
		parent.left = top;
		rotate.right = top.left;
		top.left = rotate;
	}

	public Node findParent(Node t, Node parent) {
		if (parent.right != null && parent.right.equals(t)) {
			return parent;
		} else if (parent.left != null && parent.left.equals(t)) {
			return parent;
		}
		if (t.getData() < parent.getData()) {
			return findParent(t, parent.left);
		} else {
			return findParent(t, parent.right);
		}
	}

	public static void main(String[] args) {
		BinarySearchTree2 b = new BinarySearchTree2();
		// 10 5 15 2 7 6 8
		b.add(10);
		b.add(7);
		b.add(11);
		System.out.println(b.bfs());
		b.rotateLeft(b.head);
		System.out.println(b.bfs());
	}
}