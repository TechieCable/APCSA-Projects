import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;

public class Driver {

	public static void main(String[] args) {
		int n = 500;

		int runs = 50;
		String dataTimes = "";

		for (int i = 0; i < runs; i++) {
			int[] data = genData(n * i);

			long start = System.currentTimeMillis();

			insertionSort(data);

			long time = (System.currentTimeMillis() - start);

			dataTimes += time + "\n";

			System.out.println(i + ": " + time);
		}

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(dataTimes), null);

	}

	public static int[] genData(int n) {
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = n - i;
//			data[i] = (int) (Math.random() * 100);
		}
		return data;
	}

	public static void bubbleSort(int[] elements) {
		boolean swapping = true;
		while (swapping) {
			swapping = false;
			for (int i = 0; i < elements.length - 1; i++) {
				if (elements[i] > elements[i + 1]) {
					int temp = elements[i];
					elements[i] = elements[i + 1];
					elements[i + 1] = temp;
					swapping = true;
				}
			}
		}
	}

	public static void selectionSort(int[] elements) {
		for (int j = 0; j < elements.length - 1; j++) {
			int minIndex = j;
			for (int k = j + 1; k < elements.length; k++) {
				if (elements[k] < elements[minIndex]) {
					minIndex = k;
				}
			}
			int temp = elements[j];
			elements[j] = elements[minIndex];
			elements[minIndex] = temp;
		}
	}

	public static void insertionSort(int[] elements) {
		for (int j = 1; j < elements.length; j++) {
			int temp = elements[j];
			int possibleIndex = j;
			while (possibleIndex > 0 && temp < elements[possibleIndex - 1]) {
				elements[possibleIndex] = elements[possibleIndex - 1];
				possibleIndex--;
			}
			elements[possibleIndex] = temp;
		}
	}

}
