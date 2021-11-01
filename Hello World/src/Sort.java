import java.util.ArrayList;

public class Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Integer> nums = new ArrayList<Integer>();
		int max = 30;
		int min = 1;

		for (int i = 0; i < 20; i++) {
			nums.add((int) (Math.random() * (max - min + 1)) + min);
		}

		System.out.println("\nGenerated random, unsorted list:");
		System.out.println(nums);

		System.out.println("\n\nBubble sort:");
		System.out.println(bubble(nums));

		System.out.println("\n\nSelection sort");
		System.out.println(selection(nums));

	}

	public static ArrayList<Integer> selection(ArrayList<Integer> nums) {
		for (int i = 0; i < nums.size(); i++) {

		}

		return nums;
	}

//	public static ArrayList<Integer> selection(ArrayList<Integer> nums) {
//		for (int i = 0; i < nums.size(); i++) {
//			int temp = nums.get(i);
//			nums.remove(i);
//			
//			for (int j = 0; j < i; j++) {
//				int check = nums.get(j);
//				if (temp < check) {
//					continue;
//				}
//			}
//		}
//		
//		return nums;
//	}

	public static ArrayList<Integer> bubble(ArrayList<Integer> nums) {
		for (int j = 0; j < nums.size(); j++) {
			for (int i = 0; i < nums.size() - 1; i++) {
				int num1 = nums.get(i);
				int num2 = nums.get(i + 1);

				// number @ i is greater than number @ i + 1
				if (num1 > num2) {
					nums.set(i, num2);
					nums.set(i + 1, num1);
				}
			}
		}

		return nums;
	}

}
