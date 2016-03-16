package greedy;

import java.util.Stack;
import java.util.TreeMap;

public class RemoveDuplicates {

    TreeMap<Character,Integer> indexMap;
    
    public String removeDuplicateLetters(String s) {int n = s.length();
    if(n <= 1) return s;
    
    int[] count = new int[26];
    for(char c : s.toCharArray())
        count[c-'a']++;
      
    boolean[] added = new boolean[26];  
    Stack<Character> st = new Stack<Character>();
    
    for(char ch : s.toCharArray())
    {
        count[ch-'a']--;
        
        if(added[ch-'a'])
            continue;
        
        while(!st.isEmpty() && st.peek() > ch && count[st.peek()-'a']>0)
            added[st.pop()-'a'] = false;
        
        st.push(ch);
        added[ch-'a'] = true;
    }
    
    String res = "";
    while(!st.isEmpty())
        res = st.pop()+res;
        
    return res;
    }
    
    public static void main(String args[])
    {
    	RemoveDuplicates obj = new RemoveDuplicates();
    	System.out.println(obj.removeDuplicateLetters("cbacdcbc"));
    }
}
