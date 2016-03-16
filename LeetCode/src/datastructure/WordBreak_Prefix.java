package datastructure;

import java.util.HashSet;
import java.util.Set;

public class WordBreak_Prefix {
	
	public boolean wordBreak(String s, Set<String> wordDict) {
        int n = s.length();
        int end = 1;
        
        boolean[] isWord = new boolean[n];
        
        for(; end <= n; ++end)
        {
        	String word = s.substring(0, end);
            if(!isWord[end-1] && wordDict.contains(word))
            {
                System.out.println("***1**" + word + " :" + wordDict);
            	isWord[end-1] = true;
            }
            
            if(isWord[end-1])
            {
            	if(end == n)
            		return true;
            	for(int j = end+1; j <= n; ++j)
            	{
            		word = s.substring(end, j);
            		if(!isWord[j-1] && wordDict.contains(word))
            		{
            			isWord[j-1] = true;System.out.println("**2** :" + word);
            		}
            	}
            }
        }
       
        return isWord[n-1];
    }
	
	public static void main(String args[])
	{
		WordBreak_Prefix obj = new WordBreak_Prefix();
		
		Set<String> wordDict = new HashSet<String>();
		wordDict.add("aaaa");
		wordDict.add("aaa");
		
		obj.wordBreak("aaaaaaa", wordDict);
	}
}
