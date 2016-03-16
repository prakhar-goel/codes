/**
 * Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
 * */
public class FirstMissingPositiveNumber {
	public int firstMissingPositive(int[] nums) {
	       
	       int max = nums.length;
	       
	       for(int i = 0; i < max; ++i)
	       {
	           while(nums[i] != i+1)
	           {
	               if(nums[i] <= 0 || nums[i] > max || nums[nums[i]-1]==nums[i]) // assuming duplicate exists
	                break;
	                
	                swap(nums, i, nums[i]-1);
	           }
	       }
	       
	       for(int i = 0; i < max; ++i)
	       {
	           if(nums[i] != i+1)
	            return i+1;
	       }
	       
	       if (max == 0)
	        return 1;
	        
	        return nums[max-1]+1;
	    }
	    
	    void swap(int[] nums, int i, int j)
	    {
	        int temp = nums[i];
	        nums[i] = nums[j];
	        nums[j] = temp;
	    }
}
