package algorithms.sorting;

public class MergeSort {
	
	static int inversionCount = 0;
	
	static void mergeSort(int[] arr, int n)
	{
		mergeSort(arr, 0, n-1);
	}
	
	static void mergeSort(int[] arr, int l, int r)
	{
		if(l < r)
		{
			int m = (l+r)/2;
			mergeSort(arr, l, m);
			mergeSort(arr, m+1, r);
			merge(arr, l, m, r);
		}
	}
	
	static void merge(int[] arr, int l, int m, int r)
	{
		int ls = l, le = m;
		int rs = m+1, re = r;
		int k = r-l+1;
		int tmp[] = new int[k]; 
		int i = 0;
		
		while(ls <= le && rs <= re)
		{
			if(arr[ls] <= arr[rs])
			{
				tmp[i++] = arr[ls++];
			}
			else if(arr[ls] > arr[rs])
			{
				System.out.println(arr[ls] + " " + arr[rs]);
				tmp[i++] = arr[rs++];
				inversionCount++;
			}
		}
		while(ls <= le)
			tmp[i++] = arr[ls++];
		while(rs <= re)
			tmp[i++] = arr[rs++];
		
		for(int k1 = 0; k1 < k; ++k1)
			arr[l++] = tmp[k1];
	}
	
	static void displayArray(int[] arr)
	{
		for(int n : arr)
		{
			System.out.print(n + " ");
		}
	}
	
	static int getInversionsCount(int[] arr, int n)
	{
		int inv_count = 0;
		  int i, j;
		 
		  for(i = 0; i < n - 1; i++)
		    for(j = i+1; j < n; j++)
		      if(arr[i] > arr[j])
		        inv_count++;
		 
		  return inv_count;
	}
	
	public static void main(String args[])
	{
		int[] arr = {1, 2, 5, 4, 3};
		//mergeSort(arr, 5);
		//displayArray(arr);
		System.out.println(getInversionsCount(arr, 5));
		System.out.println("\ninversionCount :" + inversionCount);
	}
}
