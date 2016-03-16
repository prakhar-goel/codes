import java.util.*;

public class CombinationSum {
	List<List<Integer>> lists;
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        lists = new ArrayList<List<Integer>>();
        int n = candidates.length;
        
        if(n == 0)
            return lists;
        
        Arrays.sort(candidates);
        Integer[] out = new Integer[100];
        combinationSum(candidates, n, target, out, 0);
        return lists;
    }
    
    void combinationSum(int[] candidates, int n, int remaining, Integer[] out, int level)
    {
        if(remaining == 0)
        {
            List<Integer> list = new ArrayList<Integer>();
            for(int i = 0; i < level;++i)
                list.add(out[i]);
            lists.add(list);
            return;
        }
        for(int i = 0; i < n; ++i)
        {
            if(isValid(out, level, candidates[i], remaining))
            {
                out[level] = candidates[i];
                combinationSum(candidates, n, remaining-candidates[i], out, level+1);
            }
        }
        out[level] = 0;
    }
    boolean isValid(Integer[] out, int level, int num, int remaining)
    {
        if(level == 0) return true;
        if(num > remaining) return false;
        if(out[level-1] <= num) return true;
        return false;
    }
}
