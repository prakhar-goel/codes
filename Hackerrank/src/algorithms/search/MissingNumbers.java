package algorithms.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class MissingNumbers {
	public static void main(String[] args)throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input1[] = (br.readLine()).split(" ");
        int m = Integer.parseInt(br.readLine());
        String input2[] = (br.readLine()).split(" ");
        
        TreeMap<Long,Long> countMap1 = new TreeMap<Long,Long>();
        for(int i = 0; i < m; ++i)
        {
            long num = Long.parseLong(input2[i]);
            long count = 1;
            if(countMap1.containsKey(num))
                count+= countMap1.get(num);
            countMap1.put(num, count);
        }
        TreeMap<Long,Long> countMap2 = new TreeMap<Long,Long>();
        for(int i = 0; i < n; ++i)
        {
             long num = Long.parseLong(input1[i]);
            long count = 1;
            if(countMap2.containsKey(num))
                count+= countMap2.get(num);
            countMap2.put(num, count);   
        }
        
      //  System.out.println(countMap1 + "\n" + countMap2);
        for(long num : countMap1.keySet())
        {
            if(!countMap2.containsKey(num) || !countMap2.get(num).equals(countMap1.get(num)))
            {
            	System.out.println("missing :" + num + " ");
            	long count1 = countMap2.get(num);
            	long count2 = countMap1.get(num);
            	System.out.println( count1+ " " + count2 +  " " + (count1==count2));
            	
            }
        }
    }
}
