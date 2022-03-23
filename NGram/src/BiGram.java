import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BiGram {
	public static final String replacements = "n't|'s|,|\\.|;|:|\"|'|`|~|&|%|\\(|\\)|\\?|!|-|\\d";

	HashMap<String, Word> map = new HashMap<String, Word>();

	public BiGram(Scanner s) {
		String curr = trim(s.next()), after = trim(s.next());

		while (curr != null) {
			if (map.containsKey(curr)) {
				Word x = map.get(curr);
				x.addAfter(after);
				map.put(curr, x);
			} else {
				map.put(curr, new Word(after, curr));
			}

			curr = trim(after);
			after = (s.hasNext()) ? trim(s.next()) : null;

			while (curr != null && curr.equals("")) {
				curr = trim(after);
				after = (s.hasNext()) ? trim(s.next()) : null;
			}
		}

		s.close();
	}

	public String trim(String x) {
		if (x == null)
			return null;
		return x.toLowerCase().replaceAll(replacements, "").trim();
	}

	public String nextWord(ArrayList<String> text) {
		String x = trim(text.get(text.size() - 1));
		Map<String, Integer> n;

		try {
			n = map.get(x).after;
		} catch (Exception e) {
			return ".";
		}

//		n.remove("room");
//		n.remove("door");
//		n.remove("house");

		n.remove("enter");
		n.remove("exit");
		n.remove("tybalt");
		n.remove("romeo");
		n.remove("mother");

		for (String e : text) {
			if (n.containsKey(e)) {
				n.put(e, n.get(e) / 2);
				if (n.get(e) < 1) {
					n.remove(e);
				}
			}
		}

		Map.Entry<String, Integer> maxOccurance = null;
		for (Map.Entry<String, Integer> e : n.entrySet()) {
			if (maxOccurance == null || maxOccurance.getValue() < e.getValue())
				maxOccurance = e;
		}

		if (maxOccurance == null) {
			return ".";
		}

		return maxOccurance.getKey();

	}

}

class Word {
	private String word;
	Map<String, Integer> after;
	private int count;

	public Word() {
		this.word = "";
		this.after = new HashMap<String, Integer>();
		this.count = 0;
	}

	public Word(String word) {
		this();
		this.word = word;
	}

	public Word(String a, String word) {
		this();
		this.word = word;
		addAfter(a);
	}

	public void addAfter(String x) {
		count++;
		if (x == null || x.equals(""))
			return;
		after.put(x, (after.containsKey(x)) ? after.get(x) + 1 : 1);

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