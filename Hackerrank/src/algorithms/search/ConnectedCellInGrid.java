package algorithms.search;

import java.util.Scanner;

public class ConnectedCellInGrid {
static int maxCount = 0, count = 0;
    
    static void gridRegion(char[][]grid, int i, int j, int n, int m)
    {
        if(i < n && j < m && i >= 0 && j >= 0 && grid[i][j]  == '1')
        {
            count++;
            maxCount = Math.max(count, maxCount);
            grid[i][j] = 'x';
            gridRegion(grid, i+1, j, n, m);
            gridRegion(grid, i, j+1, n, m);
            gridRegion(grid, i-1, j, n, m);
            gridRegion(grid, i, j-1, n, m);
            gridRegion(grid, i+1, j+1, n, m);
            gridRegion(grid, i-1, j-1, n, m);
            
        }
        else
            return;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] grid = new char[n][m];
        sc.nextLine();
        
        for(int i = 0; i < n; ++i)
        {
        	String line = sc.nextLine();
        	String row[] = line.split(" ");
            for(int j = 0; j < m; ++j)
                grid[i][j] = row[j].equals("0")?'0':'1'; 
        }
        
        for(int i = 0; i < n; ++i)
        {
            for(int j = 0; j < m; ++j)
            {
                if(grid[i][j] == '1')
                {
                    count = 0;
                    gridRegion(grid, i, j, n, m);
                }
            }
        }
        System.out.println(maxCount);
    }
}
