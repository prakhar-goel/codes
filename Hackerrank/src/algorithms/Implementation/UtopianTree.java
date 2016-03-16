package algorithms.Implementation;
import java.util.Scanner;


public class UtopianTree {

    static int getHeight(int n, boolean isSpring)
    {
        if(n == 0)
            return 1;
        
        if(isSpring)
            return 2*getHeight(n-1, !isSpring);
        else
            return 1+getHeight(n-1, !isSpring);
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            boolean isSummer = n%2==0?false: true;
            System.out.println(getHeight(n, isSummer));
        }
    }
}
