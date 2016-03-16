package algorithms.dp;

import java.io.InputStreamReader;
import java.util.Scanner;

public class MaximumSubarray {
	static int getMaxSubarray(int[] arr, int n)
    {
		if(arr[0] < 0)
		{
			boolean isPositive = false;
			int max = arr[0];
			for(int i = 0; i < n; ++i)
			{
				if(arr[i] > 0)
				{
					isPositive = true;
					break;
				}
				else
					max = Math.max(max, arr[i]);
			}
			if(!isPositive)
				return max;
		}
		
        int max = 0;
        int curMax = 0;
        for(int i = 0; i < n; ++i)    
        {
            if(arr[i]+curMax > 0)
                curMax = arr[i]+curMax;
            else
                curMax = 0;
            
            max = Math.max(max, curMax);
        }
        return max;
    }
    
    static int getMaxSubsequence(int[] arr, int n)
    {
    	if(arr[0] < 0)
		{
			boolean isPositive = false;
			int max = arr[0];
			for(int i = 0; i < n; ++i)
			{
				if(arr[i] > 0)
				{
					isPositive = true;
					break;
				}
				else
					max = Math.max(max, arr[i]);
			}
			if(!isPositive)
				return max;
		}
    	
        int max = 0;
        for(int i = 0; i < n; ++i)
        {
            if(arr[i] > 0)
                max += arr[i];
        }
        return max;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int T = sc.nextInt();
        while(T-->0)
        {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; ++i)
            {
                arr[i] = sc.nextInt();    
            }
            int maxSubarray = getMaxSubarray(arr, n);
            int maxSubseq = getMaxSubsequence(arr, n);
            System.out.println(maxSubarray + " " + maxSubseq);
        }
        sc.close();
    }
}
