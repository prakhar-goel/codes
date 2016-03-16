/**
 * Each number in C may only be used once in the combination.
 * */
import java.util.*;

public class CombinationSumII {
	 List<List<Integer>> lists;
	    
	    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	        
	        lists = new ArrayList<List<Integer>>();
	        int n = candidates.length;
	        
	        if(n == 0)
	            return lists;
	     
	       Arrays.sort(candidates);
	        Integer[] out = new Integer[100];
	        combinationSum2(candidates, n, target, out, 0, 0);
	        return lists;
	    }
	    
	    void combinationSum2(int[] candidates, int n, int remaining, Integer[] out, int level, int start)
	    {
	        if(remaining == 0)
	        {
	            List<Integer> list = new ArrayList<Integer>();
	            for(int i = 0; i < level;++i)
	                list.add(out[i]);
	            lists.add(list);
	            return;
	        }
	      
	        for(int i = start; i < n; ++i)
	        {
	            int num = candidates[i];
	            if(isValid(out, level, num, remaining))
	            {
	                out[level] = num;
	                // follow same array to for next digit
	                combinationSum2(candidates, n, remaining-num, out, level+1, i+1);
	                // set for next combination
	                while(i+1 < n && candidates[i]==candidates[i+1])i++;
	            }
	        }
	    }
	    
	    boolean isValid(Integer[] out, int level, int num, int remaining)
	    {
	        if(num > remaining) return false;
	        return true;
	    }
}
