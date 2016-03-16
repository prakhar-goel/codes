package algorithms.sorting;
import java.util.Scanner;

public class RotateArray {
	static void rotateArray(int[] arr, int k)
    {
        int n = arr.length;
        k = k %n;
        
        int iterator = 0;
        int i = k;
        int prev = arr[0];
        for(;iterator < n; ++iterator, i=(i+k)%n)
        {
        	int temp = arr[i];
        	arr[i] = prev;
        	prev = temp;
        }
        //arr[i] = prev;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        
       // String[] toks = (sc.nextLine()).split(" ");
        for(int i = 0; i < n; ++i)
        {
            arr[i] = sc.nextInt();//Integer.parseInt(toks[i]);    
        }
        rotateArray(arr, k);
        
        for(int i = 0;i < q; ++i)
        {
            int ind = sc.nextInt();
            System.out.println(arr[ind]);
        }
        sc.close();
    }
}
