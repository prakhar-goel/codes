package ds.trees;

class TreeNode
{
	int data;
	TreeNode left;
	TreeNode right;
	TreeNode(int data)
	{
		this.data = data;
		this.left = this.right = null;
	}
	
	TreeNode(int data, TreeNode left, TreeNode right)
	{
		this.data = data;
		this.left = left;
		this.right = right;
	}
}

public class CheckBalancedTree {
	
	static boolean isBalanced = true; 
	static int isBalanced(TreeNode root)
	{
		if(isBalanced)
		{
			if(root == null)
				return 0;
		//	if(root.left == null && root.right == null)
		//		return 0;
			
			int lh = isBalanced(root.left);
			int rh = isBalanced(root.right);
			
			if(lh == -1 || rh == -1  || (lh != rh && Math.abs(lh-rh) > 1))
			{
				isBalanced = false;
				return -1;
			}
			else
				return 1+Math.max(lh, rh);
				
		}
		return -1;
	}
	public static void main(String args[])
	{
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.right = new TreeNode(3);
		System.out.println(isBalanced(root) + " " + isBalanced);
	}
}
