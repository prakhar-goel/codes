package ds.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


class Node 
{
	int data;
    Node left;
    Node right;
}
    
class QItem
{
 Node node;
 int depth;
 QItem(Node node, int depth)
 {
     this.node = node;
     this.depth = depth;
 }
}

public class TopView {

	void top_view(Node root)
	{
	    Queue<QItem> q = new LinkedList<QItem>();
	    q.add(new QItem(root,0));
	    int min = 1, max = -1; 
	    LinkedList<Integer> list = null;
	    list.addFirst(3);
	    	
	    while(!q.isEmpty())
	    {
	        QItem qItem = q.remove();
	        if(qItem.depth > max || qItem.depth < min)
	        {
	            System.out.print(qItem.node.data + " ");
	            min = Math.min(min, qItem.depth);
	            max = Math.max(max, qItem.depth);
	        }
	        if(qItem.node.left != null)
	            q.add(new QItem(qItem.node.left, qItem.depth-1));
	        if(qItem.node.right != null)
	            q.add(new QItem(qItem.node.right, qItem.depth+1));
	    }
	}
	
}
