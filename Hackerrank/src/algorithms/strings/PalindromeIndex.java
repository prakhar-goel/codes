package algorithms.strings;

import java.util.Scanner;

public class PalindromeIndex {
	static boolean isPalindrome(String str, int i, int j)
    {
        int len = str.length();
        int range = len/2;
        
        for(; i < range; ++i, --j)
        {
            if(str.charAt(i) != str.charAt(j)) 
                return false;
        }
        return true;
    }
    
    static int palindromeByRemovingIndex(String str)
    {
    	int n = str.length();
        boolean isPalindrome = isPalindrome(str, 0, n-1);
        
        if(isPalindrome)
            return -1;
        
        int i = 0, j = n-1;
        for(; i < j; ++i,--j)
        {
            if(str.charAt(i) != str.charAt(j))
                return isPalindrome(str, i+1, j)?i:j;
        }
        return -1;
    }
    
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0; i < t; ++i)
        {
            String str = sc.next();
            System.out.println(palindromeByRemovingIndex(str));
        }
    	

    }
}
