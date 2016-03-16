package algorithms.greedy;

import java.io.InputStreamReader;
import java.util.Scanner;

public class LargestPermutation {
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        int[] indices = new int[n];
        for(int i = 0; i < n; ++i)
        {
            arr[i] = sc.nextInt();
            indices[arr[i]-1] = i;
        }
        sc.close();
        
        int digInd = 0;
        for(int i = 0; i < k && digInd<n; )
        {
             if(indices[n-1-digInd] != digInd)
             {
                 swap(arr, digInd, indices[n-1-digInd]);
                 ++i;
             }
            digInd++;
        }
        for(int num : arr)
            System.out.print(num + " ");
    }
    
    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
