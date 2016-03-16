package algorithms.strings;
import java.util.*;


public class Pangram {
	static boolean isPangram(String s)
    {
        HashSet<Character> charSet = new HashSet<Character>();
        for(int i = 0; i < s.length();++i)
        {
            char ch = s.charAt(i);
            if(ch >= 'a' && ch <= 'z')
            {
            	charSet.add(ch);     
            }
            else if(ch>='A'&&ch<='Z')
            {
                // convert to little
                int diff = ch-'A';
                char newch = (char) ('a' + diff);
                charSet.add(newch);  
            }
        }
        return charSet.size()==26;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        
        if(isPangram(s))
            System.out.println("pangram");    
        else
            System.out.println("not pangram");    
    }
}
