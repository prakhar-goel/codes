package algorithms.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CountLuck {
	
	static int target = 0, n, m;
	static char forest[][];
	static boolean[][]visited;
	
	private static int check(int i, int j) {
        if (i<0||j<0||i>=n||j>=m)return 0;
        if (forest[i][j]=='X')return 0;
        if (visited[i][j])return 0;
        return 1;
    }
	
	static boolean countLuck(int i, int j, int[] count)
    {
		if(i < 0 || j < 0 || i >= n || j >= m) return false;
		if(forest[i][j] == 'X') return false;
		if(visited[i][j]) return false;
		
		if(forest[i][j] == '*')
        {
        	//System.out.println("found after " + count);
        	return true;
        }
        
        visited[i][j] = true;
        if(check(i+1,j) + check(i-1, j) + check(i, j+1) + check(i,j-1) >1)
        {
        	System.out.println("confused at : " + i +" " + j + " current count :" + count[0]);
        	//System.out.println(new String(grid[i])+"\n");
        	count[0]++;
        }
        boolean passed = false;
        int[]m1 = {0}, m2 = {0}, m3 = {0}, m4 ={0};
        
        if(countLuck(i-1, j, m1))
        {
        	passed = true;
        	count[0] += m1[0];
        }
        
        else if(countLuck(i+1, j, m2))
        {
        	passed = true;
        	count[0] += m2[0];
        }
        
        else if(countLuck(i, j-1, m3))
        {
        	passed = true;
        	count[0] += m3[0];
        }
        
        else if(countLuck(i, j+1, m4))
        {
        	passed = true;
        	count[0] += m4[0];
        }
        
        return passed;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 0; t < T; ++t)
        {
            n = sc.nextInt();
            m = sc.nextInt();
            forest = new char[n][m];
            visited = new boolean[n][m];
            
            int start_i = 0, start_j = 0;
            sc.nextLine();
            for(int i = 0; i < n; ++i)
            {
            	String line = sc.nextLine();
                char[]temp = line.toCharArray();
                forest[i] = temp;
                Arrays.fill(visited[i], false);;
            
                for(int j = 0; j < m; ++j)
                {
                    if(forest[i][j] == 'M')
                    {
                        start_i = i;start_j = j;    
                    }
                }    
            }
            target = sc.nextInt();
            int[] count = {0};
            countLuck(start_i, start_j, count);
            if(target==count[0])
        		System.out.println("Impressed");
        	else
        		System.out.println("Oops! found");
            
            ArrayList<Integer> list = new ArrayList<Integer>();
            
            /*System.out.println(count);		
            if(target == count)
                System.out.println("Impressed");
            else
                System.out.println("Oops!");*/
        }
        
    }
}
