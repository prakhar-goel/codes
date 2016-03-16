package algorithms.dp;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciModified {
	
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int a = sc.nextInt();
        int b = sc.nextInt();
        int n = sc.nextInt();
        sc.close();
        
        BigInteger A = new BigInteger(a+"");
        BigInteger B = new BigInteger(b+"");
        BigInteger C = new BigInteger(a+"");
        
        for(int i = 3; i <= n; ++i)
        {
           C = B.multiply(B).add(A);
           A = B;
           B = C;
        }
        System.out.println(C);
    }
}
