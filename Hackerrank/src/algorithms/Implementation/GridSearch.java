package algorithms.Implementation;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GridSearch {

	static boolean isRowPresent(char[][]grid, int R, int C, char[][]pattern, int r)
	{
		//System.out.println("grid col :" + tempGridCol);
		int c = 0;
		int gridCol = grid[0].length;
		int patCol = pattern[0].length;

		for(;C < gridCol && c < patCol; C++,c++)
		{
			if(grid[R][C] != pattern[r][c])
				return false;
		}

		//System.out.println("matched grid row :" + gridRow +" pattern row :" + patRow);
		return true;
	}

	static boolean searchPattern(char[][]grid, char[][]pattern)
	{
		int gridRow = grid.length;
		int gridCol = grid[0].length;
		
		for(int R = 0; R < gridRow; ++R)
		{
			for(int C = 0; C < gridCol; ++C)
			{
				// search row
				boolean isRowPresent = isRowPresent(grid, R, C, pattern, 0);
				if(isRowPresent)
				{
					int pat_row = 1, patternRow = pattern.length;
					for(int tempGridRow=R+1; isRowPresent && pat_row<patternRow && tempGridRow<gridRow; ++pat_row,++tempGridRow)
					{
						isRowPresent = isRowPresent(grid, tempGridRow, C, pattern, pat_row);
					}
					if(isRowPresent && pat_row == patternRow)
						return true;
				}
			}
		}
		return false;
	}
	
	static String findPattern(char[][] grid, char[][] pattern){
        // R, C, r, & c are the same letters used in the problem
        for(int R = 0; R < grid.length - pattern.length + 1; R++){
            for(int C = 0; C < grid[0].length - pattern[0].length + 1; C++){
                outerLoop:
                for(int r = 0; r < pattern.length; r++){
                    for(int c = 0; c < pattern[0].length; c++){
                        if(grid[R + r][C + c] != pattern[r][c]){
                            break outerLoop;
                        }
                    }
                    if(r == pattern.length - 1){
                        return "YES";
                    }
                }
            }
        }
        return "NO";
    }
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int a0 = 0; a0 < t; a0++){
			int R = in.nextInt();
			int C = in.nextInt();
			char G[][] = new char[R][];
			for(int G_i=0; G_i < R; G_i++){
				G[G_i] = in.next().toCharArray();
			}
			int r = in.nextInt();
			int c = in.nextInt();
			char P[][] = new char[r][];
			for(int P_i=0; P_i < r; P_i++){
				P[P_i] = in.next().toCharArray();
			}

			/*if(searchPattern(G, P))
				System.out.println("YES");
			else
				System.out.println("NO");*/
			
			System.out.println(findPattern(G, P));
		}
	}
}
