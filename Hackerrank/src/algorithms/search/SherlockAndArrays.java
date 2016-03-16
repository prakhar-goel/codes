package algorithms.search;

import java.util.Scanner;

public class SherlockAndArrays {
	static boolean isValidNumPresent(int[] arr, int n)
    {
		if(n == 1)
			return true;
		
        int[] sums = new int[n];
        sums[0] = arr[0];
        for(int i = 1; i < n; ++i)
        {
            sums[i] = arr[i] + sums[i-1];
        }
        
        // check
        for(int i = 1; i < n-1; i++)
        {
            if(sums[i-1] == (sums[n-1]-sums[i]))    
                return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 0 ;t < T; ++t)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; ++i)
            {
                arr[i] = sc.nextInt(); 
            }
            if(isValidNumPresent(arr, n))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}	
