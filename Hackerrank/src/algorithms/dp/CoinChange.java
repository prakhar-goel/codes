package algorithms.dp;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {
	static int numberOfWaysForDenominations(int[] C, int N, int M)
    {
        int[][] matrix = new int[N+1][M+1];
        for(int i = 0; i <= N; ++i)
            matrix[i][0] = 0;
        
        for(int i = 0; i <= M; ++i)
            matrix[0][i] = 0;
        
        for(int i = 1; i <= M; ++i)
        {
            for(int j = 1; j <= N; ++j)
            {
                int count = matrix[j][i-1];
                if(j == C[i-1])
                {
                    count++;    
                }
                else if(C[i-1] < j)
                {
                    if(j == 2*C[i-1])
                        count += matrix[C[i-1]][i];
                    else
                        count += matrix[j-C[i-1]][i];
                }
                matrix[j][i] = count;
                System.out.println(j + " " + i + " " + count);
            }
        }
        return matrix[N][M];
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int N = sc.nextInt();
        int M = sc.nextInt();
        int C[] = new int[M];
        for(int i = 0; i < M; ++i)
        {
            C[i] = sc.nextInt();    
        }
        sc.close();
        
        Arrays.sort(C);
        System.out.println(numberOfWaysForDenominations(C, N, M));
    }
}
