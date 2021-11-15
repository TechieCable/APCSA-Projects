
public class Runner2DArrays {

	public static void main(String[] args) {
		int[] arr1 = new int[5];
		// type[] name = new type[size];

		int[][] arr2 = new int[5][5];
		// type[][] name = new type[rows][cols];
		// RC-cola, RaceCar, RiCe

		// create a 2x3 int 2D array
		int[][] t0 = new int[2][3];
		t0[0][0] = 7;
		t0[1][2] = 11;

		System.out.println("num rows " + t0.length);

		// how do you get the # of columns in a rectangular 2D array?
		// access a column

		System.out.println("num columns: " + t0[0].length + "\n");

		// print out
		print2DArr(t0);

		int[][] t1 = { { 1, 2 }, { 3, 4, 5 }, { 6, 7, 8, 9 } };

		print2DArr(t1);

		int[][][] j9 = new int[3][3][3];
	}

	public static void print2DArr(int[][] arr) {
		System.out.print("{");
		for (int r = 0; r < arr.length; r++) {
			System.out.print("{");
			for (int c = 0; c < arr[r].length; c++) {
				System.out.print(arr[r][c]);
				if (c != arr[r].length - 1) {
					System.out.print(", ");
				}
			}
			System.out.print("}");
			if (r != arr.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
	}
}
