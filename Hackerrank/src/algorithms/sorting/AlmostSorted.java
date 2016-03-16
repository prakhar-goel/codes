package algorithms.sorting;

import java.util.Arrays;

public class AlmostSorted {
	static boolean isSorted(int[] arr, int n)
    {
        for(int i = 0; i < n; ++i)
        {
            if(arr[i] > arr[i+1])
                return false;
        }
        return true;
    }
    
    static boolean findInvertedPair(int[] arr, int n)
    {
        // if at most 1 invert pair exists, send true
        int inv_count = 0;
        int i, j;
        int l = -1, r = -1;
        
        for(i = 0; i < n - 1; i++)
        {
            for(j = i+1; j < n; j++)
            {
                if(arr[i] > arr[j])
                {
                    inv_count++;
                    l = i+1;
                    r = j+1;
                }    
                if(inv_count > 1)
                    return false;
            }    
        }    
        System.out.println("yes\nswap " + l + " " + r);    
        return true;
    }
    
    static boolean findInvertedSequence(int[] arr, int n)
    {
        boolean startInvSequence = false;
        boolean endInvSequence = false;
        int l = -1, r = -1;
        for(int i = 0; i < n-1; ++i)
        {
            if(startInvSequence && endInvSequence)
            {
            	if(arr[i] < arr[l] || arr[i] < arr[r])
            		return false;    
            }
            // 1,2,3,7,5,4,6,8   
            if(startInvSequence)
            {
                if(arr[i] < arr[i+1])    
                {
                    endInvSequence = true;  
                    r = i;
                }    
            }
            else
            {
                if(arr[i] > arr[i+1])
                {
                    startInvSequence = true;
                    l = i;
                }
            }
        }
        
        if(startInvSequence && endInvSequence)
        {
            boolean leftCond = false;
            boolean rightCond = false;
            if(l > 0)
            {
               leftCond =  arr[r] >= arr[l-1];
            }
            else
                leftCond = true;
            if(r < n-1)
                rightCond = arr[l] <= arr[r+1];
            else
                rightCond = true;
            
            if(leftCond && rightCond)
            {
                System.out.println("yes\nreverse " + (l+1) + " " + (r+1));  
                return true; 
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        /*Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; ++i)
        {
            arr[i] = sc.nextInt();    
        }*/
    	int[] arr = {3, 1, 2};
    	int[] sortedArr = {3, 1, 2};
    	
    	int n = arr.length;
    	 Arrays.sort(sortedArr);
         
         int diff = 0;
         int[] indices = new int[n];
         int ind = 0;
         for(int i = 0; i < n; ++i)
         {
             if(arr[i] != sortedArr[i])
             {
                 diff++;
                 indices[ind++] = i;
             }    
         }
         if(diff == 0)
             System.out.println("yes");
         else if(diff == 2)
             System.out.println("yes\nswap " + (indices[0]+1) + " " + (indices[1]+1));    
         else
         {
             boolean isInvertible= true;
             int start = indices[0], end = indices[ind-1];
             for(int i = start, j = end; i <= end && j>=start; ++i,--j)
             {
                  if(arr[i] != sortedArr[j])
                  {
                      isInvertible = false;
                      break;
                  }
             }
             if(isInvertible)
                 System.out.println("yes\nreverse " + (start+1) + " " + (end+1));
             else
                 System.out.println("no");
         }
    }
}
