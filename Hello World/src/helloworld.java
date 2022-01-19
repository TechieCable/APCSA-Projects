
public class helloworld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("hello world!");

		System.out.println("print to the console.");

		System.out.println("TESTS ON STACK");
		int i = 0;
		Stack test = new Stack();
		test.push(i++);
		test.push(i++);
		System.out.println(test);
		System.out.println(test.pop());

		System.out.println("TESTS ON QUEUE");
		i = 0;
		Queue test1 = new Queue();
		test1.add(i++);
		test1.add(i++);
		System.out.println(test1);
		System.out.println(test1.remove());
		test1.add(i++);
		System.out.println(test1);
		System.out.println(test1.remove());

	}

}
