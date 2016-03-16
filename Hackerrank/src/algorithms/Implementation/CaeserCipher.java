package algorithms.Implementation;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CaeserCipher {

    static String encrypt(int n, String s, int k)
    {
        char[] chars = s.toCharArray();
        for(int i = 0; i < n; ++i)
        {
            char ch = chars[i];
            if(ch >= 'a' && ch <= 'z')
            {
            	chars[i] = (char) (ch+k <= 'z'?ch+k:'a'+('z'-ch+k));
            }
            else if(ch >= 'A' && ch <= 'Z')
            {
                chars[i] = (char) (ch+k <= 'Z'?ch+k:'A'+('Z'-ch+k));
            }
        }
        return new String(chars);
    }
    public static void main(String[] args) {
        /*Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int k = in.nextInt();
        
        System.out.println(encrypt(n, s, k));*/
    	
    	char ch = 'a' + ('x'+2-'a')%26;
    	
    	System.out.println(ch);
    }
}
