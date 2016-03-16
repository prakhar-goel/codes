package algorithms.Implementation;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CutSticks {
    
    static int min = -1;
    static int cutOperation(int[] arr)
    {
        int cut_count = 0;
        int n = arr.length;
        int new_min = -1;
        
        for(int i = 0; i < n;++i)
        {   if(arr[i] != 0)
            {
                cut_count++;
                arr[i] = arr[i]-min;
                if(arr[i] != 0)
                {
                    if(new_min == -1)
                    {
                    	new_min = arr[i];
                    }    
                    else
                    {
                    	new_min = Math.min(new_min, arr[i]);    
                    }    
                }
              }    
          }
        min = new_min;
        return cut_count;
     }
        
   static void printArray(int[] arr)
    {
    	for(int a: arr)
    		System.out.print(a + " ");
    	System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[n];
        for(int arr_i=0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
            min = min == -1? arr[arr_i]:Math.min(min, arr[arr_i]);
        }
        
        
        int cut_count = cutOperation(arr);
        while(cut_count != 0)
        {
              System.out.println(cut_count);
              cut_count = cutOperation(arr);
        }
        
    }
}
