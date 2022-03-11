public class p1 {
	// help
	// method
	// time
	// incoordinate
	// outcoordinate

	static final int mapNum = 7;

	public static void main(String[] args) {
		PathFinder p = new PathFinder(args);

		p.setMap(mapNum);

		// print map before finding path
		System.out.println(p.m.printMapFormatted());

		// print the path-finding method being used
		p.printMethod();

		// start timer
		p.startTimer();

		Map pathFound = p.find();

		// end timer and print time
		p.printTime();

		System.out.println(pathFound.printMapFormatted());

	}
}
