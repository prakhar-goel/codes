import java.util.Arrays;


public class LongestCommonPrefix {

	public static void main(String[] args) {
		
		String[] arr = {"d", "bb", "a", "ba"};
		Arrays.sort(arr);
		
		for(String str : arr)
			System.out.println(str);

	}

}
