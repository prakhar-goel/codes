package algorithms.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindMedian {
	static int getMedianBypartition(int[] arr, int n)
    {
        int mid = n/2;
        int l = 0, r = n-1;
        int p = partition(arr, l, r);
        while(true)
        {
            if(p == mid)
                return arr[p];
            
            if(p < mid)
                l = p+1; // very important to change it from "p"
            else
                r = p-1;
            p = partition(arr, l, r);
        }
    }
    
    static int partition(int[] arr, int l, int r)
    {
        int pivotValue = arr[l];
         int left = l+1, right = r;
        
        while(true)
        {
            while(left <= r && pivotValue >= arr[left])left++;
            while(right >= 0 && pivotValue < arr[right])right--;
            
            if(left < right)
            {
                swap(arr, left, right);
                left++;right--;
            }
            else
            {
                swap(arr, l, right);
                return right;
            }
        }
    }
    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args)throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] tok = (br.readLine()).split(" ");
        int[] arr = new int[n];
        
        for(int i = 0; i < n; ++i)
        {
            arr[i] = Integer.parseInt(tok[i]);    
        }
        System.out.println(partition(arr,0,n-1)/*getMedianBypartition(arr, n)*/);
    }
}
