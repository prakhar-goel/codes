package dp;

import java.util.HashMap;

public class MinimumWindowSubstring {
    HashMap<Character,Integer> tMap;
    HashMap<Character,Integer> counts = new HashMap<Character,Integer>();
    
    /** good to keep these 3 methods to hide implementation details and to show real idea only in main method */
    void incrementCount(char ch)
    {
        int freq = 1;
        if(counts.containsKey(ch))
            freq += counts.get(ch);
        counts.put(ch, freq);
    }
    
    void decrementCount(char ch)
    {
        if(counts.containsKey(ch))
        {
           int freq = counts.get(ch);
           if(freq > 1)
                counts.put(ch, freq-1);
            else
                counts.remove(ch);
        }
    }
    
    int getCount(char ch)
    {
        if(counts.containsKey(ch))
            return counts.get(ch);
            
        return 0;
    }
    
    public String minWindow(String s, String t) {
        
        int S = s.length();
         int T = t.length();
         
        if(S == 0 || T == 0) return "";
        
        tMap = new HashMap<Character,Integer>();
        for(char ch : t.toCharArray())
        {
            int freq = 1;
            if(tMap.containsKey(ch))
                freq += tMap.get(ch);
            tMap.put(ch, freq);
        }
        
        int minWindowLen = S;
        int minStart = 0, minEnd = S-1;
        
        int tCount = 0;
        int start = 0, end = 0;
        
        while(start < S && !tMap.containsKey(s.charAt(start)))start++; // important to start with targeted characters
        end = start;
        
        for(; end < S; ++end)
        {
            char ch = s.charAt(end);
            incrementCount(ch);
            
            if(tMap.containsKey(ch))
            {
                if(getCount(ch) == tMap.get(ch))
                    tCount+=tMap.get(ch);
                else if(getCount(ch) > tMap.get(ch) && 
                    ch == s.charAt(start))
                {
                    while(start < end)
                    {
                        char startCh = s.charAt(start);
                        
                        if(!tMap.containsKey(startCh))
                            start++;
                        else if(getCount(startCh) > tMap.get(startCh))
                        {
                            decrementCount(startCh);
                            start++;
                        }
                        else
                            break;
                    }
                }
            }
            
            if(tCount == T && minWindowLen > (end-start+1))
            {
                minWindowLen = end-start+1;
                minStart = start;
                minEnd = end;
            }
        }
        if(tCount == T) // ** important check to cover if S does not has all the characters of T
            return s.substring(minStart, minEnd+1);
        return "";
    }
}
