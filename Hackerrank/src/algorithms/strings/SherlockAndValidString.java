package algorithms.strings;

import java.util.Scanner;

public class SherlockAndValidString {
	static String isPossibleToRemove(String str)
    {
        int n = str.length();
        if(n == 0)
            return "YES";
        
        int[] counts = new int[26];
        for(int i = 0; i < n; ++i)
        {
            char ch = str.charAt(i);
            counts[ch-'a']++;
        }
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < 26; ++i)
        {
            if(counts[i] > 0)
            {
               min = Math.min(counts[i], min);
               max = Math.max(counts[i], max);
            }
        }
        
        if(min == max)
            return "YES";
        
        int minFreq = 0, maxFreq = 0;
        for(int i = 0; i < 26; ++i)
        {
            if(counts[i] == min)    
                minFreq++;
            if(counts[i] == max)
                maxFreq++;
        }
        
        if(maxFreq*(max-min) < 2 || min*minFreq < 2)
            return "YES";
        return "NO";
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(isPossibleToRemove(str));
    }
}
