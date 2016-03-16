package algorithms.greedy;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class GridChallenge {

	static boolean isGridSortPossible(char[][] grid, int n)
    {
        for(int i = 0; i < n-1; ++i)
        {
        	for(int j = 0; j < n; ++j)
        	{
        		if(grid[j][i] > grid[j][i+1])
                    return false;
        	}
        }
        return true;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int T = sc.nextInt();
        for(int t = 0; t < T; ++t)
        {
            int n = sc.nextInt();
            char[][] grid = new char[n][n];
            for(int i = 0; i < n; ++i)
            {
                grid[i] = sc.next().toCharArray();    
                Arrays.sort(grid[i]);
            }
            
            boolean isGridSortPossible = isGridSortPossible(grid, n);
            if(isGridSortPossible)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
        sc.close();
        
        
    }
}
