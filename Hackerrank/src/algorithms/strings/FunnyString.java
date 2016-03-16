package algorithms.strings;
import java.util.Scanner;


public class FunnyString {
	static boolean isFunny(String s)
    {
        int len = s.length();
        for(int i =1; i <= len/2; ++i)
        {
            if((s.charAt(i)-s.charAt(i-1)) != (s.charAt(len-i)-s.charAt(len-i-1)))
                return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0; i < t; ++i)
        {
            String s = sc.next();
        
            if(isFunny(s))
                System.out.println("Funny");
            else
                System.out.println("Not Funny");    
        }
    }
}
