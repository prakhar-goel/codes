package dp;

import java.util.Stack;

public class MultipleStockBuySell {
	
	public int maxProfit(int[] prices) {
        
        int n = prices.length;
        if(n == 0 || n == 1) return 0;
        
        Stack<Integer> sellPoints = new Stack<Integer>();
        for(int i = n-1; i > 0; --i)
        {
            if(prices[i] > prices[i-1])
               sellPoints.push(i); 
        }
        
        if(sellPoints.isEmpty()) // important check
            return 0;
        
        int minBuy = prices[0];
        int totalProfit = 0;
        int curSellPoint = sellPoints.pop();
        
        for(int i = 0; i < n; ++i)
        {
            if(i < curSellPoint)
            {
                minBuy = Math.min(minBuy, prices[i]);
            }
            else if(i == curSellPoint)
            {
                int profit = prices[i]-minBuy;
                while(!sellPoints.isEmpty() && sellPoints.peek()==i+1)
                {
                    curSellPoint = sellPoints.pop();i++;
                    profit = prices[curSellPoint] - minBuy;
                }
                totalProfit += profit;
                
                if(sellPoints.isEmpty())
                    break;
                
                curSellPoint = sellPoints.pop(); // important to set next sell point
                minBuy = Integer.MAX_VALUE; // important to put to rest minBuy value
            }
        }
        
        return totalProfit;
    }
}
