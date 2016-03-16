package algorithms.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SherlockAndPairs {
	
	static void anotherMethod() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; ++t)
        {
            int n = Integer.parseInt(br.readLine());
            //int[] arr = new int[n];
            String line[] = (br.readLine()).split(" ");
            HashMap<Integer,Integer> countMap = new HashMap<Integer,Integer>();
            
            for(int i = 0; i < n; ++i)
            {
                int temp = Integer.parseInt(line[i]);  
                int count = 1;
                if(countMap.containsKey(temp))
                    count += countMap.get(temp);
                countMap.put(temp, count);
            }
            
            long pairCount = 0;
            for(int count : countMap.values())
            {
                pairCount += count*(count-1);
            }
            System.out.println(pairCount);
        }
        br.close();   
	}
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		/*int T,N;
	    Scanner in=new Scanner(System.in);
	    T=in.nextInt();
	    while(T-->0){
	        N=in.nextInt();
	        int[] a=new int[N];
	        for(int i=0;i<N;i++){
	            a[i]=in.nextInt();
	        }
	        Arrays.sort(a);
	        long count=0,pair=0;
	        for(int i=0;i<N-1;i++){
	            count=0;
	            while(i<N-1 && a[i]==a[i+1]){
	                count++;
	                i++;
	            }
	            pair+=count*(count+1);
	        }
	        System.out.println(pair);
	    }*/
		anotherMethod();
	}
}
