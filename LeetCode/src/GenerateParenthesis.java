import java.util.ArrayList;
import java.util.List;


public class GenerateParenthesis {
	public List<String> generateParenthesis(int n) {
        List<String> parenthesises = new ArrayList<String>(); 
        char[] out = new char[2*n];
        generateParenthesis(out, n, n, parenthesises, 0);
        return parenthesises;
    }
    
    void generateParenthesis(char[] out, int leftParen, int rightParen, List<String> parenthesises, int level)
    {
        if(leftParen==0 && rightParen==0)
        {
            parenthesises.add(new String(out));
            return;
        }
        
        if(leftParen>rightParen)
            return;
            
        if(leftParen>0)
        {
            out[level] = '(';
            generateParenthesis(out, leftParen-1, rightParen, parenthesises, level+1);
        }    
        if(rightParen>0)
        {
            out[level] = ')';
            generateParenthesis(out, leftParen, rightParen-1, parenthesises, level+1);
        }
    }
}
