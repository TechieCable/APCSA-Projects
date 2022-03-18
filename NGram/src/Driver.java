import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		// current, before, after
		HashMap<String, Word> text = new HashMap<String, Word>();

		Scanner s = new Scanner(new File("great-gatsby.txt"));

		String before = null, curr = trim(s.next()), after = trim(s.next());

		while (curr != null) {
//			System.out.println("before: " + before + ", curr: " + curr + ", after: " + after);

			if (text.containsKey(curr)) {
				Word x = text.get(curr);
				x.add(before, after);
				text.put(curr, x);
			} else {
				text.put(curr, new Word(before, after));
			}

			before = trim(curr);
			curr = trim(after);

			after = (s.hasNext()) ? trim(s.next()) : null;
		}

		s.close();

		System.out.println(text);
	}

	public static String trim(String x) {
		if (x == null)
			return null;
		return x.toLowerCase().replaceAll(",|\\.|;|:|\"|'|`|~|&|%|\\(|\\)|\\?|!|-|\\d", "").trim();
	}

}

class Word {
	ArrayList<String> before, after;

	public Word() {
		this.before = new ArrayList<String>();
		this.after = new ArrayList<String>();
	}

	public Word(String b, String a) {
		this();
		add(b, a);
	}

	public void add(String b, String a) {
		addBefore(b);
		addAfter(a);
	}

	public void addBefore(String x) {
		if (x == null)
			return;
		before.add(x);
	}

	public void addAfter(String x) {
		if (x == null)
			return;
		after.add(x);
	}

	public String toString() {
		return "(" + before.toString() + "; " + after.toString() + ")";
	}
}