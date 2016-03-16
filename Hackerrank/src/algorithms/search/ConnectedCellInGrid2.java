package algorithms.search;

import java.util.Scanner;

public class ConnectedCellInGrid2 {
static int maxCount = 0;//, count = 0;
    
    static int gridRegion(char[][]grid, int i, int j, int n, int m, char[][] visit)
    {
        //maxCount = Math.max(count, maxCount);
        visit[i][j] = 'x';
        
        int left = 0, right=0, top=0, down=0, diag11=0,diag12=0,diag21=0,diag22=0;
        
        if(i < n-1 && visit[i+1][j] == '1')
           down = gridRegion(grid, i+1, j, n, m, visit);
            
        if(j < m-1 && visit[i][j+1] == '1')
           right = gridRegion(grid, i, j+1, n, m, visit);    
            
        if(i > 0 && visit[i-1][j] == '1')
           top = gridRegion(grid, i-1, j, n, m, visit);
            
        if(j > 0 && visit[i][j-1] == '1')
           left = gridRegion(grid, i, j-1, n, m, visit);
            
        if(i < n-1 && j < m-1 && visit[i+1][j+1] == '1')
           diag11 = gridRegion(grid, i+1, j+1, n, m, visit);
            
        if(i > 0 && j > 0 && visit[i-1][j-1] == '1')
           diag12 = gridRegion(grid, i-1, j-1, n, m, visit);
        
        if(i < n-1 && j > 0 && visit[i+1][j-1] == '1')
            diag21 = gridRegion(grid, i+1, j-1, n, m, visit);
        
        if(i > 0 && j < m-1 && visit[i-1][j+1] == '1')
            diag22 = gridRegion(grid, i-1, j+1, n, m, visit);
        
        return 1+left+right+top+down+diag11+diag12+diag21+diag22;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] grid = new char[n][m];
        char[][] visit = new char[n][m];
        sc.nextLine();
        for(int i = 0; i < n; ++i)
        {
            String row[] = (sc.nextLine()).split(" ");
            for(int j = 0; j < m; ++j)
            {
                grid[i][j] = row[j].equals("0")?'0':'1'; 
                visit[i][j] = grid[i][j];
            }    
        }
        
        for(int i = 0; i < n; ++i)
        {
            for(int j = 0; j < m; ++j)
            {
                if(visit[i][j] == '1')
                {
                	int count = gridRegion(grid, i, j, n, m, visit);
                	System.out.println(count + " count starts at :" + i + " " + j);
                	maxCount = Math.max(count, maxCount);
                    
                }
            }
        }
       System.out.println(maxCount);
    }
}
