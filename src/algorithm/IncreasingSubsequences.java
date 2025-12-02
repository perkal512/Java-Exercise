package algorithm;

import java.util.ArrayList;
import java.util.List;

public class IncreasingSubsequences {

	// According to the input and output example,
	// we need to traverse the array and find the ascending contiguous sub arrays.
	// Divide the array into ascending sub arrays
	// input:
	//	•	[1, 2, 3, 1, 2]
	// output:
	//	•	[1,2,3]
	//	•	[1,2]

	public static List<List<Integer>> findIncreasingSubarrays(int[] arr) {
		List<List<Integer>> result = new ArrayList<>();

		if (arr == null || arr.length == 0) {
			return result;
		}

		List<Integer> current = new ArrayList<>();
		current.add(arr[0]);

		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > arr[i - 1]) {
				current.add(arr[i]);
			} else {
				if (current.size() > 1) {
					result.add(new ArrayList<>(current));
					current.clear();
					current.add(arr[i]);
				}
			}
		}
		
		if (current.size() > 1) {
			result.add(current);
		}

		return result;
	}
}
