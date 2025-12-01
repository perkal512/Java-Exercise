package algorithm;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		int[] arr = { 1, 2, 3, 1, 2 };
		List<List<Integer>> res = IncreasingSubsequences.findIncreasingSubarrays(arr);

		System.out.println("increasing sequence:");
		for (List<Integer> seq : res) {
			System.out.println(seq);
		}

	}

}
