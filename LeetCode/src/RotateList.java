class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

public class RotateList {
	int getSize(ListNode head)
    {
        int size = 0;
        ListNode current = head;    
        while(current != null)
        {
            size++;
            current = current.next;
        }
        return size;
    }
    
    public ListNode rotateRight(ListNode head, int k) {
        int n = getSize(head);
        if(n == 0 || k == 0)
            return head;
            
        k = k%n;
        if(k == 0)
            return head;
            
        ListNode current = head;
        for(int i = 1; i < n-k; ++i)
        {
            current = current.next;
        }
        ListNode newHead = current.next;
        current.next = null;
        current = newHead;
        while(current.next != null)
        {
            current = current.next;
        }
        current.next = head;
        return newHead;
    }
    
    void printList(ListNode head)
    {
    	ListNode current = head;
    	while(current != null)
    	{
    		System.out.print(current.val +  " ");
    		current = current.next;
    	}
    }
    public static void main(String args[])
    {
    	ListNode head = new ListNode(1);
    	head.next = new ListNode(2);
    	
    	RotateList obj = new RotateList();
    	ListNode newHead = obj.rotateRight(head, 1);
    	
    	obj.printList(newHead);
    }
}
