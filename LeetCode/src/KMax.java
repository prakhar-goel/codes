import java.util.*;

public class KMax {

	int getKMaxSum(List<Stack<Integer>> stacks, int k)
	{
		int n = stacks.size();
		
		int sum = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(n, new Comparator<Integer>()
				{
					@Override
					public int compare(Integer o1, Integer o2) {
						
						return o1>o2?-1:1;
					}
					
				});
		
		for(Stack<Integer> st : stacks)
		{
			pq.add(st.peek());
		}
		
		for(int i = 0; i < k; ++i)
			sum += pq.remove();
		
		return sum;
	}
	
	public static void main(String[] args) {
		
		KMax obj = new KMax();
		List<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();
		Stack<Integer> st1 = new Stack<Integer>();
		st1.push(1);
		stacks.add(st1);
		//obj.getKMaxSum(null, 4);
		
		String test = "a";
		System.out.println(test.substring(0));
	}

}
