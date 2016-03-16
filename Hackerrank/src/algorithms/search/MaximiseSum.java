package algorithms.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaximiseSum {
	static long getMaxSumSubarray(long[] arr, long n, long m)
    {
		long max = 0;//Long.MIN_VALUE;
        int curSum = 0;
        for(int i = 0; i < n; ++i)
        {
              curSum += arr[i];
            if(curSum%m > max)
                max = curSum%m;
            else if(curSum < 0)
                curSum = 0;
        }
        System.out.println(max);
        return max;
    }
    
    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long T = Long.parseLong(br.readLine());
        for(long t = 0; t < T; ++t)
        {
            String input[] = (br.readLine()).split(" ");
            int N = Integer.parseInt(input[0]);
            long M = Long.parseLong(input[1]);
            long[] arr = new long[N];
            String arrStr[] = (br.readLine()).split(" ");
            
            for(int i = 0; i < N; ++i)
            	arr[i] = Long.parseLong(arrStr[i]);
            
            System.out.println(getMaxSumSubarray(arr, N, M));
        }
    }
}
