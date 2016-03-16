
public class ScrambleString {
	
	String reverseAndMerge(String s)
    {System.out.println(s);
        int n = s.length();
        int mid = n/2;
        
        if(mid > 0)
        {
        	String first = s.substring(0,mid);
            String second = s.substring(mid);
            
            String revSecond = reverseAndMerge(second);
            String revFirst = reverseAndMerge(first);
            
            System.out.println("rev second :" + revSecond + "\nrevFirst :" + revFirst +"\n");
            
            return revSecond + revFirst;
        }
        return s;
    }
	
	public boolean isScramble(String s1, String s2) {
		
		int n = s1.length();
		int mid = n/2;
		String first = s1.substring(0,mid);
        String second = s1.substring(mid);
		String result = reverseAndMerge(first)+reverseAndMerge(second);
        return result.equals(s2);
    }
	
	public static void main(String args[])
	{
		String s1 = "ab";
		String s2 = "ba";
		
		ScrambleString obj = new ScrambleString();
		System.out.println(obj.isScramble(s1, s2));
	}
}
