package algorithms.strings;

import java.util.HashMap;
import java.util.Scanner;

public class MakeItAnagram {
	static int minDel(String str1, String str2)
    {
        HashMap<Character,Integer> charCountMap = new HashMap<Character, Integer>();
        for(char ch : str1.toCharArray())
        {
            int charCount = 1;
            if(charCountMap.containsKey(ch))
                charCount += charCountMap.get(ch);
            charCountMap.put(ch,  charCount);
        }
        
        int minDel = 0;
        int len2 = str2.length();
        for(int i = 0; i < len2; ++i)
        {
            char ch2 = str2.charAt(i);
            if(charCountMap.containsKey(ch2))
            {
                int charCount = charCountMap.get(ch2);
                if(charCount == 1)
                    charCountMap.remove(ch2);
                else
                    charCountMap.put(ch2, charCount-1);
            }
            else if(charCountMap.size() == 0) // str2 is bigger than str1
            {
               minDel += (len2 - i);    
                break;
            }
            else
               minDel++; 
        }
        for(char ch : charCountMap.keySet())
        	minDel += charCountMap.get(ch);
        return minDel;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        System.out.println(minDel(str1, str2));
        sc.close();
    }
}
