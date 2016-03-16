package algorithms.greedy;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

class Range
{
	int a, b;
	Range(int a, int b)
	{
		this.a = a;
		this.b = b;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Range range = (Range)obj;
		boolean isOutRange = (range.a<a && range.b<a) || (range.a>b && range.b>b);
		return !isOutRange; 
	}
	
	@Override
	public int hashCode()
	{
		return 0;
	}
}

public class AlgorithmicCrush {
	public static void main(String[] args) {
		
		HashMap<Range,Integer> map = new HashMap<Range,Integer>();
		Range r1 = new Range(3,6);
		map.put(r1, 100);
		Range r2 = new Range(4, 5);
		
		map.put(r2, 200);
		System.out.println(map.containsKey(r1) + " " + map.get(r1));
		//System.out.println(map.containsKey(new Range(4,5)));
		
		/*
         Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. 
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        int max = 0;
        for(int i = 0; i < m; ++i)
        {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int k = sc.nextInt();
            for(int c = a; c <= b; ++c)
            {
                int count = k;
                if(map.containsKey(c))
                {
                     count = map.get(c)+k;
                }
                map.put(c, count);
                max = Math.max(max, count);
            }
        }
        sc.close();
        System.out.println(max);
    */}
}
