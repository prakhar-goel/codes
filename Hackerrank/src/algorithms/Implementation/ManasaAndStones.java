package algorithms.Implementation;
import java.util.Scanner;


public class ManasaAndStones {
	static void smartApproach(int N, int A, int B)
    {
		StringBuffer sb = new StringBuffer();
        //Make sure A <= B
            if (A > B){
                int temp = A;
                A = B;
                B = temp;
            }
            
            //Get min value of last stone (all A's)
            //There are N-1 gaps between stones
            int val = ((int)--N)*A;
            
            //Calculate change in val per switch to B
            short dval = (short)(B - A);
            
            //Print possible last stone values from smallest to largest
            //Avoid duplicate prints if A == B (dval == 0)
            sb.append(val);
            if (dval > 0){
                while(N-- > 0){
                    sb.append(" " + (val += dval));
                }
            }
            System.out.println(sb); 
    }
          
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
		int t = in.nextInt();

		for(int a0 = 0; a0 < t; a0++){
			int n = in.nextInt();
			int a = in.nextInt();
            int b = in.nextInt();
            smartApproach(n, a, b);
		}
    }
}
