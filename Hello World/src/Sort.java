import java.util.*;

public class Sort {

	public static void main(String[] args) {
		int[] values = new int[1000];
		ArrayList<Integer> nums = new ArrayList<Integer>();

		for (int i = 0; i < values.length; i++) {
			values[i] = (int) (Math.random() * 100 + 1);
			nums.add(values[i]);
		}

		printArr(nums);
		insertionSort(nums);
		printArr(nums);

		// for 1000 values, selection sort always goes 499500 times
		// insertion sort is around 250000

	}

	public static void insertionSort(ArrayList<Integer> elem) {
		int counter = 0;
		// traverses the array, starting at index 0
		for (int j = 1; j < elem.size(); j++) {
			// [6,5,9,1,3]
			int temp = elem.remove(j);
			// [6,9,1,3]
			int possibleIndex = j;
			while (possibleIndex > 0 && temp < elem.get(possibleIndex - 1)) {
				possibleIndex--; // counts down from j
				counter++;
			}
			elem.add(possibleIndex, temp);
		}
		System.out.println(counter);
	}

	public static void selectionSort(ArrayList<Integer> elem) {
		int counter = 0;
		for (int j = 0; j < elem.size() - 1; j++) {
			int minIndex = j;
			for (int k = j + 1; k < elem.size(); k++) {
				if (elem.get(k) < elem.get(minIndex)) { // looks for smallest
					minIndex = k;
				}
				counter++;
			}
			int temp = elem.get(j);
			elem.set(j, elem.get(minIndex));
			elem.set(minIndex, temp);
		}
		System.out.println(counter);
	}

	public static void printArr(ArrayList<Integer> elem) {
		System.out.println("\n\n");
		int i = 0;
		for (int j : elem) {
			System.out.print(j);
			i++;

			if (i < elem.size()) {
				System.out.print(",");
			}
		}
		System.out.println("\n\n");
	}

	public static void printArr(int[] elem) {
		System.out.println("\n\n");
		int i = 0;
		for (int j : elem) {
			System.out.print(j);
			i++;
			if (i < elem.length) {
				System.out.print(",");
			}
		}
		System.out.println("\n\n");
	}

	public static void insertionSort(int[] elem) {
		int counter = 0;
		// traverses the array, starting at index 0
		for (int j = 1; j < elem.length; j++) {
			int temp = elem[j];
			int possibleIndex = j;
			while (possibleIndex > 0 && temp < elem[possibleIndex - 1]) {
				elem[possibleIndex] = elem[possibleIndex - 1]; // shifts elements to the right
				possibleIndex--; // counts down from j
				counter++;
			}
			elem[possibleIndex] = temp;
		}
		System.out.println(counter);
	}

	public static void selectionSort(int[] elem) {
		int counter = 0;
		for (int j = 0; j < elem.length - 1; j++) {
			int minIndex = j;
			for (int k = j + 1; k < elem.length; k++) {
				if (elem[k] < elem[minIndex]) { // looks for smallest
					minIndex = k;
				}
				counter++;
			}
			int temp = elem[j];
			elem[j] = elem[minIndex];
			elem[minIndex] = temp;
		}
		System.out.println(counter);
	}
}
