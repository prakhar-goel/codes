package datastructure;
import java.util.*;

public class WordBreakII {
	
	void getWordBreaks(boolean[] isWord, String s, Set<String> wordDict, List<String> sentences, int end, String sentence)
    {
        if(end == 0)
        {
        	sentences.add(sentence.trim());
        	return;
        }
        
        if(isWord[end-1])
        {
            for(int start = end-1; start >= 0; start--)
            {
                String word = s.substring(start, end);
                    
                if(wordDict.contains(word))
                {
                    String newSentence = word + " " + sentence; 
                    getWordBreaks(isWord, s, wordDict, sentences, start, newSentence);  
                }
            }
        }
    }
    
    public List<String> wordBreak(String s, Set<String> wordDict) {
        
        List<String> sentences = new ArrayList<String>();
        
        int n = s.length();
        int end = 1;
        
        boolean[] isWord = new boolean[n];
        
        for(; end <= n; ++end)
        {
        	String word = s.substring(0, end);
        	
            if(!isWord[end-1] && wordDict.contains(word))
            {
                isWord[end-1] = true;
            }
            
            if(isWord[end-1])
            {
            	if(end == n)
            		break;
            		
            	for(int j = end+1; j <= n; ++j)
            	{
            		word = s.substring(end, j);
            		
            		if(!isWord[j-1] && wordDict.contains(word))
            		{
            			isWord[j-1] = true;
            		}
            	}
            }
        }
        for(boolean isword : isWord)
        	System.out.print(isword + " ");
        System.out.println();
        
        getWordBreaks(isWord, s, wordDict, sentences, s.length(), "");  
        return sentences;
    }
    
    public static void main(String args[])
    {
    	WordBreakII obj = new WordBreakII();
    	Set<String> dict = new HashSet<String>();
    	dict.add("cat");
    	dict.add("cats");
    	dict.add("sand");
    	dict.add("and");
    	dict.add("dog");
    	
    	System.out.println(obj.wordBreak("catsanddog", dict));
    }
}
