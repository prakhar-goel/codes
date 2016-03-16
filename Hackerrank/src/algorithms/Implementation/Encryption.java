package algorithms.Implementation;
import java.util.Scanner;


public class Encryption {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int len = s.length();
        double sqrt = Math.sqrt(len);
        int row = (int)Math.floor(sqrt);
        int col = (int)Math.ceil(sqrt);
        
        if(row*col < len)
        {
        	if(row < col)
        		row++;
        	else
        		col++;
        }
        
        int lastRowCol = col - (row*col-len);  
        
        for(int j = 0; j < col; ++j)
        {
            for(int i = 0; i < row; ++i)
            {
               if(!(i == row-1 && j >= lastRowCol))
               {
                    System.out.print(s.charAt(i*col+j));       
               }
            }
            System.out.print(" ");
        }
    }
}
