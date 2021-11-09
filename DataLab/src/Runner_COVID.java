import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner_COVID {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File text = new File("COVID19_Cases_by_Episode_Date.csv");

		ArrayList<Case> cases = new ArrayList<Case>();

		Scanner scnr;
		try {
			scnr = new Scanner(text);

			// skip header line
			scnr.nextLine();

			// loop through every line
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine();

				String[] lineArr = line.split(",");
				String[] episode = lineArr[6].split("/");
				String[] update = lineArr[7].split("/");
				// 0: X, 1: Y, 2: objectid, 3: case_count, 4: pointx, 5: pointy, 6:
				// episode_date, 7: update_date
				// 8: case_count_excluding_inmates

				// 0: year, 1: month, 2: date

				Date episode_date = new Date(Integer.valueOf(episode[1]), Integer.valueOf(episode[2].split(" ")[0]),
						Integer.valueOf(episode[0]));
				Date update_date = new Date(Integer.valueOf(update[1]), Integer.valueOf(update[2].split(" ")[0]),
						Integer.valueOf(update[0]));

				Case temp = new Case(Integer.valueOf(lineArr[3]), episode_date, update_date);

				cases.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Case k = cases.get(0);
		
		for (int i = 1; i < cases.size(); i++) {
			if (cases.get(i).case_count > k.case_count) {
				k = cases.get(i);
			}
		}

		System.out.println(k);
	}

}

class Case {
	// X, Y, objectid, case_count, pointx, pointy, episode_date, update_date
	// case_count_excluding_inmates
	int case_count;
	Date episode_date;
	Date update_date;

	public Case(int case_count, Date episode_date, Date update_date) {
		this.case_count = case_count;
		this.episode_date = episode_date;
		this.update_date = update_date;
	}

	public String toString() {
		return "Case [case_count=" + case_count + ", episode_date=" + episode_date + ", update_date=" + update_date
				+ "]";
	}

}

class Date {
	int month;
	int day;
	int year;

	public Date(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}

	public String toString() {
		return month + "/" + day + "/" + year;
	}

}
