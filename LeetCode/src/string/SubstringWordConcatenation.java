package string;
import java.util.*;

public class SubstringWordConcatenation {

    public List<Integer> findSubstring(String s, String[] words) {
        
        int n = s.length();
        int k = 0;
        int wordsCount = words.length;
        if(n == 0 || wordsCount==0 || ((k=words[0].length())==0)) return null;
        
        HashMap<String, Integer> wordFreqMap = new HashMap<String, Integer>();
        for(String word : words)
        {
            int freq = 1;
            
            if(wordFreqMap.containsKey(word)) 
            	freq += wordFreqMap.get(word); // add previous frequency
            
            wordFreqMap.put(word, freq);
        }
        
        List<Integer> res = new ArrayList<Integer>();
        String[] suffixes = new String[n-k+1];
        
        for(int i = 0; i <= n-k; ++i)
        {
            suffixes[i] = s.substring(i, i+k);
        }
        
        for(int i = 0; (n-i)/k>=wordsCount; ++i) // ** important condition "="
        {
            String suffix = suffixes[i];
            
            if(wordFreqMap.containsKey(suffix)) // can be a part of substring
            {System.out.println("checking :" + i);
                HashMap<String,Integer> wordFreqMapCopy = new HashMap<String,Integer>(wordFreqMap);
                int temp_i = i;
                
                while(temp_i <= n-k && // *** important boundary condition : "="
                		wordFreqMapCopy.containsKey((suffix=suffixes[temp_i])))
                {
                    int freq = wordFreqMapCopy.get(suffix);
                    if(freq == 1)
                        wordFreqMapCopy.remove(suffix);
                    else
                        wordFreqMapCopy.put(suffix, freq-1);
                        
                    temp_i += k;
                }
                if(wordFreqMapCopy.size() == 0)
                {
                    res.add(i);
                }
            }
        }
        return res;
    }

	public static void main(String[] args) {

		SubstringWordConcatenation obj = new SubstringWordConcatenation();
		
		String str = "aabbaabbaabb";
		String[] words = {"bb","aa","bb","aa","bb"};
		//String[] words = {"word", "good", "best", "good"};
		System.out.println(obj.findSubstring(str, words));
	}

}
