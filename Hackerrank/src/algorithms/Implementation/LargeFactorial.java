package algorithms.Implementation;
import java.math.BigInteger;
import java.util.Scanner;


public class LargeFactorial {
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        BigInteger num = new BigInteger(n+"");
        for(int i = 2;i < n; ++i)
        {
        	num = num.multiply(new BigInteger(i+""));
        }
        System.out.println(num);
    }
}
