 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class FlattenBinaryTree {
public void flatten(TreeNode root) {
        
        if(root != null)
        {
            root = flattenTree(root);
        }
    }
    
    TreeNode flattenTree(TreeNode root)
    {
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        
        TreeNode leftList = null;
        TreeNode rightList = null;
        
        if(left != null)
            leftList = flattenTree(left);
            
        if(right != null)    
            rightList = flattenTree(right);
        
        if(leftList != null)
        {
            root.right = leftList;
            
            TreeNode tempLeft = leftList;
            
            while(tempLeft.right != null)
                tempLeft = tempLeft.right;
            
            tempLeft.right = rightList;
        }
        
        return root;
    }
}
