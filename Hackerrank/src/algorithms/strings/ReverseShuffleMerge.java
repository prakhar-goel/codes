package algorithms.strings;

import java.util.Scanner;
import java.util.TreeMap;

public class ReverseShuffleMerge {
	static String getminStrPossible(String mergedStr)
    {
        int n = mergedStr.length();
        TreeMap<Character,Integer> charCountMap = new TreeMap<Character, Integer>();
        
        for(int i = 0; i < n; ++i)
        {
            int count = 1;
            char ch = mergedStr.charAt(i);
            if(charCountMap.containsKey(ch))
                count += charCountMap.get(ch);
            charCountMap.put(ch, count);
        }
        StringBuffer sb = new StringBuffer();
        sb.append("");
        for(char ch : charCountMap.keySet())
        {
            int count = charCountMap.get(ch)/2;
            for(int i = 0; i < count; ++i)
                sb.append(ch);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String mergedStr = sc.next();
        System.out.println(getminStrPossible(mergedStr));
    }
}
