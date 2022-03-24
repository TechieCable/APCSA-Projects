import java.util.ArrayList;

public class Fibonacci {

	public static void main(String[] args) {
		ArrayList<Integer> slowTimes = new ArrayList<Integer>();
		Timer t = new Timer();
		long time = 0;
		for (int i = 0, res = 0; i < 40; i++) {
			System.out.println("finding fibonnaci of " + i);
			t.start();

			res = factorial(i);
			time = t.out();

//			slowTimes.add((int) time);

			System.out.println("\tslow found " + res + " in " + time + " ms");

			t.start();

			res = factorialOpt(i);
			time = t.out();

			System.out.println("\tfast found " + res + " in " + time + " ms");
		}

		for (int n : slowTimes)
			System.out.println(n);
	}

	public static int factorialOpt(int n) {
		int[] f = new int[n + 1];
		if (n >= 1)
			f[1] = 1;
		return factorialOpt(n, f);
	}

	private static int factorialOpt(int n, int[] f) {
		if (n != 0 && f[n] == 0)
			f[n] = factorialOpt(n - 1, f) + factorialOpt(n - 2, f);
		return f[n];
	}

	public static int factorial(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return factorial(n - 1) + factorial(n - 2);
	}

}

class Timer {
	private long start = 0;

	public void start() {
		start = System.currentTimeMillis();
	}

	public long out() {
		return System.currentTimeMillis() - start;
	}
}
