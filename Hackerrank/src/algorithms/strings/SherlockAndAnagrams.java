package algorithms.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SherlockAndAnagrams {
	static int findAnagramaticPairs(String str) {
		boolean anagramsPairFound = false;

		HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			int count = 1;
			if (charCountMap.containsKey(ch))
				count += charCountMap.get(ch);
			charCountMap.put(ch, count);
			if (count >= 2)
				anagramsPairFound = true;
		}

		if (!anagramsPairFound)
			return 0;

		int anagramPairCount = 0;
		for (char ch : charCountMap.keySet()) {
			int count = charCountMap.get(ch);
			if (count > 1)
				anagramPairCount += (count * (count - 1)) / 2; // same pairs
		}

		List<Integer> countList = new ArrayList<Integer>(charCountMap.values());
		for (int i = 0; i < countList.size(); ++i) {
			int count1 = countList.get(i);
			
			for (int j = i + 1; count1 > 1 && j < countList.size(); ++j) {
			
				int count2 = countList.get(j);
				if (count2 > 1)
					anagramPairCount += (count1*count2) / 2;
			}
		}

		return anagramPairCount;
	}

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; ++i) {
			String str = sc.next();
			System.out.println(findAnagramaticPairs(str));
		}
		sc.close();
	}
}
