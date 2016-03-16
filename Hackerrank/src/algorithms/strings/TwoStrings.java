package algorithms.strings;

import java.util.Arrays;
import java.util.Scanner;

public class TwoStrings {
	
	static String isSubstringExists(String str1, String str2)
    {
        int n1 = str1.length();
        int n2 = str2.length();
        
        for(int i = 0; i < n1; ++i)
        {
            char ch1 = str1.charAt(i);
            for(int j = 0; j < n2; ++j)
            {
                char ch2 = str2.charAt(j);
                 if(ch1 == ch2)
                    return "YES";
            }   
        }
        return "NO";
    }
	
	static String isSubstringExistsOptimized(String str1, String str2)
    {
        char[] charArr1 = str1.toCharArray();
        char[] charArr2 = str2.toCharArray();
        
        Arrays.sort(charArr1);
        Arrays.sort(charArr2);
        
        int len1 = charArr1.length, len2 = charArr2.length;
        int i = 0, j =0;
        
        while(i < len1 && j < len2)
        {
            char ch1 = charArr1[i];  
            char ch2 = charArr2[j];  
            if(ch1 == ch2)
                return "YES";
            else if(ch1 < ch2)
                ++i;
            else
                ++j;
        }
        return "NO";
    }
	
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0; i < t; ++i)
        {
            String str1 = sc.next();
            String str2 = sc.next();
            System.out.println(isSubstringExistsOptimized(str1, str2));
        }
    }
}
