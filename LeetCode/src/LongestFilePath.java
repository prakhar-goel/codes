
// given a serialized string of unix file system, find longest absolute path available
public class LongestFilePath {

	int maxPathLen = 0;
	
	public int getMaxPath(String serializedString)
	{
		int n = serializedString.length();
		if(n == 0) return 0;
		deserializeAndGetMaxPath(0, serializedString, 0, 0, n);
		return maxPathLen;
	}
	
	// deserialize tree
	private void deserializeAndGetMaxPath(int curIndex, String serializedStr, int tabCount, int pathLen, int serializedStrLen)
	{
		if(curIndex < 0 || curIndex >= serializedStrLen)
			return;
		
		String curNodeStr = "";
		int indexOfNextNode = serializedStr.indexOf("\n", curIndex); 
		if( indexOfNextNode!= -1)
			curNodeStr = serializedStr.substring(curIndex, indexOfNextNode);
		else
			curNodeStr = serializedStr.substring(curIndex);
		
		System.out.println("curNodeStr :" + curNodeStr + " tabCount :" + tabCount);
		
		pathLen += curNodeStr.length();
		maxPathLen = Math.max(maxPathLen, pathLen);
		
		// check if left child exists
		String tabString = getTabString(tabCount+1);
		if(indexOfNextNode != -1)
		{
			boolean leftChildExists = serializedStr.substring(indexOfNextNode).startsWith(tabString);
			if(leftChildExists)
			{
				
			}
		}
		
		
		int tabStringLen = tabString.length();
		int leftChildInd = serializedStr.indexOf(tabString, curIndex);
		
		if(leftChildInd != -1 && (leftChildInd+tabStringLen+1>=serializedStrLen || serializedStr.charAt(leftChildInd+tabStringLen+1)!='\\'))
		{
			deserializeAndGetMaxPath(leftChildInd+tabStringLen, serializedStr, tabCount+1, pathLen, serializedStrLen);
			int rightChildInd = serializedStr.indexOf(tabString, leftChildInd+2);
			
			if(rightChildInd != -1 && (rightChildInd + tabStringLen+1 >= serializedStrLen || serializedStr.charAt(rightChildInd+tabStringLen+1)!='\\'))
				deserializeAndGetMaxPath(rightChildInd+tabStringLen, serializedStr, tabCount+1, pathLen, serializedStrLen);
		}
	}
	
	private String getTabString(int tabCount) {
		
		String tabStr = "\n";
		
		while(tabCount-- > 0)
			tabStr+= "\t";
		
		return tabStr;
	}

	public static void main(String[] args) {

		String serializedStr = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
		System.out.println(serializedStr.indexOf("\n"));
		LongestFilePath obj = new LongestFilePath();
		System.out.println(obj.getMaxPath(serializedStr));
	}

}
