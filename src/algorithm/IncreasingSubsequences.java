package algorithm;

import java.util.ArrayList;
import java.util.List;

public class IncreasingSubsequences {

// Algorithm to find contiguous increasing subsequences in an array
// Rationale:
// In the assignment example, we were asked to find all increasing subsequences
// in the array [1, 2, 3, 1, 2], with the expected output:
// [1, 2, 3]
// [1, 2]
//
// Therefore, to meet the requirements exactly:
//  We only search for contiguous subsequences, as shown in the example.
//  We check that the sequence has length > 1 before adding it,
//	    to match the output format in the example.

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
