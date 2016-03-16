package dp;

import java.util.Stack;

public class StockBuySell {
public int maxProfit(int[] prices) {
        
        int n = prices.length;
        if(n == 0 || n == 1) return 0;
        
        Stack<Integer> sellPoints = new Stack<Integer>();
        
        for(int i = n-1; i > 0; --i)
        {
            if(prices[i] > prices[i-1])
               sellPoints.push(i); 
        }
        
        if(sellPoints.isEmpty())
            return 0;
        
        int minCostPrice = prices[0];
        int maxProfit = 0;
        int curSellPoint = sellPoints.pop();
        
        for(int i = 1; i < n; ++i)
        {
            if(i < curSellPoint)
            {
                minCostPrice = Math.min(minCostPrice, prices[i]);
            }
            else if(i == curSellPoint)
            {
                maxProfit = Math.max(maxProfit, (prices[i]-minCostPrice));
                
                if(sellPoints.isEmpty())
                    break;
                    
                curSellPoint = sellPoints.pop();
            }
        }
        return maxProfit;
    }
}
