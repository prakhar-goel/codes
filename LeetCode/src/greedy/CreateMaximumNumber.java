package greedy;

import java.util.Stack;

public class CreateMaximumNumber {

	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int i = 0, j = 0;
     
        int res[] = new int[k];
        Stack<Integer> st = new Stack<Integer>();
        
        while(i < n1 && j < n2)
        {
            int maxDig = -1;
            if(nums1[i] > nums2[j])
                maxDig = nums1[i++];
            else
                maxDig = nums2[j++];
            
            
            while(!st.isEmpty() && st.peek() < maxDig && (n1-i+n2-j+1)>=k-st.size())
                st.pop();
                
            st.push(maxDig);
        }
     
        while(i < n1)
        {
            int maxDig = nums1[i++];
            
            while(!st.isEmpty() && st.peek() < maxDig && (n1-i+1)>=k-st.size())
                st.pop();
                
            st.push(maxDig);
        }
        
        while(j < n2)
        {
            int maxDig = nums2[j++];
            
            while(!st.isEmpty() && st.peek() < maxDig && (n2-j+1)>=k-st.size())
                st.pop();
                
            st.push(maxDig);
        }
        
        int n = st.size();
        while(n > k)
        {
            st.pop();n--;
        }
        
        for(int l = n-1; !st.isEmpty(); --l)
        {
            res[l] = st.pop();
        }
        return res;
    }
	public static void main(String[] args) {
		
		CreateMaximumNumber obj = new CreateMaximumNumber();
		int n1[] = {3,4,6,5};
		int n2[] = {9,1,2,5,8,3};
		int[] res = obj.maxNumber(n1, n2, 5);
		for(int n : res)
			System.out.println(n);
	}

}
