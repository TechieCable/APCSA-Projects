import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {
	public static final String[] ignoreWords = { "the", "and", "a", "i", "to", "of" };

	public static void main(String[] args) throws FileNotFoundException {

		BiGram grams = new BiGram(new Scanner(new File("great-gatsby.txt")));

		System.out.println(grams.map);
//		System.out.println(grams.allWords);

		ArrayList<String> printText = new ArrayList<String>();
		printText.add("thousand");
		while (printText.size() < 200) {
			printText.add(grams.nextWord(printText));

			if (printText.get(printText.size() - 1).equals("."))
				break;
		}

		for (String x : printText) {
			System.out.print(x + " ");
		}
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
