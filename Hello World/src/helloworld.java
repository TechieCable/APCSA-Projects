
public class helloworld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("hello world!");

		System.out.println("print to the console.");

		System.out.println("TESTS ON QUEUE");
		int i = 0;
		Queue test = new Queue();
		test.add(i++);
		test.add(i++);
		System.out.println(test);
		System.out.println(test.size());
		System.out.println(test.remove());
		test.add(i++);
		System.out.println(test);
		System.out.println(test.remove());
		System.out.println(test.remove());
		System.out.println(test.remove());
		System.out.println(test.remove());
		System.out.println(test.remove());
		System.out.println(test.remove());

	}

}
