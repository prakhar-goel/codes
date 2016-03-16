package algorithms.Implementation;
import java.util.*;

public class SqaureNums {

    static boolean isSquare(double sqrt)
    {
        //double sqrt = Math.sqrt(n);
		
		String dubStr = sqrt+"";
		String intStr = ((int)sqrt)+ ".0"; 
		
		if(dubStr.equals(intStr))
		{
			return true;
		}
        return false;
    }
    
    static int getLeftSqrt(int leftSqrt, int n1, int n2)
    {
      //  if(leftSqrt == 1)
     //       leftSqrt = 2;
       int square = leftSqrt * leftSqrt;
       if(square >= n1 && square <= n2)
       {
          return leftSqrt;  
       }
       return 0;
    }
    static int getRightSqrt(int rightSqrt, int n1, int n2)
    {
       int square = rightSqrt*rightSqrt;
       if(square >= n1 && square <= n2)
       {
          return rightSqrt;  
       }
       return 0;
    }
    
    static int getSqareNumCount(int n1, int n2)
    {
        double sqrt1 = Math.sqrt(n1);
        double sqrt2 = Math.sqrt(n2);
        
        boolean isSquare1 = isSquare(sqrt1);
        boolean isSquare2 = isSquare(sqrt2);
        
        if(isSquare1 && isSquare2)
        {
            return (int)(sqrt2-sqrt1)+1;  
        }
        else 
        {
            int right = (int)sqrt2;
            int left = (int)sqrt1;
            
            int rightSqrt = 0, leftSqrt = 0;
            
            if(isSquare1)
            {
                leftSqrt = left;
                rightSqrt = getRightSqrt(right, n1, n2);
            }
            else if(isSquare2)
            {
                leftSqrt = getLeftSqrt(left+1, n1, n2);
                rightSqrt = right;
            }
            else
            {
                leftSqrt = getLeftSqrt(left+1, n1, n2);
                rightSqrt = getRightSqrt(right, n1, n2);
            }
            System.out.println(leftSqrt + " " + rightSqrt);
            if(leftSqrt == 0 && rightSqrt == 0)
                return 0;
            
            if(!(leftSqrt != 0 && rightSqrt != 0))
               return 1;
            
            return rightSqrt-leftSqrt+1;
        }    
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        /* Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n1 = in.nextInt();
            int n2 = in.nextInt();
            System.out.println(getSqareNumCount(n1, n2));
        }
        */
        
    	System.out.println(Math.sqrt(10000));
    	
    	
    }
}