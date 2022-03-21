import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {
	public static final String replacements = "n't|'s|,|\\.|;|:|\\\"|'|`|~|&|%|\\(|\\)|\\?|!|-|\\d";

	public static void main(String[] args) throws FileNotFoundException {
		HashMap<String, Word> text = new HashMap<String, Word>();
		Scanner s = new Scanner(new File("great-gatsby.txt"));

		String curr = trim(s.next()), after = trim(s.next());

		Word max = new Word();

		while (curr != null) {
			if (text.containsKey(curr)) {
				Word x = text.get(curr);
				x.addAfter(after);
				text.put(curr, x);
			} else {
				text.put(curr, new Word(after, curr));
			}

			if (!(equals(curr, "the", "and", "a", "i", "to", "of"))) {
				if (text.get(curr).count() > max.count()) {
					max = text.get(curr);
				}
			}

			curr = trim(after);
			after = (s.hasNext()) ? trim(s.next()) : null;

			while (curr != null && curr.equals("")) {
				curr = trim(after);
				after = (s.hasNext()) ? trim(s.next()) : null;
			}
		}

		s.close();

		System.out.println(text);
		System.out.println(max.info() + " and is followed by: " + max);
	}

	public static String trim(String x) {
		if (x == null)
			return null;
		return x.toLowerCase().replaceAll(replacements, "").trim();
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

class Word {
	private String word;
	private ArrayList<String> after;
	private int count;

	public Word() {
		this.word = "";
		this.after = new ArrayList<String>();
		this.count = 0;
	}

	public Word(String a, String word) {
		this();
		this.word = word;
		addAfter(a);
	}

	public void addAfter(String x) {
		count++;
		if (x == null)
			return;
		after.add(x);
	}

	public int count() {
		return count;
	}

	public String info() {
		return word + " appears " + count + " times";
	}

	public String toString() {
		return after.toString();
	}
}