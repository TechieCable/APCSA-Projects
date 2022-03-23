import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static final String[] ignoreWords = { "the", "and", "a", "i", "to", "of" };

	public static void main(String[] args) throws FileNotFoundException {
		BiGram grams = new BiGram(new Scanner(new File("romeo-juliet.txt")));
		ArrayList<String> printText = new ArrayList<String>();
		Scanner in = new Scanner(System.in);

		System.out.println(grams.map.keySet());

		System.out.println("Enter any of the above words");
		System.out.print("\\> ");

		while (in.hasNextLine()) {
			String nextLine = in.nextLine();
			if (grams.map.keySet().contains(nextLine)) {
				printText.add(nextLine);
				break;
			}
			System.out.print("\\> ");
		}

		while (printText.size() < 200) {
			printText.add(grams.nextWord(printText));

			if (printText.get(printText.size() - 1).equals("i")) {
				printText.set(printText.size() - 1, "I");
			}

			if (printText.get(printText.size() - 1).equals("."))
				break;
		}

		for (String x : printText) {
			System.out.print((!x.equals(".") ? " " : "") + x);
		}
		System.out.println("");

	}

	public static boolean equals(String x, String... checks) {
		for (String c : checks) {
			if (x.equals(c)) {
				return true;
			}
		}
		return false;
	}

}
