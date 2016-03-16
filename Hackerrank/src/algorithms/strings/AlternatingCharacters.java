package algorithms.strings;

import java.util.Scanner;

public class AlternatingCharacters {
	static int minDel(String s)
    {
        int minDel = 0;
        for(int i = 1; i < s.length();++i)
        {
            if(s.charAt(i-1)==s.charAt(i))
                minDel++;
        }
        return minDel;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0; i < t; ++i)
        {
            String str = sc.next();
            System.out.println(minDel(str));    
        }
    }
}
