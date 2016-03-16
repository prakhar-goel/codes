package datastructure;

// keep ti, mid and send l, r

public class SumRangeQuery {

    int[] st;
    int n;
    int nums[];
    
    public SumRangeQuery(int[] nums) {
        n = nums.length;
        this.nums = nums;
        constructST();
    }

    void constructST()
    {
        int height = (int)Math.ceil(Math.log(n)/(double)Math.log(2));
        int max_size =  2*((int)Math.pow(2, height))-1;
        st = new int[max_size];
        //System.out.println(n + " " + Math.log(3)/(double) Math.log(2) + " "+"height :" + height + " " + "max_size :" + max_size);
        constructSTUtil(0, 0, n-1);
    }
    
    int constructSTUtil(int ti, int l, int r)
    {
        if(l == r)
        {
            st[ti] = nums[l];
            return nums[l];
        }
        
        int mid = l+(r-l)/2;
        st[ti] = constructSTUtil(2*ti+1, l, mid) + constructSTUtil(2*ti+2, mid+1, r);
        return st[ti];
    }
    
    void update(int i, int val) {
        
    	updateUtil(i, val-nums[i], 0, n-1, 0);
    }

    void updateUtil(int i, int diff, int l, int r, int ti)
    {
        if(i < l || i > r)
        {
        	return;
        }
        
        st[ti] += diff;
        
        if(l!=r)
        {
        	int mid = l+(r-l)/2;
            updateUtil(i, diff, l, mid, 2*ti+1);
            updateUtil(i, diff, mid+1, r, 2*ti+2);
        }
        else
        	nums[l]+=diff;
    }
    
    public int sumRange(int i, int j) {
        
        return sumRangeUtil(i, j, 0, n-1, 0);
    }
    
    int sumRangeUtil(int i, int j, int l, int r, int ti)
    {
        if(i <= l && j >= r)
            return st[ti];
        
        if(i > r || j < l)
            return 0;
        
        int mid = l+(r-l)/2;
        return sumRangeUtil(i, j, l, mid, 2*ti+1) + sumRangeUtil(i, j, mid+1, r, 2*ti+2);
    }
    
    void printArray(int[] arr, int l, int r)
    {
    	for(int i = l; i <= r; ++i)
    		System.out.print(arr[i] + " ");
    	System.out.println();
    }
    
    public static void main(String args[])
    {
    	int[] nums = {7, 2, 7, 2, 0};//{1, 3, 5};
    	SumRangeQuery obj = new SumRangeQuery(nums);
    	
    	obj.update(4,6);
    	obj.printArray(obj.st, 0, 8);
    	obj.update(0,2);
    	obj.printArray(obj.st, 0, 8);
    	obj.update(0,9);
    	obj.printArray(obj.st, 0, 8);
    	/*System.out.println(obj.sumRange(4,4));
    	obj.update(3,8);
    	obj.printArray(obj.st, 0, 8);
    	System.out.println(obj.sumRange(0,4));
    	obj.update(4,1);
    	System.out.println(obj.sumRange(0,3));
    	System.out.println(obj.sumRange(0,4));
    	obj.update(0,4);*/
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);