package algorithms.dp;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Candies {
	
	static long[] test;
	static long getMinCandies(int[] arr, int n)
	{
		test = new long[n];
		
		long minCandy = 0;
        long prev = 0;
        for(int i = 0; i < n; ++i)
        {
            long cur = 0;
            if(i == 0)
            {
                if(i+1 < n)
                {
                    if(arr[i+1] < arr[i])
                        cur = 2;
                    else
                        cur = 1;
                }
                else
                    cur = 1;
            }
            else if(i == n-1)
            {
                if(i-1>=0)
                {
                    if(arr[i-1] >= arr[i])
                        cur = 1;
                    else
                        cur = prev+1;
                }
            }
            else
            {
                if(arr[i] > arr[i-1])
                {
                    cur = prev+1;
                }
                else
                {
                    if(arr[i] > arr[i+1])
                    {
                        cur = 2;
                    }
                    else
                    {
                        cur = 1;
                    }
                }
            }
            minCandy += cur;
            prev = cur;
            test[i] = cur;
        }
        
        return minCandy;
	}
	
	static void test(int arr[], int n)
	{
		for(int i = 0; i < n; ++i)
		{
			boolean isFail = false;
			if(i == 0 && i+1 < n)
			{
				if(arr[i] > arr[i+1] && test[i] <= test[i+1])
					isFail = true;
				else if(arr[i] <= arr[i+1] && test[i] > test[i+1])
					isFail = true;
			}
			else if(i == n-1 && i-1 >= 0)
			{
				if(arr[i] > arr[i-1] && test[i] <= test[i-1])
					isFail = true;
				else if(arr[i] <= arr[i-1] && test[i] > test[i-1])
					isFail = true;
			}
			else
			{
				if(arr[i] > arr[i+1] && test[i] <= test[i+1])
					isFail = true;
				else if(arr[i] <= arr[i+1] && test[i] > test[i+1])
					isFail = true;
				if(arr[i] > arr[i-1] && test[i] <= test[i-1])
					isFail = true;
				else if(arr[i] <= arr[i-1] && test[i] > test[i-1])
					isFail = true;
			}
			
			if(isFail)
				System.out.println("test fail :" + arr[i-1] + " " + arr[i] +" " + arr[i+1] + "=>" + test[i-1] + " " +test[i] + " " + test[i+1]);
		}
	}
	
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; ++i)
            arr[i] = sc.nextInt();
        sc.close();
        
        
        System.out.println(getMinCandies(arr, n));
        
        test(arr, n);
    }
}
