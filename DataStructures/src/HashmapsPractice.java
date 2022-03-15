import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class HashmapsPractice {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("covid317.csv"));
		HashMap<String, Integer> states = new HashMap<String, Integer>();

		s.nextLine(); // skips column headers
		int count = 0;
		while (s.hasNextLine()) { // && count++ < 56
			String[] line = s.nextLine().split(",");

			if (states.containsKey(line[1])) {
				states.put(line[1], states.get(line[1]) + Integer.valueOf(line[4]));
			} else {
				states.put(line[1], Integer.valueOf(line[4]));
			}

//			if (line[1].equals("CA")) {
//				System.out.println(Integer.valueOf("0" + line[2]));
//			}

		}

		System.out.println(states.get("CA"));

		System.out.println(states);

		s.close();
	}

	// date,state,death,deathConfirmed,deathIncrease,deathProbable,hospitalized,hospitalizedCumulative,hospitalizedCurrently,hospitalizedIncrease,inIcuCumulative,inIcuCurrently,negative,negativeIncrease,negativeTestsAntibody,negativeTestsPeopleAntibody,negativeTestsViral,onVentilatorCumulative,onVentilatorCurrently,positive,positiveCasesViral,positiveIncrease,positiveScore,positiveTestsAntibody,positiveTestsAntigen,positiveTestsPeopleAntibody,positiveTestsPeopleAntigen,positiveTestsViral,recovered,totalTestEncountersViral,totalTestEncountersViralIncrease,totalTestResults,totalTestResultsIncrease,totalTestsAntibody,totalTestsAntigen,totalTestsPeopleAntibody,totalTestsPeopleAntigen,totalTestsPeopleViral,totalTestsPeopleViralIncrease,totalTestsViral,totalTestsViralIncrease

}
