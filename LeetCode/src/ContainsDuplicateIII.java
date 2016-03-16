import java.util.*;

class Pair
{
    int num, ind;
    Pair(int num, int ind)
    {
        this.num = num;
        this.ind = ind;
    }
}

public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        List<Pair> list = new ArrayList<Pair>();
        int n = nums.length;
        if(n == 0) return false;
        for(int i = 0; i < n; ++i)
        {
            list.add(new Pair(nums[i], i));
        }
        Collections.sort(list, new Comparator<Pair>()
        {
            @Override
            public int compare(Pair pair1, Pair pair2)
            {
                if(pair1.num > pair2.num)
                    return 1;
                return -1;
            }
        });
        
        for(int i = 1; i < n; ++i)
        {
            Pair pair = list.get(i);
            if(binarySearch(list, 0, i-1, pair, k, t))
                return true;
            
            if(binarySearch(list, i+1, n-1, pair, k, t))
                return true;
        }
        return false;
    }
    
    boolean binarySearch(List<Pair> list, int l, int r, Pair numPair, int k, int t)
    {
        while(l <= r)
        {
            int mid = l+(r-l)/2;
            Pair midPair = list.get(mid);
            
            long numDiff = Math.abs((long)numPair.num-(long)midPair.num); 
            long indDiff = Math.abs((long)numPair.ind-(long)midPair.ind);
            
            if(numDiff<=t && indDiff<=k)
                return true;
            
            r = mid-1;
        }
        return false;
    }
   
}