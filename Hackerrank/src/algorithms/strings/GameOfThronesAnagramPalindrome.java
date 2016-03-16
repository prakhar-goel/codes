package algorithms.strings;

import java.util.HashMap;
import java.util.Scanner;

public class GameOfThronesAnagramPalindrome {
	static String isPalindromeAnagram(String s)
    {
        int len = s.length();
        HashMap<Character,Integer> set = new HashMap<Character,Integer>();
        for(int i = 0; i < len; ++i)
        {
            char ch = s.charAt(i);
            int charCount = 1;
            if(set.containsKey(ch))
            {
                charCount+=set.get(ch);
            }
            set.put(ch, charCount);    
        }
        
        int odd_count = 0;
        for(char ch : set.keySet())
        {
            if(set.get(ch) %2 != 0)
                odd_count++;
        }
        if(len %2 == 0)
            return odd_count == 0?"YES":"NO";
        
        return odd_count == 1?"YES":"NO";
    }
    
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        String inputString = myScan.nextLine();
       
        String ans = isPalindromeAnagram(inputString);
        // Assign ans a value of YES or NO, depending on whether or not inputString satisfies the required condition
        System.out.println(ans);
        myScan.close();
    }
}
