package algorithms.sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Pair{
	int index;
	String str;
	Pair(int index, String str)
	{
		this.index = index;
		this.str = str;
	}
}

public class CountingSorting {
	 static void countingSortExtended2(int[] arr, int n, HashMap<Integer,List<Pair>> map)
	    {
	        int[] counts = new int[100];
	        for(int i = 0; i < n; ++i)
	            counts[arr[i]]++;
	        
	        for(int num = 0; num < 100; ++num)
	        {
	            if(counts[num] > 0)
	            {
	                List<Pair> pairList = map.get(num);
	                for(Pair pair : pairList)
	                {
	                    if(pair.index < n/2)
	                        System.out.print("- ");
	                    else
	                        System.out.print(pair.str + " ");
	                }                    
	            }
	        }
	    }
	    public static void main(String[] args) {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
	        Scanner sc = new Scanner(System.in); // change to BR
	        int n = sc.nextInt();
	        int[] arr = new int[n];
	        HashMap<Integer, List<Pair>> map = new HashMap<Integer,List<Pair>>();
	        
	        for(int i = 0; i < n;)
	        {
	            String line = sc.nextLine();
	            if(line.length() > 0)
	            {
	                String[] toks = line.split(" ");
	                int num = Integer.parseInt(toks[0]);
	                List<Pair> pairList = null;
	                
	                if(map.containsKey(num))
	                    pairList = map.get(num);
	                
	                else
	                {
	                    pairList = new ArrayList<Pair>();
	                }
	                pairList.add(new Pair(i, toks[1]));
	                map.put(num, pairList);
	                arr[i++] = num;
	            }
	        }
	       // System.out.println(map);
	        countingSortExtended2(arr, n, map);
	    }
}
