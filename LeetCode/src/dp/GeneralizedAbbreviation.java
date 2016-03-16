package dp;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
	
	List<List<String>> lists;
	
	public List<List<String>> getGeneralizedAbbrev(String word)
	{
		lists = new ArrayList<List<String>>();
		getGeneralizedAbbrev(word, word.length());
		return lists;
	}
	
	private List<String> getGeneralizedAbbrev(String word, int n)
	{
		List<String> curLevel = null;
		if(n < 0) return curLevel;
		
		curLevel = new ArrayList<String>();
		
		if(n == 0)
		{
			curLevel.add(word);
			lists.add(curLevel);
			return curLevel;
		}
		
		List<String> lastLevel = getGeneralizedAbbrev(word, n-1);
		for(String str : lastLevel)
		{
			curLevel.addAll(insertAndExpand(str, n));
		}
		
		lists.add(curLevel);
		return curLevel;
	}
	
	private List<String> insertAndExpand(String str, int n)
	{System.out.println("insertAndExpand:" + n + " " +str);
		List<String> newAbbrevs = new ArrayList<String>();
		int len = str.length();
		
		int start = 0;
		if(n > 1)
		{
			while(start < len && !isDigit(str.charAt(start))) start++;
		}
		
		for(int i = start; i < len; ++i)
		{
			char ch = str.charAt(i);
			if(!isDigit(ch)) // insert 1 after last digit in original str
			{
				if(!isDigit(i-1, str, len) && !isDigit(i+1, str, len))
				{
					String newStr = insertNum(str, i, len, 1, 1);
					if(newStr.length() > 0) 
						newAbbrevs.add(newStr);
				}
			}
			else if(n > 1) //expand with last level digit
			{
				if(ch-'0' == n-1)
				{
					if(i+1<len && !isDigit(i+2, str, len))
					{
						String newStr = insertNum(str, i, len, n, 2);
						if(newStr.length() > 0) 
							newAbbrevs.add(newStr);
						System.out.println(ch + " " + n + " newstr :" + newStr);
					}
				}
			}
		}
		
		return newAbbrevs;
	}
	
	private String insertNum(String str, int ind, int len, int num, int winLen)
	{
		if(ind < 0 || ind >= len || ind+winLen-1 >= len) return "";
		
		String newStr = "";
		
		if(ind == 0)
			newStr = num+str.substring(winLen);
		else if(ind == len-winLen)
			newStr = str.substring(0, len-winLen)+num;
		else
			newStr = str.substring(0, ind)+num+str.substring(ind+winLen);
		
		return newStr;
	}
	
	private boolean isDigit(int ind, String str, int len)
	{
		if(ind < 0 || ind >= len) return false;
		
		return isDigit(str.charAt(ind));
	}
	
	private boolean isDigit(char ch)
	{
		return ch>='0'&&ch<='9';
	}
	
	public static void main(String args[])
	{
		GeneralizedAbbreviation obj = new GeneralizedAbbreviation();
		System.out.println(obj.getGeneralizedAbbrev("word"));
	}
}
