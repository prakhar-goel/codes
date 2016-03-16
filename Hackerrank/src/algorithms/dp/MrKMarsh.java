package algorithms.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Cell
{
    int hor;
    int ver;
    Cell(int hor, int ver)
    {
        this.hor = hor;
        this.ver = ver;
    }
}

public class MrKMarsh {

    static int getMarshPerimeter(char[][] grid, int n, int m)
    {
        Cell[][] matrix = new Cell[n][m];
        // fill the matrix
        if(grid[0][0] == '.')
            matrix[0][0] = new Cell(0, 0);
        else
            matrix[0][0] = new Cell(-1, -1);
        
        for(int i = 1; i < n; ++i)
        {
            if(grid[i][0] == '.')
                matrix[i][0] = new Cell(0, matrix[i-1][0].ver+1);
            else
                matrix[i][0] = new Cell(-1, -1);
        }
        
        for(int i = 1; i < m; ++i)
        {
            if(grid[0][i] == '.')
                matrix[0][i] = new Cell(matrix[0][i-1].hor+1, 0);
            else
                matrix[0][i] = new Cell(-1, -1);
        }
       
        int maxPerimeter = 0;
        // col
        for(int j = 1; j < m; ++j)
        {
        	// row
            for(int i = 1; i < n; ++i)
            {System.out.println("row :" + i + " col :" + j);
                if(grid[i][j] == '.')   
                    matrix[i][j] = new Cell(matrix[i][j-1].hor+1, matrix[i-1][j].ver+1);
                else
                    matrix[i][j] = new Cell(-1, -1);
                
                // calculate perimeter
                int row = i, col = j;
                int ver = matrix[row][col].ver;
                int hor = matrix[row][col].hor;
                col = col-1;
                while(col >= 0 && matrix[row][col].hor >= 0 && matrix[row][col].ver > 0)
                {
                	int perimeter = 2*((hor-matrix[row][col].hor) + Math.min(ver, matrix[row][col].ver));
                    maxPerimeter = Math.max(maxPerimeter, perimeter);
                    System.out.println(row + " " + col);
                    col--;
                }    
            }
        }
      //  printmatrix(matrix, n, m);
        return maxPerimeter;
    }
    
    private static void printmatrix(Cell[][] matrix, int n, int m) {
		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < m; ++j)
			{
				System.out.print("[" +matrix[i][j].hor + "," + matrix[i][j].ver+ "] ");
			}
			System.out.println();
		}
		
	}

	public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line[] = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        char[][] grid = new char[n][m];
        for(int i = 0; i < n; ++i)
        {
            grid[i] = br.readLine().toCharArray();    
        }
        br.close();
        
        int perimeter = getMarshPerimeter(grid, n, m);
        if(perimeter == 0)
            System.out.println("impossible");
        else
            System.out.println(perimeter);
    }
}
