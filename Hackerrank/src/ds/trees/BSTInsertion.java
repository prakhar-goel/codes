package ds.trees;

public class BSTInsertion {
	
	static Node Insert(Node root,int value)
	    {
	        Node node = new Node();
	        node.data = value;
	    
	        if(root.data >= value)
	        {
	            if(root.left == null)
	                root.left = node;
	            else
	                return Insert(root.left, value);
	        }
	        else
	        {
	            if(root.right == null)
	                root.right = node;
	            else
	                return Insert(root.right, value);
	        }
	       return root;
	    }
	
	void printTree(Node root)
	{
		
	}
	public static void main(String args[])
	{
		//int[] arr = {4 2 3 1 7};
		
		Node root = new Node();
		root.data = 5;
		Node node = Insert(root, 4);
		System.out.println(node.data + " " + node.left + " " + node.right);
	}
}
