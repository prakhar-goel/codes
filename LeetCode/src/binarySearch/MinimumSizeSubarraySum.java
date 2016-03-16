package binarySearch;

public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int s, int[] nums) {
        
        int n = nums.length;
        if(n == 0) return 0;
        
        int[] sums = new int[n];
        sums[0] = nums[0];
        if(nums[0] == s) return 1;
        
        for(int i = 1; i < n; ++i)
        {
            if(nums[i] == s) return 1;    
            sums[i] = sums[i-1]+nums[i];
        }
        
        if(sums[n-1] < s) return 0;
        
        int startMin = 0, endMin = n-1;
        
        for(int i = n-1; i >= 0 && sums[i] >= s; --i)
        {
            int left = -1;
            int right = i;
            
            if(sums[i] == s)
            {
                left = 0;
            }
            else // sums[i] > s
            {
            	left = searchLeft(sums, i-1, sums[i]-s)+1; // *** important to add 1, return index is removal index
            }
            
            if(left != -1 && (right-left)<(endMin-startMin))
            {
                startMin = left;
                endMin = right;    
            }
        }
        System.out.println(startMin + " " + endMin);
        return endMin-startMin+1;
    }
    
    int searchLeft(int[] sums, int r, int num)
    {
        int l = 0;
        while(l>=0 && l <= r)
        {
            if(l == r)
            {System.out.println("*1** :" + num +" " + l );
                if(sums[l] <= num) 
                    return l;
                return -1;
            }
            
            int mid = (int) Math.ceil(l+(r-l)/2.0); //*** important to keep ceil for search left
            if(sums[mid] == num) return mid;
            
            if(sums[mid] > num) r = mid-1;
            else l = mid;
        }
        return -1;
    }

    public static void main(String args[])
    {
    	int[] nums = {1,2,3,4,5};
    	
    	MinimumSizeSubarraySum obj = new MinimumSizeSubarraySum();
    	System.out.println(obj.minSubArrayLen(11, nums));
    }
}
