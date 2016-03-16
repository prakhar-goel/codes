package datastructure;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
	
	List<String> list = new ArrayList<String>();
    int count = 0;
    
    public String getPermutation(int n, int k) {
        
        int[] out = new int[n];
        getPermutation(n, k, 0, out);
        return list.get(0);
    }
    
    void getPermutation(int n, int k, int level, int[] out)
    {
        if(level == n)
        {
            count++;
            if(count == k)
            {
                String str = "";
        	
            	for(int num : out)
        	    	str += num;
        	
            	list.add(str);    
            }
        	
        	return;
        }
        
        for(int i = 1; i <= n && count < k; ++i)
        {
        	if(!isExist(out, level, i))
        	{
        		out[level] = i;
        		getPermutation(n, k, level+1, out);
        	}
        }
    }
    
    boolean isExist(int[] out, int level, int num)
    {
        for(int i = 0; i < level; ++i)
        {
            if(out[i] == num)
                return true;
        }
        
        return false;
    }

    public static void main(String args[])
    {
    	Permutation obj = new Permutation();
    	obj.getPermutation(3, 4);
    	System.out.println(obj.list);
    }
}
